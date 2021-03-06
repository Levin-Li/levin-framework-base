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
 * ???????????????????????????
 * <p>
 * ???????????????????????????????????????????????????
 */
@Aspect
@Slf4j
@Component(PLUGIN_PREFIX + "ModuleWebControllerAspect")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleWebControllerAspect", matchIfMissing = true)
public class ModuleWebControllerAspect {

    @Resource
    GenericApplicationContext context;

    @Resource
    VariableInjector variableInjector;

    @Resource
    VariableResolverManager variableResolverManager;

    @Resource
    HttpServletRequest request;

    @Resource
    HttpServletResponse response;

    @Resource
    ScheduledExecutorService scheduledExecutorService;

    @Resource
    AccessLogService accessLogService;

    @Resource
    AuthService authService;

    @Resource
    InjectVarService injectVarService;

    @Resource
    BizTenantService bizTenantService;

    @Resource
    FrameworkProperties frameworkProperties;

    @Resource
    ServerProperties serverProperties;

    @Resource
    PluginManager pluginManager;


    final AsyncHandler<CreateAccessLogReq> asyncHandler = new AsyncHandler<>();

    //???????????????????????????
    private static final Gson gson = new Gson();

    /**
     * ??????????????????????????????
     */
    private MultiValueMap<String, VariableResolver> moduleResolverMap = new LinkedMultiValueMap<>();

    @PostConstruct
    void init() {
        //
        this.frameworkProperties.getLog().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));

        //???????????????
        this.asyncHandler
                .setName("??????????????????")
                .setDefaultScheduler(scheduledExecutorService)
                .setTaskDelay(1500)
                .setSyncTaskExecutor(req ->
                        scheduledExecutorService.submit(() -> {

                            accessLogService.create(req);

                        })
                );

        log.info("init...");
    }


    /**
     * ?????????
     */
    @Pointcut("execution(* com.levin.oak.base..*.*(..))")
    public void modulePackagePointcut() {
    }

    /**
     * ?????????
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)" +
            " || @within(org.springframework.stereotype.Controller)")
    public void controllerPointcut() {
    }

    /**
     * ??????????????????
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
     * ??????JoinPoint??????????????????????????????
     *
     * @param joinPoint
     * @return
     */
    private List<VariableResolver> getModuleResolverList(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();

        final String className = signature.getDeclaringTypeName();

        //??????????????????
        Plugin plugin = pluginManager.getInstalledPlugins()
                .stream()
                .filter(plugin1 -> className.startsWith(plugin1.getPackageName() + "."))
                .findFirst()
                .orElse(null);

        if (plugin == null) {
            return Collections.emptyList();
        }

        final String packageName = plugin.getPackageName();

        if (!moduleResolverMap.containsKey(packageName)) {

            //???????????????
            moduleResolverMap.addAll(packageName, Collections.emptyList());

//            Supplier<List<Map<String, ?>>>

            //???bean????????? List<VariableResolver> bean
            SpringContextHolder.<List<VariableResolver>>findBeanByBeanName(context
                    , ResolvableType.forClassWithGenerics(Iterable.class, VariableResolver.class).getType()
                    , "plugin." + packageName, packageName)
                    .forEach(list -> moduleResolverMap.addAll(packageName, list));

            //???bean????????? VariableResolver bean
            SpringContextHolder.<VariableResolver>findBeanByBeanName(context
                    , ResolvableType.forClass(VariableResolver.class).getType()
                    , "plugin." + packageName, packageName)
                    .forEach(v -> moduleResolverMap.add(packageName, v));

            //???????????????
//            SpringContextHolder.<VariableResolver>findBeanByPkgName(context
//                    , ResolvableType.forClass(VariableResolver.class).getType()
//                    , packageName)
//                    .forEach(v -> moduleResolverMap.add(packageName, v));
        }

        return moduleResolverMap.getOrDefault(packageName, Collections.emptyList());

    }

    /**
     * ????????????
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

        if (className.startsWith("springfox.")) {
            return;
        }

        //????????????????????????????????????
        if (path.equals(serverProperties.getError().getPath())
                || !frameworkProperties.getInject().isMatched(className, path)) {
            return;
        }

        if (log.isDebugEnabled()) {
            log.debug("??????????????? {} ????????????...", signature);
        }

        final List<VariableResolver> variableResolverList = new ArrayList<>();

        final Map<String, ?> injectVars = injectVarService.getInjectVars();

        variableResolverList.addAll(getModuleResolverList(joinPoint));
        variableResolverList.addAll(variableResolverManager.getVariableResolvers());

        Optional.ofNullable(joinPoint.getArgs()).ifPresent(args ->

                //???????????????????????????
                Arrays.stream(args).filter(Objects::nonNull).forEachOrdered(arg -> {

                    //???????????????????????????????????????????????????????????????????????????
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
     * ????????????
     * <p>
     * ??????????????????
     */
    @Around("controllerPointcut() && requestMappingPointcut()")
//    @Around("modulePackagePointcut() && controllerPointcut() && requestMappingPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        final String path = getRequestPath();

        final String className = joinPoint.getSignature().getDeclaringTypeName();

        //????????????????????????????????????
        if (path.equals(serverProperties.getError().getPath())
                || !frameworkProperties.getLog().isMatched(className, path)) {
            return joinPoint.proceed(joinPoint.getArgs());
        }

        //?????????????????????
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();

        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();

        final String title = getRequestInfo(joinPoint, headerMap, paramMap, true);

        if (log.isDebugEnabled()) {
            log.debug("*** " + title + " *** URL: {}?{}, headers:{}, ????????????????????????{}"
                    , request.getRequestURL(), request.getQueryString()
                    , headerMap, paramMap);
        }

        //////////////////////////////////////////////////////////////////////////////////////////

        final boolean isAccessLogController = AccessLogController.class.getName().contentEquals(className);

        final long st = System.currentTimeMillis();

        //?????????????????????
        //?????????????????????joinPoint.proceed()?????????????????????????????????????????????????????????joinPoint.proceed(Object[] args)

        Object result = null;

        Throwable ex = null;

        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            ex = e;
        } finally {

            final long execTime = System.currentTimeMillis() - st;

            if (log.isDebugEnabled()) {
                log.debug("*** " + title + " *** URL: {}?{}, ???????????????{}ms, ???????????????{} , ????????????:{}"
                        , request.getRequestURL(), request.getQueryString(),
                        execTime, ex != null, isAccessLogController ? "????????????????????????????????????????????????" : result);
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
                req.setResponseData("????????????????????????????????????????????????");
            } else if (result instanceof HttpEntity) {
                MediaType contentType = ((HttpEntity) result).getHeaders().getContentType();
                if (contentType != null
                        && contentType.isCompatibleWith(APPLICATION_JSON)) {
                    //?????????JSON
                    req.setResponseData(gson.toJson(result));
                } else {
                    req.setResponseData("????????????-" + contentType);
                }
            } else {
                req.setResponseData(result != null ? gson.toJson(result) : null);
            }

            asyncHandler.addTask(req);
        }

        //?????????????????????result?????????????????????????????????????????????null
        if (ex != null) {
            throw ex;
        }

        return result;
    }

    private String getRequestPath() {

        String contextPath = serverProperties.getServlet().getContextPath() + "/";

        contextPath = contextPath.replace("//", "/");

        String path = request.getRequestURI().replace("//", "/");

        //??????????????????
        if (path.startsWith(contextPath)) {
            path = path.substring(contextPath.length() - 1);
        }

        return path;

    }

    /**
     * ??????????????????
     *
     * @param joinPoint
     * @param headerMap
     * @param paramMap
     * @return
     * @throws Throwable
     */
    public String getRequestInfo(ProceedingJoinPoint joinPoint, Map<String, String> headerMap, Map<String, Object> paramMap, boolean isOnlyHttpParams) throws Throwable {

        //???????????????????????????
        Object[] args = joinPoint.getArgs();

        //?????????????????????
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //??????????????????????????????
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

        //??????????????????
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