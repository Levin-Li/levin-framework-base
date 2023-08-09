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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;
import com.levin.oak.base.services.area.info.*;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Area.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Area.*;

////////////////////////////////////

/**
 * 区域 主键通用请求
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月10日 上午2:41:24, 代码生成哈希校验码：[dd4274f8df99157df26d057343a114a7]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS, resultClass = AreaInfo.class)
public class AreaIdReq extends BaseReq {

    private static final long serialVersionUID = -445860277L;

    @Schema(title = L_code, required = true, requiredMode = REQUIRED)
    @Eq(require = true)
    // @NotNull
    protected String code;

    public AreaIdReq updateCodeWhenNotBlank(String code) {
        if (isNotBlank(code)) {
            this.code = code;
        }
        return this;
    }

    @PostConstruct
    public void preQuery() {
        // @todo ID 查询之前初始化数据
    }
}
