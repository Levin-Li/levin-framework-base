package com.levin.oak.base.services.setting.req;

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

import com.levin.oak.base.services.setting.info.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Setting.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.oak.base.entities.Setting.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 查询系统设置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月26日 下午2:34:55, 代码生成哈希校验码：[617306af07da6daae7432c0934a45026]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS, resultClass = SettingInfo.class)
public class QuerySettingReq extends MultiTenantOrgReq<QuerySettingReq> {

    private static final long serialVersionUID = 147875794L;

    @Schema(title = "是否包含平台公共数据")
    @Ignore
    boolean isContainsPublicData = true;

    @Schema(title = "是否包含组织公共数据")
    @Ignore
    boolean isContainsOrgPublicData = true;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)", remark = "生成排序表达式")
    @OrderBy(value = createTime, condition = "#isEmpty(orderBy) || #isEmpty(orderDir)", order = Integer.MAX_VALUE - 10000, scope = OrderBy.Scope.OnlyForNotGroupBy, desc = "默认按时间排序")
    OrderBy.Type orderDir;

    @Schema(title = L_id + "集合")
    @In(E_Setting.id)
    List<String> idList;



    @Size(max = 384)
    @Schema(title = L_id)
    String id;

    @Schema(title = L_domain)
    String domain;

    @Schema(title = "模糊匹配-" + L_domain)
    @EndsWith
    String endsWithDomain;

    @Size(max = 64)
    @Schema(title = L_categoryName)
    String categoryName;

    @Size(max = 64)
    @Schema(title = L_groupName)
    String groupName;

    @Schema(title = "模糊匹配-" + L_groupName)
    @Contains
    String containsGroupName;

    @Size(max = 64)
    @Schema(title = L_code)
    String code;

    @Schema(title = L_valueType)
    ValueType valueType;

    @Schema(title = L_editor , description = D_editor)
    String editor;

    @Schema(title = L_valueContent)
    String valueContent;

    @Schema(title = L_nullable)
    Boolean nullable;

    @Size(max = 128)
    @Schema(title = L_inputPlaceholder)
    String inputPlaceholder;

    @Size(max = 64)
    @Schema(title = L_name)
    String name;

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

    public QuerySettingReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }
}
