package com.levin.oak.base.services.role;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
//import org.springframework.cache.annotation.*;
import java.util.*;
import javax.validation.constraints.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  角色-服务接口
 *  @author Auto gen by simple-dao-codegen 2023年6月28日 上午11:30:54
 *  代码生成哈希校验码：[812afe1bb255cce04080fb33bd82a9c5]
 */
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)
public interface RoleService {

    String BIZ_NAME = E_Role.BIZ_NAME;

    /**
     * 创建记录，返回主键ID
     * @param req
     * @return pkId 主键ID
     */
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    String create(@NotNull CreateRoleReq req);

    /**
     * 创建记录，返回主键ID列表
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(@NotNull List<CreateRoleReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    RoleInfo findById(@NotNull String id);

    /**
    * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
    * @param req
    * @return data 数据详情
    */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    RoleInfo findById(@NotNull RoleIdReq req);

    /**
     * 更新记录，并返回更新记录数
     *
     * @param req
     * @return num 更新记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    boolean update(@NotNull UpdateRoleReq req);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateRoleReq> reqList);

    /**
     * 删除记录，并返回删除记录数
     * @param req
     * @return num 删除记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    boolean delete(@NotNull RoleIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     * @param req
     * @return num 删除记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteRoleReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<RoleInfo> query(@NotNull QueryRoleReq req, Paging paging);

    /**
     * 简单统计
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(tags = {BIZ_NAME}, summary = STAT_ACTION)
    PagingData<StatRoleReq.Result> stat(@NotNull StatRoleReq req, Paging paging);

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Operation(tags = {BIZ_NAME}, summary = STAT_ACTION)
    int count(@NotNull QueryRoleReq req);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    RoleInfo findOne(@NotNull QueryRoleReq req);

     /**
     * 查询并返回唯一一条数据
     * 如果有多余1条数据，将抛出异常
     * @param req
     * @return data
     */
     @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
     RoleInfo findUnique(QueryRoleReq req);

    /**
    * 清除缓存
    * @param key 缓存Key
    */
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION,  description = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);

}
