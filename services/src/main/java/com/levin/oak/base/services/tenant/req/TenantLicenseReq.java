package com.levin.oak.base.services.tenant.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.Gt;
import com.levin.commons.dao.annotation.Where;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.services.commons.req.BaseReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

@Schema(description = "租户许可变更请求")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS)
//默认更新注解
public class TenantLicenseReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;

    @Schema(description = "ID", required = true)
    @NotNull
    @NotBlank
    @Eq(require = true)
    private String id;

    @Schema(description = "剩余坐席数")
    @Update(paramExpr = "${_name} + ${_val}", require = true)
    //更新后的
    @Where(paramExpr = E_Tenant.licenseCnt + " >= ( ${_name} + ${_val} ) AND ( ${_name} + ${_val} ) >=0 ", require = true)
    private Integer remainingLicenseCnt;

    @Schema(description = "到期时间")
    @Gt
    private final Date licenseExpire = new Date();

    @Schema(description = "更新时间")
    @Update
    private Date lastUpdateTime;

    @Schema(description = "是否允许")
    private final Boolean enable = true;

    public TenantLicenseReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }

}
