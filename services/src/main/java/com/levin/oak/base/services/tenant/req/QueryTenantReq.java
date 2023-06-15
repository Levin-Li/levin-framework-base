package com.levin.oak.base.services.tenant.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.logic.OR;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.JsonStrLikeConverter;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.services.commons.req.BaseReq;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 查询租户
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 18:38:00
 */
@Schema(title = "查询租户")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS, resultClass = TenantInfo.class)
public class QueryTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;

    @Ignore
    @Schema(title = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(title = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotBlank

    @Schema(title = "ID")
    private String id;


    @Schema(title = "系统名称")
    private String sysName;


    @Schema(title = "系统Logo")
    private String sysLogo;

    @Schema(title = "租户头像")
    private String logo;


    @Schema(title = "企业信用编码")
    private String code;


    //@NotBlank

    @Schema(title = "租户编码")
    private String tenantKey;


    @Schema(title = "帐号余额")
    private Double balance;


    @Schema(title = "总许可数")
    private Integer licenseCnt;


    @Schema(title = "剩余许可数")
    private Integer remainingLicenseCnt;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于到期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLicenseExpire;

    @Schema(title = "小于等于到期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLicenseExpire;


    @Schema(title = "联系人")
    private String contractPerson;


    @Schema(title = "联系电话")
    private String contractPhone;


    @Schema(title = "模糊匹配 - 域名列表")
    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao",converter = JsonStrLikeConverter.class, isRequired = "false")
    private List<String> containsDomainList;


    //@Size(max = 32)

    @Schema(title = "appId")
    private String appId;


    //@Size(max = 256)

    @Schema(title = "appSecret")
    private String appSecret;


    @Schema(title = "EncryptKey")
    private String encryptKey;


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


    public QueryTenantReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据


    }

}
