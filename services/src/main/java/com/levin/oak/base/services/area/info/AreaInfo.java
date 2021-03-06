package com.levin.oak.base.services.area.info;


import com.levin.oak.base.entities.Area.Type;
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
import java.util.Set;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 区域
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "区域")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"code"})
@ToString(exclude = {"parent", "children",})
@FieldNameConstants
public class AreaInfo implements Serializable {

    private static final long serialVersionUID = -445860277L;


    @NotBlank
    @Size(max = 64)
    @Schema(description = "编码")
    private String code;


    @Schema(description = "图标")
    private String icon;


    @Size(max = 64)
    @Schema(description = "父区域ID")
    private String parentCode;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "父区域")
    private AreaInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "子区域")
    private Set<AreaInfo> children;


    @NotNull
    @Schema(description = "类型")
    private Type type;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    private String name;


    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(description = "创建者")
    private String creator;


    @NotNull
    @Schema(description = "创建时间")
    private Date createTime;


    @Schema(description = "更新时间")
    private Date lastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(description = "是否允许")
    private Boolean enable;


    @NotNull
    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


}
