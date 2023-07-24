package com.levin.oak.base.services.simpleform.req;

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
 * 新增简单表单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月24日 15:26:17, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[8b6a545b9bda9c736213d211c99d7142], 请不要修改和删除此行内容。
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
@TargetOption(entityClass = SimpleForm.class, alias = E_SimpleForm.ALIAS)
public class SimpleCreateSimpleFormReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1598335188L;

    @Schema(title = L_commitApi )
    String commitApi;

    @Schema(title = L_type )
    @NotBlank
    @Size(max = 128)
    String type;

    @Schema(title = L_category )
    @NotBlank
    @Size(max = 128)
    String category;

    @Schema(title = L_groupName )
    @NotBlank
    @Size(max = 128)
    String groupName;

    @Schema(title = L_icon )
    String icon;

    @Schema(title = L_path )
    @NotBlank
    @Size(max = 800)
    String path;

    @Schema(title = L_requireAuthorizations )
    @InjectVar(domain = "dao",  expectBaseType = String.class,  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    List<String> requireAuthorizations;

    @Schema(title = L_content )
    String content;

    @Schema(title = L_domain )
    @Size(max = 128)
    String domain;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 64)
    String name;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等
    }
}
