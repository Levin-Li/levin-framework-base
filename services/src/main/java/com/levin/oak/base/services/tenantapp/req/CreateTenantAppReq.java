package com.levin.oak.base.services.tenantapp.req;

//import static com.levin.oak.base.ModuleOption.*;
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
import static com.levin.oak.base.entities.E_TenantApp.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 新增租户应用
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月14日 下午3:54:11, 代码生成哈希校验码：[4b2828c434a8cb1faf44ed620ce927ea]，请不要修改和删除此行内容。
 *
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = TenantApp.class, alias = E_TenantApp.ALIAS)
public class CreateTenantAppReq extends MultiTenantReq {

    private static final long serialVersionUID = 1292984857L;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_logo )
    String logo;

    @Schema(title = L_entryUrl )
    String entryUrl;

    @Schema(title = L_infoUrl )
    String infoUrl;

    @Schema(title = L_modules )
    @InjectVar(domain = "dao",  expectBaseType = String.class,  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    List<String> modules;

    @Schema(title = L_appSecret , description = D_appSecret )
    String appSecret;

    @Schema(title = L_salePrice , description = D_salePrice )
    BigDecimal salePrice;

    @Schema(title = L_purchasePrice , description = D_purchasePrice )
    BigDecimal purchasePrice;

    @Schema(title = L_orderNo , description = D_orderNo )
    String orderNo;

    @Schema(title = L_expiredTime , description = D_expiredTime )
    Date expiredTime;

    @Schema(title = L_creator , hidden = true)
    //@Size(max = 128)
    String creator;

    @Schema(title = L_createTime , hidden = true)
    //@NotNull
    Date createTime;

    @Schema(title = L_lastUpdateTime , hidden = true)
    Date lastUpdateTime;

    @Schema(title = L_orderCode , hidden = true)
    Integer orderCode;

    @Schema(title = L_enable , hidden = true)
    //@NotNull
    Boolean enable;

    @Schema(title = L_editable , hidden = true)
    //@NotNull
    Boolean editable;

    @Schema(title = L_remark , hidden = true)
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等

        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
    }
}
