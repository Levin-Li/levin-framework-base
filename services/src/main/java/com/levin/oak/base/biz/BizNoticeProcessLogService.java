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
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  通知处理日志-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月12日 下午10:13:36, 代码生成哈希校验码：[e1834a9c222831c8ac39d31c5921ae51]，请不要修改和删除此行内容。
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
