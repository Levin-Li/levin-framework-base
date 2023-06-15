package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 用户 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */

@Schema(title = "用户 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS, resultClass = UserInfo.class)
public class UserIdReq extends MultiTenantReq {

    private static final long serialVersionUID = -445263479L;


    @Schema(title = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected String id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
