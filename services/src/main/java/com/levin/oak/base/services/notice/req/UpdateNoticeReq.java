package com.levin.oak.base.services.notice.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

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

import com.levin.oak.base.entities.Notice;
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
 *  更新通知
 *  Auto gen by simple-dao-codegen 2022-6-20 16:50:11
 */
@Schema(description = "更新通知")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Notice.class, alias = E_Notice.ALIAS)
//默认更新注解
@Update
public class UpdateNoticeReq extends MultiTenantReq {

    private static final long serialVersionUID = 1394869526L;

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition ="!#user.isSuperAdmin()")
    final boolean eqEditable = true;


    @InjectVar()
    @Size(max = 128)
    @Schema(title = "所有者ID", description = "所有者ID")
    String ownerId;

    @Size(max = 64)
    @Schema(description = "通知类别")
    String category;

    @Schema(description = "通知内容类型")
    ContentType contentType;

    @Schema(description = "通知内容")
    String content;

    @Schema(description = "过期时间")
    Date expiredDate;

    @Size(max = 128)
    @Schema(description = "系统域")
    String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    String name;

    @Size(max = 128)
    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "拼音名称", description = "拼音，格式Json数组：[全拼,简拼]")
    List<String> pinyinName;

    @Schema(description = "更新时间")
    Date lastUpdateTime;

    @Schema(description = "排序代码")
    Integer orderCode;

    @Schema(description = "是否允许")
    Boolean enable;

    @Schema(description = "是否可编辑")
    Boolean editable;

    @Size(max = 512)
    @Schema(description = "备注")
    String remark;


    public UpdateNoticeReq(String id) {
        this.id = id;
    }

    public UpdateNoticeReq setIdOnNotBlank(String id){
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
