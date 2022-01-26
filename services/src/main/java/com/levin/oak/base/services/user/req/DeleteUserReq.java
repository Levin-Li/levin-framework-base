package com.levin.oak.base.services.user.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

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
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.User.*;
import java.util.Date;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
////////////////////////////////////

/**
 *  删除用户
 *  //Auto gen by simple-dao-codegen 2022-1-26 17:07:14
 */
@Schema(description = "删除用户")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
public class DeleteUserReq extends MultiTenantReq {

    private static final long serialVersionUID = -445263479L;


    @Schema(description = "id集合")
    @In(value = E_User.id, require = true)
    @NotEmpty
    private Long[] idList;

    public DeleteUserReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteUserReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
