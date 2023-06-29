package com.levin.oak.base.services.simpleform.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
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
import static com.levin.oak.base.entities.E_SimpleForm.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
////////////////////////////////////

/**
 * 简单表单
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 下午6:00:40, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[fc5fb12860bbfa0dd9235c607fa2a5f3], 请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"content",})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class SimpleFormInfo implements Serializable {

    private static final long serialVersionUID = 1598335188L;


    @Schema(title = L_commitApi )
    String commitApi;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = REQUIRED)
    String id;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_type , required = true, requiredMode = REQUIRED)
    String type;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category , required = true, requiredMode = REQUIRED)
    String category;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_groupName , required = true, requiredMode = REQUIRED)
    String groupName;


    @Schema(title = L_icon )
    String icon;


    @NotBlank
    @Schema(title = L_path , required = true, requiredMode = REQUIRED)
    String path;


    @InjectVar(domain = "dao",  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations )
    List<String> requireAuthorizations;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_content )
    String content;


    @Size(max = 128)
    @Schema(title = L_domain )
    String domain;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name , required = true, requiredMode = REQUIRED)
    String name;


    @Size(max = 128)
    @Schema(title = L_orgId )
    String orgId;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_creator )
    String creator;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = REQUIRED)
    Date createTime;


    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;


    @Schema(title = L_orderCode )
    Integer orderCode;


    @NotNull
    @Schema(title = L_enable , required = true, requiredMode = REQUIRED)
    Boolean enable;


    @NotNull
    @Schema(title = L_editable , required = true, requiredMode = REQUIRED)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = L_remark )
    String remark;


}
