package com.levin.oak.base.services.menures.req;

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
import static com.levin.oak.base.entities.E_MenuRes.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.MenuRes;
import java.util.Date;
import com.levin.commons.rbac.MenuItem.*;
import java.util.Set;
import com.levin.oak.base.services.menures.info.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 新增菜单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月24日 下午9:39:11, 代码生成哈希校验码：[e2273f5340d2dc60755e31c25ddea8cd]，请不要修改和删除此行内容。
 *
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
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)
public class SimpleCreateMenuResReq extends MultiTenantReq {

    private static final long serialVersionUID = -887712701L;

    @Schema(title = L_parentId )
    @Size(max = 64)
    String parentId;

    @Schema(title = L_domain , description = D_domain )
    String domain;

    @Schema(title = L_module , description = D_module )
    String module;

    @Schema(title = L_requireAuthorizations )
    @Size(max = 1800)
    String requireAuthorizations;

    @Schema(title = L_alwaysShow )
    @NotNull
    Boolean alwaysShow;

    @Schema(title = L_target )
    @Size(max = 64)
    String target;

    @Schema(title = L_actionType )
    ActionType actionType;

    @Schema(title = L_icon )
    String icon;

    @Schema(title = L_path )
    String path;

    @Schema(title = L_params )
    @Size(max = 1800)
    String params;

    @Schema(title = L_idPath , description = D_idPath )
    @Size(max = 1800)
    String idPath;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = L_pinyinName , description = D_pinyinName )
    @Size(max = 128)
    String pinyinName;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等
    }
}
