package com.levin.oak.base.services.user;


import com.levin.commons.dao.Paging;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 用户-服务接口
 *
 * @author Auto gen by simple-dao-codegen 2022-4-2 19:50:05
 */
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)
public interface UserService {

    String BIZ_NAME = E_User.BIZ_NAME;

    /**
     * 创建记录，返回主键ID
     *
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    String create(@NotNull CreateUserReq req);

    /**
     * 创建记录，返回主键ID列表
     *
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(@NotNull List<CreateUserReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     *
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    UserInfo findById(@NotNull String id);

    /**
     * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
     *
     * @param req
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    UserInfo findById(@NotNull UserIdReq req);

    /**
     * 修改密码
     *
     * @param req
     * @return
     */
    @Operation(summary = "修改密码")
    int update(UpdateUserPwdReq req);

    /**
     * 更新记录，并返回更新记录数
     *
     * @param req
     * @return num 更新记录数
     */
    @Operation(summary = UPDATE_ACTION)
    boolean update(@NotNull UpdateUserReq req);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateUserReq> reqList);

    /**
     * 删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = DELETE_ACTION)
    boolean delete(@NotNull UserIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteUserReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION)
    PagingData<UserInfo> query(@NotNull QueryUserReq req, Paging paging);

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Operation(tags = {BIZ_NAME}, summary = STAT_ACTION)
    int count(@NotNull QueryUserReq req);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    UserInfo findOne(@NotNull QueryUserReq req);

    /**
     * 查询并返回唯一一条数据
     * 如果有多余1条数据，将抛出异常
     * @param req
     * @return data
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    UserInfo findUnique(QueryUserReq req);
    /**
     * 清除缓存
     *
     * @param key 缓存Key
     */
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);


}
