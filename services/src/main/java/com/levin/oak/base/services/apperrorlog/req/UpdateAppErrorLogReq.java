package com.levin.oak.base.services.apperrorlog.req;

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

import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppErrorLog.*;
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
 * 更新应用错误日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:27, 代码生成哈希校验码：[f26569a3b53fc29025aed19371455fda]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)

public class UpdateAppErrorLogReq extends SimpleUpdateAppErrorLogReq {

    private static final long serialVersionUID = 1594864095L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotNull
    @Eq(require = true)
    Long id;

    public UpdateAppErrorLogReq() {
    }

    public UpdateAppErrorLogReq(Long id) {
        this.id = id;
    }

    public UpdateAppErrorLogReq(Long id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateAppErrorLogReq updateIdWhenNotBlank(Long id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateAppErrorLogReq of(Long id, boolean forceUpdate){
        return new UpdateAppErrorLogReq(id, forceUpdate);
    }

}
