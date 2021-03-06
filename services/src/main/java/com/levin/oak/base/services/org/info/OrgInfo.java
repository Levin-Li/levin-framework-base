package com.levin.oak.base.services.org.info;


import com.levin.oak.base.entities.Org.State;
import com.levin.oak.base.entities.Org.Type;
import com.levin.oak.base.services.area.info.AreaInfo;
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
 * 机构
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:27
 */
@Schema(description = "机构")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"area", "parent", "children",})
@FieldNameConstants
public class OrgInfo implements Serializable {

    private static final long serialVersionUID = -1399842458L;


    @NotNull
    @Schema(description = "id", required = true)
    private String id;


    @Schema(description = "租户ID")
    private String tenantId;


    @Size(max = 128)
    @Schema(description = "编码")
    private String code;


    @Schema(description = "图标")
    private String icon;


    @NotNull
    @Schema(description = "状态", required = true)
    private State state;


    @NotNull
    @Schema(description = "类型", required = true)
    private Type type;


    @Size(max = 64)
    @Schema(description = "所属行业")
    private String industries;


    @NotBlank
    @Size(max = 64)
    @Schema(description = "区域编码", required = true)
    private String areaCode;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "所属区域")
    private AreaInfo area;


    @Size(max = 128)
    @Schema(description = "机构级别")
    private String level;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "机构类别", required = true)
    private String category;


    @NotNull
    @Schema(description = "是否外部机构", required = true)
    private Boolean isExternal;


    @Size(max = 64)
    @Schema(description = "联系人")
    private String contacts;


    @Size(max = 20)
    @Schema(description = "联系电话")
    private String phones;


    @Size(max = 32)
    @Schema(description = "联系邮箱")
    private String emails;


    @Schema(description = "联系地址")
    private String address;


    @Size(max = 32)
    @Schema(description = "邮政编码")
    private String zipCode;


    @Schema(description = "父ID")
    private String parentId;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "父对象")
    private OrgInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "子节点")
    private Set<OrgInfo> children;


    @Size(max = 1800)
    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称", required = true)
    private String name;


    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(description = "创建者")
    private String creator;


    @NotNull
    @Schema(description = "创建时间", required = true)
    private Date createTime;


    @Schema(description = "更新时间")
    private Date lastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(description = "是否允许", required = true)
    private Boolean enable;


    @NotNull
    @Schema(description = "是否可编辑", required = true)
    private Boolean editable;


    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


}
