package com.levin.oak.base.services.notice.req;

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
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
////////////////////////////////////


/**
 *  新增通知
 *  //Auto gen by simple-dao-codegen 2022-6-20 16:50:11
 */
@Schema(description = "新增通知")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Notice.class, alias = E_Notice.ALIAS)
public class CreateNoticeReq extends MultiTenantReq {

    private static final long serialVersionUID = 1394869526L;


    @Schema(title = "所有者ID", description = "所有者ID"  )
    @InjectVar()
    @Size(max = 128)
    String ownerId;

    @Schema(description = "通知类别"  )
    @Size(max = 64)
    String category;

    @Schema(description = "通知内容类型"  )
    ContentType contentType;

    @Schema(description = "通知内容"  )
    String content;

    @Schema(description = "过期时间"  )
    Date expiredDate;

    @Schema(description = "系统域"  )
    @Size(max = 128)
    String domain;

    @Schema(description = "名称"  , required = true)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = "拼音名称", description = "拼音，格式Json数组：[全拼,简拼]"  )
    @Size(max = 128)
    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    List<String> pinyinName;

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
