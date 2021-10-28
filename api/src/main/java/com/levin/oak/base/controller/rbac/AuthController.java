package com.levin.oak.base.controller.rbac;

import cn.dev33.satoken.stp.StpUtil;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.rbac.AuthService;
import com.levin.oak.base.services.rbac.req.LoginReq;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.levin.oak.base.ModuleOption.API_PATH;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "AuthController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "AuthController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "auth")

@Tag(name = "认证", description = "认证管理")
@Slf4j
@Valid
public class AuthController {

    //请求级别变量
    @Autowired
    HttpServletResponse httpResponse;

    //请求级别变量
    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthService authService;

    /**
     * 新增
     *
     * @param req CreateRoleEvt
     * @return ApiResp
     */
    @PostMapping("login")
    @Operation(tags = {"认证管理"}, summary = "登录")
    public ApiResp<String> create(@RequestBody LoginReq req, @RequestHeader("user-agent") String ua) {

        if (!StringUtils.hasText(req.getAccount())
                || !StringUtils.hasText(req.getPassword())) {
            return ApiResp.error("请输入正确的帐号或密码");
        }

        //密码加密
        req.setPassword(authService.encryptPassword(req.getPassword()));

        String deviceType = getDeviceType(ua);

        UserInfo user = authService.login(req);

        if (user == null) {
            return ApiResp.error("1帐号或密码错误");
        }

//        if (!SaSecureUtil.sha1(req.getPassword()).equals(user.getPassword())) {
//            return ApiResp.error("2帐号或密码错误");
//        }

        if (!user.getEnable()
                || !User.State.Normal.equals(user.getState())) {
            return ApiResp.error("帐号状态异常");
        }

        if (user.getExpiredDate() != null
                && user.getExpiredDate().getTime() < System.currentTimeMillis()) {
            return ApiResp.error("帐号已过期");
        }

        StpUtil.login(user.getId(), deviceType);

        return ApiResp.ok(StpUtil.getTokenValueByLoginId(user.getId(), deviceType));

    }


    public static String getDeviceType(String ua) {

        // ua = ua.toLowerCase();

        if (ua.contains("Android ") || ua.contains("iPhone OS ")) {
            return "Phone";
        } else if (ua.contains("iPad;") || ua.contains("iPhone OS ")) {
            return "Pad";
        } else if (ua.contains("Windows ") || ua.contains("iPhone OS ")) {
            return "PC";
        }

        return "Unknown";
    }


}
