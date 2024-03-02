package com.levin.oak.base.services.org.req;

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

import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Org.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import java.io.Serializable;
import com.levin.commons.dao.domain.StatefulObject;
import com.levin.commons.service.support.InjectConst;
import com.levin.oak.base.services.org.info.*;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.oak.base.entities.Org;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 更新机构
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:06, 代码生成哈希校验码：[dd0c7f70cddaa1d2376b2b381df793c7]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)

public class UpdateOrgReq extends SimpleUpdateOrgReq {

    private static final long serialVersionUID = -1399842458L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    public UpdateOrgReq() {
    }

    public UpdateOrgReq(String id) {
        this.id = id;
    }

    public UpdateOrgReq(String id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateOrgReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateOrgReq of(String id, boolean forceUpdate){
        return new UpdateOrgReq(id, forceUpdate);
    }

}
