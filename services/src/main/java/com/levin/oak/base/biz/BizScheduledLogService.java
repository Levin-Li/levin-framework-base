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
import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.biz.bo.scheduledlog.*;
import com.levin.oak.base.services.scheduledlog.*;
import com.levin.oak.base.services.scheduledlog.req.*;
import com.levin.oak.base.services.scheduledlog.info.*;

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
 *  调度日志-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:26, 代码生成哈希校验码：[277de61429ff080b2b41840bca8958cc]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_ScheduledLog.BIZ_NAME + "-业务服务", description = "")
public interface BizScheduledLogService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatScheduledLogReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatScheduledLogReq.Result stat(StatScheduledLogReq req, Paging paging);
}
