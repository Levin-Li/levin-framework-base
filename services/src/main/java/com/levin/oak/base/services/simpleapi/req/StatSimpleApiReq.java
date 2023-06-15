package com.levin.oak.base.services.simpleapi.req;

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
import java.io.Serializable;

import com.levin.oak.base.services.simpleapi.info.*;
import com.levin.oak.base.entities.SimpleApi;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.SimpleApi.*;

import java.util.Date;
////////////////////////////////////

/**
 * 统计简单动态接口
 *
 * @Author Auto gen by simple-dao-codegen 2022-5-23 10:30:00
 */
@Schema(title = "统计简单动态接口")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS,
        //连接统计
        //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
        resultClass = StatSimpleApiReq.Result.class
)
public class StatSimpleApiReq extends MultiTenantReq {

    private static final long serialVersionUID = 1021385738L;


    //@Size(max = 16)
    @Schema(title = "逗号隔开", description = "http方法")
    String methods;

    //@NotNull
    @Schema(title = "脚本语言")
    Language language;

    //@NotNull
    @Schema(title = "id")
    String id;

    //@NotBlank
    //@Size(max = 64)
    @Schema(title = "类型")
    String type;

    //@NotBlank
    //@Size(max = 64)
    @Schema(title = "分类名称")
    String category;

    //@NotBlank
    //@Size(max = 64)
    @Schema(title = "分组名称")
    String groupName;
    @Schema(title = "模糊匹配 - 分组名称")
    @Contains
    String containsGroupName;

    //@NotBlank
    @Schema(title = "访问路径")
    String path;

    @Schema(title = "内容")
    String content;

    //@Size(max = 128)
    @Schema(title = "系统子域")
    String domain;

    //@NotBlank
    //@Size(max = 128)
    @Schema(title = "名称")
    String name;
    @Schema(title = "模糊匹配 - 名称")
    @Contains
    String containsName;

    //@Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    String pinyinName;
    @Schema(title = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    String containsPinyinName;

    //@InjectVar()
    //@Size(max = 128)
    @Schema(title = "创建者")
    String creator;

    //@NotNull
    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteCreateTime;

    @Schema(title = "创建时间-日期范围，格式：yyyyMMdd-yyyyMMdd，大于等于且小余等于")
    @Between(paramDelimiter = "-", patterns = {"yyyyMMdd"})
    String betweenCreateTime;

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteLastUpdateTime;

    @Schema(title = "更新时间-日期范围，格式：yyyyMMdd-yyyyMMdd，大于等于且小余等于")
    @Between(paramDelimiter = "-", patterns = {"yyyyMMdd"})
    String betweenLastUpdateTime;

    @Schema(title = "排序代码")
    Integer orderCode;

    //@NotNull
    @Schema(title = "是否允许")
    Boolean enable;

    //@NotNull
    @Schema(title = "是否可编辑")
    Boolean editable;

    //@Size(max = 512)
    @Schema(title = "备注")
    String remark;

    public StatSimpleApiReq(String id) {
        this.id = id;
    }

    //
    //@Schema(title = "是否按状态分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore
    //private boolean isGroupByStatus;

    //@Schema(title = "是否按日期分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore //
    //private boolean isGroupByDate;


    @PostConstruct
    public void preStat() {
        //@todo 统计之前初始化数据
    }

    @Schema(title = "简单动态接口统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(title = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(title = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_SimpleApi.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(title = "记录数")
        @Count
        Integer cnt;

        //@Schema(title = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_SimpleApi.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(title = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
