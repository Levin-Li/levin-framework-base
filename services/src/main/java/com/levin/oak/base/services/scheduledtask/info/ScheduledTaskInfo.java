package com.levin.oak.base.services.scheduledtask.info;


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
 * 调度任务
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:26
 */
@Schema(title = "调度任务")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class ScheduledTaskInfo implements Serializable {

    private static final long serialVersionUID = -2056389676L;


    @NotNull
    @Schema(title = "id", required = true)
    private String id;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "任务分类", required = true)
    private String category;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "任务组", required = true)
    private String groupName;


    @NotBlank
    @Schema(title = "调度表达式", required = true)
    private String cron;


    @Schema(title = "执行表达式")
    private String invokeExpr;


    @Schema(title = "允许并发执行")
    private Boolean parallelInvoke;


    @Schema(title = "最后一次时间")
    private Date lastInvokedTime;


    @Schema(title = "下一次时间")
    private Date nextInvokeTime;


    @Size(max = 64)
    @Schema(title = "机构ID")
    private String orgId;


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
