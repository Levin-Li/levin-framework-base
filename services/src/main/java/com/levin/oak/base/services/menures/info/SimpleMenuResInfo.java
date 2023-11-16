package com.levin.oak.base.services.menures.info;

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
import static com.levin.oak.base.entities.E_MenuRes.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.MenuRes;
import java.util.Date;
import com.levin.commons.rbac.MenuItem.*;
import java.util.Set;
import com.levin.oak.base.services.menures.info.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 菜单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午11:01:09, 代码生成哈希校验码：[4ff5dfd921cd3da30b3af3ac8ee44d90]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(
        exclude = {
            "parent",
            "children",
        })
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
@Select
public class SimpleMenuResInfo implements Serializable {

    private static final long serialVersionUID = -887712701L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 64)
    @Schema(title = L_parentId)
    String parentId;

    @InjectVar(value = InjectConsts.TENANT_ID)
    @Size(max = 64)
    @Schema(title = L_tenantId)
    String tenantId;

    @Schema(title = L_domain, description = D_domain)
    String domain;

    @Schema(title = L_module, description = D_module)
    String module;

    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations)
    String requireAuthorizations;

    @NotNull
    @Schema(title = L_alwaysShow)
    Boolean alwaysShow;

    @Size(max = 64)
    @Schema(title = L_target)
    String target;

    @Schema(title = L_actionType)
    ActionType actionType;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_path)
    String path;

    @Size(max = 1800)
    @Schema(title = L_params)
    String params;

    // @Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_parent)
    MenuResInfo parent;

    // @Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_children)
    Set<MenuResInfo> children;

    @Size(max = 1800)
    @Schema(title = L_idPath, description = D_idPath)
    String idPath;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName, description = D_pinyinName)
    String pinyinName;
}
