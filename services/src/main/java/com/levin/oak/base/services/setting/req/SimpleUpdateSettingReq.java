package com.levin.oak.base.services.setting.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Setting.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.levin.oak.base.entities.Setting.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 更新系统设置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:40, 代码生成哈希校验码：[bc652397db988a00783528a84e978359]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
//默认更新注解
@Update
public class SimpleUpdateSettingReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 147875794L;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_categoryName)
    String categoryName;

    @Size(max = 64)
    @Schema(title = L_groupName)
    String groupName;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_code)
    String code;

    @Schema(title = L_valueType)
    ValueType valueType;

    @Schema(title = L_valueContent)
    String valueContent;

    @Schema(title = L_nullable)
    Boolean nullable;

    @Size(max = 128)
    @Schema(title = L_inputPlaceholder)
    String inputPlaceholder;

    @Size(max = 128)
    @Schema(title = L_domain , description = D_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Eq(desc = "乐观锁更新条件")
    @Update(incrementMode = true, paramExpr = "1", condition = "", desc = "乐观锁版本号 + 1")
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

        @Schema(title = L_lastUpdateTime)
        Date lastUpdateTime;


    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }
}
