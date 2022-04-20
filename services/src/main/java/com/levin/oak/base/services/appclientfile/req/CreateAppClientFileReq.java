package com.levin.oak.base.services.appclientfile.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////


/**
 *  新增客户端文件
 *  //Auto gen by simple-dao-codegen 2022-4-20 10:49:23
 */
@Schema(description = "新增客户端文件")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS)
public class CreateAppClientFileReq extends MultiTenantReq {

    private static final long serialVersionUID = -1155395350L;


    @Schema(description = "客户端类型"  )
    @Size(max = 64)
    String clientType;

    @Schema(description = "文件类型"  )
    @Size(max = 64)
    String mimeType;

    @Schema(description = "文件路径"  , required = true)
    @NotBlank
    String path;

    @Schema(description = "文件内容"  )
    String content;

    @Schema(description = "系统子域"  )
    @Size(max = 128)
    String domain;

    @Schema(description = "名称"  , required = true)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(description = "拼音，格式：全拼(简拼)"  )
    @Size(max = 128)
    String pinyinName;

    @Schema(description = "创建者" , hidden = true )
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    String creator;

    @Schema(description = "创建时间" , hidden = true )
    //@NotNull
    Date createTime;

    @Schema(description = "更新时间" , hidden = true )
    Date lastUpdateTime;

    @Schema(description = "排序代码" , hidden = true )
    Integer orderCode;

    @Schema(description = "是否允许" , hidden = true )
    //@NotNull
    Boolean enable;

    @Schema(description = "是否可编辑" , hidden = true )
    //@NotNull
    Boolean editable;

    @Schema(description = "备注" , hidden = true )
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
