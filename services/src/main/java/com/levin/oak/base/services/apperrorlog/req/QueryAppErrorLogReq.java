package com.levin.oak.base.services.apperrorlog.req;

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

import com.levin.oak.base.services.apperrorlog.info.*;
import com.levin.oak.base.entities.AppErrorLog;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppErrorLog.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  查询应用错误日志
 *  @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:11, 请不要修改和删除此行内容。
 *  代码生成哈希校验码：[9b46227faab81796efa2294fa84c714e], 请不要修改和删除此行内容。
 */
@Schema(title = QUERY_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS, resultClass = AppErrorLogInfo.class)
public class QueryAppErrorLogReq extends MultiTenantReq{

    private static final long serialVersionUID = 1594864095L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    OrderBy.Type orderDir;


    @NotNull
    @Schema(title = L_id)
    Long id;

    @Size(max = 64)
    @Schema(title = L_moduleId)
    String moduleId;

    @NotNull
    @Schema(title = L_occurTime , description = "大于等于" + L_occurTime)
    @Gte
    Date gteOccurTime;

    @Schema(title = L_occurTime , description = "小于等于" + L_occurTime)
    @Lte
    Date lteOccurTime;

    //@Schema(title = L_occurTime + "-日期范围")
    //@Between(paramDelimiter = "-")
    //String betweenOccurTime;


    @NotBlank
    @Size(max = 768)
    @Schema(title = L_title)
    String title;

    @Schema(title = "模糊匹配-" + L_title)
    @Contains
    String containsTitle;

    @Schema(title = L_errorLevel)
    String errorLevel;

    @Schema(title = L_rootExceptionType)
    String rootExceptionType;

    @Schema(title = "模糊匹配-" + L_rootExceptionType)
    @Contains
    String containsRootExceptionType;

    @Schema(title = L_exceptionFullInfo)
    String exceptionFullInfo;

    public QueryAppErrorLogReq(Long id) {
        this.id = id;
    }
    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
