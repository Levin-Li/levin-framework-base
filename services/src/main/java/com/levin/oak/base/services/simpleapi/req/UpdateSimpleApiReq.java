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
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.SimpleApi.*;

import java.util.Date;
////////////////////////////////////


/**
 * 更新简单动态接口
 * Auto gen by simple-dao-codegen 2022-5-23 10:30:00
 */
@Schema(description = "更新简单动态接口")
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

    @Schema(description = "id", required = true)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Size(max = 16)
    @Schema(title = "逗号隔开", description = "http方法")
    String methods;

    @Schema(description = "脚本语言")
    Language language;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "类型")
    String type;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "分类名称")
    String category;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "分组名称")
    String groupName;

    @NotBlank
    @Schema(description = "访问路径")
    String path;

    @Size(max = 1800)
    @Schema(description = "需要的授权，权限或角色，json数组")
    String requireAuthorizations;

    @Schema(description = "内容")
    String content;

    @Size(max = 128)
    @Schema(description = "系统子域")
    String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    String name;

    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    String pinyinName;

    @Schema(description = "更新时间")
    Date lastUpdateTime;

    @Schema(description = "排序代码")
    Integer orderCode;

    @Schema(description = "是否允许")
    Boolean enable;

    @Schema(description = "是否可编辑")
    Boolean editable;

    @Size(max = 512)
    @Schema(description = "备注")
    String remark;


    public UpdateSimpleApiReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }

}
