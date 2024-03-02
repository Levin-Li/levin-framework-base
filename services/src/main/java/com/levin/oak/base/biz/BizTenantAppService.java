package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.validation.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.TenantApp;
import com.levin.oak.base.biz.bo.tenantapp.*;
import com.levin.oak.base.services.tenantapp.*;
import com.levin.oak.base.services.tenantapp.req.*;
import com.levin.oak.base.services.tenantapp.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.dao.domain.NamedObject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  租户应用-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:05, 代码生成哈希校验码：[c43da5dcd5ec2aeff52ca8d96dfd51e2]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_TenantApp.BIZ_NAME + "-业务服务", description = "")
public interface BizTenantAppService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatTenantAppReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatTenantAppReq.Result stat(StatTenantAppReq req, Paging paging);
}
