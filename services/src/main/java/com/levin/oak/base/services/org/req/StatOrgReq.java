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
import java.io.Serializable;

import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.support.InjectConsts;
    import com.levin.commons.service.domain.InjectVar;
    import com.levin.oak.base.entities.Org.*;
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
 *  统计机构
 *  @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(description = "统计机构")
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
public class StatOrgReq extends MultiTenantReq{

    private static final long serialVersionUID = -1399842458L;


    //@NotNull

    @Schema(description = "id")
     Long id;

    //@Size(max = 128)

    @Schema(description = "编码")
     String code;
    @Schema(description = "模糊匹配 - 编码")
    @Contains
     String containsCode;


    @Schema(description = "图标")
     String icon;

    //@NotNull

    @Schema(description = "状态")
     State state;

    //@NotNull

    @Schema(description = "类型")
     Type type;

    //@Size(max = 64)

    @Schema(description = "所属行业")
     String industries;

    //@NotBlank
    //@Size(max = 64)

    @Schema(description = "区域编码")
     String areaCode;
    @Schema(description = "模糊匹配 - 区域编码")
    @Contains
     String containsAreaCode;



    //@Size(max = 128)

    @Schema(description = "机构级别")
     String level;

    //@NotBlank
    //@Size(max = 128)

    @Schema(description = "机构类别")
     String category;

    //@NotNull

    @Schema(description = "是否外部机构")
     Boolean isExternal;

    //@Size(max = 64)

    @Schema(description = "联系人")
     String contacts;
    @Schema(description = "模糊匹配 - 联系人")
    @Contains
     String containsContacts;

    //@Size(max = 20)

    @Schema(description = "联系电话")
     String phones;
    @Schema(description = "模糊匹配 - 联系电话")
    @Contains
     String containsPhones;

    //@Size(max = 32)

    @Schema(description = "联系邮箱")
     String emails;


    @Schema(description = "联系地址")
     String address;
    @Schema(description = "模糊匹配 - 联系地址")
    @Contains
     String containsAddress;

    //@Size(max = 32)

    @Schema(description = "邮政编码")
     String zipCode;


    @Schema(description = "父ID")
     Long parentId;





    //@Size(max = 1800)

    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
     String idPath;

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

    public StatOrgReq(Long id) {
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

    @Schema(description = "机构统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(description = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(description = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_Org.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        //@Schema(description = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_Org.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(description = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
