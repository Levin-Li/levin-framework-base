package com.levin.oak.base.services.noticeprocesslog.req;

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

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;

import java.util.*;

import com.levin.oak.base.services.noticeprocesslog.info.*;
import com.levin.oak.base.entities.NoticeProcessLog;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;

import java.util.Date;
////////////////////////////////////

/**
 * 查询通知处理日志
 *
 * @Author Auto gen by simple-dao-codegen 2022-6-20 16:50:12
 */
@Schema(description = "查询通知处理日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = NoticeProcessLog.class, alias = E_NoticeProcessLog.ALIAS, resultClass = NoticeProcessLogInfo.class)
public class QueryNoticeProcessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = -1991983093L;

    @Ignore
    @Schema(description = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    OrderBy.Type orderDir;


    //@NotBlank
    //@Size(max = 64)
    @Schema(description = "id")
    String id;

    //@NotBlank
    //@InjectVar()
    //@Size(max = 128)
    @Schema(description = "用户ID")
    String ownerId;

    //@NotBlank
    //@Size(max = 128)
    @Schema(description = "消息ID")
    String noticeId;

    //@Size(max = 128)
    @Schema(description = "处理状态")
    String status;

    //@NotNull
    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于处理时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteCreateTime;

    @Schema(description = "小于等于处理时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteCreateTime;

    @Schema(description = "处理时间-日期范围，格式：yyyyMMdd-yyyyMMdd，大于等于且小余等于")
    @Between(paramDelimiter = "-", patterns = {"yyyyMMdd"})
    String betweenCreateTime;

    //@Size(max = 512)
    @Schema(description = "备注")
    String remark;

    public QueryNoticeProcessLogReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
