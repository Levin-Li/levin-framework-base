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
import com.levin.oak.base.services.user.info.*;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_User.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.User.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
////////////////////////////////////

/**
 *  用户 主键通用请求
 *  //@author Auto gen by simple-dao-codegen, @time: 2023年6月29日 下午6:00:39, 请不要修改和删除此行内容。
 *  代码生成哈希校验码：[a5e5b019c3692a080eaa2c647f6a5118], 请不要修改和删除此行内容。
 */

@Schema(title =  BIZ_NAME + " 主键通用查询")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS, resultClass = UserInfo.class)
public class UserIdReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(title = L_id , required = true, requiredMode = REQUIRED)
    @Eq(require = true)
    //@NotNull
    protected String id;

    public UserIdReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
