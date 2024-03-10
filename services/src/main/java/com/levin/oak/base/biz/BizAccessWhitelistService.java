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
import com.levin.oak.base.entities.AccessWhitelist;
import com.levin.oak.base.biz.bo.accesswhitelist.*;
import com.levin.oak.base.services.accesswhitelist.*;
import com.levin.oak.base.services.accesswhitelist.req.*;
import com.levin.oak.base.services.accesswhitelist.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  访问白名单-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月10日 上午9:21:45, 代码生成哈希校验码：[1dd1d3b60837a9e0ac301c8ebc09277c]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_AccessWhitelist.BIZ_NAME + "-业务服务", description = "")
public interface BizAccessWhitelistService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatAccessWhitelistReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatAccessWhitelistReq.Result stat(StatAccessWhitelistReq req, Paging paging);
}
