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
import com.levin.oak.base.entities.AppClient;

import com.levin.oak.base.services.appclient.*;
import com.levin.oak.base.services.appclient.req.*;
import com.levin.oak.base.services.appclient.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 应用接入-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月13日 下午4:53:12, 代码生成哈希校验码：[f88ff0051e53466f372e8d46e006eca0]，请不要修改和删除此行内容。
 */
@Tag(name = E_AppClient.BIZ_NAME + "-业务服务", description = "")
public interface BizAppClientService {}
