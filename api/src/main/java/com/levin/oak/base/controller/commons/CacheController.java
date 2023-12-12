package com.levin.oak.base.controller.commons;

import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.EntityConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;


// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

/**
 * 一次性返回所有的枚举变量
 */
@RestController(PLUGIN_PREFIX + "CacheController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "CacheController", matchIfMissing = true)
@RequestMapping(API_PATH + "cache")

@Slf4j
@Valid
@ResAuthorize(domain = ID, type = EntityConst.SYS_TYPE_NAME + "-")
@Tag(name = "缓存", description = "缓存服务")
@MenuResTag(false)
public class CacheController extends BaseController {

    @Autowired
    CacheManager cacheManager;

    @PostMapping("evict")
    @Operation(summary = "清除缓存", description = "返回清除成功的缓存Key")
    public ApiResp<List<String>> evict(@NotNull @RequestBody List<String> cacheKeys) {

        List<String> result = new ArrayList<>(cacheKeys.size());

        cacheManager.getCacheNames().parallelStream().forEach(cacheName -> {

            cacheKeys.stream().filter(cacheKey -> cacheKey.startsWith(cacheName)).forEach(cacheKey -> {

                Cache cache = cacheManager.getCache(cacheName);

                if (cache == null) {
                    return;
                }

                if (cache.evictIfPresent(cacheKey)) {
                    result.add(cacheKey);
                }
            });
        });

        return ApiResp.ok(result);
    }

}
