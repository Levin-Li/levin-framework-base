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
import com.levin.oak.base.entities.Org;
import com.levin.oak.base.biz.bo.org.*;
import com.levin.oak.base.services.org.*;
import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  机构-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月12日 下午10:13:36, 代码生成哈希校验码：[ef7606220eff436c006c8d9cea6535b5]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_Org.BIZ_NAME + "-业务服务", description = "")
public interface BizOrgService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatOrgReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatOrgReq.Result stat(StatOrgReq req, Paging paging);
}
