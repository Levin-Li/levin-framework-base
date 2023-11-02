package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.commons.service.support.AbstractAppDataInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *  应用数据初始化
 *  @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:21:53, 代码生成哈希校验码：[564d60e51984e416f309893916d15c1c]，请不要修改和删除此行内容。
 *
 */

@Component(PLUGIN_PREFIX + "AppDataInitializer")
@Slf4j
public class AppDataInitializer  {

    @PostConstruct
    protected void initData() {
    }

}
