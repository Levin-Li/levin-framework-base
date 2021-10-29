package com.levin.oak.base.services.rbac;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.levin.commons.dao.SimpleDao;
import com.levin.oak.base.services.rbac.req.LoginReq;
import com.levin.oak.base.services.user.info.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


@Service(PLUGIN_PREFIX + "AuthService")
@Slf4j
//@ConditionalOnMissingBean(AuthService.class)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SimpleDao simpleDao;

    @Override
    public UserInfo login(LoginReq req) {
        return simpleDao.findOneByQueryObj(req);
    }

    @Override
    public String encryptPassword(String pwd) {
        return SaSecureUtil.sha1(pwd);
    }
}
