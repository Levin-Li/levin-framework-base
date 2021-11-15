package com.levin.oak.base.services.simpleapi.req;

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
import java.util.*;

import com.levin.oak.base.services.simpleapi.info.*;
import com.levin.oak.base.entities.SimpleApi;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.SimpleApi.*;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询动态API
 *  @Author Auto gen by simple-dao-codegen 2021-11-15 15:08:50
 */
@Schema(description = "查询动态API")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS
, resultClass = SimpleApiInfo.class)
public class QuerySimpleApiReq implements ServiceReq  {

    private static final long serialVersionUID = 1021385738L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "分类名称")
    private String category;


    @Schema(description = "分组名称")
    private String groupName;


    @Schema(description = "路径")
    private String path;


    @Schema(description = "http方法")
    private String methods;


    @Schema(description = "脚本语言")
    private Language language;


    @Schema(description = "处理脚本")
    private String script;


    @Schema(description = "机构ID")
    private String orgId;


    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "系统子域")
    private String domain;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_SimpleApi.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_SimpleApi.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_SimpleApi.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_SimpleApi.lastUpdateTime)
    private Date maxLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QuerySimpleApiReq(Long id) {
        this.id = id;
    }
}
