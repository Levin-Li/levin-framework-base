package com.levin.oak.base.biz;


import com.levin.commons.dao.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.oak.base.biz.bo.user.UpdateUserPwdReq;
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
public interface BizUserService<U> {

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
     * 创建必须是否有权限分配角色列表
     *
     * @param req
     * @return
     */
    String create(U userPrincipal, CreateUserReq req);

    /**
     * 修改密码
     * <p>
     * 必须考虑的2点：
     * 1-修改本人密码
     * 2-修改他人密码
     *
     * @param req
     * @return
     */
    boolean updatePwd(U userPrincipal, UpdateUserPwdReq req);

    /**
     * 删除
     * <p>
     * 只能删除同级部门或是下级部门的用户
     *
     * @param req
     * @return
     */
    boolean delete(U userPrincipal, UserIdReq req);

    /**
     * 修改
     * 只能修改同级部门或是下级部门的用户
     *
     * @param req
     * @return
     */
    boolean update(U userPrincipal, UpdateUserReq req);

    /**
     * 查找用户
     *
     * @param req
     * @return
     */
    UserInfo findById(U userPrincipal, UserIdReq req);

    /**
     * 查询用户
     *
     * @param req
     * @return
     */
    UserInfo findUnique(U userPrincipal, QueryUserReq req);

    /**
     * 查询用户
     *
     * @param req
     * @param paging
     * @return
     */
    PagingData<UserInfo> query(U userPrincipal, QueryUserReq req, SimplePaging paging);

}
