package com.levin.oak.base.services.notice.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

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

import com.levin.oak.base.entities.Notice;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.Notice.*;

import java.util.Date;
import java.util.List;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
////////////////////////////////////

/**
 * 删除通知
 * //Auto gen by simple-dao-codegen 2022-6-20 16:50:11
 */
@Schema(title = "删除通知")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Notice.class, alias = E_Notice.ALIAS)
public class DeleteNoticeReq extends MultiTenantReq {

    private static final long serialVersionUID = 1394869526L;


    @Schema(title = "id集合")
    @In(value = E_Notice.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteNoticeReq(String... idList) {
        this.idList = idList;
    }

    public DeleteNoticeReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @Schema(title = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
