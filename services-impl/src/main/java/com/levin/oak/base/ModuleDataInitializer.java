package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.UUID;
import com.levin.commons.dao.SelectDao;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.domain.support.E_TestEntity;
import com.levin.commons.dao.domain.support.TestEntity;
import com.levin.commons.plugin.PluginManager;
import com.levin.oak.base.biz.BizSettingService;
import com.levin.oak.base.biz.Sms4JSmsSendService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.vo.TestEntityQueryObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.*;
import org.springframework.beans.factory.annotation.*;

import javax.annotation.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executor;

import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.SimpleApi;
import com.levin.oak.base.entities.AppClient;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.entities.TenantApp;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.Notice;
import com.levin.oak.base.entities.AppClientFile;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.ScheduledTask;
import com.levin.oak.base.entities.NoticeProcessLog;
import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.JobPost;
import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.SimplePage;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.entities.SimpleForm;

/**
 * 模块初始化器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:46, 代码生成哈希校验码：[b8d15df8ed4b76b786beb474e36f4257]，请不要修改和删除此行内容。
 */
@Slf4j
@Component(PLUGIN_PREFIX + "ModuleDataInitializer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleDataInitializer", matchIfMissing = true)
public class ModuleDataInitializer implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    SimpleDao dao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private ApplicationContext applicationContext;

    @Autowired
    private BizSettingService bizSettingService;

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

        Random random = new Random(this.hashCode());

        selfTest();


        //@todo 初始化数据

        log.info("***** {} 数据初始化完成 ******", ModuleOption.ID);
    }

    private void selfTest() {

        log.info("[ {} ] 模块自检中 ...", ModuleOption.ID);

        Tenant tenant = new Tenant().setId(UUID.randomUUID().toString(true));

        //删除测试数据
        String category = "自检测试数据";

        int cnt = dao.deleteFrom(TestEntity.class)
                .eq(TestEntity::getTenantId, tenant.getId())
                .eq(E_TestEntity.category, category)
                .delete();

        TestEntity testEntity = dao.create(
                new TestEntity()
                        .setCategory(category)
                        .setTenantId(tenant.getId())
                        .setName("租户自有数据1")
        );

        TestEntity publicOne = dao.create(
                new TestEntity()
                        .setCategory(category)
                        .setTenantId(null)
                        .setName("公共有数据1")
        );

        TestEntity one = dao.findOneByQueryObj(
                new TestEntityQueryObj()
                        .setTenantId(tenant.getId())
                        .setContainsPublicData(true)
        );

        Assert.isTrue(one != null && one.getId().equals(testEntity.getId()), "自检测试未通过");

        SelectDao<TestEntity> selectDao = dao.forSelect(
                new TestEntityQueryObj()
                        .setTenantId(tenant.getId())
                        .setContainsPublicData(false)
        );

        String jpql = selectDao.genFinalStatement();

        Assert.isTrue(!jpql.contains(" Order By "), "自检测试异常，出现非预期的排序语句");

        selectDao = dao.forSelect(
                new TestEntityQueryObj()
                        .setTenantId(tenant.getId())
                        .setContainsPublicData(true)
                        .setTenantShared(true)
        );

        jpql = selectDao.genFinalStatement();

        Assert.isTrue(!jpql.contains(" Order By "), "自检测试异常，出现非预期的排序语句");


        selectDao = dao.forSelect(
                new TestEntityQueryObj()
                        .setOrgIdList(Arrays.asList("org-0001"))
                        .setTenantId(tenant.getId())
                        .setContainsPublicData(true)
                        .setContainsOrgPublicData(true)
        );

        jpql = selectDao.genFinalStatement().replace("  ", " ");

        Assert.isTrue(jpql.contains("tenantId IS NULL"), "自检测试异常，未出现预期的tenantId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("tenantId Desc"), "自检测试异常，未出现预期的tenantId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("orgId Desc"), "自检测试异常，未出现预期的orgId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("orgId IS NULL"), "自检测试异常，未出现预期的orgId字段排序语句,{}", jpql);


        selectDao = dao.forSelect(
                new TestEntityQueryObj()
                        .setOrgIdList(Arrays.asList("org-0001"))
                        .setTenantId(tenant.getId())
                        .setContainsPublicData(true)
                        .setContainsOrgPublicData(true)
                        .setTenantShared(true)
                        .setOrgShared(true)
        );

        jpql = selectDao.genFinalStatement().replace("  ", " ");

        Assert.isTrue(!jpql.contains("tenantId Desc"), "自检测试异常，未出现预期的tenantId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("OR tenantShared ="), "自检测试异常，未出现预期的[tenantShared]语句,{}", jpql);
        Assert.isTrue(!jpql.contains("orgId Desc"), "自检测试异常，未出现预期的orgId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("orgId IN"), "自检测试异常，未出现预期的orgId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("orgId IS NULL"), "自检测试异常，未出现预期的orgId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("OR orgShared ="), "自检测试异常，未出现预期的[orgShared]语句,{}", jpql);

        selectDao = dao.forSelect(
                new TestEntityQueryObj()
                     //   .setOrgIdList(Arrays.asList("org-0001"))
                        .setTenantId(tenant.getId())
                        .setContainsPublicData(true)
                        .setContainsOrgPublicData(true)
                        .setTenantShared(true)
                        .setOrgShared(true)
                        .setAllOrgScope(true)
        );

        jpql = selectDao.genFinalStatement().replace("  ", " ");

        Assert.isTrue(!jpql.contains("tenantId Desc"), "自检测试异常，未出现预期的tenantId字段排序语句,{}", jpql);
        Assert.isTrue(jpql.contains("OR tenantShared ="), "自检测试异常，未出现预期的[tenantShared]语句,{}", jpql);
        Assert.isTrue(jpql.contains("tenantId IS NULL"), "自检测试异常，未出现预期的tenantId字段排序语句,{}", jpql);

        Assert.isTrue(!jpql.contains("orgId Desc"), "自检测试异常，未出现预期的orgId字段排序语句,{}", jpql);
        Assert.isTrue(!jpql.contains("OR orgShared ="), "自检测试异常，出现未预期的[orgShared]语句,{}", jpql);

    }

}
