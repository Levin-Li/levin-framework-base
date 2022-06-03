package com.levin.oak.base.services.commons.req;

import com.levin.commons.dao.annotation.Ignore;
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
public class TenantShareReq
        extends MultiTenantReq {

    @Schema(description = "是否包含公共数据")
    @Ignore
    private boolean isContainsPublicData = true;

}
