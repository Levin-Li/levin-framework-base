package com.levin.oak.base.services.org.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.stat.Count;
import com.levin.oak.base.entities.E_Org;
import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.Org.State;
import com.levin.oak.base.entities.Org.Type;
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
 * 统计机构
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(title = "统计机构")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS,
        //连接统计
        //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
        resultClass = StatOrgReq.Result.class
)
public class StatOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;


    //@NotNull

    @Schema(title = "id")
    String id;

    //@Size(max = 128)

    @Schema(title = "编码")
    String code;
    @Schema(title = "模糊匹配 - 编码")
    @Contains
    String containsCode;


    @Schema(title = "图标")
    String icon;

    //@NotNull

    @Schema(title = "状态")
    State state;

    //@NotNull

    @Schema(title = "类型")
    Type type;

    //@Size(max = 64)

    @Schema(title = "所属行业")
    String industries;

    //@NotBlank
    //@Size(max = 64)

    @Schema(title = "区域编码")
    String areaCode;
    @Schema(title = "模糊匹配 - 区域编码")
    @Contains
    String containsAreaCode;


    //@Size(max = 128)

    @Schema(title = "机构级别")
    String level;

    //@NotBlank
    //@Size(max = 128)

    @Schema(title = "机构类别")
    String category;

    //@NotNull

    @Schema(title = "是否外部机构")
    Boolean isExternal;

    //@Size(max = 64)

    @Schema(title = "联系人")
    String contacts;
    @Schema(title = "模糊匹配 - 联系人")
    @Contains
    String containsContacts;

    //@Size(max = 20)

    @Schema(title = "联系电话")
    String phones;
    @Schema(title = "模糊匹配 - 联系电话")
    @Contains
    String containsPhones;

    //@Size(max = 32)

    @Schema(title = "联系邮箱")
    String emails;


    @Schema(title = "联系地址")
    String address;
    @Schema(title = "模糊匹配 - 联系地址")
    @Contains
    String containsAddress;

    //@Size(max = 32)

    @Schema(title = "邮政编码")
    String zipCode;


    @Schema(title = "父ID")
    String parentId;


    //@Size(max = 1800)

    @Schema(title = "id路径， 使用|包围，如|1|3|15|")
    String idPath;

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


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteLastUpdateTime;


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

    public StatOrgReq(String id) {
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

    @Schema(title = "机构统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(title = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(title = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_Org.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(title = "记录数")
        @Count
        Integer cnt;

        //@Schema(title = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_Org.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(title = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
