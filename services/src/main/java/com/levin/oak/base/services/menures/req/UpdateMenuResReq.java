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
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.rbac.MenuItem.*;
import java.util.Set;
import com.levin.oak.base.services.menures.info.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新菜单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:28, 代码生成哈希校验码：[328cc0f9a573c18907a7e5f3fa937b08]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)

public class UpdateMenuResReq extends SimpleUpdateMenuResReq {

    private static final long serialVersionUID = -887712701L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    public UpdateMenuResReq() {
    }

    public UpdateMenuResReq(String id) {
        this.id = id;
    }

    public UpdateMenuResReq(String id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateMenuResReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateMenuResReq of(String id, boolean forceUpdate){
        return new UpdateMenuResReq(id, forceUpdate);
    }

}
