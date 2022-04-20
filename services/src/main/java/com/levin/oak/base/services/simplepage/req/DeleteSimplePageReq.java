package com.levin.oak.base.services.simplepage.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.E_SimplePage;
import com.levin.oak.base.entities.SimplePage;
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
 * 删除简单页面
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "删除简单页面")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimplePage.class, alias = E_SimplePage.ALIAS)
public class DeleteSimplePageReq extends MultiTenantReq {

    private static final long serialVersionUID = 1598619295L;


    @Schema(description = "id集合")
    @In(value = E_SimplePage.id, require = true)
    @NotEmpty
    private Long[] idList;

    public DeleteSimplePageReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteSimplePageReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
