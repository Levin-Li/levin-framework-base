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
import com.levin.oak.base.entities.SimplePage;

import com.levin.oak.base.services.simplepage.*;
import com.levin.oak.base.services.simplepage.req.*;
import com.levin.oak.base.services.simplepage.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;

import java.util.List;
import java.util.Date;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 简单页面-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月13日 下午4:53:28, 代码生成哈希校验码：[71c70212af67c2b17999fd61dda1737b]，请不要修改和删除此行内容。
 */
@Tag(name = E_SimplePage.BIZ_NAME + "-业务服务", description = "")
public interface BizSimplePageService {

    /**
     * 加载一个页面
     *
     * @param req
     * @return
     */
    SimplePageInfo findOnePage(QuerySimplePageReq req);

}
