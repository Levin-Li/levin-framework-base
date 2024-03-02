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
import com.levin.oak.base.entities.Whitelist;
import com.levin.oak.base.biz.bo.whitelist.*;
import com.levin.oak.base.services.whitelist.*;
import com.levin.oak.base.services.whitelist.req.*;
import com.levin.oak.base.services.whitelist.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  白名单-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午8:20:51, 代码生成哈希校验码：[6061eb694369a85be940513d321dc602]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_Whitelist.BIZ_NAME + "-业务服务", description = "")
public interface BizWhitelistService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatWhitelistReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatWhitelistReq.Result stat(StatWhitelistReq req, Paging paging);
}
