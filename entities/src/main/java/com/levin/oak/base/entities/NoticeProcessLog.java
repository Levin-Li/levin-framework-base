package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.SimpleTenantOrgObject;
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
@Schema(title = "通知处理日志")

@Entity(name = EntityConst.PREFIX + "NoticeProcessLog")

@Table(
        indexes = {
                @Index(columnList = E_NoticeProcessLog.tenantId),
                @Index(columnList = E_NoticeProcessLog.orgId),
                @Index(columnList = E_NoticeProcessLog.creator),
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
public class NoticeProcessLog extends SimpleTenantOrgObject {

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(title = "创建者")
    @Column(length = 128)
    @InjectVar(value = InjectConsts.USER_ID, isRequired = "false")
    String creator;

    @Schema(title = "消息ID")
    @Column(length = 128, nullable = false)
    String noticeId;

    @Schema(title = "处理状态")
    @Column(length = 128)
    String status;

    @Schema(title = "备注")
    @Column(length = 512)
    String remark;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}

