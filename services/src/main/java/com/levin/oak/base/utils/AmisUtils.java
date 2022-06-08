package com.levin.oak.base.utils;

import cn.hutool.core.io.NioUtil;
import com.levin.commons.service.support.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public abstract class AmisUtils {

    /**
     * 读取amis admin模块资源
     *
     * @param url
     * @return
     */
    public static String readAdminClassPathResource(String url, String... paths) {

        if (paths == null || paths.length == 0) {
            paths = new String[]{"page", "form"};
        }

        for (String path : paths) {

            //本地路径替换
            url = url.replace("/admin/", "/admin/amis/" + path + "/");

            org.springframework.core.io.Resource resource = SpringContextHolder.getResourceLoader().getResource("classpath:/templates/" + url + ".json");

            if (resource != null
                    && resource.isReadable()) {
                try {
                    return NioUtil.read(resource.readableChannel(), Charset.forName("utf-8"));
                } catch (Exception e) {
                    log.warn("Read " + url + " error", e);
                }
            }

        }

        return null;
    }

}
