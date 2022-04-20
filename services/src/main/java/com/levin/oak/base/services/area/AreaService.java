package com.levin.oak.base.services.area;


import com.levin.commons.dao.Paging;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.entities.E_Area;
import com.levin.oak.base.services.area.info.AreaInfo;
import com.levin.oak.base.services.area.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 区域-服务接口
 *
 * @author Auto gen by simple-dao-codegen 2022-4-2 19:44:59
 */
@Tag(name = E_Area.BIZ_NAME, description = E_Area.BIZ_NAME + MAINTAIN_ACTION)
public interface AreaService {

    String BIZ_NAME = E_Area.BIZ_NAME;

    /**
     * 创建记录，返回主键ID
     *
     * @param req
     * @return pkId 主键ID
     */
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    String create(@NotNull CreateAreaReq req);

    /**
     * 创建记录，返回主键ID列表
     *
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(@NotNull List<CreateAreaReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     *
     * @param code 主键ID
     * @return data 数据详情
     */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    AreaInfo findById(@NotNull String code);

    /**
     * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
     *
     * @param req
     * @return data 数据详情
     */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    AreaInfo findById(@NotNull AreaIdReq req);

    /**
     * 更新记录，并返回更新记录数
     *
     * @param req
     * @return num 更新记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(@NotNull UpdateAreaReq req);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateAreaReq> reqList);

    /**
     * 删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(@NotNull AreaIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteAreaReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<AreaInfo> query(@NotNull QueryAreaReq req, Paging paging);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    AreaInfo findOne(@NotNull QueryAreaReq req);

    /**
     * 清除缓存
     *
     * @param key 缓存Key
     */
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);

}
