package com.levin.oak.base.entities;


import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants

@Entity(name = EntityConst.PREFIX + "Whitelist")

@Table(
        indexes = {
                @Index(columnList = E_Whitelist.enable),
                @Index(columnList = E_Whitelist.title),
                @Index(columnList = E_Whitelist.createTime),
                @Index(columnList = E_Whitelist.orderCode),
        }
)
@Schema(title = "白名单")
@EntityCategory(EntityOpConst.PLATFORM_TYPE_NAME)
public class Whitelist extends AbstractBaseEntityObject {

    @Id
    @Contains
    @Schema(title = "URL路径", description = "去除域名后的路径(不含参数)，规范的URL路径，中间不能有2个斜杠等")
    protected Long id;

    @Schema(title = "模块ID")
    @Column(nullable = false)
    protected String moduleId;

    @Schema(title = "名称")
    @Column(length = 64)
    protected String moduleName;

    @Schema(title = "标题")
    @Column(nullable = false)
    @Contains
    protected String title;


    @Schema(title = "域名列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String domainList;

    @Schema(title = "地区列表", description = "逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符")
    @Column(length = 1800)
    protected String regionList;

    @Schema(title = "IP地址列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String ipList;

    @Schema(title = "操作系统列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String osList;

    @Schema(title = "浏览器列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String browserList;

    @Schema(title = "Http方法列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 64)
    protected String methodList;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}
