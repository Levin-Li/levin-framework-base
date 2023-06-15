package com.levin.oak.base.services.user.info;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.springframework.data.annotation.ReadOnlyProperty;

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
@Schema(title = "用户")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"org",})
@FieldNameConstants
public class UserInfo implements RbacUserInfo<String>, Serializable {

    private static final long serialVersionUID = -445263479L;


    @NotNull
    @Schema(title = "id")
    private String id;


    @Size(max = 64)
    @Schema(title = "租户ID")
    private String tenantId;


    @Size(max = 64)
    @Schema(title = "登录名")
    private String loginName;


    @Size(max = 256)
    @Schema(title = "登录密码")
    private String password;


    @Size(max = 20)
    @Schema(title = "手机号")
    private String telephone;


    @Size(max = 32)
    @Schema(title = "邮箱")
    private String email;


    @Size(max = 64)
    @Schema(title = "名称")
    private String name;


    @Size(max = 32)
    @Schema(title = "昵称")
    private String nickname;


    @Schema(title = "头像")
    private String avatar;


    @Schema(title = "性别")
    private Sex sex;


    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "标签列表")
    private List<String> tagList;


    @Schema(title = "帐号类型")
    private Category category;


    @Schema(title = "过期时间")
    private Date expiredDate;


    @NotNull
    @Schema(title = "帐号状态")
    private State state;


    @Size(max = 32)
    @Schema(title = "工号")
    private String staffNo;


    @Size(max = 128)
    @Schema(title = "岗位职级")
    private String jobPostCode;


    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "角色列表")
    private List<String> roleList;


    @Schema(title = "所属部门ID")
    private String orgId;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = "所属部门")
    private OrgInfo org;


    @Size(max = 128)
    @Schema(title = "微信 OpendId")
    private String wxOpenId;


    @Size(max = 128)
    @Schema(title = "阿里 OpendId")
    private String aliOpenId;


    @Size(max = 128)
    @Schema(title = "创建者")
    private String creator;


    @NotNull
    @Schema(title = "创建时间")
    private Date createTime;


    @Schema(title = "更新时间")
    private Date lastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(title = "是否允许")
    private Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑")
    private Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;

    @ReadOnlyProperty
    @JsonIgnore
    @Override
    public boolean isTenantAdmin() {
        return RbacUserInfo.super.isTenantAdmin();
    }

    @ReadOnlyProperty
    @JsonIgnore
    @Override
    public boolean isSuperAdmin() {
        return RbacUserInfo.super.isSuperAdmin();
    }

}
