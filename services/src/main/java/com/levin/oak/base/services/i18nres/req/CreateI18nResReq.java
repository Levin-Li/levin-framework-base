package com.levin.oak.base.services.i18nres.req;

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
import static com.levin.oak.base.entities.E_I18nRes.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import java.util.Date;
import com.levin.commons.dao.domain.MultiTenantSharedObject;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 新增国际化资源
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月27日 下午3:55:27, 代码生成哈希校验码：[90e1250e2ad8d90783bbce0fd8a01e32]，请不要修改和删除此行内容。
 *
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS)
public class CreateI18nResReq extends MultiTenantReq<CreateI18nResReq> {

    private static final long serialVersionUID = -1681554652L;

    @Schema(title = L_tenantShared )
    @NotNull
    boolean tenantShared;

    @Schema(title = L_domain )
    String domain;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 512)
    String name;

    @Schema(title = L_category )
    @Size(max = 128)
    String category;

    @Schema(title = L_lang , description = D_lang )
    @NotBlank
    @Size(max = 64)
    String lang;

    @Schema(title = L_country , description = D_country )
    @NotBlank
    @Size(max = 64)
    String country;

    @Schema(title = L_label )
    @NotBlank
    @Size(max = 1800)
    String label;

    @Schema(title = L_creator , hidden = true)
    @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Size(max = 128)
    String creator;

    @Schema(title = L_createTime , hidden = true)
    Date createTime;

    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;

    @Schema(title = L_orderCode )
    Integer orderCode;

    @Schema(title = L_enable )
    Boolean enable;

    @Schema(title = L_editable )
    Boolean editable;

    @Schema(title = L_remark )
    @Size(max = 512)
    String remark;

    @Schema(title = L_optimisticLock )
    Integer optimisticLock;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等

        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
    }
}
