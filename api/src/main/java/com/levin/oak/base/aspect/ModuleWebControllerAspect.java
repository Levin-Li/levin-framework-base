package com.levin.oak.base.aspect;

import com.google.gson.Gson;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.service.domain.Desc;
import com.levin.commons.service.support.*;
import com.levin.commons.utils.ExceptionUtils;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.InjectVarService;
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
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * 支持全局的模块拦截
 * <p>
 * 控制器请求对象的变量注入和日志记录
 */
@Aspect
@Slf4j
@Component(PLUGIN_PREFIX + "ModuleWebControllerAspect")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleWebControllerAspect", matchIfMissing = true)
public class ModuleWebControllerAspect {

    @Autowired
    GenericApplicationContext context;

    @Autowired
    VariableInjector variableInjector;

    @Autowired
    VariableResolverManager variableResolverManager;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    ScheduledExecutorService scheduledExecutorService;

    @Autowired
    AccessLogService accessLogService;

    @Autowired
    AuthService authService;

    @Autowired
    InjectVarService injectVarService;

    @Autowired
    BizTenantService bizTenantService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    PluginManager pluginManager;


    final AsyncHandler<CreateAccessLogReq> asyncHandler = new AsyncHandler<>();

    //全局，线程安全变量
    private static final Gson gson = new Gson();

    /**
     * 存储模块的变量解析器
     */
    private MultiValueMap<String, VariableResolver> moduleResolverMap = new LinkedMultiValueMap<>();

    @PostConstruct
    void init() {
        //
        this.frameworkProperties.getLog().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));

        //设置处理器
        this.asyncHandler
                .setName("异步日志存储")
                .setDefaultScheduler(scheduledExecutorService)
                .setTaskDelay(1500)
                .setSyncTaskExecutor(req ->
                        scheduledExecutorService.submit(() -> {
                            accessLogService.create(req);
                        })
                );

        log.info("控制器拦截器init...");
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
     * 获取JoinPoint所在模块的变量解析器
     *
     * @param joinPoint
     * @return
     */
    private List<VariableResolver> getModuleResolverList(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();

        final String className = signature.getDeclaringTypeName();

        //获取当前插件
        Plugin plugin = pluginManager.getInstalledPlugins()
                .stream()
                .filter(plugin1 -> className.startsWith(plugin1.getPackageName() + "."))
                .findFirst()
                .orElse(null);

        if (plugin == null) {
            return Collections.emptyList();
        }

        final String packageName = plugin.getPackageName();

        //如果当前模块不存在解析器
        if (!moduleResolverMap.containsKey(packageName)) {

            //放入一个空，防止解析变量出现错误
            moduleResolverMap.addAll(packageName, Collections.emptyList());

//            Supplier<List<Map<String, ?>>>

            //按bean名查找 List<VariableResolver> bean
            SpringContextHolder.<List<VariableResolver>>findBeanByBeanName(context
                    , ResolvableType.forClassWithGenerics(Iterable.class, VariableResolver.class).getType()
                    , "plugin." + packageName, packageName)
                    .forEach(list -> moduleResolverMap.addAll(packageName, list));

            //按bean名查找 VariableResolver bean
            SpringContextHolder.<VariableResolver>findBeanByBeanName(context
                    , ResolvableType.forClass(VariableResolver.class).getType()
                    , "plugin." + packageName, packageName)
                    .forEach(v -> moduleResolverMap.add(packageName, v));

            //按包名查找
//            SpringContextHolder.<VariableResolver>findBeanByPkgName(context
//                    , ResolvableType.forClass(VariableResolver.class).getType()
//                    , packageName)
//                    .forEach(v -> moduleResolverMap.add(packageName, v));
        }

        return moduleResolverMap.getOrDefault(packageName, Collections.emptyList());

    }

    /**
     * 变量注入
     *
     * @param joinPoint
     * @throws Throwable
     */
//    @Before("modulePackagePointcut() && controllerPointcut() && requestMappingPointcut()")
    @Before("controllerPointcut() && requestMappingPointcut()")
    public void injectVar(JoinPoint joinPoint) {

        final String path = getRequestPath();

        Signature signature = joinPoint.getSignature();

        final String className = signature.getDeclaringTypeName();

        if (className.startsWith("springfox.")
                || className.startsWith("org.springdoc.")) {
            return;
        }

        //去除应用路径后，进行匹配
        if (path.equals(serverProperties.getError().getPath())
                || !frameworkProperties.getInject().isMatched(className, path)) {
            return;
        }

        if (log.isDebugEnabled()) {
            log.debug("开始为方法 {} 注入变量...", signature);
        }

        final List<VariableResolver> variableResolverList = new ArrayList<>();

        final Map<String, ?> injectVars = injectVarService.getInjectVars();

        variableResolverList.addAll(getModuleResolverList(joinPoint));
        variableResolverList.addAll(variableResolverManager.getVariableResolvers());

        Optional.ofNullable(joinPoint.getArgs()).ifPresent(args ->

                //对方法参数进行迭代
                Arrays.stream(args).filter(Objects::nonNull).forEachOrdered(arg -> {

                    //如果参数本身是一个集合，支持第一级是集合对象的参数
                    Collection<?> params = (arg instanceof Collection) ? ((Collection<?>) arg) : Arrays.asList(arg);

                    params.stream()
                            .filter(Objects::nonNull)
                            .forEachOrdered(param -> {

                                ArrayList<VariableResolver> tempList = new ArrayList<>(variableResolverList.size() + 1);

                                tempList.add(VariableInjector.newResolverByMap(MapUtils.put("_this", param).build(), injectVars));

                                tempList.addAll(variableResolverList);

                                variableInjector.injectByVariableResolvers(param, tempList);
                            });

                })
        );
    }


    /**
     * 记录日志
     * <p>
     * 记录所有日志
     */
    @Around("controllerPointcut() && requestMappingPointcut()")
//    @Around("modulePackagePointcut() && controllerPointcut() && requestMappingPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        final String path = getRequestPath();

        final String className = joinPoint.getSignature().getDeclaringTypeName();

        //去除应用路径后，进行匹配
        if (path.equals(serverProperties.getError().getPath())
                || !frameworkProperties.getLog().isMatched(className, path)) {
            return joinPoint.proceed(joinPoint.getArgs());
        }

        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();

        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();

        final String title = getRequestInfo(joinPoint, headerMap, paramMap, true);

        if (log.isDebugEnabled()) {
            log.debug("*** " + title + " *** URL: {}?{}, headers:{}, 控制器方法参数：{}"
                    , request.getRequestURL(), request.getQueryString()
                    , headerMap, paramMap);
        }

        //////////////////////////////////////////////////////////////////////////////////////////

        final boolean isAccessLogController = AccessLogController.class.getName().contentEquals(className);

        final long st = System.currentTimeMillis();

        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)

        Object result = null;

        Throwable ex = null;

        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            ex = e;
        } finally {

            final long execTime = System.currentTimeMillis() - st;

            if (log.isDebugEnabled()) {
                log.debug("*** " + title + " *** URL: {}?{}, 执行耗时：{}ms, 发生异常：{} , 响应结果:{}"
                        , request.getRequestURL(), request.getQueryString(),
                        execTime, ex != null, isAccessLogController ? "忽略对于访问日志控制器的访问结果" : result);
            }

            TenantInfo tenantInfo = bizTenantService.getCurrentTenant();

            String tenantInfoId = null;

            if (tenantInfo != null) {
                tenantInfoId = tenantInfo.getId();
            }

            String visitor = null;

            if (authService.isLogin()) {
                tenantInfoId = authService.getUserInfo().getTenantId();
                visitor = authService.getUserInfo().getName() + "(" + authService.getLoginId() + ")";
            }

            CreateAccessLogReq req = new CreateAccessLogReq()
                    .setCreateTime(new Date())
                    .setTitle(title)
                    .setVisitor(visitor)
                    .setDomain(request.getServerName())
                    .setRemoteAddr(IPAddrUtils.try2GetUserRealIPAddr(request, false))
                    .setServerAddr(request.getLocalAddr())
                    .setRequestMethod(request.getMethod())
                    .setRequestUri(request.getRequestURI() + "?" + request.getQueryString())
                    .setRequestParams(gson.toJson(paramMap))
                    .setHeadInfo(gson.toJson(headerMap))
                    .setExecuteTime(execTime)
                    .setIsException(ex != null)
                    .setExceptionInfo(ex != null ? ExceptionUtils.getPrintInfo(ex) : null)
                    .setUserAgent(headerMap.get("user-agent"))
                    .setTenantId(tenantInfoId);

            if (isAccessLogController) {
                req.setResponseData("忽略对于访问日志控制器的访问结果");
            } else if (result instanceof HttpEntity) {
                MediaType contentType = ((HttpEntity) result).getHeaders().getContentType();
                if (contentType != null
                        && contentType.isCompatibleWith(APPLICATION_JSON)) {
                    //只记录JSON
                    req.setResponseData(gson.toJson(result));
                } else {
                    req.setResponseData("忽略类型-" + contentType);
                }
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

    private String getRequestPath() {

        String contextPath = serverProperties.getServlet().getContextPath() + "/";

        contextPath = contextPath.replace("//", "/");

        String path = request.getRequestURI().replace("//", "/");

        //去除应用路径
        if (path.startsWith(contextPath)) {
            path = path.substring(contextPath.length() - 1);
        }

        return path;

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
