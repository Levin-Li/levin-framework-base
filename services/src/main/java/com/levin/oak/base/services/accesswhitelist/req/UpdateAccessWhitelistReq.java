package com.levin.oak.base.services.accesswhitelist.req;

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

import com.levin.oak.base.entities.AccessWhitelist;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AccessWhitelist.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新访问白名单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月9日 下午10:46:12, 代码生成哈希校验码：[d6c2a597f99fafacc36f380f1af83f1d]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AccessWhitelist.class, alias = E_AccessWhitelist.ALIAS)

public class UpdateAccessWhitelistReq extends SimpleUpdateAccessWhitelistReq {

    private static final long serialVersionUID = 101397189L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    public UpdateAccessWhitelistReq() {
    }

    public UpdateAccessWhitelistReq(String id) {
        this.id = id;
    }

    public UpdateAccessWhitelistReq(String id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateAccessWhitelistReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateAccessWhitelistReq of(String id, boolean forceUpdate){
        return new UpdateAccessWhitelistReq(id, forceUpdate);
    }

}
