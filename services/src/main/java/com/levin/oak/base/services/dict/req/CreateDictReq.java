package com.levin.oak.base.services.dict.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.DefaultJsonConverter;
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.Dict.Item;
import com.levin.oak.base.entities.Dict.Type;
import com.levin.oak.base.entities.E_Dict;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增字典
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增字典")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS)
public class CreateDictReq extends MultiTenantReq {

    private static final long serialVersionUID = -445779596L;


    @Schema(title = "类型", required = true)
    @NotNull
    private Type type;

    @Schema(title = "编码", required = true)
    @NotBlank
    @Size(max = 64)
    private String code;

    @Schema(title = "编码项")
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = DefaultJsonConverter.class, isRequired = "false")
    private List<Item> itemList;

    @Schema(title = "系统子域")
    @Size(max = 64)
    private String domain;

    @Schema(title = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(title = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    private String pinyinName;

    @Schema(title = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(title = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(title = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(title = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(title = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(title = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(title = "备注")
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
