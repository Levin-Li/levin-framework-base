package com.levin.oak.base.services.setting;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
//import org.springframework.cache.annotation.*;
import java.util.*;
import javax.validation.constraints.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 系统设置-服务接口
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月24日 15:26:13, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[ae8f6a5c3ecb815c27a8dcbd7c0409e9], 请不要修改和删除此行内容。
 */
@Tag(name = E_Setting.BIZ_NAME, description = E_Setting.BIZ_NAME + MAINTAIN_ACTION)
public interface SettingService {

    String BIZ_NAME = E_Setting.BIZ_NAME;

    /**
     * 创建记录，返回主键ID
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    String create(@NotNull CreateSettingReq req);

    /**
     * 创建记录，返回主键ID列表
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(@NotNull List<CreateSettingReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    SettingInfo findById(@NotNull String id);

    /**
    * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
    * @param req
    * @return data 数据详情
    */
    @Operation(summary = VIEW_DETAIL_ACTION)
    SettingInfo findById(@NotNull SettingIdReq req);

    /**
     * 更新记录，并返回更新记录数
     *
     * @param req
     * @return num 更新记录数
     */
    @Operation(summary = UPDATE_ACTION)
    boolean update(@NotNull UpdateSettingReq req);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateSettingReq> reqList);

    /**
     * 删除记录，并返回删除记录数
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = DELETE_ACTION)
    boolean delete(@NotNull SettingIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteSettingReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION)
    PagingData<SettingInfo> query(@NotNull QuerySettingReq req, Paging paging);

    /**
     * 指定选择列查询
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    PagingData<SimpleSettingInfo> simpleQuery(@NotNull QuerySettingReq req, Paging paging);

    /**
     * 简单统计
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = STAT_ACTION)
    PagingData<StatSettingReq.Result> stat(@NotNull StatSettingReq req, Paging paging);

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Operation(summary = STAT_ACTION)
    int count(@NotNull QuerySettingReq req);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(summary = QUERY_ACTION)
    SettingInfo findOne(@NotNull QuerySettingReq req);

    /**
     * 查询并返回唯一一条数据
     * 如果有多余1条数据，将抛出异常
     * @param req
     * @return data
     */
    @Operation(summary = QUERY_ACTION)
    SettingInfo findUnique(QuerySettingReq req);

    /**
     * 清除缓存
     * @param key 缓存Key
     */
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);

}
