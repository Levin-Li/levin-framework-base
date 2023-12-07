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
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
import com.levin.oak.base.entities.Area.*;
////////////////////////////////////

/**
 * 更新区域
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月7日 上午11:03:11, 代码生成哈希校验码：[e4a45f6ab4e97d46c751fb46c834511a]，请不要修改和删除此行内容。
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


    @Schema(title = L_code, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String code;

    public UpdateAreaReq() {
    }

    public UpdateAreaReq(String code) {
        this.code = code;
    }

    public UpdateAreaReq(String code, boolean forceUpdate) {
        super(forceUpdate);
        this.code = code;
    }

    public UpdateAreaReq updateCodeWhenNotBlank(String code){
        if(isNotBlank(code)){
            this.code = code;
        }
        return this;
    }

}
