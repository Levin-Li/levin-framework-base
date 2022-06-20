package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.PersonalObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConsts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)

@ToString(exclude = "org")

@FieldNameConstants
@Schema(description = "通知处理日志")

@Entity(name = EntityConst.PREFIX + "NoticeProcessLog")

@Table(
        indexes = {
                @Index(columnList = E_NoticeProcessLog.tenantId),
                @Index(columnList = E_NoticeProcessLog.orgId),
                @Index(columnList = E_NoticeProcessLog.ownerId),
                @Index(columnList = E_NoticeProcessLog.noticeId),
                @Index(columnList = E_NoticeProcessLog.createTime),
                @Index(columnList = E_NoticeProcessLog.status),
        }
        ,
        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.telephone}),
//                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.email}),
        }
)
public class NoticeProcessLog
        implements PersonalObject, OrganizedObject, MultiTenantObject {

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    /**
     * 冗余字段
     */
    @Schema(description = "租户ID")
    @Column(length = 128)
    @InjectVar(value = InjectConsts.TENANT_ID, isRequired = "false")
    String tenantId;

    /**
     * 冗余字段
     */
    @Schema(description = "部门ID")
    @Column(length = 128)
    @InjectVar(value = InjectConsts.ORG_ID, isRequired = "false")
    String orgId;

    @Schema(description = "用户ID")
    @Column(length = 128, nullable = false)
    @InjectVar(InjectConsts.USER_ID)
    String ownerId;

    @Schema(description = "消息ID")
    @Column(length = 128, nullable = false)
    String noticeId;

    @Schema(description = "处理状态")
    @Column(length = 128)
    String status;

    @Schema(description = "处理时间")
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createTime;

    @Schema(description = "备注")
    @Column(length = 512)
    protected String remark;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}

