package com.levin.oak.base.services.simpleform.req;

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

import com.levin.oak.base.entities.SimpleForm;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_SimpleForm.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
////////////////////////////////////

/**
 * 更新简单表单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月24日 15:26:17, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[6da98841c2b352889193fa5ed1a318ec], 请不要修改和删除此行内容。
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
@TargetOption(entityClass = SimpleForm.class, alias = E_SimpleForm.ALIAS)
//默认更新注解
@Update
public class SimpleUpdateSimpleFormReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1598335188L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Schema(title = L_commitApi)
    String commitApi;

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
    @Size(max = 800)
    @Schema(title = L_path)
    String path;

    @InjectVar(domain = "dao",  expectBaseType = String.class,  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations)
    List<String> requireAuthorizations;

    @Schema(title = L_content)
    String content;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;


    public SimpleUpdateSimpleFormReq(String id) {
        this.id = id;
    }

    public SimpleUpdateSimpleFormReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
        this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }
}
