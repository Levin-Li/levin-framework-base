package com.levin.oak.base.biz.rbac.req;

import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.domain.ServiceReq;
import com.levin.commons.service.support.InjectConsts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Schema(description = "客户端请求")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class AppClientReq implements ServiceReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(description = "租户ID", hidden = true)
    @InjectVar(isRequired = "false")
    protected String tenantId;

    @Schema(description = "客户端类型", hidden = true)
    @InjectVar(InjectConsts.USER_AGENT)
    protected String ua;

    @Schema(description = "客户端类型", hidden = true)
    protected String clientType;

    @Schema(description = "应用ID")
    protected String appId;

}
