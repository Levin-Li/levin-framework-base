package com.levin.oak.base.services.tenant.req;

// import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Tenant.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 新增平台租户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月15日 下午6:31:18, 代码生成哈希校验码：[635ee21eec43a5f244b0a21599a6b669]，请不要修改和删除此行内容。
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
// @EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS)
public class CreateTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;

    @Schema(title = L_sysName)
    @Size(max = 128)
    String sysName;

    @Schema(title = L_sysLogo)
    String sysLogo;

    @Schema(title = L_logo)
    String logo;

    @Schema(title = L_code)
    @Size(max = 128)
    String code;

    @Schema(title = L_tenantKey)
    @NotBlank
    String tenantKey;

    @Schema(title = L_balance)
    Double balance;

    @Schema(title = L_licenseCnt)
    Integer licenseCnt;

    @Schema(title = L_remainingLicenseCnt)
    Integer remainingLicenseCnt;

    @Schema(title = L_licenseExpire)
    Date licenseExpire;

    @Schema(title = L_contractPerson)
    @Size(max = 32)
    String contractPerson;

    @Schema(title = L_contractPhone)
    @Size(max = 32)
    String contractPhone;

    @Schema(title = L_domainList)
    @Size(max = 1200)
    @InjectVar(domain = "dao", isRequired = "false")
    List<String> domainList;

    @Schema(title = L_appId)
    @Size(max = 64)
    String appId;

    @Schema(title = L_appSecret)
    @Size(max = 512)
    String appSecret;

    @Schema(title = L_encryptKey)
    @Size(max = 512)
    String encryptKey;

    @Schema(title = L_name)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = L_pinyinName, description = D_pinyinName)
    @Size(max = 128)
    String pinyinName;

    @Schema(title = L_creator, hidden = true)
    // @Size(max = 128)
    // @InjectVar(value = InjectConsts.USER_ID, isRequired = "false")
    String creator;

    @Schema(title = L_createTime, hidden = true)
    // @NotNull
    Date createTime;

    @Schema(title = L_lastUpdateTime, hidden = true)
    Date lastUpdateTime;

    @Schema(title = L_orderCode, hidden = true)
    Integer orderCode;

    @Schema(title = L_enable, hidden = true)
    // @NotNull
    Boolean enable;

    @Schema(title = L_editable, hidden = true)
    // @NotNull
    Boolean editable;

    @Schema(title = L_remark, hidden = true)
    // @Size(max = 512)
    String remark;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等

        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }
    }
}
