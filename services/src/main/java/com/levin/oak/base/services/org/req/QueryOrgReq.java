package com.levin.oak.base.services.org.req;

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

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

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
 *  @Author Auto gen by simple-dao-codegen 2022-3-25 13:28:15
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
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS, resultClass = OrgInfo.class)
public class QueryOrgReq extends MultiTenantReq{

    private static final long serialVersionUID = -1399842458L;

    @Ignore
    @Schema(description = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull

    @Schema(description = "id")
    private Long id;


    //@Size(max = 128)

    @Schema(description = "编码")
    private String code;

    @Schema(description = "模糊匹配 - 编码")
    @Contains
    private String containsCode;



    @Schema(description = "图标")
    private String icon;


    //@NotNull

    @Schema(description = "状态")
    private State state;


    //@NotNull

    @Schema(description = "类型")
    private Type type;


    //@Size(max = 64)

    @Schema(description = "所属行业")
    private String industries;


    //@NotBlank
    //@Size(max = 64)

    @Schema(description = "区域编码")
    private String areaCode;

    @Schema(description = "模糊匹配 - 区域编码")
    @Contains
    private String containsAreaCode;



    @Schema(description = "是否加载所属区域")
    @Fetch(attrs = E_Org.area, condition = "#_val == true")
    private Boolean loadArea;


    //@Size(max = 128)

    @Schema(description = "机构级别")
    private String level;


    //@NotBlank
    //@Size(max = 128)

    @Schema(description = "机构类别")
    private String category;


    //@NotNull

    @Schema(description = "是否外部机构")
    private Boolean isExternal;


    //@Size(max = 64)

    @Schema(description = "联系人")
    private String contacts;

    @Schema(description = "模糊匹配 - 联系人")
    @Contains
    private String containsContacts;


    //@Size(max = 20)

    @Schema(description = "联系电话")
    private String phones;

    @Schema(description = "模糊匹配 - 联系电话")
    @Contains
    private String containsPhones;


    //@Size(max = 32)

    @Schema(description = "联系邮箱")
    private String emails;



    @Schema(description = "联系地址")
    private String address;

    @Schema(description = "模糊匹配 - 联系地址")
    @Contains
    private String containsAddress;


    //@Size(max = 32)

    @Schema(description = "邮政编码")
    private String zipCode;



    @Schema(description = "父ID")
    private Long parentId;



    @Schema(description = "是否加载父对象")
    @Fetch(attrs = E_Org.parent, condition = "#_val == true")
    private Boolean loadParent;



    @Schema(description = "是否加载子节点")
    @Fetch(attrs = E_Org.children, condition = "#_val == true")
    private Boolean loadChildren;


    //@Size(max = 1800)

    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    //@NotBlank
    //@Size(max = 128)

    @Schema(description = "名称")
    private String name;

    @Schema(description = "模糊匹配 - 名称")
    @Contains
    private String containsName;


    //@Size(max = 128)

    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(description = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    private String containsPinyinName;


    //@Size(max = 128)

    @Schema(description = "创建者")
    private String creator;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;




    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLastUpdateTime;




    @Schema(description = "排序代码")
    private Integer orderCode;


    //@NotNull

    @Schema(description = "是否允许")
    private Boolean enable;


    //@NotNull

    @Schema(description = "是否可编辑")
    private Boolean editable;


    //@Size(max = 512)

    @Schema(description = "备注")
    private String remark;


    public QueryOrgReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
