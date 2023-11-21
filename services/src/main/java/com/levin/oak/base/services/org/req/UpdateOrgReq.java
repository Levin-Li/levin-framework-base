package com.levin.oak.base.services.org.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Org.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 更新机构
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月21日 下午4:56:55, 代码生成哈希校验码：[4aee182149d6f3512234e8604ead5e20]，请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
// 默认更新注解
@Update
public class UpdateOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件", hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 64)
    @Schema(title = L_parentId)
    String parentId;

    @Size(max = 64)
    @Schema(title = L_code, description = D_code)
    String code;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_state)
    State state;

    @Schema(title = L_type)
    Type type;

    @Size(max = 64)
    @Schema(title = L_industries)
    String industries;

    @Size(max = 64)
    @Schema(title = L_areaCode)
    String areaCode;

    @Size(max = 128)
    @Schema(title = L_level, description = D_level)
    String level;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category, description = D_category)
    String category;

    @Schema(title = L_isExternal, description = D_isExternal)
    Boolean isExternal;

    @Size(max = 64)
    @Schema(title = L_contacts)
    String contacts;

    @Size(max = 20)
    @Schema(title = L_phones)
    String phones;

    @Size(max = 32)
    @Schema(title = L_emails)
    String emails;

    @Schema(title = L_address)
    String address;

    @Size(max = 32)
    @Schema(title = L_zipCode)
    String zipCode;

    @Schema(title = L_extInfo)
    String extInfo;

    @Size(max = 1800)
    @Schema(title = L_idPath, description = D_idPath)
    String idPath;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName, description = D_pinyinName)
    String pinyinName;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    public UpdateOrgReq(String id) {
        this.id = id;
    }

    public UpdateOrgReq updateIdWhenNotBlank(String id) {
        if (isNotBlank(id)) {
            this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        // @todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }
}
