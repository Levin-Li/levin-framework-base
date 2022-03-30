package com.levin.oak.base.aspect;

import com.google.gson.Gson;
import com.levin.commons.service.domain.Desc;
import com.levin.commons.service.support.*;
import com.levin.commons.utils.AsyncHandler;
import com.levin.commons.utils.ExceptionUtils;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.controller.accesslog.AccessLogController;
import com.levin.oak.base.services.accesslog.AccessLogService;
import com.levin.oak.base.services.accesslog.req.CreateAccessLogReq;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.levin.oak.base.ModuleOption.HTTP_REQUEST_INFO_RESOLVER;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Aspect
@Slf4j
@Component(PLUGIN_PREFIX + "ModuleWebControllerAspect")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleWebControllerAspect", matchIfMissing = true)
public class ModuleWebControllerAspect {

    @Resource
    ApplicationContext context;

    @Resource
    VariableInjector variableInjector;

    @Resource
    VariableResolverManager variableResolverManager;

    @Resource
    HttpServletRequest request;

    @Resource
    HttpServletResponse response;

    @Resource(name = HTTP_REQUEST_INFO_RESOLVER)
    HttpRequestInfoResolver httpRequestInfoResolver;

    @Resource
    ScheduledExecutorService scheduledExecutorService;

    @Resource
    AccessLogService accessLogService;

    @Resource
    AuthService authService;

    @Resource
    BizTenantService bizTenantService;

    final AsyncHandler<CreateAccessLogReq> asyncHandler = new AsyncHandler<>();

    static final Gson gson = new Gson();

    @Value("${" + PLUGIN_PREFIX + "logHttp:true}")
    boolean enableLog;

    @Value("${" + PLUGIN_PREFIX + "log.disablePackages:}")
    List<String> disablePackages;

    final AtomicBoolean enableHttpLog = new AtomicBoolean(false);


    /**
     * 存储本模块的变量解析器
     */
    private List<VariableResolver> moduleResolverList = new ArrayList<>(7);

    @PostConstruct
    void init() {

        this.enableHttpLog.set(enableLog);

        //设置处理器
        this.asyncHandler
                .setName("异步日志存储")
                .setDefaultScheduler(scheduledExecutorService)
                .setTaskDelay(1500)
                .setSyncTaskExecutor(req ->
                        scheduledExecutorService.submit(() -> accessLogService.create(req))
                );

        //增加 HttpRequestInfoResolver
        moduleResolverList.add(httpRequestInfoResolver);

        //只找出本模块的解析器
        List<List<VariableResolver>> resolvers = SpringContextHolder.findBeanByBeanName(context, ResolvableType.forClassWithGenerics(Iterable.class, VariableResolver.class).getType(), PLUGIN_PREFIX);

        resolvers.forEach(moduleResolverList::addAll);

        log.info("init...");
    }

    /**
     * 模块包
     */
    @Pointcut("execution(* com.levin.oak.base..*.*(..))")
    public void modulePackagePointcut() {
    }

    /**
     * 控制器
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)" +
            " || @within(org.springframework.stereotype.Controller)")
    public void controllerPointcut() {
    }

    /**
     * 请求映射方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)"
    )
    public void requestMappingPointcut() {
    }


    /**
     * 变量注入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("modulePackagePointcut() && controllerPointcut() && requestMappingPointcut()")
    public void injectVar(JoinPoint joinPoint) {

        if (log.isDebugEnabled()) {
            log.debug("开始为方法 {} 注入变量...", joinPoint.getSignature());
        }

        String headerValue = request.getHeader(PLUGIN_PREFIX + "logHttp");
        if (StringUtils.hasText(headerValue)) {
            enableHttpLog.set(Boolean.TRUE.toString().equalsIgnoreCase(headerValue));
        }

        Optional.ofNullable(joinPoint.getArgs()).ifPresent(args -> {
            Arrays.stream(args)
                    .filter(Objects::nonNull)
                    .forEachOrdered(arg -> {
                        variableInjector.injectByVariableResolvers(arg
                                , () -> moduleResolverList
                                , () -> variableResolverManager.getVariableResolvers());
                    });
        });

    }


    /**
     * 记录日志
     * <p>
     * 记录所有日志
     */
    @Around("controllerPointcut() && requestMappingPointcut()")
//    @Around("modulePackagePointcut() && controllerPointcut() && requestMappingPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        if (!enableHttpLog.get() || !log.isDebugEnabled()) {
            return joinPoint.proceed(joinPoint.getArgs());
        }

        String className = joinPoint.getSignature().getDeclaringTypeName();

        if (disablePackages != null) {
            if (disablePackages.stream().anyMatch(pkg -> className.startsWith(pkg))) {
                return joinPoint.proceed(joinPoint.getArgs());
            }
        }

        boolean isAccessLogController = AccessLogController.class.getName().contentEquals(className);

        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();

        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();

        String requestName = getRequestInfo(joinPoint, headerMap, paramMap, true);

        if (log.isDebugEnabled()) {
            log.debug("*** " + requestName + " *** URL: {}?{}, headers:{}, 控制器方法参数：{}"
                    , request.getRequestURL(), request.getQueryString()
                    , headerMap, paramMap);
        }

        long st = System.currentTimeMillis();

        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)

        Object result = null;

        Throwable ex = null;

        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            ex = e;
        } finally {

            long execTime = System.currentTimeMillis() - st;

            if (log.isDebugEnabled()) {
                log.debug("*** " + requestName + " *** URL: {}?{}, 执行耗时：{}ms, 发生异常：{} , 响应结果:{}"
                        , request.getRequestURL(), request.getQueryString(),
                        execTime, ex != null, isAccessLogController ? "忽略结果" : result);
            }

            TenantInfo tenantInfo = bizTenantService.getCurrentTenant();

            CreateAccessLogReq req = new CreateAccessLogReq()
                    .setCreateTime(new Date())
                    .setTitle(requestName)
                    .setVisitor(authService.isLogin() ? (authService.getUserInfo().getName() + "(" + authService.getUserInfo().getId() + ")") : null)
                    .setDomain(request.getServerName())
                    .setRemoteAddr(IPAddrUtils.try2GetUserRealIPAddr(request))
                    .setServerAddr(request.getLocalAddr())
                    .setRequestMethod(request.getMethod())
                    .setRequestUri(request.getRequestURI() + "?" + request.getQueryString())
                    .setRequestParams(gson.toJson(paramMap))
                    .setHeadInfo(gson.toJson(headerMap))
                    .setExecuteTime(execTime)
                    .setIsException(ex != null)
                    .setExceptionInfo(ex != null ? ExceptionUtils.getPrintInfo(ex) : null)
                    .setUserAgent(headerMap.get("user-agent"))
                    .setTenantId(tenantInfo != null ? tenantInfo.getId() : null);

            if (isAccessLogController) {
                req.setResponseData("忽略对于访问日志查询的结果记录");
            } else {
                req.setResponseData(result != null ? gson.toJson(result) : null);
            }

            asyncHandler.addTask(req);
        }

        //如果这里不返回result，则目标对象实际返回值会被置为null
        if (ex != null) {
            throw ex;
        }

        return result;
    }

    /**
     * 获取请求信息
     *
     * @param joinPoint
     * @param headerMap
     * @param paramMap
     * @return
     * @throws Throwable
     */
    public String getRequestInfo(ProceedingJoinPoint joinPoint, Map<String, String> headerMap, Map<String, Object> paramMap, boolean isOnlyHttpParams) throws Throwable {

        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();

        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypes = methodSignature.getParameterTypes();
        String[] paramNames = methodSignature.getParameterNames();

        Method method = methodSignature.getMethod();

        String requestName = "";

        if (method.isAnnotationPresent(Operation.class)) {

            Operation operation = method.getAnnotation(Operation.class);

            String[] tags = operation.tags();

            if (tags != null && tags.length > 0) {
                requestName = tags[0];
            }

            requestName += "|" + operation.summary();

        } else if (method.isAnnotationPresent(Schema.class)) {
            requestName = method.getAnnotation(Schema.class).description();
        } else if (method.isAnnotationPresent(Desc.class)) {
            requestName = method.getAnnotation(Desc.class).value();
        } else {
            requestName = request.getRequestURI();
        }

        //获取请求参数
        if (paramMap != null) {

            if (isOnlyHttpParams) {

                request.getParameterMap().forEach((name, value) -> {
                    paramMap.put(name, value.length > 1 ? Arrays.asList(value) : (value.length > 0 ? value[0] : null));
                });

            } else if (paramTypes != null && paramTypes.length > 0) {

                if (paramNames == null || paramNames.length != paramTypes.length) {
                    paramNames = new String[paramTypes.length];
                }

                for (int i = 0; i < paramTypes.length; i++) {
                    //  Class paramType = paramTypes[i];

                    String paramName = paramNames[i];

//                    paramName = paramName != null ? paramName : "";
//                    paramName = paramName + "(" + paramType.getSimpleName() + ")";

                    if (StringUtils.hasText(paramName)) {
                        paramMap.put(paramName, args[i]);
                    }
                }
            }


        }

        if (headerMap != null) {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                headerMap.put(key, request.getHeader(key));
            }
        }

        return requestName;
    }

}