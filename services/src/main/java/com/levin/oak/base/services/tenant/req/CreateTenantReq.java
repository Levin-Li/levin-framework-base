package com.levin.oak.base.services.tenant.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


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
 *  新增租户
 *  //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(description = "新增租户")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS)
public class CreateTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;


    @Schema(description = "租户头像"  )
    private String logo;

    @Schema(description = "企业信用编码"  )
    private String code;

    @Schema(description = "租户编码"  , required = true)
    @NotBlank
    private String tenantKey;

    @Schema(description = "帐号余额"  )
    private Double balance;

    @Schema(description = "总许可数"  )
    private Integer licenseCnt;

    @Schema(description = "剩余许可数"  )
    private Integer remainingLicenseCnt;

    @Schema(description = "到期时间"  )
    private Date licenseExpire;

    @Schema(description = "联系人"  )
    private String contractPerson;

    @Schema(description = "联系电话"  )
    private String contractPhone;

    @Schema(description = "域名列表"  )
    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> domainList;

    @Schema(description = "appId"  )
    @Size(max = 32)
    private String appId;

    @Schema(description = "appSecret"  )
    @Size(max = 256)
    private String appSecret;

    @Schema(description = "EncryptKey"  )
    private String encryptKey;

    @Schema(description = "名称"  , required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(description = "拼音，格式：全拼(简拼)"  )
    @Size(max = 128)
    private String pinyinName;

    @Schema(description = "创建者" , hidden = true )
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(description = "创建时间" , hidden = true )
    //@NotNull
    private Date createTime;

    @Schema(description = "更新时间" , hidden = true )
    private Date lastUpdateTime;

    @Schema(description = "排序代码" , hidden = true )
    private Integer orderCode;

    @Schema(description = "是否允许" , hidden = true )
    //@NotNull
    private Boolean enable;

    @Schema(description = "是否可编辑" , hidden = true )
    //@NotNull
    private Boolean editable;

    @Schema(description = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
