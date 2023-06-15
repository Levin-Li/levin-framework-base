package com.levin.oak.base.services.org.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.misc.Fetch;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.oak.base.entities.E_Org;
import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.Org.State;
import com.levin.oak.base.entities.Org.Type;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.org.info.OrgInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 查询机构
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "查询机构")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS, resultClass = OrgInfo.class)
public class QueryOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;

    @Ignore
    @Schema(title = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(title = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull

    @Schema(title = "id")
    private String id;


    //@Size(max = 128)

    @Schema(title = "编码")
    private String code;

    @Schema(title = "模糊匹配 - 编码")
    @Contains
    private String containsCode;


    @Schema(title = "图标")
    private String icon;


    //@NotNull

    @Schema(title = "状态")
    private State state;


    //@NotNull

    @Schema(title = "类型")
    private Type type;


    //@Size(max = 64)

    @Schema(title = "所属行业")
    private String industries;


    //@NotBlank
    //@Size(max = 64)

    @Schema(title = "区域编码")
    private String areaCode;

    @Schema(title = "模糊匹配 - 区域编码")
    @Contains
    private String containsAreaCode;


    @Schema(title = "是否加载所属区域")
    @Fetch(attrs = E_Org.area, condition = "#_val == true")
    private Boolean loadArea;


    //@Size(max = 128)

    @Schema(title = "机构级别")
    private String level;


    //@NotBlank
    //@Size(max = 128)

    @Schema(title = "机构类别")
    private String category;


    //@NotNull

    @Schema(title = "是否外部机构")
    private Boolean isExternal;


    //@Size(max = 64)

    @Schema(title = "联系人")
    private String contacts;

    @Schema(title = "模糊匹配 - 联系人")
    @Contains
    private String containsContacts;


    //@Size(max = 20)

    @Schema(title = "联系电话")
    private String phones;

    @Schema(title = "模糊匹配 - 联系电话")
    @Contains
    private String containsPhones;


    //@Size(max = 32)

    @Schema(title = "联系邮箱")
    private String emails;


    @Schema(title = "联系地址")
    private String address;

    @Schema(title = "模糊匹配 - 联系地址")
    @Contains
    private String containsAddress;


    //@Size(max = 32)

    @Schema(title = "邮政编码")
    private String zipCode;


    @Schema(title = "父ID")
    private String parentId;


    @Schema(title = "是否加载父对象")
    @Fetch(attrs = E_Org.parent, condition = "#_val == true")
    private Boolean loadParent;


    @Schema(title = "是否加载子节点")
    @Fetch(attrs = E_Org.children, condition = "#_val == true")
    private Boolean loadChildren;


    //@Size(max = 1800)

    @Schema(title = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    //@NotBlank
    //@Size(max = 128)

    @Schema(title = "名称")
    private String name;

    @Schema(title = "模糊匹配 - 名称")
    @Contains
    private String containsName;


    //@Size(max = 128)

    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(title = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    private String containsPinyinName;


    //@Size(max = 128)

    @Schema(title = "创建者")
    private String creator;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(title = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    //@NotNull

    @Schema(title = "是否允许")
    private Boolean enable;


    //@NotNull

    @Schema(title = "是否可编辑")
    private Boolean editable;


    //@Size(max = 512)

    @Schema(title = "备注")
    private String remark;


    public QueryOrgReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
