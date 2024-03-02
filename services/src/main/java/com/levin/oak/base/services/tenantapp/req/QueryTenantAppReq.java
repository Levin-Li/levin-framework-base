package com.levin.oak.base.services.tenantapp.req;

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

import com.levin.oak.base.services.tenantapp.info.*;
import com.levin.oak.base.entities.TenantApp;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_TenantApp.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.dao.domain.NamedObject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 查询租户应用
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:05, 代码生成哈希校验码：[b792f5adf88304b50ab5e9380ff5830e]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = TenantApp.class, alias = E_TenantApp.ALIAS, resultClass = TenantAppInfo.class)
public class QueryTenantAppReq extends MultiTenantReq<QueryTenantAppReq> {

    private static final long serialVersionUID = 1292984857L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)", remark = "生成排序表达式")
    @OrderBy(value = createTime, condition = "#isEmpty(orderBy) || #isEmpty(orderDir)", order = Integer.MAX_VALUE, desc = "默认按时间排序")
    OrderBy.Type orderDir;

    @Schema(title = L_id + "集合")
    @In(E_TenantApp.id)
    List<String> idList;



    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Size(max = 255)
    @Schema(title = L_logo)
    String logo;

    @Size(max = 255)
    @Schema(title = L_entryUrl)
    String entryUrl;

    @Size(max = 255)
    @Schema(title = L_infoUrl)
    String infoUrl;

    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_modules)
    List<String> modules;

    @Size(max = 255)
    @Schema(title = L_appSecret , description = D_appSecret)
    String appSecret;

    @Schema(title = L_salePrice , description = D_salePrice)
    BigDecimal salePrice;

    @Schema(title = L_purchasePrice , description = D_purchasePrice)
    BigDecimal purchasePrice;

    @Size(max = 255)
    @Schema(title = L_orderNo , description = D_orderNo)
    String orderNo;

    @Schema(title = L_expiredTime , description = L_expiredTime + "大于等于字段值")
    @Gte
    Date gteExpiredTime;

    @Schema(title = L_expiredTime , description = L_expiredTime + "小于等于字段值")
    @Lte
    Date lteExpiredTime;

    @Schema(title = L_expiredTime + "-日期范围" , description = D_expiredTime)
    @Between
    String betweenExpiredTime;


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

    public QueryTenantAppReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }
}
