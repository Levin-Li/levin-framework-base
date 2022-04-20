package com.levin.oak.base.services.appclient.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.AppClient;
import com.levin.oak.base.entities.E_AppClient;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
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
 * 删除应用接入
 * //Auto gen by simple-dao-codegen 2022-4-3 0:55:04
 */
@Schema(description = "删除应用接入")
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


    @Schema(description = "id集合")
    @In(value = E_AppClient.id, require = true)
    @NotEmpty
    private Long[] idList;

    public DeleteAppClientReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteAppClientReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
