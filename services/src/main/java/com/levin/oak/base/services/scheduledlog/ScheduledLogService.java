package com.levin.oak.base.services.scheduledlog;

import static com.levin.oak.base.ModuleOption.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;

//import org.springframework.cache.annotation.*;
//import org.springframework.dao.*;

import java.util.*;
import java.util.stream.*;
import javax.validation.*;
import javax.validation.constraints.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.scheduledlog.req.*;
import com.levin.oak.base.services.scheduledlog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 调度日志-服务接口
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月28日 下午4:50:44, 代码生成哈希校验码：[e121b128a201c3ce67de4225dbeae956]，请不要修改和删除此行内容。
 *
 */
@Tag(name = E_ScheduledLog.BIZ_NAME, description = E_ScheduledLog.BIZ_NAME + MAINTAIN_ACTION)
public interface ScheduledLogService {

    String BIZ_NAME = E_ScheduledLog.BIZ_NAME;

    String CACHE_NAME = ModuleOption.ID + CACHE_DELIM + E_ScheduledLog.SIMPLE_CLASS_NAME;

    //缓存key前缀
    String CK_PREFIX = E_ScheduledLog.CACHE_KEY_PREFIX;

    //缓存key前缀表达式
    String CK_PREFIX_EXPR = E_ScheduledLog.CACHE_KEY_PREFIX_EXPR;

    String SERVICE_NAME = "ScheduledLogService";

    String SERVICE_BEAN_NAME = PLUGIN_PREFIX + SERVICE_NAME;

    /**
    */
    default Class<?> getEntityClass() {
        return ScheduledLog.class;
    }

    /**
     * 创建记录，返回主键ID
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    String create(@NotNull CreateScheduledLogReq req);

    /**
     * 创建记录，返回主键ID列表
     * @param reqList
     * @return pkId 主键ID列表
     */
    @Operation(summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(@NotNull List<CreateScheduledLogReq> reqList);

    /**
     * 更新记录，并返回更新是否成功
     *
     * @param req
     * @param queryObjs 附加的查询条件或是更新内容，如可以是 Consumer<UpdateDao<?>> 对象
     * @return boolean 是否成功
     */
    @Operation(summary = UPDATE_ACTION)
    boolean update(@NotNull UpdateScheduledLogReq req, Object... queryObjs);

    /**
     * 批量无ID更新记录，并返回更新记录数，请小心使用
     *
     * @param setReq
     * @param whereReq
     * @param queryObjs 附加的查询条件或是更新内容，如可以是 Consumer<UpdateDao<?>> 对象
     * @return int 记录数
     */
    @Operation(summary = UPDATE_ACTION)
    int batchUpdate(@NotNull SimpleUpdateScheduledLogReq setReq, QueryScheduledLogReq whereReq, Object... queryObjs);

    /**
     * 批量更新记录，并返回更新记录数
     *
     * @param reqList
     * @return num 更新记录数
     */
    @Operation(summary = BATCH_UPDATE_ACTION)
    int batchUpdate(@NotNull List<UpdateScheduledLogReq> reqList);

    /**
     * 删除记录，并返回删除是否成功
     * @param req
     * @return boolean 删除是否成功
     */
    @Operation(summary = DELETE_ACTION)
    boolean delete(@NotNull ScheduledLogIdReq req);

    /**
     * 批量删除记录，并返回删除记录数
     * @param req
     * @return num 删除记录数
     */
    @Operation(summary = BATCH_DELETE_ACTION)
    int batchDelete(@NotNull DeleteScheduledLogReq req);

    /**
     * 查询记录
     *
     * @param req
     * @param paging 分页设置，可空
     * @param queryObjs 扩展的查询对象，可以是 Consumer<SelectDao<?>> 对象
     * @return defaultPagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION)
    PagingData<ScheduledLogInfo> query(@NotNull QueryScheduledLogReq req, Paging paging, Object... queryObjs);

    /**
     * 指定选择列查询
     *
     * @param req
     * @param paging 分页设置，可空
     * @param selectColumnNames 列名
     * @return defaultPagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    PagingData<ScheduledLogInfo> selectQuery(@NotNull QueryScheduledLogReq req, Paging paging, String... selectColumnNames);

    /**
    * 指定选择列查询
    *
    * @param req
    * @param paging 分页设置，可空
    * @param selectColumns 列名
    * @return defaultPagingData 分页数据
    */
    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    default PagingData<ScheduledLogInfo> selectQuery(@NotNull QueryScheduledLogReq req, Paging paging, PFunction<ScheduledLog,?>... selectColumns){
        return selectQuery(req, paging, Stream.of(selectColumns).filter(Objects::nonNull).map(PFunction::get).toArray(String[]::new));
    }

    /**
     * 统计记录数
     *
     * @param req
     * @param queryObjs 扩展的查询对象，可以是 Consumer<SelectDao<?>> 对象
     * @return record count
     */
    @Operation(summary = STAT_ACTION)
    int count(@NotNull QueryScheduledLogReq req, Object... queryObjs);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     * @param id 主键ID
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    ScheduledLogInfo findById(@NotNull String id);

    /**
    * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
    * @param req
    * @return data 数据详情
    */
    @Operation(summary = VIEW_DETAIL_ACTION)
    ScheduledLogInfo findById(@NotNull ScheduledLogIdReq req);

    /**
     * 查询并返回第一条数据
     *
     * @param req
     * @param queryObjs 扩展的查询对象，可以是 Consumer<SelectDao<?>> 对象
     * @return data 第一条数据
     */
    @Operation(summary = QUERY_ACTION)
    ScheduledLogInfo findOne(@NotNull QueryScheduledLogReq req, Object... queryObjs);

    /**
     * 查询并返回唯一一条数据
     * 如果有多余1条数据，将抛出异常
     *
     * @param req
     * @return data
     * @throws RuntimeException 多条数据时抛出异常
     */
    @Operation(summary = QUERY_ACTION)
    ScheduledLogInfo findUnique(QueryScheduledLogReq req);

    /**
     * 清除缓存
     * @param keySuffix 缓存Key后缀，不包含前缀
     */
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "通常是主键ID")
    void clearCacheByKeySuffix(@NotNull Object keySuffix);

     /**
      * 清除缓存
      * @param key 缓存Key
     */
     @Operation(summary = CLEAR_CACHE_ACTION,  description = "完整的缓存Key")
     void clearCache(@NotNull Object key);

    /**
     * 清除所有缓存
     * 
     */
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "清除所有缓存")
    void clearAllCache();

}
