package com.levin.oak.base.services.dict.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.Dict.Type;
import com.levin.oak.base.entities.E_Dict;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.dict.info.DictInfo;
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
 * 查询字典
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "查询字典")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS, resultClass = DictInfo.class)
public class QueryDictReq extends MultiTenantReq {

    private static final long serialVersionUID = -445779596L;

    @Schema(description = "是否包含公共数据", hidden = true)
    @Ignore
    private boolean isContainsPublicData = true;

    @Ignore
    @Schema(description = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;

    //@NotNull

    @Schema(description = "id")
    private String id;


    //@NotNull

    @Schema(description = "类型")
    private Type type;


    //@NotBlank
    //@Size(max = 64)

    @Schema(description = "编码")
    private String code;

    @Schema(description = "模糊匹配 - 编码")
    @Contains
    private String containsCode;


    //@InjectVar(domain = "dao", expectBaseType = String.class, converter = DefaultJsonConverter.class)

//    @Schema(description = "编码项")
//    private List<Item> itemList;


    //@Size(max = 64)

    @Schema(description = "系统子域")
    private String domain;


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


    public QueryDictReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
