package com.levin.oak.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.StatefulObject;
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
@Schema(title = "用户")

@Entity(name = EntityConst.PREFIX + "User")

@Table(
        indexes = {
                @Index(columnList = E_User.orderCode),
                @Index(columnList = E_User.tenantId),
                @Index(columnList = E_User.orgId),
                @Index(columnList = E_User.staffNo),
                @Index(columnList = E_User.telephone),
                @Index(columnList = E_User.email),
                @Index(columnList = E_User.state),
                @Index(columnList = E_User.name),
                @Index(columnList = E_User.wxOpenId),
                @Index(columnList = E_User.aliOpenId),

                @Index(columnList = E_TenantOrgNamedEntity.tenantId + "," + E_TenantOrgNamedEntity.orgId),
                @Index(columnList = E_User.tenantId + "," + E_User.orgId + "," + E_User.id),
                @Index(columnList = E_User.tenantId + "," + E_User.telephone),
                @Index(columnList = E_User.tenantId + "," + E_User.email),
        }
        ,
        uniqueConstraints = {
                //允许不同的租户中有一样的手机号
                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.telephone}),
                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.email}),
        }
)
public class User
        extends TenantOrgNamedEntity
        implements OrganizedObject, MultiTenantObject, StatefulObject {

    public enum State implements EnumDesc {
        @Schema(title = "正常") Normal,
        @Schema(title = "冻结") Freeze,
        @Schema(title = "注销") Cancellation,
    }

    public enum Sex implements EnumDesc {
        @Schema(title = "男") Man,
        @Schema(title = "女") Women,
    }

    public enum Category implements EnumDesc {
        @Schema(title = "正式员工")
        Staff,
        @Schema(title = "外包员工")
        OutsourcingStaff,
        @Schema(title = "临时员工")
        TmpStaff,
        @Schema(title = "外部驻场")
        ExternalStaff,
        @Schema(title = "VIP访客")
        VipVisitor,
        @Schema(title = "普通访客")
        Visitor,
    }

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(title = "手机号", description = "可做为登录帐号")
    @Column(length = 20)
    @Contains
    String telephone;

    @Schema(title = "邮箱", description = "可做为登录帐号")
    @Column(length = 32)
    String email;

    @JsonIgnore
    @Schema(title = "登录密码")
    @Column(length = 256)
    String password;

    @Schema(title = "昵称")
    @Column(length = 32)
    @Contains
    String nickname;

    @Schema(title = "头像")
    String avatar;

    @Schema(title = "性别")
    @Column(length = 8)
    @Enumerated(EnumType.STRING)
    Sex sex;

    @Schema(title = "标签列表")
    @Column(length = 1800)
    @Contains
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String tagList;

    ////////////////////////////////////////////////////////////////////

    @Schema(title = "帐号类型")
    @Column(length = 64)
    @Enumerated(EnumType.STRING)
    Category category;

    @Schema(title = "过期时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date expiredDate;

    @Schema(title = "帐号状态")
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    State state;

    @Schema(title = "工号")
    @Column(length = 32)
    @Contains
    String staffNo;

    @Schema(title = "岗位职级")
    @Column(length = 128)
    String jobPostCode;

    @Schema(title = "角色ID列表")
    @Column(length = 1800)
    @Contains
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String roleList;

    ///////////////////////////////////////////////////////////////////////

    @Schema(title = "所属部门")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId", insertable = false, updatable = false)
    Org org;

    //////////////////////////////////////////////////////////////////////

    @Schema(title = "微信 OpendId")
    @Column(length = 64)
    String wxOpenId;

    @Schema(title = "阿里 OpendId")
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
