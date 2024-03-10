package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.DynamicVerifyCodeCfg;
import com.levin.oak.base.biz.bo.dynamicverifycodecfg.*;
import com.levin.oak.base.services.dynamicverifycodecfg.*;
import com.levin.oak.base.services.dynamicverifycodecfg.req.*;
import com.levin.oak.base.services.dynamicverifycodecfg.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.oak.base.entities.VerifyCodeType;
import java.io.Serializable;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  动态验证码配置-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月9日 下午10:46:13, 代码生成哈希校验码：[781338c52ea0cc1ab075bc8cfa90c7d2]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_DynamicVerifyCodeCfg.BIZ_NAME + "-业务服务", description = "")
public interface BizDynamicVerifyCodeCfgService {

    default List<String> parseItem(String rules) {

        if (!StringUtils.hasText(rules)) {
            return Collections.emptyList();
        }

        return Stream.of(rules.split("[,，\\t\\s\\n\\r|]")).filter(StringUtils::hasText).collect(Collectors.toList());
    }

    default boolean anyMatch(Supplier<String> valueSupplier, String rules) {

        List<String> items = parseItem(rules);

        if (items.isEmpty()) {
            return true;
        }

        String value = valueSupplier.get();

        //如果有规则但却没有值，则直接返回false
        if (!StringUtils.hasText(value)) {
            return false;
        }

        return items.stream().anyMatch(item -> PatternMatchUtils.simpleMatch(item, value));
    }

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatDynamicVerifyCodeCfgReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatDynamicVerifyCodeCfgReq.Result stat(StatDynamicVerifyCodeCfgReq req, Paging paging);
}
