package com.levin.oak.base.cache;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.service.support.SpringCacheResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

/**
 * 模块缓存解析器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月29日 上午1:09:23, 代码生成哈希校验码：[8325f4b720c723a51d276affa643ecf0]，请不要修改和删除此行内容。
 *
 */

@Slf4j
@Component(PLUGIN_PREFIX + "ModuleSpringCacheResolver")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleSpringCacheResolver", matchIfMissing = true)
public class ModuleSpringCacheResolver extends SpringCacheResolver implements ModuleCacheService {

}