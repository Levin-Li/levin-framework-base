package com.levin.oak.base.services.jobpost.info;


import com.levin.oak.base.entities.JobPost.Type;
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
 * 工作岗位
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:27
 */
@Schema(title = "工作岗位")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class JobPostInfo implements Serializable {

    private static final long serialVersionUID = 1018878847L;


    @NotNull
    @Schema(title = "id", required = true)
    private String id;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "编码", required = true)
    private String code;


    @NotNull
    @Schema(title = "类型", required = true)
    private Type type;


    @Size(max = 64)
    @Schema(title = "租户ID")
    private String tenantId;


    @Size(max = 64)
    @Schema(title = "系统子域")
    private String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称", required = true)
    private String name;


    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(title = "创建者")
    private String creator;


    @NotNull
    @Schema(title = "创建时间", required = true)
    private Date createTime;


    @Schema(title = "更新时间")
    private Date lastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(title = "是否允许", required = true)
    private Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑", required = true)
    private Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;


}
