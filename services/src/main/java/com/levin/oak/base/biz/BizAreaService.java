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
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.biz.bo.area.*;
import com.levin.oak.base.services.area.*;
import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
import com.levin.oak.base.entities.Area.*;
////////////////////////////////////

/**
 *  区域-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:27, 代码生成哈希校验码：[45e77f10c779a4a7ed6f99924ce567ac]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_Area.BIZ_NAME + "-业务服务", description = "")
public interface BizAreaService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatAreaReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatAreaReq.Result stat(StatAreaReq req, Paging paging);
}
