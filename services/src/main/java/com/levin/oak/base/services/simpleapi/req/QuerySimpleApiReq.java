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
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.SimpleApi.*;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询简单接口
 *  @Author Auto gen by simple-dao-codegen 2021-12-17 11:57:22
 */
@Schema(description = "查询简单接口")
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
public class QuerySimpleApiReq extends MultiTenantReq{

    private static final long serialVersionUID = 1021385738L;


    @Schema(description = "http方法")
    private String methods;


    @Schema(description = "脚本语言")
    private Language language;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "分类名称")
    private String category;


    @Schema(description = "分组名称")
    private String groupName;


    @Schema(description = "访问路径")
    private String path;


    @Schema(description = "内容")
    private String content;


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


    @Schema(description = "大于等于创建时间")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间")
    @Lte
    private Date lteCreateTime;



    @Schema(description = "大于等于更新时间")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间")
    @Lte
    private Date lteLastUpdateTime;



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
