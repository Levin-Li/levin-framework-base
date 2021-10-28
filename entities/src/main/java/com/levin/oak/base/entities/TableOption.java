package com.levin.oak.base.entities;

/**
 * module table prefix
 *
 * eg.
 *
 * //@Entity(name = ModuleTableOption.PREFIX + "exam_tasks")
 * //@Table(name = ModuleTableOption.PREFIX + "exam_tasks")
 * //Auto gen by simple-dao-codegen Mon Oct 18 14:12:00 CST 2021
 *
 */
public interface TableOption {

    /**
     * JPA/Hibernate table name prefix
     */
      String PREFIX = "com.levin.oak.base-";

}
