package com.levin.oak.base;

import com.levin.commons.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static com.levin.commons.utils.IPAddrUtils.isValidIP;

////////////////////////////////////
//自动导入列表
////////////////////////////////////
//import org.junit.jupiter.api.Test;

/**
 *  访问日志测试
 *
 *  @author auto gen by simple-dao-codegen 2022-3-29 23:06:04
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class IpAddrUtilTest {

    String[] defaultHeadNames = {
            "x-real-ip",
            "X-Real-IP",
            "x-forwarded-for",
            "X-Forwarded-For",
            "REMOTE-HOST",
            "proxy-client-ip",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"

    };

    @Test
    public void deleteAccessLogTest() {


        Map<String,String>  head = MapUtils.put("x-real-ip","11.111:11.11,120.235.173.83").build();


        for (String headName : defaultHeadNames) {

            String ip = head.get(headName);

            if (ip == null || ip.trim().length() == 0)
                continue;

            ip = ip.trim();

            String[] sections = ip.split(",");

            String[] split1 = ip.split(":");

            String[] split = ip.split(".");

            for (String section : sections) {

                if (isValidIP(section))
                    System.out.println(section);
            }

        }
    }
}
