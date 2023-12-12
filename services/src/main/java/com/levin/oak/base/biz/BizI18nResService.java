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
import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.biz.bo.i18nres.*;
import com.levin.oak.base.services.i18nres.*;
import com.levin.oak.base.services.i18nres.req.*;
import com.levin.oak.base.services.i18nres.info.*;

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
 *  国际化资源-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月12日 下午10:13:36, 代码生成哈希校验码：[1c0c0a10b3f3209d5fd9cb23b4c12f57]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_I18nRes.BIZ_NAME + "-业务服务", description = "")
public interface BizI18nResService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatI18nResReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatI18nResReq.Result stat(StatI18nResReq req, Paging paging);
}
