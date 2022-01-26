package com.levin.oak.base.services.i18nres.req;

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

import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
////////////////////////////////////


/**
 *  更新国际化资源
 *  Auto gen by simple-dao-codegen 2022-1-26 17:07:14
 */
@Schema(description = "更新国际化资源")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS)
//默认更新注解
@Update
public class UpdateI18nResReq extends MultiTenantReq {

    private static final long serialVersionUID = -1681554652L;

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    //@NotBlank
    //@Size(max = 64)
    @Schema(description = "分类")
    private String category;

    //@NotBlank
    //@Size(max = 32)
    @Schema(description = "语言")
    private String lang;

    //@NotBlank
    //@Size(max = 768)
    @Schema(description = "标签")
    private String label;

    //@Size(max = 64)
    @Schema(description = "系统子域")
    private String domain;

    //@NotBlank
    //@Size(max = 128)
    @Schema(description = "名称")
    private String name;

    //@Size(max = 128)
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

    //@Size(max = 512)
    @Schema(description = "备注")
    private String remark;


    public UpdateI18nResReq(Long id) {
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
