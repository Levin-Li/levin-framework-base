package com.levin.oak.base.services.simpleapi.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.SimpleApi;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.SimpleApi.*;
    import java.util.Date;
////////////////////////////////////


/**
 *  更新简单接口
 *  Auto gen by simple-dao-codegen 2022-1-5 15:46:43
 */
@Schema(description = "更新简单接口")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS)
//默认更新注解
@Update
public class UpdateSimpleApiReq extends MultiTenantReq {

    private static final long serialVersionUID = 1021385738L;

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    //@Size(max = 16)
    @Schema(description = "http方法")
    private String methods;

    @Schema(description = "脚本语言")
    private Language language;

    //@Size(max = 64)
    @Schema(description = "分类名称")
    private String category;

    //@Size(max = 64)
    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "访问路径")
    private String path;

    @Schema(description = "内容")
    private String content;

    //@Size(max = 64)
    @Schema(description = "系统子域")
    private String domain;

    //@Size(max = 512)
    @Schema(description = "名称")
    private String name;

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    //@Size(max = 512)
    @Schema(description = "备注")
    private String remark;


    public UpdateSimpleApiReq(Long id) {
        this.id = id;
    }
    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}
