package com.levin.oak.base.services.whitelist.req;

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

import com.levin.oak.base.entities.Whitelist;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Whitelist.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新白名单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午8:20:51, 代码生成哈希校验码：[7ef57f4330624c9bb0f3b70871b5ed1c]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Whitelist.class, alias = E_Whitelist.ALIAS)

public class UpdateWhitelistReq extends SimpleUpdateWhitelistReq {

    private static final long serialVersionUID = 1491943753L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotNull
    @Eq(require = true)
    Long id;

    public UpdateWhitelistReq() {
    }

    public UpdateWhitelistReq(Long id) {
        this.id = id;
    }

    public UpdateWhitelistReq(Long id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateWhitelistReq updateIdWhenNotBlank(Long id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateWhitelistReq of(Long id, boolean forceUpdate){
        return new UpdateWhitelistReq(id, forceUpdate);
    }

}
