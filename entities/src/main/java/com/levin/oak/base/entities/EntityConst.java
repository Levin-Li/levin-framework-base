package com.levin.oak.base.entities;

/**
 * module table prefix
 * <p>
 * eg.
 * <p>
 * //@Entity(name = ModuleTableOption.PREFIX + "exam_tasks")
 * //@Table(name = ModuleTableOption.PREFIX + "exam_tasks")
 * //Auto gen by simple-dao-codegen Mon Oct 18 14:12:00 CST 2021
 */
public interface EntityConst {

    /**
     * JPA/Hibernate table name prefix
     */
    String PREFIX = "com.levin.oak.base-";

    /**
     *
     */
    String TYPE_NAME = "实体数据";

    /**
     * 通用功能，通常登录就可使用
     */
    String COMMON_TYPE_NAME = "系统通用";

    /**
     * 需要有管理员角色
     */
    String SYS_TYPE_NAME = "系统管理";

    /**
     *
     */
    String MAINTAIN_ACTION = "管理";

    /**
     *
     */
    String CLEAR_CACHE_ACTION = "清除缓存";
    /**
     *
     */
    String CREATE_ACTION = "新增";
    String BATCH_CREATE_ACTION = "批量新增";

    /**
     *
     */
    String QUERY_ACTION = "查询";

    String STAT_ACTION = "统计";

    /**
     *
     */
    String VIEW_DETAIL_ACTION = "查看详情";

    /**
     *
     */
    String UPDATE_ACTION = "更新";
    String BATCH_UPDATE_ACTION = "批量更新";

    /**
     *
     */
    String DELETE_ACTION = "删除";
    String BATCH_DELETE_ACTION = "批量删除";
}
