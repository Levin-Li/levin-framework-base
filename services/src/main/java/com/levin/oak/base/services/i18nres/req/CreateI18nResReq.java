package com.levin.oak.base.services.i18nres.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.E_I18nRes;
import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增国际化资源
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(description = "新增国际化资源")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS)
public class CreateI18nResReq extends MultiTenantReq {

    private static final long serialVersionUID = -1681554652L;


    @Schema(description = "分类", required = true)
    @NotBlank
    @Size(max = 64)
    private String category;

    @Schema(description = "语言", required = true)
    @NotBlank
    @Size(max = 32)
    private String lang;

    @Schema(description = "标签", required = true)
    @NotBlank
    @Size(max = 768)
    private String label;

    @Schema(description = "系统子域")
    @Size(max = 64)
    private String domain;

    @Schema(description = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(description = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    private String pinyinName;

    @Schema(description = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(description = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(description = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(description = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(description = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(description = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(description = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
