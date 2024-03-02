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
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.biz.bo.menures.*;
import com.levin.oak.base.services.menures.*;
import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import com.levin.oak.base.entities.MenuRes;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.rbac.MenuItem.*;
import java.util.Set;
import com.levin.oak.base.services.menures.info.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  菜单-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:06, 代码生成哈希校验码：[a0a7fd481523aeaf47c42a785cd475ac]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_MenuRes.BIZ_NAME + "-业务服务", description = "")
public interface BizMenuResService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatMenuResReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatMenuResReq.Result stat(StatMenuResReq req, Paging paging);
}
