package com.levin.oak.base.services.setting.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.E_Setting;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.setting.info.SettingInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 系统设置 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */

@Schema(title = "系统设置 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS, resultClass = SettingInfo.class)
public class SettingIdReq extends MultiTenantReq {

    private static final long serialVersionUID = 147875794L;


    @Schema(title = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected String id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
