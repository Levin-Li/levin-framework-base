package com.levin.oak.base.services.appclientfile.req;

import static com.levin.oak.base.entities.EntityConst.*;


import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;
import com.levin.oak.base.services.appclientfile.info.*;
import com.levin.oak.base.entities.AppClientFile;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppClientFile.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  客户端文件 主键通用请求
 *  //Auto gen by simple-dao-codegen 2023年6月28日 上午12:31:50
 *  代码生成哈希校验码：[2c94fcaece38b0299ee441196fc32c96]
 */

@Schema(title =  BIZ_NAME + " 主键通用查询")
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

    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @Eq(require = true)
    //@NotNull
    protected String id;

    public AppClientFileIdReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
