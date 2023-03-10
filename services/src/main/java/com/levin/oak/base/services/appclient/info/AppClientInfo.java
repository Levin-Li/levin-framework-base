package com.levin.oak.base.services.appclient.info;


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
 * 应用接入
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-3 0:55:04
 */
@Schema(description = "应用接入")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class AppClientInfo implements Serializable {

    private static final long serialVersionUID = -115048882L;


    @NotNull
    @Schema(description = "id", required = true)
    private String id;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "应用ID", required = true)
    private String appId;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "应用密钥", required = true)
    private String appSecret;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "租户ID")
    private String tenantId;


    @Size(max = 128)
    @Schema(description = "系统子域")
    private String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称", required = true)
    private String name;


    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "创建者")
    private String creator;


    @NotNull
    @Schema(description = "创建时间", required = true)
    private Date createTime;


    @Schema(description = "更新时间")
    private Date lastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(description = "是否允许", required = true)
    private Boolean enable;


    @NotNull
    @Schema(description = "是否可编辑", required = true)
    private Boolean editable;


    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


}
