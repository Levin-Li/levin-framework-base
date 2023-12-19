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
import com.levin.oak.base.entities.JobPost;
import com.levin.oak.base.biz.bo.jobpost.*;
import com.levin.oak.base.services.jobpost.*;
import com.levin.oak.base.services.jobpost.req.*;
import com.levin.oak.base.services.jobpost.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.oak.base.entities.JobPost.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  工作岗位-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:27, 代码生成哈希校验码：[06e42d11ac52c18b8090bb629cfad0b1]，请不要修改和删除此行内容。
 *
 */

@Tag(name = E_JobPost.BIZ_NAME + "-业务服务", description = "")
public interface BizJobPostService {

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatJobPostReq.Result
    */
    @Operation(summary = STAT_ACTION)
    StatJobPostReq.Result stat(StatJobPostReq req, Paging paging);
}
