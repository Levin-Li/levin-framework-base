package com.levin.oak.base;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.domain.support.TestEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.levin.oak.base.services.appclientfile.AppClientFileService;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.accesslog.AccessLogService;
import com.levin.oak.base.services.scheduledtask.ScheduledTaskService;
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
import com.levin.oak.base.controller.appclientfile.AppClientFileController;
import com.levin.oak.base.controller.user.UserController;
import com.levin.oak.base.controller.accesslog.AccessLogController;
import com.levin.oak.base.controller.scheduledtask.ScheduledTaskController;
import com.levin.oak.base.controller.org.OrgController;
import com.levin.oak.base.controller.jobpost.JobPostController;
import com.levin.oak.base.controller.i18nres.I18nResController;
import com.levin.oak.base.controller.area.AreaController;
import com.levin.oak.base.controller.simplepage.SimplePageController;
import com.levin.oak.base.controller.menures.MenuResController;
import com.levin.oak.base.controller.simpleform.SimpleFormController;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)

@SpringBootTest //?????????????????????@SpringBootApplication???@SpringBootConfiguration?????????????????????web?????????MOCK????????????????????????
//@DataRedisTest //?????????Redis????????????????????????@RedisHash????????????????????????Spring Data Redis??????
//@DataJpaTest //????????????JPA????????????????????????????????????TestEntityManager??????JPA???EntityManager

//@DataJdbcTest //????????????Spring Data JDBC??????????????????
//@JsonTest //??????JSON???????????????????????????
//@WebMvcTest //??????Spring MVC??????controllers
//@WebFluxTest //??????Spring WebFlux??????controllers
//@RestClientTest //?????????REST??????????????????
//@DataLdapTest //?????????LDAP?????????
//@DataMongoTest //?????????MongoDB?????????
//@DataNeo4jTest //?????????Neo4j?????????
/**
 *  ?????????
 *  @author Auto gen by simple-dao-codegen 2022-6-14 9:26:30
 */
public class TestCase {

    @Resource
    SimpleDao simpleDao;


    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }

    @Test
    public void test() {

        List<Object> objects = simpleDao.selectFrom(TestEntity.class).find();

    }

}
