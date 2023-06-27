package com.levin.oak.base.services.menures.info;

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
import static com.levin.oak.base.entities.E_MenuRes.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.rbac.MenuItem.*;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.services.menures.info.*;
import java.util.Set;
import java.util.Date;
////////////////////////////////////

/**
 * 菜单
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 上午12:31:51
 * 代码生成哈希校验码：[8056beb5fb8e951b3a07968d6643d46c]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"parent","children",})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class MenuResInfo implements Serializable {

    private static final long serialVersionUID = -887712701L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String id;


    @Size(max = 64)
    @Schema(title = L_parentId )
    String parentId;


    @Size(max = 64)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_domain )
    String domain;


    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations )
    String requireAuthorizations;


    @NotNull
    @Schema(title = L_alwaysShow , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean alwaysShow;


    @Size(max = 64)
    @Schema(title = L_target )
    String target;


    @Schema(title = L_actionType )
    ActionType actionType;


    @Schema(title = L_icon )
    String icon;


    @Schema(title = L_path )
    String path;


    @Size(max = 1800)
    @Schema(title = L_params )
    String params;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_parent )
    MenuResInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_children )
    Set<MenuResInfo> children;


    @Size(max = 1800)
    @Schema(title = L_idPath , description = D_idPath )
    String idPath;


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
