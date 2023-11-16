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

import com.levin.oak.base.services.tenantapp.*;
import com.levin.oak.base.services.tenantapp.req.*;
import com.levin.oak.base.services.tenantapp.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 租户应用-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午9:16:19, 代码生成哈希校验码：[9c819afe4a359d0237c007b250b981e0]，请不要修改和删除此行内容。
 */
@Tag(name = E_TenantApp.BIZ_NAME + "-业务服务", description = "")
public interface BizTenantAppService {}
