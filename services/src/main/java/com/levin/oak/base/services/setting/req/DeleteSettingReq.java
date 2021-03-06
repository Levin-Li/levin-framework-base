package com.levin.oak.base.services.setting.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.E_Setting;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 删除系统设置
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "删除系统设置")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
public class DeleteSettingReq extends MultiTenantReq {

    private static final long serialVersionUID = 147875794L;


    @Schema(description = "id集合")
    @In(value = E_Setting.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteSettingReq(String... idList) {
        this.idList = idList;
    }

    public DeleteSettingReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
