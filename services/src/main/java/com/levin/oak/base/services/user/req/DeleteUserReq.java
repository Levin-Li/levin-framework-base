package com.levin.oak.base.services.user.req;

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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.User;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_User.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.User.*;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import java.util.Date;
import com.levin.oak.base.entities.Org;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 删除用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午9:16:20, 代码生成哈希校验码：[ff6e17b672083a3b585c9e2857d452b0]，请不要修改和删除此行内容。
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
@Data

// @AllArgsConstructor

@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
public class DeleteUserReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Schema(title = L_id + "集合", required = true, requiredMode = REQUIRED)
    @In(value = E_User.id)
    @NotEmpty
    private String[] idList;

    public DeleteUserReq(String... idList) {
        this.idList = idList;
    }

    public DeleteUserReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }

    @PostConstruct
    public void preDelete() {
        // @todo 删除之前初始化数据
    }
}
