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
 * @Author Auto gen by simple-dao-codegen 2021-10-28 16:17:42
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
    @Schema(description = "id")
    private Long id;


    @Schema(description = "租户ID")
    private Long tenantId;


    @Schema(description = "编码")
    private String code;


    @Schema(description = "图标")
    private String icon;


    @NotNull
    @Schema(description = "状态")
    private State state;


    @NotNull
    @Schema(description = "类型")
    private Type type;


    @Schema(description = "所属行业")
    private String industries;


    @NotNull
    @Schema(description = "区域编码")
    private String areaCode;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "所属区域")
    private AreaInfo area;


    @Schema(description = "机构级别")
    private String level;


    @NotNull
    @Schema(description = "机构类别")
    private String category;


    @NotNull
    @Schema(description = "是否外部机构")
    private Boolean isExternal;


    @Schema(description = "联系人")
    private String contacts;


    @Schema(description = "联系电话")
    private String phones;


    @Schema(description = "联系邮箱")
    private String emails;


    @Schema(description = "联系地址")
    private String address;


    @Schema(description = "邮政编码")
    private String zipCode;


    @Schema(description = "父ID")
    private Long parentId;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "父对象")
    private OrgInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "子节点")
    private Set<OrgInfo> children;


    @Size(max = 1800)
    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    @NotNull
    @Size(max = 512)
    @Schema(description = "名称")
    private String name;


    @Size(max = 512)
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


    @Size(max = 1800)
    @Schema(description = "备注")
    private String remark;


}
