package com.levin.oak.base.services.org.req;

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

import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Org.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 查询机构
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午11:01:08, 代码生成哈希校验码：[949f43d42bb0da35b7d7f2ae8b0bd02e]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS, resultClass = OrgInfo.class)
public class QueryOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;

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

    @Size(max = 64)
    @Schema(title = L_parentId)
    String parentId;

    @Size(max = 64)
    @Schema(title = L_code, description = D_code)
    String code;

    @Schema(title = "模糊匹配-" + L_code, description = D_code)
    @Contains
    String containsCode;

    @Schema(title = L_icon)
    String icon;

    @NotNull
    @Schema(title = L_state)
    State state;

    @NotNull
    @Schema(title = L_type)
    Type type;

    @Size(max = 64)
    @Schema(title = L_industries)
    String industries;

    @Size(max = 64)
    @Schema(title = L_areaCode)
    String areaCode;

    @Schema(title = "模糊匹配-" + L_areaCode)
    @Contains
    String containsAreaCode;

    @Schema(title = "是否加载" + L_area)
    @Fetch(attrs = E_Org.area, condition = "#_val == true")
    Boolean loadArea;

    @Size(max = 128)
    @Schema(title = L_level, description = D_level)
    String level;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category, description = D_category)
    String category;

    @NotNull
    @Schema(title = L_isExternal, description = D_isExternal)
    Boolean isExternal;

    @Size(max = 64)
    @Schema(title = L_contacts)
    String contacts;

    @Schema(title = "模糊匹配-" + L_contacts)
    @Contains
    String containsContacts;

    @Size(max = 20)
    @Schema(title = L_phones)
    String phones;

    @Schema(title = "模糊匹配-" + L_phones)
    @Contains
    String containsPhones;

    @Size(max = 32)
    @Schema(title = L_emails)
    String emails;

    @Schema(title = L_address)
    String address;

    @Schema(title = "模糊匹配-" + L_address)
    @Contains
    String containsAddress;

    @Size(max = 32)
    @Schema(title = L_zipCode)
    String zipCode;

    @Schema(title = L_extInfo)
    String extInfo;

    @Schema(title = "是否加载" + L_parent)
    @Fetch(attrs = E_Org.parent, condition = "#_val == true")
    Boolean loadParent;

    @Schema(title = "是否加载" + L_children)
    @Fetch(attrs = E_Org.children, condition = "#_val == true")
    Boolean loadChildren;

    @Size(max = 1800)
    @Schema(title = L_idPath, description = D_idPath)
    String idPath;

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
    @InjectVar(value = InjectConsts.USER_ID, isRequired = "false")
    @Schema(title = L_creator)
    String creator;

    @NotNull
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

    @NotNull
    @Schema(title = L_enable)
    Boolean enable;

    @NotNull
    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    public QueryOrgReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        // @todo 查询之前初始化数据
    }
}
