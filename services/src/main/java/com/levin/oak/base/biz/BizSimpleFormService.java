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
import com.levin.oak.base.entities.SimpleForm;
import com.levin.oak.base.biz.bo.simpleform.*;
import com.levin.oak.base.services.simpleform.*;
import com.levin.oak.base.services.simpleform.req.*;
import com.levin.oak.base.services.simpleform.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  简单表单-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月12日 下午10:13:36, 代码生成哈希校验码：[47f6b2403968b4a8e852327fd09798c0]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_SimpleForm.BIZ_NAME + "-业务服务", description = "")
public interface BizSimpleFormService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatSimpleFormReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatSimpleFormReq.Result stat(StatSimpleFormReq req, Paging paging);
}
