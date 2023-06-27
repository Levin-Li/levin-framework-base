package com.levin.oak.base.services.appclient.req;

import static com.levin.oak.base.entities.EntityConst.*;

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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.AppClient;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppClient.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  删除应用接入
 *  //Auto gen by simple-dao-codegen 2023年6月28日 上午12:45:54
 * 代码生成哈希校验码：[6fe70ae65f4a16861f8d0f1462ba5002]
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppClient.class, alias = E_AppClient.ALIAS)
public class DeleteAppClientReq extends MultiTenantReq {

    private static final long serialVersionUID = -115048882L;


    @Schema(title = L_id + "集合", required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @In(value = E_AppClient.id)
    @NotEmpty
    private String[] idList;

    public DeleteAppClientReq(String... idList) {
        this.idList = idList;
    }

    public DeleteAppClientReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition ="!#user.isSuperAdmin()")
    final boolean eqEditable = true;

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
