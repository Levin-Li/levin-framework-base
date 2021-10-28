package com.levin.oak.base.config;

import com.levin.commons.service.domain.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.levin.oak.base.ModuleOption.PACKAGE_NAME;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 在Spring 3.2中
 * 新增了@ControllerAdvice、@RestControllerAdvice 注解，
 * 可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping、@PostMapping， @GetMapping注解中。
 */
@Slf4j
@Component(PLUGIN_PREFIX + "ModuleWebControllerAdvice")
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

//    @ResponseBody
//    @ExceptionHandler
//    public ApiResp handlerException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//
//
//        // 不同异常返回不同状态码
//        AjaxJson aj = null;
//        if (e instanceof NotLoginException) {    // 如果是未登录异常
//            NotLoginException ee = (NotLoginException) e;
//            aj = AjaxJson.getNotLogin().setMsg(ee.getMessage());
//        } else if (e instanceof NotRoleException) {        // 如果是角色异常
//            NotRoleException ee = (NotRoleException) e;
//            aj = AjaxJson.getNotJur("无此角色：" + ee.getRole());
//        } else if (e instanceof NotPermissionException) {    // 如果是权限异常
//            NotPermissionException ee = (NotPermissionException) e;
//            aj = AjaxJson.getNotJur("无此权限：" + ee.getCode());
//        } else if (e instanceof DisableLoginException) {    // 如果是被封禁异常
//            DisableLoginException ee = (DisableLoginException) e;
//            aj = AjaxJson.getNotJur("账号被封禁：" + ee.getDisableTime() + "秒后解封");
//        } else {    // 普通异常, 输出：500 + 异常信息
//            aj = AjaxJson.getError(e.getMessage());
//        }
//
//        // 返回给前端
//        return aj;
//    }

}