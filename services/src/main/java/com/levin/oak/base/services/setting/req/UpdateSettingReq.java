package com.levin.oak.base.services.setting.req;

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

import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Setting.*;
import java.util.Date;
////////////////////////////////////


/**
 *  更新系统设置
 *  Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "更新系统设置")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
//默认更新注解
@Update
public class UpdateSettingReq extends MultiTenantReq {

    private static final long serialVersionUID = 147875794L;

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "分类名称")
    private String categoryName;

    @Size(max = 64)
    @Schema(description = "分组名称")
    private String groupName;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "编码")
    private String code;

    @Schema(description = "值类型")
    private ValueType valueType;

    @Schema(description = "值")
    private String value;

    @Schema(description = "值是否可空")
    private Boolean nullable;

    @Size(max = 64)
    @Schema(description = "输入占位提示")
    private String inputPlaceholder;

    @Size(max = 64)
    @Schema(description = "系统子域")
    private String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    private String name;

    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


    public UpdateSettingReq(Long id) {
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
