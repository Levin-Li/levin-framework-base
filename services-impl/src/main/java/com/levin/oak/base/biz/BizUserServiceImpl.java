package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.*;

import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.biz.rbac.req.LoginReq;
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
import com.levin.oak.base.entities.User;

import com.levin.oak.base.services.user.*;
import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.User.*;

import java.util.List;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;

import java.util.Date;

import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
////////////////////////////////////

/**
 * 用户-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月16日 上午9:40:48, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[741cc220a3c9c8301f7ec6eea6bb60cb], 请不要修改和删除此行内容。
 */

@Service(PLUGIN_PREFIX + "BizUserServiceImpl")
@DubboService

@ConditionalOnMissingBean({BizUserService.class}) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizUserServiceImpl", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_User.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_User.SIMPLE_CLASS_NAME})
public class BizUserServiceImpl extends BaseService implements BizUserService {

    @Autowired
    UserService userService;


    protected BizUserServiceImpl getSelfProxy() {
        return getSelfProxy(BizUserServiceImpl.class);
    }

    //示例方法
    //@Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    //@Override
    //@CacheEvict(condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    //public boolean update(UpdateUserReq req) {
    //    Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
    //    return simpleDao.singleUpdateByQueryObj(req);
    //}

    private void checkCreateOrUpdateAccount(String... accounts) {
        for (String account : accounts) {
            //不允许创建或是变更为 SA 帐号
            Assert.isTrue(!isSuperAdminAccount(account), "帐号不允许");
        }
    }

    @Override
    public UserInfo findUser(LoginReq req) {
        //密码加密
        req.setPassword(encryptPwd(req.getPassword()));

        return simpleDao.findOneByQueryObj(req);
    }

    /**
     * 密码加密
     *
     * @param pwd
     * @return
     */
    @Override
    public String encryptPwd(String pwd) {
        return StringUtils.hasText(pwd) ? SecureUtil.sha1(pwd) : pwd;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public boolean updatePwd(UpdateUserPwdReq req) {

        Assert.notNull(req.getId(), E_User.BIZ_NAME + " id 不能为空");
        Assert.notBlank(req.getOldPassword(), "旧密码不能为空");
        Assert.notBlank(req.getPassword(), "新密码不能为空");

        req.setOldPassword(encryptPwd(req.getOldPassword()))
                .setPassword(encryptPwd(req.getPassword()));

        return simpleDao.singleUpdateByQueryObj(req);
    }

    /**
     * @param req
     * @return
     */
    @Override
    public String create(CreateUserReq req) {

        checkCreateOrUpdateAccount(req.getEmail(), req.getTelephone());

        return userService.create(req.setPassword(encryptPwd(req.getPassword())));
    }

    /**
     * 删除
     *
     * @param req
     * @return
     */
    @Override
    public boolean delete(UserIdReq req) {
        return userService.delete(req);
    }

    /**
     * @param req
     * @return
     */
    @Override
    public boolean update(UpdateUserReq req) {

        req.setPassword(null);

        checkCreateOrUpdateAccount(req.getEmail(), req.getTelephone());

        return userService.update(req);
    }

    /**
     * 查找用户
     *
     * @param req
     * @return
     */
    @Override
    public UserInfo findById(UserIdReq req) {
        Assert.notBlank(req.getId(), "用户ID必须指定");
        return userService.findById(req);
    }

    /**
     * @param req
     * @param paging
     * @return
     */
    @Override
    public PagingData<UserInfo> query(QueryUserReq req, SimplePaging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

}
