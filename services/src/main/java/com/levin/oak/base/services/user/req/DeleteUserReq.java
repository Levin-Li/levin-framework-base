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

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.User;
import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.User.*;
    import java.util.Date;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
////////////////////////////////////

/**
 *  删除用户
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
@Schema(description = "删除用户")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
public class DeleteUserReq implements ServiceReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "id集合")
    @In(E_User.id)
    @Validator(expr = "id != null || ( ids != null &&  ids.length > 0)" , promptInfo = "删除用户必须指定ID")
    private Long[] ids;


    public DeleteUserReq(Long id) {
        this.id = id;
    }

    public DeleteUserReq(Long... ids) {
        this.ids = ids;
    }
}