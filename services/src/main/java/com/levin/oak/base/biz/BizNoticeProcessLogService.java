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
import com.levin.oak.base.entities.NoticeProcessLog;
import com.levin.oak.base.biz.bo.noticeprocesslog.*;
import com.levin.oak.base.services.noticeprocesslog.*;
import com.levin.oak.base.services.noticeprocesslog.req.*;
import com.levin.oak.base.services.noticeprocesslog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.NoticeProcessLog.*;
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  通知处理日志-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月18日 下午3:08:57, 代码生成哈希校验码：[9ad3fbf6b7bc2bffc81ece09e18793ef]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_NoticeProcessLog.BIZ_NAME + "-业务服务", description = "")
public interface BizNoticeProcessLogService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatNoticeProcessLogReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatNoticeProcessLogReq.Result stat(StatNoticeProcessLogReq req, Paging paging);
}
