package com.levin.oak.base.services.dict.info;


import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.DefaultJsonConverter;
import com.levin.oak.base.entities.Dict.Item;
import com.levin.oak.base.entities.Dict.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 字典
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:26
 */
@Schema(title = "字典")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class DictInfo implements Serializable {

    private static final long serialVersionUID = -445779596L;


    @NotNull
    @Schema(title = "id", required = true)
    private String id;


    @NotNull
    @Schema(title = "类型", required = true)
    private Type type;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "编码", required = true)
    private String code;


    @InjectVar(domain = "dao", expectBaseType = String.class, converter = DefaultJsonConverter.class, isRequired = "false")
    @Schema(title = "编码项")
    private List<Item> itemList;


    @Size(max = 64)
    @Schema(title = "租户ID")
    private String tenantId;


    @Size(max = 64)
    @Schema(title = "系统子域")
    private String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称", required = true)
    private String name;


    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(title = "创建者")
    private String creator;


    @NotNull
    @Schema(title = "创建时间", required = true)
    private Date createTime;


    @Schema(title = "更新时间")
    private Date lastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(title = "是否允许", required = true)
    private Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑", required = true)
    private Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;

}
