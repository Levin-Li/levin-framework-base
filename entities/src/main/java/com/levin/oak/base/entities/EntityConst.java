package com.levin.oak.base.entities;

/**
 * module table prefix
 * <p>
 * eg.
 * <p>
 * //@Entity(name = EntityConst.PREFIX + "exam_tasks")
 * //@Table(name = EntityConst.PREFIX + "exam_tasks")
 * //Auto gen by simple-dao-codegen Sun Feb 05 11:08:33 CST 2023
 */
public interface EntityConst
        extends com.levin.commons.dao.EntityOpConst //继承默认的定义
{

    /**
     * JPA/Hibernate table name prefix
     */
    String PREFIX = "com.levin.oak.base-";

    /**
     * 通用功能，通常登录就可使用
     */
    String COMMON_TYPE_NAME = "系统通用";

    /**
     * 需要有管理员角色
     */
    String SYS_TYPE_NAME = "系统管理";

}
