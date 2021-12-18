package com.levin.oak.base.services.i18nres.req;

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

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
    import java.util.Date;
////////////////////////////////////

/**
 *  删除国际化资源
 *  //Auto gen by simple-dao-codegen 2021-12-18 11:15:49
 */
@Schema(description = "删除国际化资源")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS)
public class DeleteI18nResReq extends MultiTenantReq {

    private static final long serialVersionUID = -1681554652L;

    @OR
    @Schema(description = "id" , hidden = true)
    private Long id;

    @END
    @Schema(description = "id集合")
    @In(E_I18nRes.id)
    @Validator(expr = "id != null || ( idList != null &&  idList.length > 0)" , promptInfo = "删除国际化资源必须指定ID")
    private Long[] idList;


    public DeleteI18nResReq(Long id) {
        this.id = id;
    }

    public DeleteI18nResReq(Long... idList) {
        this.idList = idList;
    }
}
