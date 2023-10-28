package com.levin.oak.base.services.scheduledtask.req;

import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import com.levin.commons.dao.annotation.Ignore;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.support.*;

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.scheduledtask.info.*;
import com.levin.oak.base.entities.ScheduledTask;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledTask.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 查询调度任务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年10月28日 下午12:14:20, 代码生成哈希校验码：[64a1e14d39c884099aac8fb1288d1f4d]，请不要修改和删除此行内容。
 */
@Schema(title = QUERY_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(
        entityClass = ScheduledTask.class,
        alias = E_ScheduledTask.ALIAS,
        resultClass = ScheduledTaskInfo.class)
public class QueryScheduledTaskReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -2056389676L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    // @Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(
            expr = "orderBy + ' ' + orderDir",
            condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)",
            remark = "生成排序表达式")
    @OrderBy(
            value = createTime,
            condition = "#isEmpty(orderBy) || #isEmpty(orderDir)",
            order = Integer.MAX_VALUE,
            desc = "默认按时间排序")
    OrderBy.Type orderDir;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category)
    String category;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_groupName)
    String groupName;

    @Schema(title = "模糊匹配-" + L_groupName)
    @Contains
    String containsGroupName;

    @NotBlank
    @Schema(title = L_cron)
    String cron;

    @Schema(title = L_invokeExpr, description = D_invokeExpr)
    String invokeExpr;

    @Schema(title = L_parallelInvoke)
    Boolean parallelInvoke;

    @Schema(title = L_invokedCount)
    Integer invokedCount;

    @Schema(title = L_lastInvokedTime, description = "大于等于" + L_lastInvokedTime)
    @Gte
    Date gteLastInvokedTime;

    @Schema(title = L_lastInvokedTime, description = "小于等于" + L_lastInvokedTime)
    @Lte
    Date lteLastInvokedTime;

    // @Schema(title = L_lastInvokedTime + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenLastInvokedTime;

    @Schema(title = L_nextInvokeTime, description = "大于等于" + L_nextInvokeTime)
    @Gte
    Date gteNextInvokeTime;

    @Schema(title = L_nextInvokeTime, description = "小于等于" + L_nextInvokeTime)
    @Lte
    Date lteNextInvokeTime;

    // @Schema(title = L_nextInvokeTime + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenNextInvokeTime;

    @Size(max = 128)
    @Schema(title = L_domain, description = D_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @NotNull
    @Schema(title = L_createTime, description = "大于等于" + L_createTime)
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime, description = "小于等于" + L_createTime)
    @Lte
    Date lteCreateTime;

    // @Schema(title = L_createTime + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenCreateTime;

    @Schema(title = L_lastUpdateTime, description = "大于等于" + L_lastUpdateTime)
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = L_lastUpdateTime, description = "小于等于" + L_lastUpdateTime)
    @Lte
    Date lteLastUpdateTime;

    // @Schema(title = L_lastUpdateTime + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenLastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @NotNull
    @Schema(title = L_enable)
    Boolean enable;

    @NotNull
    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    public QueryScheduledTaskReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        // @todo 查询之前初始化数据
    }
}
