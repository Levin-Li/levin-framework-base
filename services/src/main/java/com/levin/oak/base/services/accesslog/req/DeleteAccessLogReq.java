package com.levin.oak.base.services.accesslog.req;

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

import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AccessLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;

////////////////////////////////////

/**
 * 删除访问日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:44, 代码生成哈希校验码：[cd161ab119ef8b1c3fc6d504844e194f]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
public class DeleteAccessLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1030736962L;

    @Schema(title = L_id + "集合", required = true, requiredMode = REQUIRED)
    @In(value = E_AccessLog.id)
    @NotEmpty
    private Long[] idList;

    public DeleteAccessLogReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteAccessLogReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }

    @PostConstruct
    public void preDelete() {
        // @todo 删除之前初始化数据
    }
}
