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
import com.levin.oak.base.biz.bo.appclient.*;
import com.levin.oak.base.services.appclient.*;
import com.levin.oak.base.services.appclient.req.*;
import com.levin.oak.base.services.appclient.info.*;

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
 *  应用接入-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:26, 代码生成哈希校验码：[07173a2862805c3e669b40b95d166457]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_AppClient.BIZ_NAME + "-业务服务", description = "")
public interface BizAppClientService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatAppClientReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatAppClientReq.Result stat(StatAppClientReq req, Paging paging);
}
