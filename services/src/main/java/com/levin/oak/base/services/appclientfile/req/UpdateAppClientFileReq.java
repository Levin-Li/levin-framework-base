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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

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
 *  更新客户端文件
 *  Auto gen by simple-dao-codegen 2023年6月28日 上午12:43:08
 *  代码生成哈希校验码：[59fd4f99e6a9418517973601c310d3d2]
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS)
//默认更新注解
@Update
public class UpdateAppClientFileReq extends MultiTenantReq {

    private static final long serialVersionUID = -1155395350L;

    @Schema(title = L_id, required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Size(max = 64)
    @Schema(title = L_clientType)
    String clientType;

    @Size(max = 128)
    @Schema(title = L_mimeType)
    String mimeType;

    @NotBlank
    @Schema(title = L_path)
    String path;

    @Schema(title = L_content)
    String content;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;


    public UpdateAppClientFileReq(String id) {
        this.id = id;
    }

    public UpdateAppClientFileReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
        this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}
