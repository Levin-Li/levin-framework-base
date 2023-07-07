package com.levin.oak.base.biz;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * OSS服务
 */
public interface BizFileStorageService {

    /**
     * 存储文件
     *
     * @param tenantId
     * @param appId
     * @param multipartFile
     * @return URL
     */
    String upload(String tenantId, String appId, MultipartFile multipartFile);

    /**
     * 存储文件
     *
     * @param tenantId
     * @param appId
     * @param fileName       文件名
     * @param sourceSupplier
     * @param sourceExtInfo
     * @return URL
     */
    String upload(String tenantId, String appId, String fileName, Supplier<InputStream> sourceSupplier,
                  Map<String, Object> sourceExtInfo);

    /**
     * 文件是否存在
     *
     * @param url
     * @return
     */
//    boolean exists(String url);

    /**
     * 删除文件
     *
     * @param url
     * @return
     */
//    boolean delete(String url);
}
