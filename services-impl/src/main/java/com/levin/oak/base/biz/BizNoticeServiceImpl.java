package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import javax.annotation.*;
import java.util.*;
import java.util.stream.*;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.dao.*;

import javax.persistence.PersistenceException;
import cn.hutool.core.lang.*;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Notice;

import com.levin.oak.base.services.notice.*;
import com.levin.oak.base.services.notice.req.*;
import com.levin.oak.base.services.notice.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;
////////////////////////////////////

/**
 *  -业务服务实现类
 *
 * @author auto gen by simple-dao-codegen 2023年6月24日 下午12:28:37
 *
 */

@DubboService
//@Service(PLUGIN_PREFIX + "BizNoticeServiceImpl")

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizNoticeServiceImpl", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_Notice.BIZ_NAME + "-业务服务", description = "")
public class BizNoticeServiceImpl extends BaseService implements BizNoticeService {

    @Autowired
    NoticeService noticeService;

    protected BizNoticeServiceImpl getSelfProxy(){
        return getSelfProxy(BizNoticeServiceImpl.class);
    }

}
