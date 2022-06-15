package com.levin.oak.base.biz;

import java.io.InputStream;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * OSS服务
 */
public interface OssService {

    /**
     * 保存
     *
     * @param tenantId
     * @param appId
     * @param fileName       文件名
     * @param sourceSupplier
     * @param sourceInfo
     * @param callback       回调
     * @return URL
     */
    String upload(String tenantId, String appId,
                  String fileName,
                  Supplier<InputStream> sourceSupplier,
                  Map<String, Object> sourceInfo,
                  BiConsumer<String /* URL */, Exception /* 异常 */> callback);

}
