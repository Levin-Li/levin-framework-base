package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.StatefulObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
                @Index(columnList = E_User.telephone),
                @Index(columnList = E_User.email),
                @Index(columnList = E_User.state),
                @Index(columnList = E_User.name),
                @Index(columnList = E_User.orgId),
                @Index(columnList = E_User.wxOpenId),
                @Index(columnList = E_User.aliOpenId),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.telephone}),
                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.email}),
        }
)
public class User
        extends TenantOrganizedEntity
        implements OrganizedObject, MultiTenantObject, StatefulObject {

    public enum State implements EnumDesc {
        @Schema(description = "正常")
        Normal,
        @Schema(description = "冻结")
        Freeze,
        @Schema(description = "注销")
        Cancellation,
    }

    public enum Sex implements EnumDesc {
        @Schema(description = "男")
        Man,
        @Schema(description = "女")
        Women,
    }

    public enum Category implements EnumDesc {
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
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(description = "租户ID")
    @Column(length = 64)
    String tenantId;

    @Schema(description = "手机号-可做为登录帐号")
    @Column(length = 20)
    @Contains
    String telephone;

    @Schema(description = "邮箱-可做为登录帐号")
    @Column(length = 32)
    String email;

    @Schema(description = "登录密码")
    @Column(length = 256)
    String password;

    @Schema(description = "昵称")
    @Column(length = 32)
    @Contains
    String nickname;

    @Schema(description = "头像")
    String avatar;

    @Schema(description = "性别")
    @Column(length = 8)
    @Enumerated(EnumType.STRING)
    Sex sex;

    @Schema(description = "标签列表（json数组）", title = "标签列表")
    @Column(length = 1800)
    @Contains
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String tagList;

    ////////////////////////////////////////////////////////////////////

    @Schema(description = "帐号类型")
    @Column(length = 64)
    @Enumerated(EnumType.STRING)
    Category category;

    @Schema(description = "过期时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date expiredDate;

    @Schema(description = "帐号状态")
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    State state;

    @Schema(description = "工号")
    @Column(length = 32)
    @Contains
    String staffNo;

    @Schema(description = "岗位职级")
    @Column(length = 128)
    String jobPostCode;

    @Schema(description = "角色列表(Json数组)", title = "角色列表")
    @Column(length = 1800)
    @Contains
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String roleList;

    ///////////////////////////////////////////////////////////////////////

    @Schema(description = "所属部门")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId", insertable = false, updatable = false)
    Org org;

    //////////////////////////////////////////////////////////////////////

    @Schema(description = "微信 OpendId")
    @Column(length = 64)
    String wxOpenId;

    @Schema(description = "阿里 OpendId")
    @Column(length = 64)
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
