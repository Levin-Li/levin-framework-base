package com.levin.oak.base.services.org.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
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

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增机构
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增机构")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
public class CreateOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;


    @Schema(title = "编码")
    @Size(max = 128)
    private String code;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "状态", required = true)
    @NotNull
    private State state;

    @Schema(title = "类型", required = true)
    @NotNull
    private Type type;

    @Schema(title = "所属行业")
    @Size(max = 64)
    private String industries;

    @Schema(title = "区域编码", required = true)
    @NotBlank
    @Size(max = 64)
    private String areaCode;


    @Schema(title = "机构级别")
    @Size(max = 128)
    private String level;

    @Schema(title = "机构类别", required = true)
    @NotBlank
    @Size(max = 128)
    private String category;

    @Schema(title = "是否外部机构", required = true)
    @NotNull
    private Boolean isExternal;

    @Schema(title = "联系人")
    @Size(max = 64)
    private String contacts;

    @Schema(title = "联系电话")
    @Size(max = 20)
    private String phones;

    @Schema(title = "联系邮箱")
    @Size(max = 32)
    private String emails;

    @Schema(title = "联系地址")
    private String address;

    @Schema(title = "邮政编码")
    @Size(max = 32)
    private String zipCode;

    @Schema(title = "父ID")
    private String parentId;


    @Schema(title = "id路径， 使用|包围，如|1|3|15|")
    @Size(max = 1800)
    private String idPath;

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
