package com.levin.oak.base.services.commons.req;


import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.service.support.InjectConsts;
import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 *  基本查询对象
 *  @Author Auto gen by simple-dao-codegen 2023年6月28日 下午4:18:30
 *  代码生成哈希校验码：[bbffd8f2b368ab4f8e7d0bc7af6c91fd]
 */
@Schema(title = "基本查询对象")
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class BaseReq
        implements
        ServiceReq  {

    /**
     * 是否非空
     * @param value
     * @return
     */
    protected boolean isNotBlank(Object value){
        return value != null
                && (!(value instanceof CharSequence) || StringUtils.hasText((CharSequence) value));
    }

}
