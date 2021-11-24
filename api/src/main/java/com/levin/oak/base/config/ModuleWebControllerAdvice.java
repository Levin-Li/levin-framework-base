package com.levin.oak.base.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.service.exception.AccessDeniedException;
import com.levin.commons.service.exception.ServiceException;
import com.levin.commons.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.levin.oak.base.ModuleOption.PACKAGE_NAME;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 在Spring 3.2中
 * 新增了@ControllerAdvice、@RestControllerAdvice 注解，
 * 可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping、@PostMapping， @GetMapping注解中。
 */
@Slf4j
@Component(PLUGIN_PREFIX + "ModuleWebControllerAdvice")
@ConditionalOnMissingBean(name = {PLUGIN_PREFIX + "ModuleWebControllerAdvice"})
@RestControllerAdvice(PACKAGE_NAME)
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ModuleWebControllerAdvice", havingValue = "false", matchIfMissing = true)
public class ModuleWebControllerAdvice {

//    // 这里@ModelAttribute("loginUserInfo")标注的modelAttribute()方法表示会在Controller方法之前
//    // 执行，返回当前登录用户的UserDetails对象
//    @ModelAttribute("loginUserInfo")
//    public UserDetails modelAttribute() {
//        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }

//    // @InitBinder标注的initBinder()方法表示注册一个Date类型的类型转换器，用于将类似这样的2019-06-10
//    // 日期格式的字符串转换成Date对象
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//    }


    @ExceptionHandler({NotLoginException.class,})
    public ApiResp onNotLoginException(Exception e) {
        return ApiResp.error(1, "未登录：" + e.getMessage()).setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler({SaTokenException.class,})
    public ApiResp onSaTokenException(Exception e) {
        return ApiResp.error(2, "帐号异常：" + e.getMessage()).setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler({AccessDeniedException.class,})
    public ApiResp onAccessDeniedException(Exception e) {
        return ApiResp.error(2, "访问异常：" + e.getMessage())
                .setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class})
    public ApiResp onParameterException(Exception e) {

        log.error("发生数据约束异常", e);

        return (ApiResp) ApiResp.error(9, "请求参数异常：" + e.getMessage())
                .setHttpStatusCode(HttpStatus.BAD_REQUEST.value())
                .setDetailMsg(ExceptionUtils.getAllCauseInfo(e," -> "));
    }

    @ExceptionHandler(ServiceException.class)
    public ApiResp onServiceException(Exception e) {
        return (ApiResp) ApiResp.error(10, e.getMessage())
                .setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setDetailMsg(ExceptionUtils.getAllCauseInfo(e," -> "));
    }

    @ExceptionHandler({ConstraintViolationException.class, SQLIntegrityConstraintViolationException.class})
    public ApiResp onConstraintViolationException(Exception e) {

        log.error("发生数据约束异常", e);

        boolean used = e.getMessage().contains(" delete ")
                || e.getMessage().contains(" update ");

        return (ApiResp) ApiResp.error(20, used ? "操作失败，数据已经被使用" : "名称、编码或其它唯一值已经存在")
                .setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setDetailMsg(ExceptionUtils.getRootCauseInfo(e));

    }


    @ExceptionHandler({PersistenceException.class, SQLException.class})
    public ApiResp onPersistenceException(Exception e) {

        Throwable rootCause = ExceptionUtils.getRootCause(e);
        if (rootCause instanceof ConstraintViolationException || rootCause instanceof SQLIntegrityConstraintViolationException) {
            return onConstraintViolationException((Exception) rootCause);
        }

        log.error("发生数据库操作异常", e);

        return (ApiResp) ApiResp.error(20, "数据异常，请稍后重试")
                .setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setDetailMsg(ExceptionUtils.getRootCauseInfo(e));
    }

    //    // 这里就是通用的异常处理器了,所有预料之外的Exception异常都由这里处理
    @ExceptionHandler(Exception.class)
    public ApiResp exceptionHandler(Exception e) {
        log.error("发生异常", e);
        return (ApiResp) ApiResp.error(1, e.getMessage())
                .setHttpStatusCode(500)
                .setDetailMsg(ExceptionUtils.getPrintInfo(e));
    }

}