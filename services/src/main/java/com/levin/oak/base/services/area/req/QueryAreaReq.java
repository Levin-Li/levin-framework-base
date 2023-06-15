package com.levin.oak.base.services.area.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.misc.Fetch;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.Area.Type;
import com.levin.oak.base.entities.E_Area;
import com.levin.oak.base.services.area.info.AreaInfo;
import com.levin.oak.base.services.commons.req.BaseReq;
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
 * 查询区域
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "查询区域")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS, resultClass = AreaInfo.class)
public class QueryAreaReq extends BaseReq {

    private static final long serialVersionUID = -445860277L;

    @Ignore
    @Schema(title = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(title = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotBlank
    //@Size(max = 64)

    @Schema(title = "编码")
    private String code;

    @Schema(title = "模糊匹配 - 编码")
    @StartsWith
    private String startsWithCode;


    @Schema(title = "图标")
    private String icon;


    //@Size(max = 64)

    @Schema(title = "父区域ID")
    private String parentCode;


    @Schema(title = "是否加载父区域")
    @Fetch(attrs = E_Area.parent, condition = "#_val == true")
    private Boolean loadParent;


    @Schema(title = "是否加载子区域")
    @Fetch(attrs = E_Area.children, condition = "#_val == true")
    private Boolean loadChildren;


    //@NotNull

    @Schema(title = "类型")
    private Type type;


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


    public QueryAreaReq(String code) {
        this.code = code;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
