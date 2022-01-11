package com.levin.oak.base.services.rbac;

import cn.dev33.satoken.exception.IdTokenInvalidException;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.AuthorizationException;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.ResPermission;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.rbac.req.LoginReq;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.CreateUserReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 认证服务
 */

@Slf4j
@Order
//@ConditionalOnMissingBean(AuthService.class)
@ConditionalOnProperty(value = PLUGIN_PREFIX + "DefaultAuthService", havingValue = "false", matchIfMissing = true)
@Service(PLUGIN_PREFIX + "DefaultAuthService")
public class AuthServiceImpl extends BaseService implements AuthService {

//    static final Gson gson = new Gson();
//
//    static final Type resPermissionListType = new TypeToken<List<ResPermission>>() {
//    }.getType();
//
//    static final Type listStrType = new TypeToken<List<String>>() {
//    }.getType();

    @Resource
    ApplicationContext context;

    @Resource
    UserService userService;

    @Resource
    RoleService roleService;

    @Resource
    MenuResService menuResService;

    @Resource
    PluginManager pluginManager;

    @PostConstruct
    public void init() {

        log.info("默认认证服务启用...");

        initData();

    }

    /**
     * 认证，并返回token
     *
     * @param account
     * @param password
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    @Override
    public String auth(String account, String password, String userAgent, Map<String, Object>... params) {
        return loginByPassword(new LoginReq().setAccount(account).setPassword(password).setUa(userAgent));
    }


    /**
     * 直接认证，并返回token
     *
     * @param loginId
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    @Override
    public String auth(String loginId, String userAgent, Map<String, Object>... params) {

//        Assert.notNull(loginId, "loginId is null");

        Assert.hasText(loginId, "loginId is empty");

        //默认登录
        StpUtil.login(loginId, getDeviceType(userAgent));

        return StpUtil.getTokenValue();
    }

    @Override
    public boolean isLogin() {
        return StpUtil.isLogin();
    }



    @Override
    public UserInfo getUserInfo() {
        return userService.findById(Long.parseLong(getLoginUserId()));
    }

    @Override
    public String getLoginIdByToken(String token) {

        Assert.hasText(token, "token is empty");

        Object loginId = StpUtil.getLoginIdByToken(token);

        if (loginId == null || !StringUtils.hasText(loginId.toString())) {
            throw new IdTokenInvalidException("token:" + token);
        }

        return loginId.toString();
    }

    @Override
    public void invalidate(String token) {

        if (StringUtils.hasText(token)) {
            StpUtil.logoutByTokenValue(token);
        }

    }

    @Override
    public void logout() {
        invalidate(StpUtil.getTokenValue());
    }

    public String loginByPassword(LoginReq req) {

        if (!StringUtils.hasText(req.getAccount())
                || !StringUtils.hasText(req.getPassword())) {
            throw new AuthorizationException("401", "请输入正确的帐号或密码");
        }

        //密码加密
        req.setPassword(encryptPassword(req.getPassword()));

//        String deviceType = getDeviceType(req.getUa());

        UserInfo user = simpleDao.findOneByQueryObj(req);

        checkUserState(user);

        return auth(user.getId().toString(), req.getUa());

    }


    private void checkUserState(UserInfo user) throws AuthorizationException {

        if (user == null) {
            throw new AuthorizationException("401", "1帐号或密码错误");
        }

//        if (!SaSecureUtil.sha1(req.getPassword()).equals(user.getPassword())) {
//            return ApiResp.error("2帐号或密码错误");
//        }

        if (!user.getEnable()
                || !User.State.Normal.equals(user.getState())) {
            throw new AuthorizationException("401", "2帐号状态异常");
        }

        if (user.getExpiredDate() != null
                && user.getExpiredDate().getTime() < System.currentTimeMillis()) {
            throw new AuthorizationException("401", "3帐号已过期");
        }
    }

    @Override
    public <T> T getLoginUserId() {
        return (T) StpUtil.getLoginId();
    }


    @Override
    public List<String> getPermissionList(Object loginId) {

        if (loginId == null) {
            loginId = getLoginUserId();
        }

        List<String> roleList = getRoleList(loginId);

        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }

        UserInfo user = userService.findById(Long.parseLong(loginId.toString()));

        checkUserState(user);

        return simpleDao.selectFrom(Role.class)
                .select(E_Role.permissions)
                .eq(E_Role.enable, true)
                .in(E_Role.code, roleList)
                .<String>find()
                .parallelStream()
                .filter(StringUtils::hasText)
                //JSON 转换
                .flatMap(json -> (JsonStrArrayUtils.<String>parse(json,null,null)).stream())
//                .map(json -> (List<ResPermission>) gson.fromJson(json, resPermissionListType))
                .filter(StringUtils::hasText)
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getRoleList(Object loginId) {

        if (loginId == null) {
            loginId = getLoginUserId();
        }

        UserInfo user = userService.findById(Long.parseLong(loginId.toString()));

        checkUserState(user);

        return JsonStrArrayUtils.parse(user.getRoles(),null,null);
    }


    @Override
    public String encryptPassword(String pwd) {
        return SaSecureUtil.sha1(pwd);
    }

    @Override
    public String getDeviceType(String ua) {

//        1. http://whatsmyuseragent.com/
//        2. http://whatsmyua.com/
//        3. http://www.useragentstring.com/

        if (ua.contains("Android ")
                || ua.contains("iPhone;")
                || ua.contains("iOS ")
                || ua.contains("iPhone OS ")) {
            return "Phone";
        } else if (ua.contains("iPad;")) {
            return "iPad";
        } else if (ua.contains("Windows ")
                || ua.contains("Macintosh;")
                || ua.contains(" Mac OS X ")) {
            return "PC";
        }

        return "Unknown";

    }

    /**
     * 初始化数据
     */
    public void initData() {

        initUser();

        initMenu();
    }

    /**
     * 自动创建一些空菜单
     */
    private void initMenu() {

        for (Plugin plugin : pluginManager.getInstalledPlugins()) {

            MenuRes menu = simpleDao.selectFrom(MenuRes.class)
                    .eq(E_MenuRes.domain, plugin.getId())
                    .isNull(E_Role.tenantId).findOne();

            if (menu != null) {
                continue;
            }

            MenuRes pluginRootMenu = simpleDao.create(new MenuRes().setDomain(plugin.getId())
                    .setName(plugin.getName())
                    .setEnable(plugin.isEnable())
                    .setOrderCode(plugin.getOrderCode())
                    .setRemark(plugin.getRemark()));

            RbacUtils.getMenuItemByController(context, plugin.getId(), EntityConst.QUERY_ACTION)
                    .parallelStream().forEach(menuItem -> {
                //创建菜单
                log.info("创建插件[ {} ]的默认菜单[ {} --> {}]", plugin.getId(), menuItem.getName(), menuItem.getPath());
                simpleDao.create(simpleDao.copyProperties(menuItem, new MenuRes().setParentId(pluginRootMenu.getId()),
                        1, E_MenuRes.parentId, E_MenuRes.children, E_MenuRes.parent));
            });

        }

    }

    private void initUser() {

        Role role = simpleDao.selectFrom(Role.class)
                .eq(E_Role.code, "SA")
                .isNull(E_Role.tenantId)
                .findOne();

        if (role == null) {

            List<String> permissions = new LinkedList<>();

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType("*")
                    .setRes("*")
                    .setAction("*")
                    .toString());

            roleService.create(new CreateRoleReq()
                    .setCode("SA")
                    .setName("超级管理员")
                    .setOrgDataScope(Role.OrgDataScope.All)
                    .setPermissions(JsonStrArrayUtils.iterableToStrArrayJson(permissions)));

//            JsonStrArrayUtils.toStrArrayJson()

            permissions.clear();

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType(EntityConst.TYPE_NAME)
                    .setRes("*")
                    .setAction(EntityConst.QUERY_ACTION + "*")
                    .toString());

            roleService.create(new CreateRoleReq()
                    .setCode("test")
                    .setName("测试员")
                    .setOrgDataScope(Role.OrgDataScope.MySelf)
                    .setPermissions(JsonStrArrayUtils.iterableToStrArrayJson(permissions)));
        }


        User user = simpleDao.selectFrom(User.class)
                .isNull(E_User.tenantId)
                .eq(E_User.loginName, "admin")
                .findOne();

        if (user == null) {

            List<String> roleList = new LinkedList<>();

            roleList.add("SA");

            userService.create(new CreateUserReq()
                    .setLoginName("admin")
                    .setPassword(encryptPassword("123456"))
                    .setName("超级管理员")
                    .setStaffNo("0000")
                    .setRoles(JsonStrArrayUtils.iterableToStrArrayJson(roleList))

            );

            roleList.clear();
            roleList.add("test");

            userService.create(new CreateUserReq()
                    .setLoginName("test")
                    .setPassword(encryptPassword("123456"))
                    .setName("测试帐号")
                    .setStaffNo("9999")
                    .setRoles(JsonStrArrayUtils.iterableToStrArrayJson(roleList))

            );
        }
    }

}
