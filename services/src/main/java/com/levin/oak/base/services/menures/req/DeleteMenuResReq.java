package com.levin.oak.base.services.menures.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.E_MenuRes;
import com.levin.oak.base.entities.MenuRes;
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
 * 删除菜单
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:37
 */
@Schema(title = "删除菜单")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)
public class DeleteMenuResReq extends MultiTenantReq {

    private static final long serialVersionUID = -887712701L;


    @Schema(title = "id集合")
    @In(value = E_MenuRes.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteMenuResReq(String... idList) {
        this.idList = idList;
    }

    public DeleteMenuResReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
