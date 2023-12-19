package com.levin.oak.base.services.apperrorlog.req;

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
import com.levin.oak.base.services.apperrorlog.info.*;
import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppErrorLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 应用错误日志 主键通用请求
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:27, 代码生成哈希校验码：[97499bca65918d7811d5ddea0128d746]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS, resultClass = AppErrorLogInfo.class)
public class AppErrorLogIdReq extends MultiTenantReq<AppErrorLogIdReq> {

    private static final long serialVersionUID = 1594864095L;

    @Schema(title = L_id , required = true, requiredMode = REQUIRED)
    @Eq(require = true)
    @NotNull
    protected Long id;

    public AppErrorLogIdReq updateIdWhenNotBlank(Long id){
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
