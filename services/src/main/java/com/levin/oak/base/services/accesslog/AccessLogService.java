package com.levin.oak.base.services.accesslog;

import static com.levin.oak.base.ModuleOption.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
//import org.springframework.cache.annotation.*;
import java.util.*;
import javax.validation.constraints.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.accesslog.req.*;
import com.levin.oak.base.services.accesslog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 访问日志-服务接口
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月25日 下午1:13:56, 代码生成哈希校验码：[a70624d57e4d14645b67a0476f265a2c]，请不要修改和删除此行内容。
 *
 */
@Tag(name = E_AccessLog.BIZ_NAME, description = E_AccessLog.BIZ_NAME + MAINTAIN_ACTION)
public interface AccessLogService {

    String BIZ_NAME = E_AccessLog.BIZ_NAME;

    String CK_PREFIX = E_AccessLog.CACHE_KEY_PREFIX;

    String SERVICE_NAME = "AccessLogService";

    String SERVICE_BEAN_NAME = PLUGIN_PREFIX + SERVICE_NAME;

    /**
     * 创建记录，返回主键ID
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    Long create(@NotNull CreateAccessLogReq req);

    /**
     * 创建记录，返回主键ID列表
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(@NotNull List<CreateAccessLogReq> reqList);

    /**
     * 更新记录，并返回更新是否成功
     *
     * @param req
     * @return boolean 是否成功
     */
    @Operation(summary = UPDATE_ACTION)
    boolean update(@NotNull UpdateAccessLogReq req);

    /**
     * 更新记录，并返回更新记录数
     *
     * @param setReq
     * @param whereReq
     * @return int 记录数
     */
    @Operation(summary = UPDATE_ACTION)
    int update(@NotNull SimpleUpdateAccessLogReq setReq, QueryAccessLogReq whereReq);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateAccessLogReq> reqList);

    /**
     * 删除记录，并返回删除是否成功
     * @param req
     * @return boolean 删除是否成功
     */
    @Operation(summary = DELETE_ACTION)
    boolean delete(@NotNull AccessLogIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteAccessLogReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION)
    PagingData<AccessLogInfo> query(@NotNull QueryAccessLogReq req, Paging paging);

    /**
     * 指定选择列查询
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    PagingData<SimpleAccessLogInfo> simpleQuery(@NotNull QueryAccessLogReq req, Paging paging);

    /**
     * 简单统计
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = STAT_ACTION)
    PagingData<StatAccessLogReq.Result> stat(@NotNull StatAccessLogReq req, Paging paging);

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Operation(summary = STAT_ACTION)
    int count(@NotNull QueryAccessLogReq req);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    AccessLogInfo findById(@NotNull Long id);

    /**
    * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
    * @param req
    * @return data 数据详情
    */
    @Operation(summary = VIEW_DETAIL_ACTION)
    AccessLogInfo findById(@NotNull AccessLogIdReq req);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @return data 第一条数据
     */
    @Operation(summary = QUERY_ACTION)
    AccessLogInfo findOne(@NotNull QueryAccessLogReq req);

    /**
     * 查询并返回唯一一条数据
     * 如果有多余1条数据，将抛出异常
     * @param req
     * @return data
     */
    @Operation(summary = QUERY_ACTION)
    AccessLogInfo findUnique(QueryAccessLogReq req);

    /**
     * 清除缓存
     * @param key 缓存Key
     */
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "缓存Key通常是主键ID")
    void clearCache(@NotNull Object key);

}
