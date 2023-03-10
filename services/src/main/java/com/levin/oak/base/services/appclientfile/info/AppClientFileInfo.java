package com.levin.oak.base.services.appclientfile.info;


import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 客户端文件
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-20 10:49:23
 */
@Schema(description = "客户端文件")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class AppClientFileInfo implements Serializable {

    private static final long serialVersionUID = -1155395350L;


    @NotNull
    @Schema(description = "id", required = true)
    String id;


    @Size(max = 64)
    @Schema(description = "客户端类型")
    String clientType;


    @Size(max = 64)
    @Schema(description = "文件类型")
    String mimeType;


    @NotBlank
    @Schema(description = "文件路径", required = true)
    String path;


    @Schema(description = "文件内容")
    String content;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "租户ID")
    String tenantId;


    @Size(max = 128)
    @Schema(description = "系统子域")
    String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称", required = true)
    String name;


    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    String pinyinName;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "创建者")
    String creator;


    @NotNull
    @Schema(description = "创建时间", required = true)
    Date createTime;


    @Schema(description = "更新时间")
    Date lastUpdateTime;


    @Schema(description = "排序代码")
    Integer orderCode;


    @NotNull
    @Schema(description = "是否允许", required = true)
    Boolean enable;


    @NotNull
    @Schema(description = "是否可编辑", required = true)
    Boolean editable;


    @Size(max = 512)
    @Schema(description = "备注")
    String remark;


}
