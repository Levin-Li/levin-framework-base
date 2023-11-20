package com.levin.oak.base.services.dict.req;

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

import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Dict.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import java.util.List;
import java.util.Date;
import com.levin.oak.base.entities.Dict.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.DefaultJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 更新字典
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月20日 下午10:19:20, 代码生成哈希校验码：[d03dc24b33a43c6d3b8df2539a239fef]，请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS)
// 默认更新注解
@Update
public class SimpleUpdateDictReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445779596L;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!(#" + InjectConst.IS_SUPER_ADMIN + "?:false)")
    final boolean eqEditable = true;

    @Schema(title = L_type)
    Type type;

    @NotBlank
    @Size(max = 256)
    @Schema(title = L_code)
    String code;

    @InjectVar(
            domain = "dao",
            isRequired = "false",
            converter = DefaultJsonConverter.class,
            expectBaseType = String.class)
    @Schema(title = L_itemList, description = D_itemList)
    List<Item> itemList;

    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain, description = D_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @JsonIgnore(value = true)
    @Eq(desc = "乐观锁更新条件")
    @Update(incrementMode = true, paramExpr = "1", condition = "", desc = "乐观锁版本号 + 1")
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @PostConstruct
    public void preUpdate() {
        // @todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }
}
