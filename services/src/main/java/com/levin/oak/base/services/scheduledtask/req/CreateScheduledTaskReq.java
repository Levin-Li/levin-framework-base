package com.levin.oak.base.services.scheduledtask.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.E_ScheduledTask;
import com.levin.oak.base.entities.ScheduledTask;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增调度任务
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增调度任务")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS)
public class CreateScheduledTaskReq extends MultiTenantReq {

    private static final long serialVersionUID = -2056389676L;


    @Schema(title = "任务分类", required = true)
    @NotBlank
    @Size(max = 64)
    private String category;

    @Schema(title = "任务组", required = true)
    @NotBlank
    @Size(max = 64)
    private String groupName;

    @Schema(title = "调度表达式", required = true)
    @NotBlank
    private String cron;

    @Schema(title = "执行表达式")
    private String invokeExpr;

    @Schema(title = "允许并发执行")
    private Boolean parallelInvoke;

    @Schema(title = "最后一次时间")
    private Date lastInvokedTime;

    @Schema(title = "下一次时间")
    private Date nextInvokeTime;

    @Schema(title = "系统子域")
    @Size(max = 64)
    private String domain;

    @Schema(title = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(title = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    private String pinyinName;

    @Schema(title = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(title = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(title = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(title = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(title = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(title = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(title = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
