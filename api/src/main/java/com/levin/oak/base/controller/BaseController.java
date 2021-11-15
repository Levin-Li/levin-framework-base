package com.levin.oak.base.controller;

import com.levin.commons.rbac.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.*;
import javax.validation.*;
import java.util.*;
import javax.annotation.*;

import javax.servlet.http.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import javax.validation.constraints.*;

import com.levin.oak.base.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2021-11-13 23:58:00


/**
 * 抽象控制器
 *
 * @author lilw
 */
@Slf4j
//默认需要权限访问
@ResAuthorize(domain = ID, type = TYPE_NAME)
public abstract class BaseController {

    @Resource
    protected HttpServletRequest httpRequest;

    @Resource
    protected HttpServletResponse httpResponse;

    public final String getModuleId() {
        return ModuleOption.ID;
    }

    protected boolean isNotEmpty(Object value) {
        return value != null
                && (!(value instanceof CharSequence) || StringUtils.hasText((CharSequence) value));
    }
}