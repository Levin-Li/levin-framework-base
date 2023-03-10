package com.levin.oak.base.services.commons.req;

import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.IsNull;
import com.levin.commons.dao.annotation.logic.OR;
import com.levin.commons.dao.annotation.misc.Case;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.E_AbstractMultiTenantObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;


/**
 * 多租户查询对象
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */
@Schema(description = "多租户查询对象")
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class MultiTenantReq
        extends BaseReq
        implements MultiTenantObject {

    @Schema(description = "租户ID", hidden = true)
    @InjectVar(value = InjectConsts.TENANT_ID
            , isOverride = InjectVar.SPEL_PREFIX + "!#" + InjectConsts.IS_SUPER_ADMIN // 如果不是超级管理员, 那么覆盖必须的
            , isRequired = InjectVar.SPEL_PREFIX + "!#" + InjectConsts.IS_SUPER_ADMIN // 如果不是超级管理员，那么值是必须的
    )
    @OR(autoClose = true)
    @Eq
    @IsNull(condition = "#_this.isContainsPublicData()", desc = "如果是公共数据，允许包括非该租户的数据") //如果是公共数据，允许包括非该租户的数据
    @OrderBy(order = -1, condition = "#_this.isContainsPublicData()", type = OrderBy.Type.Desc,
            cases = @Case(column = "", whenOptions =
            @Case.When(whenExpr = E_AbstractMultiTenantObject.tenantId + " IS NULL", thenExpr = "0"), elseExpr = "1"),
            desc = "注解的作用是包含公共数据时，让本租户的数据在前")
    protected String tenantId;

    /**
     * 是否为公共数据
     *
     * @return
     */
    @Schema(description = "请求是否包含公共数据", hidden = true)
    public boolean isContainsPublicData() {
        return false;
    }

    public <T extends MultiTenantReq> T setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return (T) this;
    }

}
