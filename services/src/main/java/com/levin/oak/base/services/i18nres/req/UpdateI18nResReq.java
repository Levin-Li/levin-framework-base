package com.levin.oak.base.services.i18nres.req;

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

import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_I18nRes.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import java.util.Date;
import com.levin.commons.dao.domain.MultiTenantSharedObject;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新国际化资源
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月27日 下午3:55:27, 代码生成哈希校验码：[4474eafd1c36ee57df243d94cf37645f]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS)

public class UpdateI18nResReq extends SimpleUpdateI18nResReq {

    private static final long serialVersionUID = -1681554652L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotNull
    @Eq(require = true)
    Long id;

    public UpdateI18nResReq() {
    }

    public UpdateI18nResReq(Long id) {
        this.id = id;
    }

    public UpdateI18nResReq(Long id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateI18nResReq updateIdWhenNotBlank(Long id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateI18nResReq of(Long id, boolean forceUpdate){
        return new UpdateI18nResReq(id, forceUpdate);
    }

}
