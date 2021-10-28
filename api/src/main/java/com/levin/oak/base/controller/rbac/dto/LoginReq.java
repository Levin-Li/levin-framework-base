package com.levin.oak.base.controller.rbac.dto;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.commons.dao.annotation.misc.Validator;
import com.levin.commons.service.domain.ServiceReq;
import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.E_Dict;
import com.levin.oak.base.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Schema(description = "登录请求")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_Dict.ALIAS)
public class LoginReq implements ServiceReq {

    private static final long serialVersionUID = -445779596L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "id集合")
    @In(E_Dict.id)
    @Validator(expr = "id != null || ( ids != null &&  ids.length > 0)" , promptInfo = "删除字典必须指定ID")
    private Long[] ids;


    public LoginReq(Long id) {
        this.id = id;
    }

    public LoginReq(Long... ids) {
        this.ids = ids;
    }
}
