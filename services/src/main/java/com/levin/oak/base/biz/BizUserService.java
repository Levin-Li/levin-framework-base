package com.levin.oak.base.biz;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.oak.base.biz.dto.user.UpdateUserPwdReq;
import com.levin.oak.base.biz.rbac.req.LoginReq;
import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;


////////////////////////////////////
//自动导入列表

import org.springframework.util.StringUtils;
////////////////////////////////////

/**
 * 用户-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:11, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[6233e60f8984ca15882bda137c8e0b00], 请不要修改和删除此行内容。
 */

@Tag(name = E_User.BIZ_NAME + "-业务服务", description = "")
public interface BizUserService {

    /**
     * 超管帐号
     */
    String SA_ACCOUNT = "sa";

    /**
     * 是否超级用户帐号
     *
     * @param account
     * @return
     */
    default boolean isSuperAdminAccount(String account) {
        return SA_ACCOUNT.equals(StringUtils.trimWhitespace(account));
    }

    /**
     * 查找用户
     *
     * @param req
     * @return
     */
    UserInfo findUser(LoginReq req);

    /**
     * 密码加密
     *
     * @param pwd
     * @return
     */
    String encryptPwd(String pwd);


    /**
     * @param req
     * @return
     */
    String create(CreateUserReq req);

    /**
     * 修改密码
     *
     * @param req
     * @return
     */
    boolean updatePwd(UpdateUserPwdReq req);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    boolean delete(UserIdReq req);

    /**
     * @param req
     * @return
     */
    boolean update(UpdateUserReq req);

    /**
     * 查找用户
     *
     * @param req
     * @return
     */
    UserInfo findById(UserIdReq req);

    /**
     *
     * @param req
     * @return
     */
    UserInfo findUnique(QueryUserReq req);
    /**
     * @param req
     * @param paging
     * @return
     */
    PagingData<UserInfo> query(QueryUserReq req, SimplePaging paging);

}
