package com.levin.oak.base.biz.rbac;

import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.ResPermission;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.BizUserService;
import com.levin.oak.base.biz.CaptchaService;
import com.levin.oak.base.biz.SmsCodeService;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.appclient.req.CreateAppClientReq;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.org.req.CreateOrgReq;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.CreateTenantReq;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.req.CreateUserReq;
import com.levin.oak.base.utils.AmisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.biz.BizUserService.SA_ACCOUNT;

/**
 * Rbac初始化服务
 *
 * @todo 分离web相关部分
 */

@Slf4j
//@org.springframework.context.annotation.Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Service(PLUGIN_PREFIX + "DefaultRbacInitService")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "DefaultRbacInitService", havingValue = "true", matchIfMissing = true)
public class DefaultRbacInitServiceImpl
        implements RbacInitService, ApplicationListener<ContextRefreshedEvent> {

//    static final Gson gson = new Gson();
//
//    static final Type resPermissionListType = new TypeToken<List<ResPermission>>() {
//    }.getType();
//
//    static final Type listStrType = new TypeToken<List<String>>() {
//    }.getType();

    @Autowired
    ApplicationContext context;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    RbacService rbacService;

    @Autowired
    BizUserService bizUserService;

    @Autowired
    BizRoleService<Serializable> bizRoleService;

    @Autowired
    MenuResService menuResService;

    @Autowired
    PluginManager pluginManager;

    @Autowired
    TenantService tenantService;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired(required = false)
    CaptchaService captchaService;

    @Autowired(required = false)
    SmsCodeService smsCodeService;

    @Autowired
    SimpleDao simpleDao;

    @Autowired
    Environment environment;

    @PostConstruct
    public void init() {

        log.info("默认Rbac初始化服务启用...");

    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.info("On ContextRefreshedEvent " + event);

        if (event.getApplicationContext() == this.context) {
            initRbacData();
        }
    }

    @Override
    public void initRbacData() {

        log.info("初始化用户数据和菜单数据...");

        initUser();

        initMenu();

    }


    /**
     * 自动创建一些空菜单
     */
    private void initMenu() {

        //  final String defaultIcon = "fa fa-list";// (serverProperties.getServlet().getContextPath() + "/" + frameworkProperties.getAdminPath() + "/img/menu-256.png").replace("//", "/");

        final AtomicInteger index = new AtomicInteger();

        for (Plugin plugin : pluginManager.getInstalledPlugins()) {

            MenuRes pluginRootMenu = (MenuRes) new MenuRes()
                    .setPath(plugin.getPackageName() + "/" + plugin.getVersion() + "/admin/index")
//                    .setIcon(defaultIcon)
                    .setDomain(plugin.getPackageName())
                    .setName(plugin.getName())
                    .setEnable(plugin.isEnable())
                    .setOrderCode(plugin.getOrderCode())
                    .setRemark(plugin.getRemark());

            if (simpleDao.selectFrom(MenuRes.class)
                    .eq(E_MenuRes.domain, pluginRootMenu.getDomain())
                    .eq(E_MenuRes.path, pluginRootMenu.getPath())
                    .isNull(E_MenuRes.tenantId)
                    .findOne() == null) {
                simpleDao.create(pluginRootMenu);
            }

            RbacUtils.getMenuItemByController(context, plugin.getPackageName(), EntityConst.QUERY_LIST_ACTION)
                    .stream().filter(Objects::nonNull).forEach(menuItem -> {

                        final String path = menuItem.getPath().replace("/api/", "/admin/");

                        if (simpleDao.selectFrom(MenuRes.class)
                                .eq(E_MenuRes.domain, pluginRootMenu.getDomain())
                                .eq(E_MenuRes.path, path)
                                .isNull(E_MenuRes.tenantId)
                                .findOne() != null) {
                            //如果目录已经存在
                            return;
                        }

                        final int no = index.incrementAndGet();

                        //创建菜单
                        log.info("创建菜单{} - 插件[ {} ][ {} --> {}]", no, plugin.getId(), menuItem.getName(), path);

                        simpleDao.create(
                                simpleDao.copy(menuItem,
                                        new MenuRes()
                                                .setPath(path)
//                                        .setIcon(defaultIcon)
                                                .setParentId(pluginRootMenu.getId()),
                                        1,
                                        E_MenuRes.parentId,
                                        E_MenuRes.children,
                                        E_MenuRes.icon,
                                        E_MenuRes.parent,
                                        E_MenuRes.path)
                        );

                        //创建默认页面
                        log.info("创建页面{} - 插件[ {} ][ {} --> {}]", no, plugin.getId(), menuItem.getName(), path);

                        simpleDao.create(new SimplePage()
                                        .setType(SimplePage.Type.json.name())
                                        .setCategory("amis")
                                        .setGroupName("管理后台页面|" + plugin.getName())
                                        .setPath(path)
                                        //设置访问需要的权限
                                        .setRequireAuthorizations(menuItem.getRequireAuthorizations())

//                        .setIcon(defaultIcon)
                                        .setContent(AmisUtils.readAdminClassPathResource(path))
                                        .setDomain(plugin.getPackageName())
                                        .setName(menuItem.getName())
                                        .setRemark("Amis默认页面")
                        );

                    });
        }
    }


    private void initUser() {

        final boolean isTestEnv = Stream.of(environment.getActiveProfiles()).allMatch(p -> p.equals("dev") || p.equals("test") || p.equals("local"));

        QueryTenantReq req = new QueryTenantReq().setOrderBy(E_Tenant.id).setOrderDir(OrderBy.Type.Asc);

        //如果允许域名
        if (frameworkProperties.getTenantBindDomain().isEnable()) {
            req.setDomainList(Arrays.asList("127.0.0.1"));
        }

        TenantInfo tenantInfo = tenantService.findOne(req);

        if (tenantInfo == null) {

            String id = tenantService.create(new CreateTenantReq()
                    .setName("默认租户")
                    .setRemark("支持本地地址")
                    .setDomainList(Arrays.asList("127.0.0.1", "localhost"))
            );

            tenantInfo = tenantService.findById(id);

//            tenantInfo = tenantService.findOne(req);
        }

        long count = simpleDao.selectFrom(AppClient.class)
                .select(E_AppClient.appId)
                .eq(E_AppClient.tenantId, tenantInfo.getId())
                .count();

        if (count < 1) {
            simpleDao.create(new CreateAppClientReq()
                    .setName("默认应用")
                    .setTenantId(tenantInfo.getId())
            );
        }

        Role role = simpleDao.selectFrom(Role.class)
                .eq(E_Role.code, RbacRoleObject.SA_ROLE)
                .isNull(E_Role.tenantId)
                .findOne();

        if (role == null) {

            simpleDao.create(new CreateRoleReq()
                    .setCode(RbacRoleObject.SA_ROLE)
                    .setName("超级管理员")
                    .setEditable(false)
                    .setOrgDataScope(Role.OrgDataScope.All)
                    .setPermissionList(Collections.singletonList(
                                    new ResPermission()
                                            .setDomain("*")
                                            .setType("*")
                                            .setRes("*")
                                            .setAction("*")
                                            .toString()
                            )
                    )
            );

        }
        ///////////////////////////////////////////////////

        role = simpleDao.selectFrom(Role.class)
                .eq(E_Role.code, RbacRoleObject.ADMIN_ROLE)
                .eq(E_User.tenantId, tenantInfo.getId())
                .findOne();

        if (role == null) {

            simpleDao.create(
                    new CreateRoleReq()
                            .setCode(RbacRoleObject.ADMIN_ROLE)
                            .setName("管理员")
                            .setEditable(false)

                            .setOrgDataScope(Role.OrgDataScope.All)
                            .setPermissionList(Arrays.asList(

                                    new ResPermission()
                                            .setDomain("*")
                                            .setType(EntityConst.BIZ_TYPE_NAME + "-*")
                                            .setRes("*")
                                            .setAction("*")
                                            .toString(),

                                    new ResPermission()
                                            .setDomain("*")
                                            .setType(EntityConst.COMMON_TYPE_NAME + "-*")
                                            .setRes("*")
                                            .setAction("*")
                                            .toString(),

                                    new ResPermission()
                                            .setDomain("*")
                                            .setType(EntityConst.SYS_TYPE_NAME + "-*")
                                            .setRes("*")
                                            .setAction("*")
                                            .toString()
                            ))
                            .setTenantId(tenantInfo.getId())
            );

        }

        role = simpleDao.selectFrom(Role.class)
                .eq(E_Role.code, "R_ORG_ADMIN")
                .eq(E_User.tenantId, tenantInfo.getId())
                .findOne();

        if (role == null) {
            simpleDao.create(
                    new CreateRoleReq()
                            .setCode("R_ORG_ADMIN")
                            .setName("部门管理员")
                            .setEditable(true)
                            .setOrgDataScope(Role.OrgDataScope.MyOrgAndChildren)
                            .setPermissionList(Arrays.asList(

                                    new ResPermission()
                                            .setDomain("*")
                                            .setType(EntityConst.BIZ_TYPE_NAME + "-*")
                                            .setRes("*")
                                            .setAction("*")
                                            .toString()


                            ))
                            .setTenantId(tenantInfo.getId())
            );
        }
        ///////////////////////////////////////////////////

        User user = simpleDao.selectFrom(User.class)
                .isNull(E_User.tenantId)
                .eq(E_User.email, SA_ACCOUNT)
                .findOne();

        if (user == null) {
            simpleDao.create(
                    new CreateUserReq()
                            .setEmail(SA_ACCOUNT)
                            .setTelephone("18995279527")
                            .setPassword(encryptPassword("123456"))
                            .setName("超级管理员")
                            .setEditable(false)
                            .setStaffNo("0000")
                            .setRoleList(Collections.singletonList(RbacRoleObject.SA_ROLE))
            );
        }

        /////////////////////////////////////////////////////////////
        String orgId = simpleDao.selectFrom(Org.class)
                .select(E_Org.id)
                .isNull(E_Org.parentId)
                .eq(E_Org.tenantId, tenantInfo.getId())
                .findOne();

        if (StrUtil.isBlank(orgId)) {

            Org org = simpleDao.create(new CreateOrgReq()
                    .setName(tenantInfo.getName())
                    .setType(Org.Type.LegalPerson)
                    .setTenantId(tenantInfo.getId())
            );

            orgId = org.getId();

            if (isTestEnv) {

                simpleDao.create(new CreateOrgReq()
                        .setName("运营一部")
                        .setType(Org.Type.Department)
                        .setParentId(orgId)
                        .setTenantId(tenantInfo.getId())
                );

                simpleDao.create(new CreateOrgReq()
                        .setName("采购部")
                        .setType(Org.Type.Department)
                        .setParentId(orgId)
                        .setTenantId(tenantInfo.getId())
                );

                simpleDao.create(new CreateOrgReq()
                        .setName("综合部")
                        .setType(Org.Type.Department)
                        .setParentId(orgId)
                        .setTenantId(tenantInfo.getId())
                );
            }

        }

        user = simpleDao.selectFrom(User.class)
                .eq(E_User.tenantId, tenantInfo.getId())
                //   .eq(E_User.email, "admin")
                .findOne();

        if (user == null) {

            simpleDao.create(new CreateUserReq()
                    .setEmail("admin")
                    .setTelephone("18095279527")
                    .setPassword(encryptPassword("123456"))
                    .setName("管理员")
                    .setStaffNo("9999")
                    .setRoleList(Collections.singletonList(RbacRoleObject.ADMIN_ROLE))
                    .setOrgId(orgId)
                    .setTenantId(tenantInfo.getId())

            );

            if (isTestEnv) {

                simpleDao.create(new CreateUserReq()
                        .setEmail("test")
                        .setTelephone("18912341234")
                        .setPassword(encryptPassword("123456"))
                        .setName("测试员工")
                        .setStaffNo("1111")
                        .setRoleList(Collections.singletonList("R_ORG_ADMIN"))
                        .setOrgId(orgId)
                        .setTenantId(tenantInfo.getId())

                );
            }

        }

        ///////////////////////////////////////////////////

    }

    private String encryptPassword(String pwd) {
        return bizUserService.encryptPwd(pwd);
    }

}