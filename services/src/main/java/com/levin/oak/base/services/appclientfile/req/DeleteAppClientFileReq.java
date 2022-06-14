package com.levin.oak.base.services.appclientfile.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.AppClientFile;
import com.levin.oak.base.entities.E_AppClientFile;
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
 * 删除客户端文件
 * //Auto gen by simple-dao-codegen 2022-4-20 10:49:23
 */
@Schema(description = "删除客户端文件")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS)
public class DeleteAppClientFileReq extends MultiTenantReq {

    private static final long serialVersionUID = -1155395350L;


    @Schema(description = "id集合")
    @In(value = E_AppClientFile.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteAppClientFileReq(String... idList) {
        this.idList = idList;
    }

    public DeleteAppClientFileReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#user.isSuperAdmin()")
    final boolean eqEditable = true;

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
