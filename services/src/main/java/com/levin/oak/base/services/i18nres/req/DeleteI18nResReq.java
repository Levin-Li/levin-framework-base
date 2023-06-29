package com.levin.oak.base.services.i18nres.req;

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

import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_I18nRes.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  删除国际化资源
 *  //@author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:12, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[8360b967048b9785ef0a87b70864f1ea], 请不要修改和删除此行内容。
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
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS)
public class DeleteI18nResReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -1681554652L;


    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Schema(title = L_id + "集合", required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @In(value = E_I18nRes.id)
    @NotEmpty
    private Long[] idList;

    public DeleteI18nResReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteI18nResReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
