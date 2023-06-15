package com.levin.oak.base.services.accesslog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.E_AccessLog;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新访问日志
 * Auto gen by simple-dao-codegen 2022-3-30 8:44:20
 */
@Schema(title = "更新访问日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
//默认更新注解
@Update
public class UpdateAccessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1030736962L;

    @Schema(title = "id", required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    @Schema(title = "请求的域名")
    private String domain;

    @Size(max = 64)
    @Schema(title = "访问者")
    private String visitor;

    @NotBlank
    @Schema(title = "标题")
    private String title;

    @Size(max = 64)
    @Schema(title = "日志类型")
    private String logType;

    @Schema(title = "差异修改数据")
    private String diffModifyData;

    @Schema(title = "业务主键")
    private String bizKey;

    @Schema(title = "业务类型")
    private String bizType;

    @Schema(title = "请求URI")
    private String requestUri;

    @Size(max = 32)
    @Schema(title = "请求方法")
    private String requestMethod;

    @Schema(title = "请求参数")
    private String requestParams;

    @Schema(title = "头部信息")
    private String headInfo;

    @Schema(title = "响应数据")
    private String responseData;

    @Size(max = 128)
    @Schema(title = "操作IP地址")
    private String remoteAddr;

    @Size(max = 64)
    @Schema(title = "服务器地址")
    private String serverAddr;

    @Schema(title = "是否有异常")
    private Boolean isException;

    @Schema(title = "异常信息")
    private String exceptionInfo;

    @Size(max = 768)
    @Schema(title = "用户代理")
    private String userAgent;

    @Size(max = 128)
    @Schema(title = "设备名称/操作系统")
    private String deviceName;

    @Size(max = 64)
    @Schema(title = "浏览器名称")
    private String browserName;

    @Schema(title = "执行时间(ms)")
    private Long executeTime;


    public UpdateAccessLogReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

}
