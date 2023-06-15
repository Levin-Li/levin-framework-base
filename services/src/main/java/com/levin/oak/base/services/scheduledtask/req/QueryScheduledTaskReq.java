package com.levin.oak.base.services.scheduledtask.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.oak.base.entities.E_ScheduledTask;
import com.levin.oak.base.entities.ScheduledTask;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.scheduledtask.info.ScheduledTaskInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 查询调度任务
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "查询调度任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS, resultClass = ScheduledTaskInfo.class)
public class QueryScheduledTaskReq extends MultiTenantReq {

    private static final long serialVersionUID = -2056389676L;

    @Ignore
    @Schema(title = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(title = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull

    @Schema(title = "id")
    private String id;


    //@NotBlank
    //@Size(max = 64)

    @Schema(title = "任务分类")
    private String category;


    //@NotBlank
    //@Size(max = 64)

    @Schema(title = "任务组")
    private String groupName;

    @Schema(title = "模糊匹配 - 任务组")
    @Contains
    private String containsGroupName;


    //@NotBlank

    @Schema(title = "调度表达式")
    private String cron;


    @Schema(title = "执行表达式")
    private String invokeExpr;


    @Schema(title = "允许并发执行")
    private Boolean parallelInvoke;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于最后一次时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastInvokedTime;

    @Schema(title = "小于等于最后一次时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLastInvokedTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于下一次时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteNextInvokeTime;

    @Schema(title = "小于等于下一次时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteNextInvokeTime;


    //@Size(max = 64)

    @Schema(title = "系统子域")
    private String domain;


    //@NotBlank
    //@Size(max = 128)

    @Schema(title = "名称")
    private String name;

    @Schema(title = "模糊匹配 - 名称")
    @Contains
    private String containsName;


    //@Size(max = 128)

    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(title = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    private String containsPinyinName;


    //@Size(max = 128)

    @Schema(title = "创建者")
    private String creator;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(title = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    //@NotNull

    @Schema(title = "是否允许")
    private Boolean enable;


    //@NotNull

    @Schema(title = "是否可编辑")
    private Boolean editable;


    //@Size(max = 512)

    @Schema(title = "备注")
    private String remark;


    public QueryScheduledTaskReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
