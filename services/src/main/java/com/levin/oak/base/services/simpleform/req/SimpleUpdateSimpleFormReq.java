package com.levin.oak.base.services.simpleform.req;

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

import com.levin.oak.base.entities.SimpleForm;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_SimpleForm.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新简单表单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月11日 下午5:00:20, 代码生成哈希校验码：[6ee492824418ce01928ad9dd571186f3]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = SimpleForm.class, alias = E_SimpleForm.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateSimpleFormReq extends MultiTenantOrgReq<SimpleUpdateSimpleFormReq> {

    private static final long serialVersionUID = 1598335188L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final List<String> needUpdateFields = new ArrayList<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件" , hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Schema(title = L_commitApi)
    String commitApi;

    @Size(max = 128)
    @Schema(title = L_type)
    String type;

    @Size(max = 128)
    @Schema(title = L_category)
    String category;

    @Size(max = 128)
    @Schema(title = L_groupName)
    String groupName;

    @Schema(title = L_icon)
    String icon;

    @Size(max = 800)
    @Schema(title = L_path)
    String path;

    @Size(max = 1800)
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_requireAuthorizations)
    List<String> requireAuthorizations;

    @Schema(title = L_content)
    String content;

    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain , description = D_domain)
    String domain;

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


    public SimpleUpdateSimpleFormReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateSimpleFormReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateSimpleFormReq> T setCommitApi(String commitApi) {
        this.commitApi = commitApi;
        return addUpdateField(E_SimpleForm.commitApi);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setType(String type) {
        this.type = type;
        return addUpdateField(E_SimpleForm.type);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setCategory(String category) {
        this.category = category;
        return addUpdateField(E_SimpleForm.category);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setGroupName(String groupName) {
        this.groupName = groupName;
        return addUpdateField(E_SimpleForm.groupName);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setIcon(String icon) {
        this.icon = icon;
        return addUpdateField(E_SimpleForm.icon);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setPath(String path) {
        this.path = path;
        return addUpdateField(E_SimpleForm.path);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setRequireAuthorizations(List<String> requireAuthorizations) {
        this.requireAuthorizations = requireAuthorizations;
        return addUpdateField(E_SimpleForm.requireAuthorizations);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setContent(String content) {
        this.content = content;
        return addUpdateField(E_SimpleForm.content);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setDomain(String domain) {
        this.domain = domain;
        return addUpdateField(E_SimpleForm.domain);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_SimpleForm.name);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_SimpleForm.lastUpdateTime);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_SimpleForm.orderCode);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_SimpleForm.enable);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_SimpleForm.editable);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_SimpleForm.remark);
    }
    public <T extends SimpleUpdateSimpleFormReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_SimpleForm.optimisticLock);
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
    public <T extends SimpleUpdateSimpleFormReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateSimpleFormReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.contains(fieldName) || needUpdateFields.add(fieldName);
        return (T) this;
    }

}
