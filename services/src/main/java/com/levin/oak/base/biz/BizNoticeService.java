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
import com.levin.oak.base.entities.Notice;

import com.levin.oak.base.services.notice.*;
import com.levin.oak.base.services.notice.req.*;
import com.levin.oak.base.services.notice.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 通知-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 上午2:26:21, 代码生成哈希校验码：[54112913cd8c9e4763526144a95d49f4]，请不要修改和删除此行内容。
 */
@Tag(name = E_Notice.BIZ_NAME + "-业务服务", description = "")
public interface BizNoticeService {}
