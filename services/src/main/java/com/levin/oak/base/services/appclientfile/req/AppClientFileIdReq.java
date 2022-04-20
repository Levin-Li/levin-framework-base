package com.levin.oak.base.services.appclientfile.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.AppClientFile;
import com.levin.oak.base.entities.E_AppClientFile;
import com.levin.oak.base.services.appclientfile.info.AppClientFileInfo;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 客户端文件 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-4-20 10:49:23
 */

@Schema(description = "客户端文件 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS, resultClass = AppClientFileInfo.class)
public class AppClientFileIdReq extends MultiTenantReq {

    private static final long serialVersionUID = -1155395350L;


    @Schema(description = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
