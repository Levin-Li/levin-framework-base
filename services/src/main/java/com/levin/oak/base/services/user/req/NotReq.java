package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.annotation.NotEq;
import com.levin.commons.dao.annotation.logic.AND;
import com.levin.oak.base.entities.E_User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.io.Serializable;


@Schema(title = "")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class NotReq implements Serializable {

    private static final long serialVersionUID = -445263479L;

    @AND(autoClose = true)
    @NotEq(require = true, value = E_User.email)
    @NotEq(require = true, value = E_User.telephone)
    protected String account;

    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }

}
