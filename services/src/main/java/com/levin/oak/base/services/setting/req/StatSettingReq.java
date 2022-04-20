package com.levin.oak.base.services.setting.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.stat.Count;
import com.levin.oak.base.entities.E_Setting;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.Setting.ValueType;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 统计系统设置
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(description = "统计系统设置")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS,
        //连接统计
        //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
        resultClass = StatSettingReq.Result.class
)
public class StatSettingReq extends MultiTenantReq {

    private static final long serialVersionUID = 147875794L;


    //@NotNull

    @Schema(description = "id")
    Long id;

    //@NotBlank
    //@Size(max = 64)

    @Schema(description = "分类名称")
    String categoryName;

    //@Size(max = 64)

    @Schema(description = "分组名称")
    String groupName;
    @Schema(description = "模糊匹配 - 分组名称")
    @Contains
    String containsGroupName;

    //@NotBlank
    //@Size(max = 64)

    @Schema(description = "编码")
    String code;

    //@NotNull

    @Schema(description = "值类型")
    ValueType valueType;


    @Schema(description = "值")
    String value;


    @Schema(description = "值是否可空")
    Boolean nullable;

    //@Size(max = 64)

    @Schema(description = "输入占位提示")
    String inputPlaceholder;

    //@Size(max = 128)

    @Schema(description = "系统子域")
    String domain;

    //@NotBlank
    //@Size(max = 128)

    @Schema(description = "名称")
    String name;
    @Schema(description = "模糊匹配 - 名称")
    @Contains
    String containsName;

    //@Size(max = 128)

    @Schema(description = "拼音，格式：全拼(简拼)")
    String pinyinName;
    @Schema(description = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    String containsPinyinName;

    //@InjectVar()
    //@Size(max = 128)

    @Schema(description = "创建者")
    String creator;

    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteLastUpdateTime;


    @Schema(description = "排序代码")
    Integer orderCode;

    //@NotNull

    @Schema(description = "是否允许")
    Boolean enable;

    //@NotNull

    @Schema(description = "是否可编辑")
    Boolean editable;

    //@Size(max = 512)

    @Schema(description = "备注")
    String remark;

    public StatSettingReq(Long id) {
        this.id = id;
    }

    //
    //@Schema(description = "是否按状态分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore
    //private boolean isGroupByStatus;

    //@Schema(description = "是否按日期分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore //
    //private boolean isGroupByDate;


    @PostConstruct
    public void preStat() {
        //@todo 统计之前初始化数据
    }

    @Schema(description = "系统设置统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(description = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(description = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_Setting.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        //@Schema(description = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_Setting.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(description = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
