package com.levin.oak.base.baidu.uid;


public interface ModuleOption {

    /**
     * module package name
     */
    String PACKAGE_NAME = "com.levin.oak.base";

    /**
     * module id
     */
    String ID = PACKAGE_NAME;

    /**
     * module prefix
     */
    String ID_PREFIX = ID + ".";

    /**
     * module prefix
     */
    String PLUGIN_PREFIX = "plugin." + ID_PREFIX;

}
