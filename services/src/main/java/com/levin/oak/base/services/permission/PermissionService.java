package com.levin.oak.base.services.permission;

import static com.levin.oak.base.ModuleOption.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;

//import org.springframework.cache.annotation.*;
//import org.springframework.dao.*;

import java.util.*;
import javax.validation.constraints.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.permission.req.*;
import com.levin.oak.base.services.permission.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 权限清单-服务接口
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年1月27日 上午11:48:06, 代码生成哈希校验码：[93a650b0ef8cc1c5326e7b9c7b82497c]，请不要修改和删除此行内容。
 *
 */
@Tag(name = E_Permission.BIZ_NAME, description = E_Permission.BIZ_NAME + MAINTAIN_ACTION)
public interface PermissionService {

    String BIZ_NAME = E_Permission.BIZ_NAME;

    String CK_PREFIX = E_Permission.CACHE_KEY_PREFIX;

    String SERVICE_NAME = "PermissionService";

    String SERVICE_BEAN_NAME = PLUGIN_PREFIX + SERVICE_NAME;

    /**
     * 创建记录，返回主键ID
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    String create(@NotNull CreatePermissionReq req);

    /**
     * 创建记录，返回主键ID列表
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(@NotNull List<CreatePermissionReq> reqList);

    /**
     * 更新记录，并返回更新是否成功
     *
     * @param req
     * @param queryObjs 附加的查询条件或是更新内容
     * @return boolean 是否成功
     */
    @Operation(summary = UPDATE_ACTION)
    boolean update(@NotNull UpdatePermissionReq req, Object... queryObjs);

    /**
     * 批量无ID更新记录，并返回更新记录数，请小心使用
     *
     * @param setReq
     * @param whereReq
     * @return int 记录数
     */
    @Operation(summary = UPDATE_ACTION)
    int batchUpdate(@NotNull SimpleUpdatePermissionReq setReq, QueryPermissionReq whereReq);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdatePermissionReq> reqList);

    /**
     * 删除记录，并返回删除是否成功
     * @param req
     * @return boolean 删除是否成功
     */
    @Operation(summary = DELETE_ACTION)
    boolean delete(@NotNull PermissionIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeletePermissionReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return defaultPagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION)
    PagingData<PermissionInfo> query(@NotNull QueryPermissionReq req, Paging paging, Object... queryObjs);

    /**
     * 指定选择列查询
     *
     * @param req
     * @param paging 分页设置，可空
     * @param columnNames 列名
     * @return defaultPagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    PagingData<PermissionInfo> selectQuery(@NotNull QueryPermissionReq req, Paging paging, String... columnNames);

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Operation(summary = STAT_ACTION)
    int count(@NotNull QueryPermissionReq req);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    PermissionInfo findById(@NotNull String id);

    /**
    * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
    * @param req
    * @return data 数据详情
    */
    @Operation(summary = VIEW_DETAIL_ACTION)
    PermissionInfo findById(@NotNull PermissionIdReq req);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(summary = QUERY_ACTION)
    PermissionInfo findOne(@NotNull QueryPermissionReq req);

    /**
     * 查询并返回唯一一条数据
     * 如果有多余1条数据，将抛出异常
     *
     * @param req
     * @return data
     * @throws RuntimeException 多条数据时抛出异常
     */
    @Operation(summary = QUERY_ACTION)
    PermissionInfo findUnique(QueryPermissionReq req);

    /**
     * 清除缓存
     * @param key 缓存Key
     */
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);

    /**
     * 清除所有缓存
     * 
     */
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "清除所有缓存")
    void clearAllCache();

}