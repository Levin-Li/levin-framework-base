package com.levin.oak.base.services.dict.req;

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

import com.levin.oak.base.services.dict.info.*;
import com.levin.oak.base.entities.Dict;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Dict.*;
    import com.levin.commons.service.support.DefaultJsonConverter;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.List;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询字典
 *  @Author Auto gen by simple-dao-codegen 2022-3-25 13:28:14
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
public class QueryDictReq extends MultiTenantReq{

    private static final long serialVersionUID = -445779596L;

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


    //@InjectVar(domain = "dao", converter = DefaultJsonConverter.class)

    @Schema(description = "编码项")
    private String items;





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


    public QueryDictReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
