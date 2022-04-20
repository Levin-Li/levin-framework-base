package com.levin.oak.base.services.dict.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.E_Dict;
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
 * 删除字典
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "删除字典")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS)
public class DeleteDictReq extends MultiTenantReq {

    private static final long serialVersionUID = -445779596L;


    @Schema(description = "id集合")
    @In(value = E_Dict.id, require = true)
    @NotEmpty
    private Long[] idList;

    public DeleteDictReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteDictReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
