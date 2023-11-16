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

import com.levin.oak.base.services.jobpost.*;
import com.levin.oak.base.services.jobpost.req.*;
import com.levin.oak.base.services.jobpost.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.oak.base.entities.JobPost.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 工作岗位-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 上午2:26:21, 代码生成哈希校验码：[d1dfaefd62db9079c0b232c716eb6f82]，请不要修改和删除此行内容。
 */
@Tag(name = E_JobPost.BIZ_NAME + "-业务服务", description = "")
public interface BizJobPostService {}
