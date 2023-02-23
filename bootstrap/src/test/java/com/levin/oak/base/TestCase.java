package com.levin.oak.base;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.domain.support.TestEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import javax.annotation.Resource;

import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.simpleapi.SimpleApiService;
import com.levin.oak.base.services.appclient.AppClientService;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.scheduledlog.ScheduledLogService;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.dict.DictService;
import com.levin.oak.base.services.apperrorlog.AppErrorLogService;
import com.levin.oak.base.services.notice.NoticeService;
import com.levin.oak.base.services.appclientfile.AppClientFileService;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.accesslog.AccessLogService;
import com.levin.oak.base.services.scheduledtask.ScheduledTaskService;
import com.levin.oak.base.services.noticeprocesslog.NoticeProcessLogService;
import com.levin.oak.base.services.org.OrgService;
import com.levin.oak.base.services.jobpost.JobPostService;
import com.levin.oak.base.services.i18nres.I18nResService;
import com.levin.oak.base.services.area.AreaService;
import com.levin.oak.base.services.simplepage.SimplePageService;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.simpleform.SimpleFormService;

import com.levin.oak.base.controller.role.RoleController;
import com.levin.oak.base.controller.simpleapi.SimpleApiController;
import com.levin.oak.base.controller.appclient.AppClientController;
import com.levin.oak.base.controller.tenant.TenantController;
import com.levin.oak.base.controller.scheduledlog.ScheduledLogController;
import com.levin.oak.base.controller.setting.SettingController;
import com.levin.oak.base.controller.dict.DictController;
import com.levin.oak.base.controller.apperrorlog.AppErrorLogController;
import com.levin.oak.base.controller.notice.NoticeController;
import com.levin.oak.base.controller.appclientfile.AppClientFileController;
import com.levin.oak.base.controller.user.UserController;
import com.levin.oak.base.controller.accesslog.AccessLogController;
import com.levin.oak.base.controller.scheduledtask.ScheduledTaskController;
import com.levin.oak.base.controller.noticeprocesslog.NoticeProcessLogController;
import com.levin.oak.base.controller.org.OrgController;
import com.levin.oak.base.controller.jobpost.JobPostController;
import com.levin.oak.base.controller.i18nres.I18nResController;
import com.levin.oak.base.controller.area.AreaController;
import com.levin.oak.base.controller.simplepage.SimplePageController;
import com.levin.oak.base.controller.menures.MenuResController;
import com.levin.oak.base.controller.simpleform.SimpleFormController;

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)

@SpringBootTest //自动侦测并加载@SpringBootApplication或@SpringBootConfiguration中的配置，默认web环境为MOCK，不监听任务端口
//@DataRedisTest //测试对Redis操作，自动扫描被@RedisHash描述的类，并配置Spring Data Redis的库
//@DataJpaTest //测试基于JPA的数据库操作，同时提供了TestEntityManager替代JPA的EntityManager

//@DataJdbcTest //测试基于Spring Data JDBC的数据库操作
//@JsonTest //测试JSON的序列化和反序列化
//@WebMvcTest //测试Spring MVC中的controllers
//@WebFluxTest //测试Spring WebFlux中的controllers
//@RestClientTest //测试对REST客户端的操作
//@DataLdapTest //测试对LDAP的操作
//@DataMongoTest //测试对MongoDB的操作
//@DataNeo4jTest //测试对Neo4j的操作
/**
 *  测试类
 *  @author Auto gen by simple-dao-codegen 2023-2-22 18:58:29
 */
public class TestCase {

    @Autowired
    SimpleDao simpleDao;

    @BeforeAll
    public static void beforeAll() throws Exception {
    }

    @AfterAll
    public static void afterAll() throws Exception {
    }

    @BeforeEach
    public void beforeEach() throws Exception {
    }

    @AfterEach
    public void afterEach() throws Exception {
    }


    @Test
    public void test() {

        List<Object> objects = simpleDao.selectFrom(TestEntity.class).find();

    }

}
