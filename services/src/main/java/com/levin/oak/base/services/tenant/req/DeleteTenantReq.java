package com.levin.oak.base.services.tenant.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.services.commons.req.BaseReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 删除租户
 * //Auto gen by simple-dao-codegen 2022-3-25 18:38:00
 */
@Schema(title = "删除租户")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS)
public class DeleteTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;


    @Schema(title = "ID集合")
    @In(value = E_Tenant.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteTenantReq(String... idList) {
        this.idList = idList;
    }

    public DeleteTenantReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
