package com.levin.oak.base.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.util.ClassUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.levin.commons.dao.exception.DaoUniqueConstraintBizException;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.service.exception.AccessDeniedException;
import com.levin.commons.service.exception.BizException;
import com.levin.commons.service.exception.ServiceException;
import com.levin.commons.service.exception.UnauthorizedException;
import com.levin.commons.utils.ExceptionUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.net.SocketException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.commons.service.domain.ServiceResp.ErrorType.UnknownError;
import static com.levin.commons.service.domain.ServiceResp.ErrorType.*;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

/**
 * 在Spring 3.2中
 * 新增了@ControllerAdvice、@RestControllerAdvice 注解，
 * 可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping、@PostMapping， @GetMapping注解中。
 */
@Slf4j
@Component(PLUGIN_PREFIX + "ModuleWebControllerAdvice")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleWebControllerAdvice", matchIfMissing = true)
//@RestControllerAdvice(PACKAGE_NAME)
//过滤所有的控制器
@RestControllerAdvice(annotations = {Controller.class, RestController.class})
public class ModuleWebControllerAdvice {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    Environment env;

    boolean isDev = false;

    /**
     * 项目启动时，初始化日志
     */
    @PostConstruct
    void init() {
        isDev = Arrays.stream(env.getActiveProfiles()).anyMatch(profile -> profile.equals("dev") || profile.equals("test") || profile.equals("local"));
        log.info("init...");
    }

    /**
     * // @InitBinder标注的initBinder()方法表示注册一个Date类型的类型转换器，用于将类似这样的2019-06-10
     * // 日期格式的字符串转换成Date对象
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"),false));
    }


    /**
     * 获取异常的message
     *
     * @param exception
     * @return
     */
    String getExMsg(Throwable exception) {

        if (exception == null) {
            return null;
        }

        String failover = exception.getClass().getSimpleName();

        //循环获取异常的message,返回第一个有message的异常
        while (exception != null) {
            if (exception.getMessage() != null) {

                if (exception instanceof BindException) {

                    BindException ex = (BindException) exception;

                    BindingResult br = ex.getBindingResult();

                    Object target = br.getTarget();

                    FieldError fieldError = br.getFieldError();

                    if (fieldError != null && target != null) {

                        String errorMessage = fieldError.getField();

                        Field field = ReflectionUtils.findField(target.getClass(), fieldError.getField());

                        if (field != null) {
                            Schema schema = field.getAnnotation(Schema.class);
                            if (schema != null) {
                                errorMessage = Stream.of(schema.title(), schema.description())
                                        .filter(StringUtils::hasText).findFirst().orElse(fieldError.getField());
                            }
                        }

                        return errorMessage + "-" + fieldError.getDefaultMessage();

                    }


                } else if (exception instanceof HttpMessageConversionException) {
                    return "数据转换异常";
                }

                return exception.getMessage();
            }
            //防止循环引用
            if (exception.getCause() == exception) {
                break;
            }
            exception = exception.getCause();
        }

        return failover;
    }

    /**
     * 获取异常的详细信息
     *
     * @param exception
     * @return
     */
    String getExDetailMsg(Throwable exception) {

        if (exception == null) {
            return null;
        }

        //开发模式下，返回异常的堆栈信息
        if (isDev) {
            return ExceptionUtils.getPrintInfo(exception);
        }

        //循环获取cause,保存到列表
        List<Throwable> causeList = new ArrayList<>();

        while (exception != null) {
            causeList.add(exception);
            //防止循环引用
            if (exception.getCause() == exception) {
                break;
            }
            exception = exception.getCause();
        }

        return causeList.stream().map(ex -> ex.getClass().getSimpleName() + (StringUtils.hasText(ex.getMessage()) ? ":" + ex.getMessage() : "")).collect(Collectors.joining(" -> "));
    }


//    // 这里@ModelAttribute("loginUserInfo")标注的modelAttribute()方法表示会在Controller方法之前
//    // 执行，返回当前登录用户的UserDetails对象
//    @ModelAttribute("loginUserInfo")
//    public UserDetails modelAttribute() {
//        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }


//    // 这里表示Controller抛出的MethodArgumentNotValidException异常由这个方法处理
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Result exceptionHandler(MethodArgumentNotValidException e) {
//        Result result = new Result(BizExceptionEnum.INVALID_REQ_PARAM.getErrorCode(),
//                BizExceptionEnum.INVALID_REQ_PARAM.getErrorMsg());
//        logger.error("req params error", e);
//        return result;
//    }
//    // 这里表示Controller抛出的BizException异常由这个方法处理
//    @ExceptionHandler(BizException.class)
//    public Result exceptionHandler(BizException e) {
//        BizExceptionEnum exceptionEnum = e.getBizExceptionEnum();
//        Result result = new Result(exceptionEnum.getErrorCode(), exceptionEnum.getErrorMsg());
//        logger.error("business error", e);
//        return result;
//    }
//    // 这里就是通用的异常处理器了,所有预料之外的Exception异常都由这里处理
//    @ExceptionHandler(Exception.class)
//    public Result exceptionHandler(Exception e) {
//        Result result = new Result(1000, "网络繁忙,请稍后再试");
//        logger.error("application error", e);
//        return result;
//    }


//    @ExceptionHandler({NotLoginException.class,})
//    public ApiResp onNotLoginException(Exception e) {
//
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//
//        return ApiResp.error(ServiceResp.AuthenticationError.getBaseErrorCode()
//                , "未登录：" + e.getMessage());
//    }
//
//    @ExceptionHandler({SaTokenException.class,})
//    public ApiResp onSaTokenException(Exception e) {
//
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//
//        return ApiResp.error(ServiceResp.AuthenticationError.getBaseErrorCode()
//                , "认证异常：" + e.getMessage());
//    }

    @ExceptionHandler({NotLoginException.class,})
    public ApiResp onNotLoginException(Exception e) {

        log.error("{} 授权异常", request.getRequestURL(), e);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        return ApiResp.error(AuthenticationError.getBaseErrorCode(), "未登录：" + getExMsg(e));
    }

    @ExceptionHandler({SaTokenException.class, UnauthorizedException.class})
    public ApiResp onAuthorizedException(Exception e) {

        log.error("{} 授权异常", request.getRequestURL(), e);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        return ApiResp.error(AuthenticationError.getBaseErrorCode(), "认证异常：" + getExMsg(e));
    }


    @ExceptionHandler({AccessDeniedException.class,})
    public ApiResp onAccessDeniedException(Exception e) {

        log.error("{} 授权异常", request.getRequestURL(), e);

        response.setStatus(HttpStatus.FORBIDDEN.value());

        return ApiResp.error(AuthenticationError.getBaseErrorCode(), getExMsg(e));
    }

    @ExceptionHandler({BizException.class, DaoUniqueConstraintBizException.class})
    public ApiResp onBizException(Exception e) {

        log.error("业务参数异常," + request.getRequestURL(), e);

        return (ApiResp) ApiResp.error(BizError.getBaseErrorCode(), getExMsg(e)).setDetailMsg(getExDetailMsg(e));
    }


    @ExceptionHandler({JacksonException.class})
    public ApiResp onJacksonException(JacksonException e) {

        log.error("请求参数异常," + request.getRequestURL(), e);

        return (ApiResp) ApiResp.error(BizError.getBaseErrorCode(), getExMsg(e)).setDetailMsg(getExDetailMsg(e));
    }


    @ExceptionHandler({IllegalArgumentException.class,
            IllegalStateException.class,
            BindException.class,
            MethodArgumentNotValidException.class,
            ValidationException.class,
            MissingServletRequestParameterException.class})
    public ApiResp onParameterException(Exception e) {

        log.error("请求参数异常," + request.getRequestURL(), e);

        return (ApiResp) ApiResp.error(BizError.getBaseErrorCode(), getExMsg(e)).setDetailMsg(getExDetailMsg(e));
    }

    @ExceptionHandler(ServiceException.class)
    public ApiResp onServiceException(Exception e) {

        log.error("{} 服务异常", request.getRequestURL(), e);

        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

        return (ApiResp) ApiResp.error(SystemInnerError.getBaseErrorCode(), getExMsg(e)).setDetailMsg(getExDetailMsg(e));
    }

    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class})
    public ApiResp onConstraintViolationException(Exception e) {

        log.error("发生数据约束异常," + request.getRequestURL(), e);

//        boolean used = e.getMessage().contains(" delete ")
//                || e.getMessage().contains(" update ");

        return (ApiResp) ApiResp.error(BizError.getBaseErrorCode(), "数据约束异常").setDetailMsg(getExDetailMsg(e));

    }

    @ExceptionHandler({PersistenceException.class, SQLException.class, DataAccessException.class})
    public ApiResp onPersistenceException(Exception e) {

        if (ExceptionUtils.getCauseByTypes(e, ConstraintViolationException.class
                , DataIntegrityViolationException.class
                , SQLIntegrityConstraintViolationException.class) != null) {
            return onConstraintViolationException(e);
        }

        log.error("发生数据库操作异常," + request.getRequestURL(), e);

        return (ApiResp) ApiResp.error(SystemInnerError.getBaseErrorCode(), "数据异常，请稍后重试").setDetailMsg(getExDetailMsg(e));

    }

    //    // 这里就是通用的异常处理器了,所有预料之外的Exception异常都由这里处理
    @ExceptionHandler(ServletException.class)
    public ApiResp onServletException(ServletException e) {

        log.error("发生 Web异常:" + request.getRequestURL(), e);

        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

        return (ApiResp) ApiResp.error(SystemInnerError.getBaseErrorCode(), getExMsg(e)).setDetailMsg(getExDetailMsg(e));
    }

    //    // 这里就是通用的异常处理器了,所有预料之外的Exception异常都由这里处理
    @ExceptionHandler(Exception.class)
    public ApiResp exceptionHandler(Exception e) {

        log.error("发生异常:" + request.getRequestURL(), e);

        //网络异常
        if (ExceptionUtils.getCauseByTypes(e, SocketException.class) != null) {

            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

            return (ApiResp) ApiResp.error(ResourceError.getBaseErrorCode(), getExMsg(e))
                    .setDetailMsg(getExDetailMsg(e));
        }

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return (ApiResp) ApiResp.error(UnknownError.getBaseErrorCode(), getExMsg(e)).setDetailMsg(getExDetailMsg(e));
    }

}
