package com.levin.oak.base.services.org.req;

import io.swagger.v3.oas.annotations.media.Schema;

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

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Org.*;
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询机构
 *  @Author Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "查询机构")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS
, resultClass = OrgInfo.class)
public class QueryOrgReq implements ServiceReq  {

    private static final long serialVersionUID = -1399842458L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "租户ID")
    private Long tenantId;


    @Schema(description = "编码")
    private String code;


    @Schema(description = "图标")
    private String icon;


    @Schema(description = "状态")
    private State state;


    @Schema(description = "类型")
    private Type type;


    @Schema(description = "所属行业")
    private String industries;


    @Schema(description = "区域编码")
    private String areaCode;


    @Schema(description = "加载所属区域")
    @Fetch(attrs = E_Org.area, condition = "#_val == true")
    private Boolean loadArea;


    @Schema(description = "机构级别")
    private String level;


    @Schema(description = "机构类别")
    private String category;


    @Schema(description = "是否外部机构")
    private Boolean isExternal;


    @Schema(description = "联系人")
    private String contacts;


    @Schema(description = "联系电话")
    private String phones;


    @Schema(description = "联系邮箱")
    private String emails;


    @Schema(description = "联系地址")
    private String address;


    @Schema(description = "邮政编码")
    private String zipCode;


    @Schema(description = "父ID")
    private Long parentId;


    @Schema(description = "加载父对象")
    @Fetch(attrs = E_Org.parent, condition = "#_val == true")
    private Boolean loadParent;


    @Schema(description = "加载子节点")
    @Fetch(attrs = E_Org.children, condition = "#_val == true")
    private Boolean loadChildren;


    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_Org.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_Org.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_Org.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_Org.lastUpdateTime)
    private Date maxLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryOrgReq(Long id) {
        this.id = id;
    }
}
