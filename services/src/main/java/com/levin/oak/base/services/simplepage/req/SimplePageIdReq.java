package com.levin.oak.base.services.simplepage.req;

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
import com.levin.oak.base.services.simplepage.info.*;
import com.levin.oak.base.entities.SimplePage;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_SimplePage.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 简单页面 主键通用请求
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月14日 下午3:54:12, 代码生成哈希校验码：[e4cd9f1cefd1be9b98bad109ab8e990f]，请不要修改和删除此行内容。
 *
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
@TargetOption(entityClass = SimplePage.class, alias = E_SimplePage.ALIAS, resultClass = SimplePageInfo.class)
public class SimplePageIdReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1598619295L;

    @Schema(title = L_id , required = true, requiredMode = REQUIRED)
    @Eq(require = true)
    //@NotNull
    protected String id;

    public SimplePageIdReq updateIdWhenNotBlank(String id){
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
