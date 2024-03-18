package com.levin.oak.base.services.accesslog.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import com.levin.commons.dao.annotation.Ignore;

import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;

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
import static com.levin.oak.base.entities.E_AccessLog.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新访问日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月18日 下午3:08:57, 代码生成哈希校验码：[cb10627bb6d87d05f949c5f195927ab3]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateAccessLogReq extends MultiTenantOrgReq<SimpleUpdateAccessLogReq> {

    private static final long serialVersionUID = 1030736962L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final Set<String> needUpdateFields = new HashSet<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @InjectVar(value = InjectConst.DOMAIN, isRequired = "false")
    @Schema(title = L_domain , description = D_domain)
    String domain;

    @Schema(title = L_moduleId , description = D_moduleId)
    String moduleId;

    @Size(max = 64)
    @InjectVar(value = InjectConst.USER_NAME, isRequired = "false")
    @Schema(title = L_visitor)
    String visitor;

    @Size(max = 255)
    @Schema(title = L_title)
    String title;

    @Size(max = 64)
    @Schema(title = L_logType)
    String logType;

    @Schema(title = L_diffModifyData)
    String diffModifyData;

    @Schema(title = L_bizKey)
    String bizKey;

    @Schema(title = L_bizType)
    String bizType;

    @Size(max = 768)
    @Schema(title = L_requestUri)
    String requestUri;

    @Size(max = 32)
    @Schema(title = L_requestMethod)
    String requestMethod;

    @Schema(title = L_headInfo)
    String headInfo;

    @Schema(title = L_requestParams)
    String requestParams;

    @Schema(title = L_requestBody)
    String requestBody;

    @Schema(title = L_responseBody)
    String responseBody;

    @Size(max = 128)
    @Schema(title = L_remoteAddr)
    String remoteAddr;

    @Size(max = 256)
    @Schema(title = L_accessRegion)
    String accessRegion;

    @Size(max = 64)
    @Schema(title = L_serverAddr)
    String serverAddr;

    @Schema(title = L_isException)
    Boolean isException;

    @Schema(title = L_exceptionInfo)
    String exceptionInfo;

    @Size(max = 1800)
    @Schema(title = L_userAgent)
    String userAgent;

    @Size(max = 128)
    @Schema(title = L_deviceName)
    String deviceName;

    @Size(max = 128)
    @Schema(title = L_browserName)
    String browserName;

    @Schema(title = L_executeTime)
    Long executeTime;

    @Eq(desc = "乐观锁更新条件")
    @Update(incrementMode = true, paramExpr = "1", condition = "", desc = "乐观锁版本号 + 1")
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;


    public SimpleUpdateAccessLogReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateAccessLogReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

    public <T extends SimpleUpdateAccessLogReq> T setDomain(String domain) {
        this.domain = domain;
        return addUpdateField(E_AccessLog.domain);
    }
    public <T extends SimpleUpdateAccessLogReq> T setModuleId(String moduleId) {
        this.moduleId = moduleId;
        return addUpdateField(E_AccessLog.moduleId);
    }
    public <T extends SimpleUpdateAccessLogReq> T setVisitor(String visitor) {
        this.visitor = visitor;
        return addUpdateField(E_AccessLog.visitor);
    }
    public <T extends SimpleUpdateAccessLogReq> T setTitle(String title) {
        this.title = title;
        return addUpdateField(E_AccessLog.title);
    }
    public <T extends SimpleUpdateAccessLogReq> T setLogType(String logType) {
        this.logType = logType;
        return addUpdateField(E_AccessLog.logType);
    }
    public <T extends SimpleUpdateAccessLogReq> T setDiffModifyData(String diffModifyData) {
        this.diffModifyData = diffModifyData;
        return addUpdateField(E_AccessLog.diffModifyData);
    }
    public <T extends SimpleUpdateAccessLogReq> T setBizKey(String bizKey) {
        this.bizKey = bizKey;
        return addUpdateField(E_AccessLog.bizKey);
    }
    public <T extends SimpleUpdateAccessLogReq> T setBizType(String bizType) {
        this.bizType = bizType;
        return addUpdateField(E_AccessLog.bizType);
    }
    public <T extends SimpleUpdateAccessLogReq> T setRequestUri(String requestUri) {
        this.requestUri = requestUri;
        return addUpdateField(E_AccessLog.requestUri);
    }
    public <T extends SimpleUpdateAccessLogReq> T setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return addUpdateField(E_AccessLog.requestMethod);
    }
    public <T extends SimpleUpdateAccessLogReq> T setHeadInfo(String headInfo) {
        this.headInfo = headInfo;
        return addUpdateField(E_AccessLog.headInfo);
    }
    public <T extends SimpleUpdateAccessLogReq> T setRequestParams(String requestParams) {
        this.requestParams = requestParams;
        return addUpdateField(E_AccessLog.requestParams);
    }
    public <T extends SimpleUpdateAccessLogReq> T setRequestBody(String requestBody) {
        this.requestBody = requestBody;
        return addUpdateField(E_AccessLog.requestBody);
    }
    public <T extends SimpleUpdateAccessLogReq> T setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return addUpdateField(E_AccessLog.responseBody);
    }
    public <T extends SimpleUpdateAccessLogReq> T setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
        return addUpdateField(E_AccessLog.remoteAddr);
    }
    public <T extends SimpleUpdateAccessLogReq> T setAccessRegion(String accessRegion) {
        this.accessRegion = accessRegion;
        return addUpdateField(E_AccessLog.accessRegion);
    }
    public <T extends SimpleUpdateAccessLogReq> T setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
        return addUpdateField(E_AccessLog.serverAddr);
    }
    public <T extends SimpleUpdateAccessLogReq> T setIsException(Boolean isException) {
        this.isException = isException;
        return addUpdateField(E_AccessLog.isException);
    }
    public <T extends SimpleUpdateAccessLogReq> T setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
        return addUpdateField(E_AccessLog.exceptionInfo);
    }
    public <T extends SimpleUpdateAccessLogReq> T setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return addUpdateField(E_AccessLog.userAgent);
    }
    public <T extends SimpleUpdateAccessLogReq> T setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return addUpdateField(E_AccessLog.deviceName);
    }
    public <T extends SimpleUpdateAccessLogReq> T setBrowserName(String browserName) {
        this.browserName = browserName;
        return addUpdateField(E_AccessLog.browserName);
    }
    public <T extends SimpleUpdateAccessLogReq> T setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
        return addUpdateField(E_AccessLog.executeTime);
    }
    public <T extends SimpleUpdateAccessLogReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_AccessLog.optimisticLock);
    }

   ////////////////////////////////////////////////////////////////////////////////

    /**
    * 是否更新字段
    *
    * @param fieldName
    * @return
    */
    public boolean isUpdateField(String fieldName) {
        return needUpdateFields.contains(fieldName);
    }

    /**
    * 是否更新字段，并删除更新标记，下次调用将不再更新
    *
    * @param fieldName
    * @return 需要更新字段返回 true
    */
    public <T extends SimpleUpdateAccessLogReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateAccessLogReq> T addUpdateField(String fieldName) {
        boolean isAdd = this.forceUpdate && needUpdateFields.add(fieldName);
        return (T) this;
    }

}
