package com.levin.oak.base.services.simpleapi.req;

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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.SimpleApi;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_SimpleApi.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.SimpleApi.*;
import java.util.Date;
////////////////////////////////////

/**
 *  更新简单动态接口
 *  Auto gen by simple-dao-codegen 2023年6月28日 上午12:43:07
 *  代码生成哈希校验码：[2221188536aec7bf92d56d770967c336]
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
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS)
//默认更新注解
@Update
public class UpdateSimpleApiReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1021385738L;

    @Schema(title = L_id, required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Size(max = 16)
    @Schema(title = L_methods , description = D_methods)
    String methods;

    @Schema(title = L_language)
    Language language;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_type)
    String type;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category)
    String category;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_groupName)
    String groupName;

    @Schema(title = L_icon)
    String icon;

    @NotBlank
    @Schema(title = L_path)
    String path;

    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations)
    String requireAuthorizations;

    @Schema(title = L_content)
    String content;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;


    public UpdateSimpleApiReq(String id) {
        this.id = id;
    }

    public UpdateSimpleApiReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
        this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}
