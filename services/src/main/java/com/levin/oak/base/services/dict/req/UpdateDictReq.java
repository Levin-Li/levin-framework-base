package com.levin.oak.base.services.dict.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;

import java.util.*;

import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.*;

import static com.levin.oak.base.entities.E_Dict.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.List;
import java.util.Date;

import com.levin.oak.base.entities.Dict.*;
import com.levin.commons.service.support.DefaultJsonConverter;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 更新字典
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月28日 下午2:37:39, 代码生成哈希校验码：[550c0b7cae234f4d9123449be1f83c1b]，请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS)
//默认更新注解
@Update(condition = "forceUpdate ? isUpdateField(#_name) : #" + C.VALUE_NOT_EMPTY)
public class UpdateDictReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445779596L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件", hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Schema(title = L_type)
    Type type;

    @Size(max = 256)
    @Schema(title = L_code)
    String code;

    @InjectVar(domain = "dao", isRequired = "false", converter = DefaultJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_itemList, description = D_itemList)
    List<Item> itemList;

    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain, description = D_domain)
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

    //是否强制更新
    @Ignore
    boolean forceUpdate = false;

    //需要更新的字段
    private final List<String> needUpdateFields = new ArrayList<>(5);

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
    public boolean removeUpdateField(String fieldName) {
        return needUpdateFields.remove(fieldName);
    }

    /**
     * 添加更新字段
     *
     * @param fieldName
     * @return
     */
    public boolean addUpdateField(String fieldName) {
        return needUpdateFields.contains(fieldName) || needUpdateFields.add(fieldName);
    }

    /**
     * 设置
     *
     * @param domain
     * @return
     */
    public <T extends UpdateDictReq> T setDomain(String domain) {
        this.domain = domain;
        addUpdateField("domain");
        return (T) this;
    }

    public UpdateDictReq(String id) {
        this.id = id;
    }

    public UpdateDictReq updateIdWhenNotBlank(String id) {
        if (isNotBlank(id)) {
            this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }
}
