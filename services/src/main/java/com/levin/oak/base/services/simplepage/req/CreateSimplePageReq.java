package com.levin.oak.base.services.simplepage.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;

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
import static com.levin.oak.base.entities.E_SimplePage.*;
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
 *  新增简单页面
 *  //@author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:12, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[e55df19100620537cfd89a34d98b6154], 请不要修改和删除此行内容。
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
@TargetOption(entityClass = SimplePage.class, alias = E_SimplePage.ALIAS)
public class CreateSimplePageReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1598619295L;


    @Schema(title = L_type  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String type;

    @Schema(title = L_category  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String category;

    @Schema(title = L_groupName  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String groupName;

    @Schema(title = L_icon  )
    String icon;

    @Schema(title = L_path  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    String path;

    @Schema(title = L_requireAuthorizations  )
    @InjectVar(domain = "dao",  expectBaseType = String.class,  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    List<String> requireAuthorizations;

    @Schema(title = L_content  )
    String content;

    @Schema(title = L_domain  )
    @Size(max = 128)
    String domain;

    @Schema(title = L_name  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
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
