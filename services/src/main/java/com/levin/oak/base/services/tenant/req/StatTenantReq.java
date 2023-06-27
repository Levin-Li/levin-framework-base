package com.levin.oak.base.services.tenant.req;

import static com.levin.oak.base.entities.EntityConst.*;

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
import com.levin.commons.service.support.*;

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
import static com.levin.oak.base.entities.E_Tenant.*;
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
 *  统计平台租户
 *  @Author Auto gen by simple-dao-codegen 2023年6月28日 上午12:45:54
 *  代码生成哈希校验码：[a153989e8caa5d6628fcdb138f636022]
 */
@Schema(title = STAT_ACTION + BIZ_NAME)
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


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 128)
    @Schema(title = L_sysName)
    String sysName;

    @Schema(title = L_sysLogo)
    String sysLogo;

    @Schema(title = L_logo)
    String logo;

    @Size(max = 128)
    @Schema(title = L_code)
    String code;

    @NotBlank
    @Schema(title = L_tenantKey)
    String tenantKey;

    @Schema(title = L_balance)
    Double balance;

    @Schema(title = L_licenseCnt)
    Integer licenseCnt;

    @Schema(title = L_remainingLicenseCnt)
    Integer remainingLicenseCnt;

    @Schema(title = L_licenseExpire , description = "大于等于" + L_licenseExpire)
    @Gte
    Date gteLicenseExpire;

    @Schema(title = L_licenseExpire , description = "小于等于" + L_licenseExpire)
    @Lte
    Date lteLicenseExpire;

    //@Schema(title = L_licenseExpire + "-日期范围")
    //@Between(paramDelimiter = "-")
    //String betweenLicenseExpire;

    @Size(max = 32)
    @Schema(title = L_contractPerson)
    String contractPerson;

    @Size(max = 32)
    @Schema(title = L_contractPhone)
    String contractPhone;

    @Size(max = 1200)
    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao",  converter = JsonStrLikeConverter.class, isRequired = "false")
    @Schema(title = L_domainList)
    List<String> domainList;

    @Size(max = 64)
    @Schema(title = L_appId)
    String appId;

    @Size(max = 512)
    @Schema(title = L_appSecret)
    String appSecret;

    @Size(max = 512)
    @Schema(title = L_encryptKey)
    String encryptKey;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Schema(title = "模糊匹配-" + L_name)
    @Contains
    String containsName;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;

    @Schema(title = "模糊匹配-" + L_pinyinName , description = D_pinyinName)
    @Contains
    String containsPinyinName;

    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @NotNull
    @Schema(title = L_createTime , description = "大于等于" + L_createTime)
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime , description = "小于等于" + L_createTime)
    @Lte
    Date lteCreateTime;

    //@Schema(title = L_createTime + "-日期范围")
    //@Between(paramDelimiter = "-")
    //String betweenCreateTime;

    @Schema(title = L_lastUpdateTime , description = "大于等于" + L_lastUpdateTime)
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = L_lastUpdateTime , description = "小于等于" + L_lastUpdateTime)
    @Lte
    Date lteLastUpdateTime;

    //@Schema(title = L_lastUpdateTime + "-日期范围")
    //@Between(paramDelimiter = "-")
    //String betweenLastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @NotNull
    @Schema(title = L_enable)
    Boolean enable;

    @NotNull
    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
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

    @Schema(description = BIZ_NAME + "统计结果")
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
