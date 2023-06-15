package com.levin.oak.base.services.dict;


import com.levin.commons.dao.Paging;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.entities.E_Dict;
import com.levin.oak.base.services.dict.info.DictInfo;
import com.levin.oak.base.services.dict.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 字典-服务接口
 *
 * @author Auto gen by simple-dao-codegen 2022-4-2 19:44:58
 */
@Tag(name = E_Dict.BIZ_NAME, title = E_Dict.BIZ_NAME + MAINTAIN_ACTION)
public interface DictService {

    String BIZ_NAME = E_Dict.BIZ_NAME;

    /**
     * 创建记录，返回主键ID
     *
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    String create(@NotNull CreateDictReq req);

    /**
     * 创建记录，返回主键ID列表
     *
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(@NotNull List<CreateDictReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     *
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    DictInfo findById(@NotNull String id);

    /**
     * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
     *
     * @param req
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    DictInfo findById(@NotNull DictIdReq req);

    /**
     * 更新记录，并返回更新记录数
     *
     * @param req
     * @return num 更新记录数
     */
    @Operation(summary = UPDATE_ACTION)
    int update(@NotNull UpdateDictReq req);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateDictReq> reqList);

    /**
     * 删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = DELETE_ACTION)
    int delete(@NotNull DictIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     *
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteDictReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION)
    PagingData<DictInfo> query(@NotNull QueryDictReq req, Paging paging);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(summary = QUERY_ACTION)
    DictInfo findOne(@NotNull QueryDictReq req);

    /**
     * 清除缓存
     *
     * @param key 缓存Key
     */
    @Operation(summary = CLEAR_CACHE_ACTION, title = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);

}
