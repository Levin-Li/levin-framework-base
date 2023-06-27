package com.levin.oak.base.services.simpleapi.info;

import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
/////////////////////////////////////////////////////
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_SimpleApi.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.SimpleApi.*;
import java.util.Date;
////////////////////////////////////

/**
 * 简单动态接口
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 上午12:31:49
 * 代码生成哈希校验码：[ce5880e9f5cf8300f06bcda8893520ba]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class SimpleApiInfo implements Serializable {

    private static final long serialVersionUID = 1021385738L;


    @Size(max = 16)
    @Schema(title = L_methods , description = D_methods )
    String methods;


    @NotNull
    @Schema(title = L_language , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Language language;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String id;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_type , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String type;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String category;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_groupName , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String groupName;


    @Schema(title = L_icon )
    String icon;


    @NotBlank
    @Schema(title = L_path , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String path;


    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations )
    String requireAuthorizations;


    @Schema(title = L_content )
    String content;


    @Size(max = 64)
    @Schema(title = L_orgId )
    String orgId;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_domain )
    String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String name;


    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName )
    String pinyinName;


    @Size(max = 128)
    @Schema(title = L_creator )
    String creator;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date createTime;


    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;


    @Schema(title = L_orderCode )
    Integer orderCode;


    @NotNull
    @Schema(title = L_enable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean enable;


    @NotNull
    @Schema(title = L_editable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = L_remark )
    String remark;


}
