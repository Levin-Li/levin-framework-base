package com.levin.oak.base.services.org.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.E_Org;
import com.levin.oak.base.entities.Org;
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
 * 删除机构
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "删除机构")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
public class DeleteOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;


    @Schema(description = "id集合")
    @In(value = E_Org.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteOrgReq(String... idList) {
        this.idList = idList;
    }

    public DeleteOrgReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
