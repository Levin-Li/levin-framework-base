package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.biz.rbac.req.LoginReq;
import com.levin.oak.base.services.user.info.UserInfo;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.ICredentialRepository;
import com.warrenstrange.googleauth.IGoogleAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.Serializable;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "GoogleMultiFactorAuthService")
@ConditionalOnClass({IGoogleAuthenticator.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "GoogleMultiFactorAuthService", havingValue = "true", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "GoogleMultiFactorAuthService"}, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")
public class GoogleMultiFactorAuthService implements MultiFactorAuthService {

    private final IGoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

    @Autowired
    BizUserService bizUserService;

    private static final String KEY_FORMAT = "otpauth://totp/%s?secret=%s";

    @PostConstruct
    public void init() {
    }

    @Override
    public String genSecretKey() {
        return googleAuthenticator.createCredentials().getKey();
    }

    /**
     * 生成密钥
     *
     * @param secretKey
     * @param userName
     * @return
     */
    @Override
    public String genQrCode(String userName, String secretKey) {
        return String.format(KEY_FORMAT, userName, secretKey);
    }

    /**
     * @param tenantId
     * @param appId
     * @param account
     * @param genParams
     * @return
     */
    @Override
    public String genCode(String tenantId, String appId, String account, Map<String, ? extends Serializable> genParams) {

        UserInfo user = bizUserService.findUser(new LoginReq().setTenantId(tenantId).setAppId(appId).setAccount(account));

        Assert.notNull(user, "用户不存在");
        Assert.notBlank(user.getMfaSecretKey(), "用户未绑定认证密钥");

        return "" + googleAuthenticator.getTotpPassword(user.getMfaSecretKey());
    }

    /**
     * 校验验证码
     *
     * @param tenantId
     * @param appId
     * @param account
     * @param code
     * @return
     */
    @Override
    public boolean verify(String tenantId, String appId, String account, String code) {

        UserInfo user = bizUserService.findUser(new LoginReq().setTenantId(tenantId).setAppId(appId).setAccount(account));

        Assert.notNull(user, "用户不存在");
        Assert.notBlank(user.getMfaSecretKey(), "用户未绑定认证密钥");

        return googleAuthenticator.authorize(user.getMfaSecretKey(), Integer.parseInt(code));
    }
}
