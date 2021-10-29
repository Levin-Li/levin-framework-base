package com.levin.oak.base.aspect;

import static  com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.*;

import com.levin.commons.service.domain.Desc;
import com.levin.commons.service.support.*;
import com.levin.commons.utils.IPAddrUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Aspect
@Component(PLUGIN_PREFIX + "ModuleWebControllerAspect")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ModuleWebControllerAspect", havingValue = "false", matchIfMissing = true)
public class ModuleWebControllerAspect {

    @Autowired
    VariableInjector variableInjector;

    @Autowired
    VariableResolverManager variableResolverManager;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Value("${" + PLUGIN_PREFIX + "logHttp:true}")
    boolean enableLog;

    final AtomicBoolean enableHttpLog = new AtomicBoolean(false);


    @PostConstruct
    void init() {
        enableHttpLog.set(enableLog);
    }

    final VariableResolver httpRequestInfoResolver = new VariableResolver() {
        @Override
        public <T> ValueHolder<T> resolve(String name, T defaultValue, boolean throwEx, Class<?>... types) throws VariableNotFoundException {

            String value = null;

//            request.getRequestURL() 返回全路径
//            request.getRequestURI() 返回除去host（域名或者ip）部分的路径
//            request.getContextPath() 返回工程名部分，如果工程映射为/，此处返回则为空
//            request.getServletPath() 返回除去host和工程名部分的路径

//            request.getRequestURL() http://localhost:8080/jqueryLearn/resources/request.jsp
//            request.getRequestURI() /jqueryLearn/resources/request.jsp
//            request.getContextPath()/jqueryLearn
//            request.getServletPath()/resources/request.jsp

            if (InjectConsts.IP_ADDR.equalsIgnoreCase(name)) {

                value = IPAddrUtils.try2GetUserRealIPAddr(request);

            } else if (InjectConsts.URL_SERVERNAME.equalsIgnoreCase(name)) {

                value = request.getServerName();

            } else if (InjectConsts.USER_AGENT.equalsIgnoreCase(name)) {

                value = request.getHeader("user-agent");

            } else if (InjectConsts.URL.equalsIgnoreCase(name)) {

                value = request.getRequestURL().toString();

            } else if (InjectConsts.URL_SCHEME.equalsIgnoreCase(name)) {

                value = request.getScheme();

            } else if ("moduleId".equalsIgnoreCase(name)) {

                value = ModuleOption.ID;

            } else {
                return ValueHolder.notValue();
            }

            return new ValueHolder()
                    .setValue(value)
                    .setHasValue(true);
        }
    };

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

        log.debug("开始为方法 {} 注入变量...", joinPoint.getSignature());

        String headerValue = request.getHeader(PLUGIN_PREFIX + "logHttp");
        if (StringUtils.hasText(headerValue)) {
            enableHttpLog.set(Boolean.TRUE.toString().equalsIgnoreCase(headerValue));
        }

        //加入线程级别的http请求解析器，线程级别解析器会被优先使用
        //httpRequestInfoResolver;

        Optional.ofNullable(joinPoint.getArgs()).ifPresent(args -> {
            Arrays.stream(args)
                    .filter(Objects::nonNull)
                    .forEachOrdered(arg -> {
                        variableInjector.injectByVariableResolver(arg
                                , () -> Arrays.asList(httpRequestInfoResolver)
                                , () -> variableResolverManager.getVariableResolvers());
                    });
        });

    }


    /**
     * 记录日志
     */
    @Around("modulePackagePointcut() && controllerPointcut() && requestMappingPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        if (!enableHttpLog.get()) {
            return joinPoint.proceed(joinPoint.getArgs());
        }

        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();

        LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();

        String requestName = getRequestInfo(joinPoint, headerMap, paramMap);

        log.debug("*** " + requestName + " *** URL: {}?{}, headers:{}, 控制器方法参数：{}"
                , request.getRequestURL(), request.getQueryString()
                , headerMap, paramMap);

        long st = System.currentTimeMillis();

        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = joinPoint.proceed(joinPoint.getArgs());

        log.debug("*** " + requestName + " *** URL: {}?{}, 执行耗时：{}ms , 响应结果:{}", request.getRequestURL(), request.getQueryString(),
                (System.currentTimeMillis() - st), result);

        //如果这里不返回result，则目标对象实际返回值会被置为null

        return result;

    }


    public String getRequestInfo(ProceedingJoinPoint joinPoint, Map<String, String> headerMap, Map<String, Object> paramMap) throws Throwable {

        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();

        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypes = methodSignature.getParameterTypes();
        String[] paramNames = methodSignature.getParameterNames();

        Method method = methodSignature.getMethod();

        String requestName = request.getRequestURI();

        if (method.isAnnotationPresent(Operation.class)) {
            requestName += " " + method.getAnnotation(Operation.class).summary();
        } else if (method.isAnnotationPresent(Schema.class)) {
            requestName += " " + method.getAnnotation(Schema.class).description();
        } else if (method.isAnnotationPresent(Desc.class)) {
            requestName += " " + method.getAnnotation(Desc.class).value();
        }

        if (paramMap != null) {

            if (paramTypes != null && paramTypes.length > 0) {

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