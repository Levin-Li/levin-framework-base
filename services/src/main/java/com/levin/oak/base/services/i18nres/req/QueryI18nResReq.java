package com.levin.oak.base.services.i18nres.req;

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

import com.levin.oak.base.services.i18nres.info.*;
import com.levin.oak.base.entities.I18nRes;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_I18nRes.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 查询国际化资源
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月22日 下午1:37:18, 代码生成哈希校验码：[048c02217dfa03653fbe2449a71a3378]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS, resultClass = I18nResInfo.class)
public class QueryI18nResReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -1681554652L;

    @Schema(title = "是否包含公共数据")
    @Ignore
    boolean isContainsPublicData = true;

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

    @Schema(title = L_id)
    Long id;

    @Schema(title = L_category)
    String category;

    @Schema(title = L_lang)
    String lang;

    @Schema(title = L_label)
    String label;

    @Schema(title = "模糊匹配-" + L_label)
    @Contains
    String containsLabel;

    // @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain, description = D_domain)
    String domain;

    @Schema(title = L_name)
    String name;

    @JsonIgnore(value = true)
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    // @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Schema(title = L_creator)
    String creator;

    @Schema(title = L_createTime, description = L_createTime + "大于等于字段值")
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime, description = L_createTime + "小于等于字段值")
    @Lte
    Date lteCreateTime;

    @Schema(title = L_createTime + "-日期范围")
    @Between
    String betweenCreateTime;

    @Schema(title = L_lastUpdateTime, description = L_lastUpdateTime + "大于等于字段值")
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = L_lastUpdateTime, description = L_lastUpdateTime + "小于等于字段值")
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

    @Schema(title = L_remark)
    String remark;

    public QueryI18nResReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        // @todo 查询之前初始化数据
    }
}
