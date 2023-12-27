package com.levin.oak.base.services.accesslog.req;

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

import com.levin.oak.base.services.accesslog.info.*;
import com.levin.oak.base.entities.AccessLog;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AccessLog.*;
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
 * 查询访问日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月22日 下午2:44:00, 代码生成哈希校验码：[2edea8bf760fb4334c55e7c68ff0f3b6]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS, resultClass = AccessLogInfo.class)
public class QueryAccessLogReq extends MultiTenantOrgReq<QueryAccessLogReq> {

    private static final long serialVersionUID = 1030736962L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)", remark = "生成排序表达式")
    @OrderBy(value = createTime, condition = "#isEmpty(orderBy) || #isEmpty(orderDir)", order = Integer.MAX_VALUE, desc = "默认按时间排序")
    OrderBy.Type orderDir;

    @Schema(title = L_id + "集合")
    @In(E_AccessLog.id)
    Long[] idList;



    @Schema(title = L_id)
    Long id;

    @Schema(title = L_domain , description = D_domain)
    String domain;

    @Schema(title = "模糊匹配-" + L_domain , description = D_domain)
    @Contains
    String containsDomain;

    @Schema(title = L_module , description = D_module)
    String module;

    @Schema(title = "模糊匹配-" + L_module , description = D_module)
    @Contains
    String containsModule;

    //@InjectVar(value = InjectConst.USER_NAME, isRequired = "false")
    @Size(max = 64)
    @Schema(title = L_visitor)
    String visitor;

    @Schema(title = "模糊匹配-" + L_visitor)
    @Contains
    String containsVisitor;

    @Size(max = 255)
    @Schema(title = L_title)
    String title;

    @Schema(title = "模糊匹配-" + L_title)
    @Contains
    String containsTitle;

    @Size(max = 64)
    @Schema(title = L_logType)
    String logType;

    @Schema(title = L_diffModifyData)
    String diffModifyData;

    @Schema(title = L_bizKey)
    String bizKey;

    @Schema(title = "模糊匹配-" + L_bizKey)
    @Contains
    String containsBizKey;

    @Schema(title = L_bizType)
    String bizType;

    @Schema(title = "模糊匹配-" + L_bizType)
    @Contains
    String containsBizType;

    @Size(max = 768)
    @Schema(title = L_requestUri)
    String requestUri;

    @Schema(title = "模糊匹配-" + L_requestUri)
    @Contains
    String containsRequestUri;

    @Size(max = 32)
    @Schema(title = L_requestMethod)
    String requestMethod;

    @Schema(title = L_headInfo)
    String headInfo;

    @Schema(title = L_requestParams)
    String requestParams;

    @Schema(title = L_requestBody)
    String requestBody;

    @Schema(title = L_responseBody)
    String responseBody;

    @Size(max = 128)
    @Schema(title = L_remoteAddr)
    String remoteAddr;

    @Schema(title = "模糊匹配-" + L_remoteAddr)
    @Contains
    String containsRemoteAddr;

    @Size(max = 256)
    @Schema(title = L_accessRegion)
    String accessRegion;

    @Schema(title = "模糊匹配-" + L_accessRegion)
    @Contains
    String containsAccessRegion;

    @Size(max = 64)
    @Schema(title = L_serverAddr)
    String serverAddr;

    @Schema(title = L_isException)
    Boolean isException;

    @Schema(title = L_exceptionInfo)
    String exceptionInfo;

    @Size(max = 1800)
    @Schema(title = L_userAgent)
    String userAgent;

    @Schema(title = "模糊匹配-" + L_userAgent)
    @Contains
    String containsUserAgent;

    @Size(max = 128)
    @Schema(title = L_deviceName)
    String deviceName;

    @Size(max = 128)
    @Schema(title = L_browserName)
    String browserName;

    @Schema(title = L_executeTime)
    Long executeTime;

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

    public QueryAccessLogReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }
}
