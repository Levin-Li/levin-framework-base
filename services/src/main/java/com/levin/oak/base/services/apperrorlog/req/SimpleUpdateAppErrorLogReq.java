package com.levin.oak.base.services.apperrorlog.req;

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

import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppErrorLog.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新应用错误日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月8日 下午11:11:15, 代码生成哈希校验码：[c5b0bcfeedba81a76dd547a41e32f7e8]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final List<String> needUpdateFields = new ArrayList<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @Size(max = 64)
    @Schema(title = L_moduleId)
    String moduleId;

    @Schema(title = L_occurTime)
    Date occurTime;

    @Size(max = 768)
    @Schema(title = L_title)
    String title;

    @Schema(title = L_errorLevel)
    String errorLevel;

    @Schema(title = L_rootExceptionType)
    String rootExceptionType;

    @Schema(title = L_exceptionFullInfo)
    String exceptionFullInfo;


    public SimpleUpdateAppErrorLogReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateAppErrorLogReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

    public <T extends SimpleUpdateAppErrorLogReq> T setModuleId(String moduleId) {
        this.moduleId = moduleId;
        return addUpdateField(E_AppErrorLog.moduleId);
    }
    public <T extends SimpleUpdateAppErrorLogReq> T setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
        return addUpdateField(E_AppErrorLog.occurTime);
    }
    public <T extends SimpleUpdateAppErrorLogReq> T setTitle(String title) {
        this.title = title;
        return addUpdateField(E_AppErrorLog.title);
    }
    public <T extends SimpleUpdateAppErrorLogReq> T setErrorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
        return addUpdateField(E_AppErrorLog.errorLevel);
    }
    public <T extends SimpleUpdateAppErrorLogReq> T setRootExceptionType(String rootExceptionType) {
        this.rootExceptionType = rootExceptionType;
        return addUpdateField(E_AppErrorLog.rootExceptionType);
    }
    public <T extends SimpleUpdateAppErrorLogReq> T setExceptionFullInfo(String exceptionFullInfo) {
        this.exceptionFullInfo = exceptionFullInfo;
        return addUpdateField(E_AppErrorLog.exceptionFullInfo);
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
    public <T extends SimpleUpdateAppErrorLogReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateAppErrorLogReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.contains(fieldName) || needUpdateFields.add(fieldName);
        return (T) this;
    }

}
