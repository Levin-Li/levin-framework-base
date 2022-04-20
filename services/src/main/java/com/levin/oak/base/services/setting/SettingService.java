package com.levin.oak.base.services.setting;


import com.levin.commons.dao.Paging;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.entities.E_Setting;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.services.setting.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 系统设置-服务接口
 *
 * @author Auto gen by simple-dao-codegen 2022-4-2 19:44:58
 */
@Tag(name = E_Setting.BIZ_NAME, description = E_Setting.BIZ_NAME + MAINTAIN_ACTION)
public interface SettingService {

    String BIZ_NAME = E_Setting.BIZ_NAME;

    /**
     * 创建记录，返回主键ID
     *
     * @param req
     * @return pkId 主键ID
     */
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(@NotNull CreateSettingReq req);

    /**
     * 创建记录，返回主键ID列表
     *
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(@NotNull List<CreateSettingReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     *
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    SettingInfo findById(@NotNull Long id);

    /**
     * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
     *
     * @param req
     * @return data 数据详情
     */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    SettingInfo findById(@NotNull SettingIdReq req);

    /**
     * 更新记录，并返回更新记录数
     *
     * @param req
     * @return num 更新记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(@NotNull UpdateSettingReq req);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateSettingReq> reqList);

    /**
     * 删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(@NotNull SettingIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteSettingReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<SettingInfo> query(@NotNull QuerySettingReq req, Paging paging);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    SettingInfo findOne(@NotNull QuerySettingReq req);

    /**
     * 清除缓存
     *
     * @param key 缓存Key
     */
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);

}
