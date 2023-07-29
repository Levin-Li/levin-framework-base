package com.levin.oak.base.services.tenant.req;

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

import com.levin.oak.base.services.tenant.info.*;
import com.levin.oak.base.entities.Tenant;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Tenant.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;

////////////////////////////////////

/**
 * 查询平台租户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月29日 下午11:45:30, 代码生成哈希校验码：[9aefb8bfa8bd839471ec6ab729beffd5]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS, resultClass = TenantInfo.class)
public class QueryTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;

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

    @Size(max = 128)
    @Schema(title = L_sysName)
    String sysName;

    @Schema(title = L_sysLogo)
    String sysLogo;

    @Schema(title = L_logo)
    String logo;

    @Size(max = 128)
    @Schema(title = L_code)
    String code;

    @NotBlank
    @Schema(title = L_tenantKey)
    String tenantKey;

    @Schema(title = L_balance)
    Double balance;

    @Schema(title = L_licenseCnt)
    Integer licenseCnt;

    @Schema(title = L_remainingLicenseCnt)
    Integer remainingLicenseCnt;

    @Schema(title = L_licenseExpire, description = "大于等于" + L_licenseExpire)
    @Gte
    Date gteLicenseExpire;

    @Schema(title = L_licenseExpire, description = "小于等于" + L_licenseExpire)
    @Lte
    Date lteLicenseExpire;

    // @Schema(title = L_licenseExpire + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenLicenseExpire;

    @Size(max = 32)
    @Schema(title = L_contractPerson)
    String contractPerson;

    @Size(max = 32)
    @Schema(title = L_contractPhone)
    String contractPhone;

    @Size(max = 1200)
    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class, isRequired = "false")
    @Schema(title = L_domainList)
    List<String> domainList;

    @Size(max = 64)
    @Schema(title = L_appId)
    String appId;

    @Size(max = 512)
    @Schema(title = L_appSecret)
    String appSecret;

    @Size(max = 512)
    @Schema(title = L_encryptKey)
    String encryptKey;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Schema(title = "模糊匹配-" + L_name)
    @Contains
    String containsName;

    @Size(max = 128)
    @Schema(title = L_pinyinName, description = D_pinyinName)
    String pinyinName;

    @Schema(title = "模糊匹配-" + L_pinyinName, description = D_pinyinName)
    @Contains
    String containsPinyinName;

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

    public QueryTenantReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        // @todo 查询之前初始化数据
    }
}
