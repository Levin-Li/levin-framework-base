package com.levin.oak.base.services.scheduledlog.req;

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

import com.levin.oak.base.services.scheduledlog.info.*;
import com.levin.oak.base.entities.ScheduledLog;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledLog.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 查询调度日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:26, 代码生成哈希校验码：[eb290690bff780c00b00dbc409bea4b9]，请不要修改和删除此行内容。
 *
 */
@Schema(title = QUERY_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS, resultClass = ScheduledLogInfo.class)
public class QueryScheduledLogReq extends MultiTenantOrgReq<QueryScheduledLogReq> {

    private static final long serialVersionUID = 1319130901L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)", remark = "生成排序表达式")
    @OrderBy(value = createTime, condition = "#isEmpty(orderBy) || #isEmpty(orderDir)", order = Integer.MAX_VALUE, desc = "默认按时间排序")
    OrderBy.Type orderDir;


    @Schema(title = L_id)
    String id;

    @Size(max = 64)
    @Schema(title = L_taskId)
    String taskId;

    @Size(max = 128)
    @Schema(title = L_invokeCycle)
    String invokeCycle;

    @Schema(title = L_invokeSnapshot , description = D_invokeSnapshot)
    String invokeSnapshot;

    @Schema(title = L_isError)
    Boolean isError;

    @Schema(title = L_invokeResult)
    String invokeResult;

    @Schema(title = L_createTime , description = L_createTime + "大于等于字段值")
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime , description = L_createTime + "小于等于字段值")
    @Lte
    Date lteCreateTime;

    @Schema(title = L_createTime + "-日期范围")
    @Between
    String betweenCreateTime;


    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    public QueryScheduledLogReq(String id) {
        this.id = id;
    }
    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }
}
