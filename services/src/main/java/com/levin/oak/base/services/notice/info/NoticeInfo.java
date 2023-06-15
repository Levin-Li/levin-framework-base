package com.levin.oak.base.services.notice.info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

/////////////////////////////////////////////////////
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;

////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.Notice.*;

import java.util.Date;
import java.util.List;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
////////////////////////////////////

/**
 * 通知
 *
 * @Author Auto gen by simple-dao-codegen 2022-6-20 16:50:11
 */
@Schema(title = "通知")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class NoticeInfo implements Serializable {

    private static final long serialVersionUID = 1394869526L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "id", required = true)
    String id;


    @InjectVar()
    @Size(max = 128)
    @Schema(title = "所有者ID", description = "所有者ID")
    String ownerId;


    @Size(max = 64)
    @Schema(title = "通知类别")
    String category;


    @Schema(title = "通知内容类型")
    ContentType contentType;


    @Schema(title = "通知内容")
    String content;


    @Schema(title = "过期时间")
    Date expiredDate;


    @Size(max = 64)
    @Schema(title = "机构ID")
    String orgId;


    @InjectVar()
    @Size(max = 128)
    @Schema(title = "租户ID")
    String tenantId;


    @Size(max = 128)
    @Schema(title = "系统域")
    String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称", required = true)
    String name;


    @Size(max = 128)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "拼音名称", description = "拼音，格式Json数组：[全拼,简拼]")
    List<String> pinyinName;


    @InjectVar()
    @Size(max = 128)
    @Schema(title = "创建者")
    String creator;


    @NotNull
    @Schema(title = "创建时间", required = true)
    Date createTime;


    @Schema(title = "更新时间")
    Date lastUpdateTime;


    @Schema(title = "排序代码")
    Integer orderCode;


    @NotNull
    @Schema(title = "是否允许", required = true)
    Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑", required = true)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    String remark;


}
