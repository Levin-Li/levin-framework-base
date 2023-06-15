package com.levin.oak.base.services.accesslog.req;

import com.levin.commons.dao.TargetOption;
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
import java.util.Date;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增访问日志
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增访问日志")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
public class CreateAccessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1030736962L;


    @Schema(title = "请求的域名")
    private String domain;

    @Schema(title = "访问者")
    @Size(max = 64)
    private String visitor;

    @Schema(title = "创建时间", required = true)
    @NotNull
    private Date createTime;

    @Schema(title = "标题", required = true)
    @NotBlank
    private String title;

    @Schema(title = "日志类型")
    @Size(max = 64)
    private String logType;

    @Schema(title = "差异修改数据")
    private String diffModifyData;

    @Schema(title = "业务主键")
    private String bizKey;

    @Schema(title = "业务类型")
    private String bizType;

    @Schema(title = "请求URI")
    private String requestUri;

    @Schema(title = "请求方法")
    @Size(max = 32)
    private String requestMethod;

    @Schema(title = "请求参数")
    private String requestParams;

    @Schema(title = "头部信息")
    private String headInfo;

    @Schema(title = "响应数据")
    private String responseData;

    @Schema(title = "操作IP地址")
    @Size(max = 128)
    private String remoteAddr;

    @Schema(title = "服务器地址")
    @Size(max = 64)
    private String serverAddr;

    @Schema(title = "是否有异常")
    private Boolean isException;

    @Schema(title = "异常信息")
    private String exceptionInfo;

    @Schema(title = "用户代理")
    @Size(max = 768)
    private String userAgent;

    @Schema(title = "设备名称/操作系统")
    @Size(max = 128)
    private String deviceName;

    @Schema(title = "浏览器名称")
    @Size(max = 64)
    private String browserName;

    @Schema(title = "执行时间(ms)")
    private Long executeTime;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
