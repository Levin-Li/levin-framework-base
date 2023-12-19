package com.levin.oak.base.services.area.req;

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

import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Area.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
import com.levin.oak.base.entities.Area.*;
////////////////////////////////////

/**
 * 更新区域
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午4:20:47, 代码生成哈希校验码：[7a0178e5892cec6d4e68e0c7cd9c424d]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS)

public class UpdateAreaReq extends SimpleUpdateAreaReq {

    private static final long serialVersionUID = -445860277L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    public UpdateAreaReq() {
    }

    public UpdateAreaReq(String id) {
        this.id = id;
    }

    public UpdateAreaReq(String id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateAreaReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateAreaReq of(String id, boolean forceUpdate){
        return new UpdateAreaReq(id, forceUpdate);
    }

}
