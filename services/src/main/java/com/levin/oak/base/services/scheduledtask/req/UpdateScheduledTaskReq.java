package com.levin.oak.base.services.scheduledtask.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.oak.base.entities.E_ScheduledTask;
import com.levin.oak.base.entities.ScheduledTask;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新调度任务
 * Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "更新调度任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS)
//默认更新注解
@Update
public class UpdateScheduledTaskReq extends MultiTenantReq {

    private static final long serialVersionUID = -2056389676L;

    @Schema(description = "id", required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#user.isSuperAdmin()")
    final boolean eqEditable = true;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "任务分类")
    private String category;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "任务组")
    private String groupName;

    @NotBlank
    @Schema(description = "调度表达式")
    private String cron;

    @Schema(description = "执行表达式")
    private String invokeExpr;

    @Schema(description = "允许并发执行")
    private Boolean parallelInvoke;

    @Schema(description = "最后一次时间")
    private Date lastInvokedTime;

    @Schema(description = "下一次时间")
    private Date nextInvokeTime;

    @Size(max = 64)
    @Schema(description = "系统子域")
    private String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    private String name;

    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


    public UpdateScheduledTaskReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }

}
