package com.levin.oak.base.services.scheduledtask.req;

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

import com.levin.oak.base.entities.ScheduledTask;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledTask.*;
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
 * 更新调度任务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月18日 下午3:08:57, 代码生成哈希校验码：[4348de6715bd008a50a2f54d2e44f841]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateScheduledTaskReq extends MultiTenantOrgReq<SimpleUpdateScheduledTaskReq> {

    private static final long serialVersionUID = -2056389676L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final Set<String> needUpdateFields = new HashSet<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件" , hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && !#_isQuery && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 128)
    @Schema(title = L_category)
    String category;

    @Size(max = 128)
    @Schema(title = L_groupName)
    String groupName;

    @Size(max = 255)
    @Schema(title = L_cron)
    String cron;

    @Schema(title = L_invokeExpr , description = D_invokeExpr)
    String invokeExpr;

    @Schema(title = L_parallelInvoke)
    Boolean parallelInvoke;

    @Schema(title = L_invokedCount)
    Integer invokedCount;

    @Schema(title = L_lastInvokedTime)
    Date lastInvokedTime;

    @Schema(title = L_nextInvokeTime)
    Date nextInvokeTime;

    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    @Eq(desc = "乐观锁更新条件")
    @Update(incrementMode = true, paramExpr = "1", condition = "", desc = "乐观锁版本号 + 1")
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;


    public SimpleUpdateScheduledTaskReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateScheduledTaskReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateScheduledTaskReq> T setCategory(String category) {
        this.category = category;
        return addUpdateField(E_ScheduledTask.category);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setGroupName(String groupName) {
        this.groupName = groupName;
        return addUpdateField(E_ScheduledTask.groupName);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setCron(String cron) {
        this.cron = cron;
        return addUpdateField(E_ScheduledTask.cron);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setInvokeExpr(String invokeExpr) {
        this.invokeExpr = invokeExpr;
        return addUpdateField(E_ScheduledTask.invokeExpr);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setParallelInvoke(Boolean parallelInvoke) {
        this.parallelInvoke = parallelInvoke;
        return addUpdateField(E_ScheduledTask.parallelInvoke);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setInvokedCount(Integer invokedCount) {
        this.invokedCount = invokedCount;
        return addUpdateField(E_ScheduledTask.invokedCount);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setLastInvokedTime(Date lastInvokedTime) {
        this.lastInvokedTime = lastInvokedTime;
        return addUpdateField(E_ScheduledTask.lastInvokedTime);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setNextInvokeTime(Date nextInvokeTime) {
        this.nextInvokeTime = nextInvokeTime;
        return addUpdateField(E_ScheduledTask.nextInvokeTime);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_ScheduledTask.name);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_ScheduledTask.lastUpdateTime);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_ScheduledTask.orderCode);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_ScheduledTask.enable);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_ScheduledTask.editable);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_ScheduledTask.remark);
    }
    public <T extends SimpleUpdateScheduledTaskReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_ScheduledTask.optimisticLock);
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
    public <T extends SimpleUpdateScheduledTaskReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateScheduledTaskReq> T addUpdateField(String fieldName) {
        boolean isAdd = this.forceUpdate && needUpdateFields.add(fieldName);
        return (T) this;
    }

}
