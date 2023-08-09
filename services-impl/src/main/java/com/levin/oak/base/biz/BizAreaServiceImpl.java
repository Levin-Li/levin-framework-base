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
import com.levin.oak.base.entities.Area;

import com.levin.oak.base.services.area.*;
import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Area.*;

////////////////////////////////////

/**
 * 区域-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月10日 上午2:41:24, 代码生成哈希校验码：[5739b79c0f84fc05b7866929d16a223a]，请不要修改和删除此行内容。
 */
@Service(PLUGIN_PREFIX + "BizAreaServiceImpl")
@DubboService
@ConditionalOnMissingBean({BizAreaService.class}) // 默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizAreaServiceImpl", matchIfMissing = true)
@Slf4j

// @Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
// @Validated
@Tag(name = E_Area.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_Area.SIMPLE_CLASS_NAME})
public class BizAreaServiceImpl extends BaseService implements BizAreaService {

    @Autowired AreaService areaService;

    protected BizAreaServiceImpl getSelfProxy() {
        return getSelfProxy(BizAreaServiceImpl.class);
    }

    // @Transactional(rollbackFor = RuntimeException.class)
    // public void update(UpdateReq req){
    //    areaService.update(req);
    // }

}
