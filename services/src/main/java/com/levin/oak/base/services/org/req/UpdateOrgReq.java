package com.levin.oak.base.services.org.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.E_Org;
import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.Org.State;
import com.levin.oak.base.entities.Org.Type;
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

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新机构
 * Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "更新机构")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
//默认更新注解
@Update
public class UpdateOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;

    @Schema(description = "id", required = true)
    @NotNull
    @Eq(require = true)
    private String id;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 128)
    @Schema(description = "编码")
    private String code;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "状态")
    private State state;

    @Schema(description = "类型")
    private Type type;

    @Size(max = 64)
    @Schema(description = "所属行业")
    private String industries;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "区域编码")
    private String areaCode;

    @Size(max = 128)
    @Schema(description = "机构级别")
    private String level;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "机构类别")
    private String category;

    @Schema(description = "是否外部机构")
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

    @Size(max = 1800)
    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;

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


    public UpdateOrgReq(String id) {
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
