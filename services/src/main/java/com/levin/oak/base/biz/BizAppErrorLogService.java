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
import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.biz.bo.apperrorlog.*;
import com.levin.oak.base.services.apperrorlog.*;
import com.levin.oak.base.services.apperrorlog.req.*;
import com.levin.oak.base.services.apperrorlog.info.*;

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
 *  应用错误日志-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:27, 代码生成哈希校验码：[13382c43910530c239e063ce1c2a43a6]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_AppErrorLog.BIZ_NAME + "-业务服务", description = "")
public interface BizAppErrorLogService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatAppErrorLogReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatAppErrorLogReq.Result stat(StatAppErrorLogReq req, Paging paging);
}
