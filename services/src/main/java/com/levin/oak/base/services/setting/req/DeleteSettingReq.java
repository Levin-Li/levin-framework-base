package com.levin.oak.base.services.setting.req;

import static com.levin.oak.base.entities.EntityConst.*;

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

import javax.annotation.*;
import javax.validation.constraints.*;

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
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Setting.*;
import java.util.Date;
////////////////////////////////////

/**
 *  删除系统设置
 *  //Auto gen by simple-dao-codegen 2023年6月26日 下午6:06:02
 * 代码生成哈希校验码：[b30b4f13bd8a218f8a23bb7728f3c12a]
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
public class DeleteSettingReq extends MultiTenantReq {

    private static final long serialVersionUID = 147875794L;


    @Schema(title = L_id + "集合", required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @In(value = E_Setting.id)
    @NotEmpty
    private String[] idList;

    public DeleteSettingReq(String... idList) {
        this.idList = idList;
    }

    public DeleteSettingReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition ="!#user.isSuperAdmin()")
    final boolean eqEditable = true;

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
