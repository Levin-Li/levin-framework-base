package com.levin.oak.base.services.commons.req;

import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;


/**
 * 通用请求对象
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */
@Schema(title = "通用请求对象", description = "支持租户，部门的注入")
@Data
@Accessors(chain = true)
@FieldNameConstants
public final class CommonReq
        extends MultiTenantReq
        implements OrganizedObject {

    @Schema(description = "组织ID", hidden = true)
    @InjectVar(value = InjectConsts.ORG_ID, isRequired = "false")
    private String orgId;

    @Schema(description = "是否包含公共数据")
    @Ignore
    private boolean isContainsPublicData = true;

}
