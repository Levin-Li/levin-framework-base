package com.levin.oak.base.services.role.req;

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

import com.levin.oak.base.services.role.info.*;
import com.levin.oak.base.entities.Role;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Role.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.Role.*;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import java.util.Set;
import java.io.Serializable;
import com.levin.commons.service.support.InjectConst;
import java.util.List;
import com.levin.oak.base.services.role.info.*;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 查询角色
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月26日 下午2:34:54, 代码生成哈希校验码：[dae114cb5a3417beeafdfe3056ff704a]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS, resultClass = RoleInfo.class)
public class QueryRoleReq extends MultiTenantReq<QueryRoleReq> {

    private static final long serialVersionUID = -445356492L;

    @Schema(title = "是否包含平台公共数据")
    @Ignore
    boolean isContainsPublicData = true;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)", remark = "生成排序表达式")
    @OrderBy(value = createTime, condition = "#isEmpty(orderBy) || #isEmpty(orderDir)", order = Integer.MAX_VALUE - 10000, scope = OrderBy.Scope.OnlyForNotGroupBy, desc = "默认按时间排序")
    OrderBy.Type orderDir;

    @Schema(title = L_id + "集合")
    @In(E_Role.id)
    List<String> idList;



    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 128)
    @Schema(title = L_moduleId , description = D_moduleId)
    String moduleId;

    @Size(max = 128)
    @Schema(title = L_parentId)
    String parentId;

    @Schema(title = L_extendable)
    Boolean extendable;

    @Schema(title = L_mutex)
    Boolean mutex;

    @Schema(title = L_userLimit)
    Integer userLimit;

    @Size(max = 1800)
    @Schema(title = L_precondition , description = D_precondition)
    String precondition;

    @Size(max = 128)
    @Schema(title = L_code)
    String code;

    @Schema(title = "模糊匹配-" + L_code)
    @Contains
    String containsCode;

    @Schema(title = L_expiredDate , description = L_expiredDate + "大于等于字段值")
    @Gte
    Date gteExpiredDate;

    @Schema(title = L_expiredDate , description = L_expiredDate + "小于等于字段值")
    @Lte
    Date lteExpiredDate;

    @Schema(title = L_expiredDate + "-日期范围")
    @Between
    String betweenExpiredDate;


    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_orgDataScope , description = D_orgDataScope)
    OrgDataScope orgDataScope;

    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class, isRequired = "false")
    @Schema(title = L_assignedOrgIdList , description = D_assignedOrgIdList)
    List<String> assignedOrgIdList;

    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class, isRequired = "false")
    @Schema(title = L_permissionList , description = D_permissionList)
    List<String> permissionList;


    @Schema(title = "是否加载" + L_parent)
    @Fetch(attrs = E_Role.parent, condition = "#_val == true")
    Boolean loadParent;


    @Schema(title = "是否加载" + L_children)
    @Fetch(attrs = E_Role.children, condition = "#_val == true")
    Boolean loadChildren;

    @Size(max = 1800)
    @Schema(title = L_nodePath , description = D_nodePath)
    String nodePath;

    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Schema(title = "模糊匹配-" + L_name)
    @Contains
    String containsName;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;

    @Schema(title = "模糊匹配-" + L_pinyinName , description = D_pinyinName)
    @Contains
    String containsPinyinName;

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

    public QueryRoleReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }
}
