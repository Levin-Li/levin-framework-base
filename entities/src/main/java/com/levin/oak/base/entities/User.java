package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.rbac.UserObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)

@ToString(exclude = "org")

@FieldNameConstants
@Schema(description = "用户")

@Entity(name = EntityConst.PREFIX + "User")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = E_User.tenantId),
                @Index(columnList = E_User.staffNo),
                @Index(columnList = E_User.loginName),
                @Index(columnList = E_User.telephone),
                @Index(columnList = E_User.email),
                @Index(columnList = E_User.state),
                @Index(columnList = E_User.name),
//                @Index(columnList = E_User.orgId),
                @Index(columnList = E_User.wxOpenId),
                @Index(columnList = E_User.aliOpenId),
        }

        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.telephone}),
                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.loginName}),
                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.email}),
        }
)
public class User
        extends AbstractBaseEntityObject
        implements OrganizedObject, UserObject, MultiTenantObject {

    public enum State {
        @Schema(description = "正常")
        Normal,
        @Schema(description = "冻结")
        Freeze,
        @Schema(description = "注销")
        Cancellation,
    }

    public enum Sex {
        @Schema(description = "男")
        Man,
        @Schema(description = "女")
        Women,
    }

    public enum Category {
        @Schema(description = "正式员工")
        Staff,
        @Schema(description = "外包员工")
        OutsourcingStaff,
        @Schema(description = "临时员工")
        TmpStaff,
        @Schema(description = "外部驻场")
        ExternalStaff,
        @Schema(description = "VIP访客")
        VipVisitor,
        @Schema(description = "普通访客")
        Visitor,
    }

    @Id
    @GeneratedValue
    Long id;

    @Schema(description = "租户ID")
            @Column(length = 64)
    String tenantId;

    @Schema(description = "登录名")
    @Column(length = 64)
    String loginName;

    @Schema(description = "登录密码")
    @Column(length = 256)
    String password;

    @Schema(description = "手机号")
    @Column(length = 20)
    String telephone;

    @Schema(description = "邮箱")
    @Column(length = 32)
    String email;

    @Schema(description = "名称")
    @Column(length = 64)
    String name;

    @Schema(description = "昵称")
    @Column(length = 32)
    String nickname;

    @Schema(description = "头像")
    String avatar;

    @Schema(description = "性别")
    Sex sex;

    @Schema(description = "帐号标签", title = "半角逗号隔开")
    @Column(length = 1800)
    String tags;

    ////////////////////////////////////////////////////////////////////

    @Schema(description = "帐号类型")
    Category category;

    @Schema(description = "过期时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date expiredDate;

    @Schema(description = "帐号状态")
    @Column(nullable = false)
    State state;

    @Schema(description = "工号")
    @Column(length = 32)
    String staffNo;

    @Schema(description = "岗位职级")
    @Column(length = 128)
    String jobPostCode;

    @Schema(description = "角色列表", title = "Json")
    @Column(length = 1800)
    String roles;

    @Transient
    @Schema(description = "角色列表")
    List<String> roleList;

    ///////////////////////////////////////////////////////////////////////
    @Schema(description = "所属部门ID")
    Long orgId;

    @Schema(description = "所属部门")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId", insertable = false, updatable = false)
    Org org;

    //////////////////////////////////////////////////////////////////////

    @Schema(description = "微信 OpendId")
    @Column(length = 128)
    String wxOpenId;

    @Schema(description = "阿里 OpendId")
    @Column(length = 128)
    String aliOpenId;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (state == null) {
            state = State.Normal;
        }

    }
}
