package com.levin.oak.base.services.area.req;

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

import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Area.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
import com.levin.oak.base.entities.Area.*;

////////////////////////////////////

/**
 * 更新区域
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月21日 下午4:56:56, 代码生成哈希校验码：[417d88667a0d098c36343b246eafd1a9]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS)
// 默认更新注解
@Update
public class UpdateAreaReq extends BaseReq {

    private static final long serialVersionUID = -445860277L;

    @Schema(title = L_code, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String code;

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件", hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Schema(title = L_icon)
    String icon;

    @Size(max = 64)
    @Schema(title = L_parentCode)
    String parentCode;

    @Schema(title = L_type)
    Type type;

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

    public UpdateAreaReq(String code) {
        this.code = code;
    }

    public UpdateAreaReq updateCodeWhenNotBlank(String code) {
        if (isNotBlank(code)) {
            this.code = code;
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
