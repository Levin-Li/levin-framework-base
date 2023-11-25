package com.levin.oak.base.services.menures.req;

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

import com.levin.oak.base.entities.MenuRes;
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
 * 更新菜单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月24日 下午9:39:11, 代码生成哈希校验码：[dcc14602eb0d39913a38a258e55d3460]，请不要修改和删除此行内容。
 *
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
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)
//默认更新注解
@Update
public class SimpleUpdateMenuResReq extends MultiTenantReq {

    private static final long serialVersionUID = -887712701L;

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件" , hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Size(max = 64)
    @Schema(title = L_parentId)
    String parentId;

    @Schema(title = L_domain , description = D_domain)
    String domain;

    @Schema(title = L_module , description = D_module)
    String module;

    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations)
    String requireAuthorizations;

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

    @Size(max = 1800)
    @Schema(title = L_idPath , description = D_idPath)
    String idPath;

    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;


    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }
}
