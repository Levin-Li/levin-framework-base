package com.levin.oak.base.services.accesslog.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////


/**
 *  更新访问日志
 *  Auto gen by simple-dao-codegen 2022-1-5 15:46:44
 */
@Schema(description = "更新访问日志")
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

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    //@InjectVar
    //@Size(max = 64)
    @Schema(description = "请求的域名")
    private String domain;

    //@Size(max = 64)
    @Schema(description = "访问者")
    private String visitor;

    @Schema(description = "标题")
    private String title;

    //@Size(max = 64)
    @Schema(description = "日志类型")
    private String logType;

    @Schema(description = "差异修改数据")
    private String diffModifyData;

    @Schema(description = "业务主键")
    private String bizKey;

    @Schema(description = "业务类型")
    private String bizType;

    @Schema(description = "请求URI")
    private String requestUri;

    //@Size(max = 32)
    @Schema(description = "请求方法")
    private String requestMethod;

    //@Size(max = 768)
    @Schema(description = "请求参数")
    private String requestParams;

    @Schema(description = "响应数据")
    private String responseData;

    //@Size(max = 64)
    @Schema(description = "操作IP地址")
    private String remoteAddr;

    //@Size(max = 64)
    @Schema(description = "服务器地址")
    private String serverAddr;

    @Schema(description = "是否有异常")
    private Boolean isException;

    @Schema(description = "异常信息")
    private String exceptionInfo;

    //@Size(max = 512)
    @Schema(description = "用户代理")
    private String userAgent;

    //@Size(max = 128)
    @Schema(description = "设备名称/操作系统")
    private String deviceName;

    //@Size(max = 64)
    @Schema(description = "浏览器名称")
    private String browserName;

    @Schema(description = "执行时间(ms)")
    private Long executeTime;


    public UpdateAccessLogReq(Long id) {
        this.id = id;
    }
    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

}
