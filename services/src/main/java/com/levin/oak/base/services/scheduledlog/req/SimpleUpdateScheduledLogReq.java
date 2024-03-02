package com.levin.oak.base.services.scheduledlog.req;

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

import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledLog.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.commons.service.domain.Identifiable;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新调度日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:05, 代码生成哈希校验码：[c55cabb581a6c236c66780e449fd6d69]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateScheduledLogReq extends MultiTenantOrgReq<SimpleUpdateScheduledLogReq> {

    private static final long serialVersionUID = 1319130901L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final Set<String> needUpdateFields = new HashSet<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @Size(max = 64)
    @Schema(title = L_taskId)
    String taskId;

    @Size(max = 128)
    @Schema(title = L_invokeCycle)
    String invokeCycle;

    @Schema(title = L_invokeSnapshot , description = D_invokeSnapshot)
    String invokeSnapshot;

    @Schema(title = L_isError)
    Boolean isError;

    @Schema(title = L_invokeResult)
    String invokeResult;

    @Eq(desc = "乐观锁更新条件")
    @Update(incrementMode = true, paramExpr = "1", condition = "", desc = "乐观锁版本号 + 1")
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;


    public SimpleUpdateScheduledLogReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateScheduledLogReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

    public <T extends SimpleUpdateScheduledLogReq> T setTaskId(String taskId) {
        this.taskId = taskId;
        return addUpdateField(E_ScheduledLog.taskId);
    }
    public <T extends SimpleUpdateScheduledLogReq> T setInvokeCycle(String invokeCycle) {
        this.invokeCycle = invokeCycle;
        return addUpdateField(E_ScheduledLog.invokeCycle);
    }
    public <T extends SimpleUpdateScheduledLogReq> T setInvokeSnapshot(String invokeSnapshot) {
        this.invokeSnapshot = invokeSnapshot;
        return addUpdateField(E_ScheduledLog.invokeSnapshot);
    }
    public <T extends SimpleUpdateScheduledLogReq> T setIsError(Boolean isError) {
        this.isError = isError;
        return addUpdateField(E_ScheduledLog.isError);
    }
    public <T extends SimpleUpdateScheduledLogReq> T setInvokeResult(String invokeResult) {
        this.invokeResult = invokeResult;
        return addUpdateField(E_ScheduledLog.invokeResult);
    }
    public <T extends SimpleUpdateScheduledLogReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_ScheduledLog.optimisticLock);
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
    public <T extends SimpleUpdateScheduledLogReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateScheduledLogReq> T addUpdateField(String fieldName) {
        boolean isAdd = this.forceUpdate && needUpdateFields.add(fieldName);
        return (T) this;
    }

}
