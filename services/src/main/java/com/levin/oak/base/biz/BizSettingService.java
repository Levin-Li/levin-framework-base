package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;

import com.levin.oak.base.biz.bo.setting.StatSettingReq;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.services.setting.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConst;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Setting.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.function.Supplier;
////////////////////////////////////

/**
 * 系统设置-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:11, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[b5b7622b0bf0a4ad9f9fa8491f60cb23], 请不要修改和删除此行内容。
 */

@Tag(name = E_Setting.BIZ_NAME + "-业务服务", description = "")
public interface BizSettingService {

    /**
     * 统计系统设置
     *
     * @param req
     * @param paging
     * @return
     */
    StatSettingReq.Result stat(StatSettingReq req, SimplePaging paging);

    /**
     * 更新系统设置
     *
     * @param code
     * @param valueContent
     * @return
     */
    default boolean updateValue(@NotNull String code, String valueContent) {
        return updateValue(null, code, valueContent);
    }

    /**
     * 更新系统设置
     *
     * @param tenantId
     * @param code
     * @param valueContent
     * @return
     */
    boolean updateValue(@Nullable String tenantId, @NotNull String code, String valueContent);

    /**
     * 获取系统设置
     *
     * @param code
     * @return
     */
    default String getValue(@NotNull String code) {
        return getValue(null, code);
    }

    /**
     * 获取系统设置
     *
     * @param tenantId
     * @param code
     * @return
     */
    default String getValue(@Nullable String tenantId, @NotNull String code) {
        return getValue(tenantId, code, null);
    }

    /**
     * 获取系统设置
     *
     * @param tenantId
     * @param code
     * @return
     */
    String getValue(@Nullable String tenantId, @NotNull String code, Supplier<SettingInfo> supplierForCreateIfNotaExist);

}
