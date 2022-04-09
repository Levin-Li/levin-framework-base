package com.levin.oak.base.services.tenant.req;

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

import com.levin.oak.base.services.tenant.info.*;
import com.levin.oak.base.entities.Tenant;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.support.InjectConsts;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
    import java.util.List;
    import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
////////////////////////////////////

/**
 *  统计租户
 *  @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(description = "统计租户")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS,
     //连接统计
    //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
    resultClass = StatTenantReq.Result.class
)
public class StatTenantReq extends BaseReq{

    private static final long serialVersionUID = 1557223144L;


    //@NotBlank

    @Schema(description = "ID")
     String id;


    @Schema(description = "租户头像")
     String logo;


    @Schema(description = "企业信用编码")
     String code;

    //@NotBlank

    @Schema(description = "租户编码")
     String tenantKey;


    @Schema(description = "帐号余额")
     Double balance;


    @Schema(description = "总许可数")
     Integer licenseCnt;


    @Schema(description = "剩余许可数")
     Integer remainingLicenseCnt;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于到期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
     Date gteLicenseExpire;

    @Schema(description = "小于等于到期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
     Date lteLicenseExpire;



    @Schema(description = "联系人")
     String contractPerson;


    @Schema(description = "联系电话")
     String contractPhone;

    //@InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")

    @Schema(description = "域名列表")
     List<String> domainList;
    @Schema(description = "模糊匹配 - 域名列表")
    @Contains
     List<String> containsDomainList;

    //@Size(max = 32)

    @Schema(description = "appId")
     String appId;

    //@Size(max = 256)

    @Schema(description = "appSecret")
     String appSecret;


    @Schema(description = "EncryptKey")
     String encryptKey;

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

    public StatTenantReq(String id) {
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

    @Schema(description = "租户统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(description = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(description = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_Tenant.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        //@Schema(description = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_Tenant.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(description = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
