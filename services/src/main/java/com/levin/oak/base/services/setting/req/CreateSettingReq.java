package com.levin.oak.base.services.setting.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
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
 *  新增系统设置
 *  //@author Auto gen by simple-dao-codegen, @time: 2023年7月6日 下午3:14:49, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[1b8a17a31f82949cf31aa39e1b3cf453], 请不要修改和删除此行内容。
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
public class CreateSettingReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 147875794L;


    @Schema(title = L_categoryName  , required = true, requiredMode = REQUIRED)
    @NotBlank
    @Size(max = 64)
    String categoryName;

    @Schema(title = L_groupName  )
    @Size(max = 64)
    String groupName;

    @Schema(title = L_code  , required = true, requiredMode = REQUIRED)
    @NotBlank
    @Size(max = 64)
    String code;

    @Schema(title = L_valueType  , required = true, requiredMode = REQUIRED)
    @NotNull
    ValueType valueType;

    @Schema(title = L_valueContent  )
    String valueContent;

    @Schema(title = L_nullable  )
    Boolean nullable;

    @Schema(title = L_inputPlaceholder  )
    @Size(max = 128)
    String inputPlaceholder;

    @Schema(title = L_domain  )
    @Size(max = 128)
    String domain;

    @Schema(title = L_name  , required = true, requiredMode = REQUIRED)
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_creator , hidden = true )
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    String creator;

    @Schema(title = L_createTime , hidden = true )
    //@NotNull
    Date createTime;

    @Schema(title = L_lastUpdateTime , hidden = true )
    Date lastUpdateTime;

    @Schema(title = L_orderCode , hidden = true )
    Integer orderCode;

    @Schema(title = L_enable , hidden = true )
    //@NotNull
    Boolean enable;

    @Schema(title = L_editable , hidden = true )
    //@NotNull
    Boolean editable;

    @Schema(title = L_remark , hidden = true )
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
