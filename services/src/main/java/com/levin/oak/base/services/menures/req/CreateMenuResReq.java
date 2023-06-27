package com.levin.oak.base.services.menures.req;

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
import static com.levin.oak.base.entities.E_MenuRes.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.rbac.MenuItem.*;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.services.menures.info.*;
import java.util.Set;
import java.util.Date;
////////////////////////////////////


/**
 *  新增菜单
 *  //Auto gen by simple-dao-codegen 2023年6月28日 上午12:43:10
 * 代码生成哈希校验码：[f01d3e49c885b559c06c1129f3335758]
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
public class CreateMenuResReq extends MultiTenantReq {

    private static final long serialVersionUID = -887712701L;


    @Schema(title = L_parentId  )
    @Size(max = 64)
    String parentId;

    @Schema(title = L_domain  )
    @Size(max = 128)
    String domain;

    @Schema(title = L_requireAuthorizations  )
    @Size(max = 1800)
    String requireAuthorizations;

    @Schema(title = L_alwaysShow  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    Boolean alwaysShow;

    @Schema(title = L_target  )
    @Size(max = 64)
    String target;

    @Schema(title = L_actionType  )
    ActionType actionType;

    @Schema(title = L_icon  )
    String icon;

    @Schema(title = L_path  )
    String path;

    @Schema(title = L_params  )
    @Size(max = 1800)
    String params;



    @Schema(title = L_idPath , description = D_idPath  )
    @Size(max = 1800)
    String idPath;

    @Schema(title = L_name  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = L_pinyinName , description = D_pinyinName  )
    @Size(max = 128)
    String pinyinName;

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
