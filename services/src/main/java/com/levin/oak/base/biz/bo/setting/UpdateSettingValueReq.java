package com.levin.oak.base.biz.bo.setting;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.InjectConst;
import com.levin.oak.base.entities.E_Setting;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.Setting.ValueType;
import com.levin.oak.base.services.commons.req.MultiTenantOrgReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.levin.oak.base.entities.E_Setting.*;
import static com.levin.oak.base.entities.EntityConst.UPDATE_ACTION;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
////////////////////////////////////

/**
 * 更新系统设置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月6日 下午3:14:49, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[c544d150c59a0458f8863f81528a4f82], 请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
//默认更新注解
@Update
public class UpdateSettingValueReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 147875794L;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConst.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_code)
    @Eq(require = true)
    String code;

    @Schema(title = L_valueContent,description = "总是更新")
    @Update(condition = "")
    String valueContent;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }

}
