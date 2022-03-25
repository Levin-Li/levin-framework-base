package com.levin.oak.base.services.setting.req;

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

import com.levin.oak.base.services.setting.info.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Setting.*;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询系统设置
 *  @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "查询系统设置")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS, resultClass = SettingInfo.class)
public class QuerySettingReq extends MultiTenantReq{

    private static final long serialVersionUID = 147875794L;

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


    //@NotBlank
    //@Size(max = 64)

    @Schema(description = "分类名称")
    private String categoryName;


    //@Size(max = 64)

    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "模糊匹配 - 分组名称")
    @Contains
    private String containsGroupName;


    //@NotBlank
    //@Size(max = 64)

    @Schema(description = "编码")
    private String code;


    //@NotNull

    @Schema(description = "值类型")
    private ValueType valueType;



    @Schema(description = "值")
    private String value;



    @Schema(description = "值是否可空")
    private Boolean nullable;


    //@Size(max = 64)

    @Schema(description = "输入占位提示")
    private String inputPlaceholder;


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


    public QuerySettingReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
