package com.levin.oak.base.services.accesswhitelist.req;

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

import com.levin.oak.base.services.accesswhitelist.info.*;
import com.levin.oak.base.entities.AccessWhitelist;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AccessWhitelist.*;
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
 * 查询访问白名单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月9日 下午10:46:12, 代码生成哈希校验码：[35492a5bc17b2e1b16209db029fb5bfb]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AccessWhitelist.class, alias = E_AccessWhitelist.ALIAS, resultClass = AccessWhitelistInfo.class)
public class QueryAccessWhitelistReq extends MultiTenantReq<QueryAccessWhitelistReq> {

    private static final long serialVersionUID = 101397189L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)", remark = "生成排序表达式")
    @OrderBy(value = createTime, condition = "#isEmpty(orderBy) || #isEmpty(orderDir)", order = Integer.MAX_VALUE, desc = "默认按时间排序")
    OrderBy.Type orderDir;

    @Schema(title = L_id + "集合")
    @In(E_AccessWhitelist.id)
    List<String> idList;



    @Size(max = 384)
    @Schema(title = L_id , description = D_id)
    String id;

    @Schema(title = "模糊匹配-" + L_id , description = D_id)
    @StartsWith
    String startsWithId;

    @Size(max = 255)
    @Schema(title = L_title)
    String title;

    @Schema(title = "模糊匹配-" + L_title)
    @Contains
    String containsTitle;

    @Size(max = 64)
    @Schema(title = L_moduleId)
    String moduleId;

    @Size(max = 64)
    @Schema(title = L_moduleName)
    String moduleName;

    @Size(max = 1800)
    @Schema(title = L_domainList , description = D_domainList)
    String domainList;

    @Size(max = 1800)
    @Schema(title = L_regionList , description = D_regionList)
    String regionList;

    @Size(max = 1800)
    @Schema(title = L_ipList , description = D_ipList)
    String ipList;

    @Size(max = 1800)
    @Schema(title = L_ipBlacklist , description = D_ipBlacklist)
    String ipBlacklist;

    @Schema(title = L_osList , description = D_osList)
    String osList;

    @Schema(title = L_browserList , description = D_browserList)
    String browserList;

    @Schema(title = L_browserTypeList , description = D_browserTypeList)
    String browserTypeList;

    @Size(max = 64)
    @Schema(title = L_methodList , description = D_methodList)
    String methodList;

    //@InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @Schema(title = L_createTime , description = L_createTime + "大于等于字段值")
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime , description = L_createTime + "小于等于字段值")
    @Lte
    Date lteCreateTime;

    @Schema(title = L_createTime + "-日期范围")
    @Between
    String betweenCreateTime;


    @Schema(title = L_lastUpdateTime , description = L_lastUpdateTime + "大于等于字段值")
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = L_lastUpdateTime , description = L_lastUpdateTime + "小于等于字段值")
    @Lte
    Date lteLastUpdateTime;

    @Schema(title = L_lastUpdateTime + "-日期范围")
    @Between
    String betweenLastUpdateTime;


    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    @Schema(title = "模糊匹配-" + L_remark)
    @Contains
    String containsRemark;

    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    public QueryAccessWhitelistReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }
}
