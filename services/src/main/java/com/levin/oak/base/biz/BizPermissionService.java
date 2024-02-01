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
import com.levin.oak.base.entities.Permission;
import com.levin.oak.base.biz.bo.permission.*;
import com.levin.oak.base.services.permission.*;
import com.levin.oak.base.services.permission.req.*;
import com.levin.oak.base.services.permission.info.*;

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
 *  权限清单-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年1月27日 上午11:48:06, 代码生成哈希校验码：[ed263d4c7b7e4189d6f0b65d8740161b]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_Permission.BIZ_NAME + "-业务服务", description = "")
public interface BizPermissionService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatPermissionReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatPermissionReq.Result stat(StatPermissionReq req, Paging paging);
}
