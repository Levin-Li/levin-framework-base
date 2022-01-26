package com.levin.oak.base.services.simplepage.req;

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

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.simplepage.info.*;
import com.levin.oak.base.entities.SimplePage;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import java.util.Date;
////////////////////////////////////

/**
 *  查询简单页面
 *  @Author Auto gen by simple-dao-codegen 2022-1-26 17:07:14
 */
@Schema(description = "查询简单页面")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimplePage.class, alias = E_SimplePage.ALIAS, resultClass = SimplePageInfo.class)
public class QuerySimplePageReq extends MultiTenantReq{

    private static final long serialVersionUID = 1598619295L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "分类名称")
    private String category;


    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "模糊匹配 - 分组名称")
    @Contains
    private String containsGroupName;


    @Schema(description = "访问路径")
    private String path;


    @Schema(description = "内容")
    private String content;


    @Schema(description = "系统子域")
    private String domain;


    @Schema(description = "名称")
    private String name;

    @Schema(description = "模糊匹配 - 名称")
    @Contains
    private String containsName;


    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(description = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    private String containsPinyinName;


    @Schema(description = "创建者")
    private String creator;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;



    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
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


    public QuerySimplePageReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
