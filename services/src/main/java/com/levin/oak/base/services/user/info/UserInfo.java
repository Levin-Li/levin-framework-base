package com.levin.oak.base.services.user.info;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.oak.base.entities.User.Category;
import com.levin.oak.base.entities.User.Sex;
import com.levin.oak.base.entities.User.State;
import com.levin.oak.base.services.org.info.OrgInfo;
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
import java.util.List;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 用户
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "用户")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"org",})
@FieldNameConstants
public class UserInfo implements RbacUserInfo<String>, Serializable {

    private static final long serialVersionUID = -445263479L;


    @NotNull
    @Schema(description = "id")
    private String id;


    @Size(max = 64)
    @Schema(description = "租户ID")
    private String tenantId;


    @Size(max = 64)
    @Schema(description = "登录名")
    private String loginName;


    @Size(max = 256)
    @Schema(description = "登录密码")
    private String password;


    @Size(max = 20)
    @Schema(description = "手机号")
    private String telephone;


    @Size(max = 32)
    @Schema(description = "邮箱")
    private String email;


    @Size(max = 64)
    @Schema(description = "名称")
    private String name;


    @Size(max = 32)
    @Schema(description = "昵称")
    private String nickname;


    @Schema(description = "头像")
    private String avatar;


    @Schema(description = "性别")
    private Sex sex;


    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(description = "标签列表")
    private List<String> tagList;


    @Schema(description = "帐号类型")
    private Category category;


    @Schema(description = "过期时间")
    private Date expiredDate;


    @NotNull
    @Schema(description = "帐号状态")
    private State state;


    @Size(max = 32)
    @Schema(description = "工号")
    private String staffNo;


    @Size(max = 128)
    @Schema(description = "岗位职级")
    private String jobPostCode;


    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(description = "角色列表")
    private List<String> roleList;


    @Schema(description = "所属部门ID")
    private String orgId;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "所属部门")
    private OrgInfo org;


    @Size(max = 128)
    @Schema(description = "微信 OpendId")
    private String wxOpenId;


    @Size(max = 128)
    @Schema(description = "阿里 OpendId")
    private String aliOpenId;


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

    @JsonIgnore
    @Override
    public boolean isSuperAdmin() {
        return roleList != null && roleList.contains(RbacRoleObject.SA_ROLE);
    }

}
