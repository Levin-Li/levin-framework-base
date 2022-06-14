package com.levin.oak.base;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.domain.support.TestEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)

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
 *  @author Auto gen by simple-dao-codegen 2022-6-13 19:41:50
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
