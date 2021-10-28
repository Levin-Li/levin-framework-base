package com.levin.oak.base;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.services.accesslog.AccessLogService;
import com.levin.oak.base.services.area.AreaService;
import com.levin.oak.base.services.dict.DictService;
import com.levin.oak.base.services.jobpost.JobPostService;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.org.OrgService;
import com.levin.oak.base.services.rbac.AuthService;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.info.RoleInfo;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.role.req.QueryRoleReq;
import com.levin.oak.base.services.scheduledtask.ScheduledTaskService;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.CreateUserReq;
import com.levin.oak.base.services.user.req.QueryUserReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Component(PLUGIN_PREFIX + "ModuleDataInitializer")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ModuleDataInitializer", havingValue = "false", matchIfMissing = true)
public class ModuleDataInitializer implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    SimpleDao dao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RoleService roleService;

    @Autowired
    SettingService settingService;

    @Autowired
    DictService dictService;

    @Autowired
    UserService userService;

    @Autowired
    AccessLogService accessLogService;

    @Autowired
    ScheduledTaskService scheduledTaskService;

    @Autowired
    OrgService orgService;

    @Autowired
    JobPostService jobPostService;

    @Autowired
    AreaService areaService;

    @Autowired
    MenuResService menuResService;

    @Autowired
    AuthService authService;


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("[ {} ] on applicationContext ...", ModuleOption.ID);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext() == applicationContext) {
            initData();
        }
    }

    void initData() {

        log.info("[ {} ] on init ...", ModuleOption.ID);

        RoleInfo role = roleService.query(new QueryRoleReq(), new SimplePaging().setPageSize(1).setPageIndex(1)).getFirst();

        if (role == null) {
            roleService.create(new CreateRoleReq()
                    .setName("超级管理员")
                    .setOrgDataScope(Role.OrgDataScope.All)
                    .setPermissions("*:*:*:*")
                    .setCode("SA"));
        }

        UserInfo user = userService.query(new QueryUserReq(), new SimplePaging().setPageSize(1).setPageIndex(1)).getFirst();

        if (user == null) {
            userService.create(new CreateUserReq()
                    .setLoginName("admin")
                    .setPassword(authService.encryptPassword("123456"))
                    .setStaffNo("0000")
                    .setRoleList("SA")
            );
        }

        //@todo 初始化数据

        log.info("***** {} 数据初始化完成 ******", ModuleOption.ID);
    }

}
