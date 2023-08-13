package com.levin.oak.base.services.tenant.req;

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
import com.levin.oak.base.services.tenant.info.*;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Tenant.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 平台租户 主键通用请求
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月13日 下午4:53:13, 代码生成哈希校验码：[94112fb16bac9c24a7c368aed22b1c63]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME + " 主键通用查询")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS, resultClass = TenantInfo.class)
public class TenantIdReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @Eq(require = true)
    // @NotNull
    protected String id;

    public TenantIdReq updateIdWhenNotBlank(String id) {
        if (isNotBlank(id)) {
            this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preQuery() {
        // @todo ID 查询之前初始化数据
    }
}
