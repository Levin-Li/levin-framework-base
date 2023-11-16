package com.levin.oak.base.services.menures.req;

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

import com.levin.oak.base.services.menures.info.*;
import com.levin.oak.base.entities.MenuRes;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_MenuRes.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.support.InjectConst;
    import com.levin.commons.service.domain.InjectVar;
    import com.levin.commons.rbac.MenuItem.*;
    import com.levin.oak.base.entities.MenuRes;
    import com.levin.oak.base.services.menures.info.*;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询菜单
 *  @Author Auto gen by simple-dao-codegen 2023年6月26日 下午6:06:03
 *  代码生成哈希校验码：[27a69a9569f3f6443056d14cd1f81a72]
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
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS, resultClass = MenuResInfo.class)
public class QueryMenuResReq extends MultiTenantReq{

    private static final long serialVersionUID = -887712701L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    OrderBy.Type orderDir;

    @Schema(title = "是否包含公共数据")
    @Ignore
    private boolean isContainsPublicData = true;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 64)
    @Schema(title = L_parentId)
    String parentId;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations)
    String requireAuthorizations;

    @NotNull
    @Schema(title = L_alwaysShow)
    Boolean alwaysShow;

    @Size(max = 64)
    @Schema(title = L_target)
    String target;

    @Schema(title = L_actionType)
    ActionType actionType;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_path)
    String path;

    @Schema(title = "模糊匹配-" + L_path)
    @Contains
    String containsPath;

    @Size(max = 1800)
    @Schema(title = L_params)
    String params;

    @Schema(title = "是否加载" + L_parent)
    @Fetch(attrs = E_MenuRes.parent, condition = "#_val == true")
    Boolean loadParent;

    @Schema(title = "是否加载" + L_children)
    @Fetch(attrs = E_MenuRes.children, condition = "#_val == true")
    Boolean loadChildren;

    @Size(max = 1800)
    @Schema(title = L_idPath , description = D_idPath)
    String idPath;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Schema(title = "模糊匹配-" + L_name)
    @Contains
    String containsName;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;

    @Schema(title = "模糊匹配-" + L_pinyinName)
    @Contains
    String containsPinyinName;

    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @NotNull
    @Schema(title = L_createTime , description = "大于等于" + L_createTime)
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime , description = "小于等于" + L_createTime)
    @Lte
    Date lteCreateTime;

    //@Schema(title = L_createTime + "-日期范围")
    //@Between(paramDelimiter = "-")
    //String betweenCreateTime;


    @Schema(title = L_lastUpdateTime , description = "大于等于" + L_lastUpdateTime)
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = L_lastUpdateTime , description = "小于等于" + L_lastUpdateTime)
    @Lte
    Date lteLastUpdateTime;

    //@Schema(title = L_lastUpdateTime + "-日期范围")
    //@Between(paramDelimiter = "-")
    //String betweenLastUpdateTime;


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

    public QueryMenuResReq(String id) {
        this.id = id;
    }
    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
