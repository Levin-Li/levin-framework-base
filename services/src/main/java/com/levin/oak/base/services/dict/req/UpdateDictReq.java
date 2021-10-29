package com.levin.oak.base.services.dict.req;

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

import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.*;


////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Dict.*;
    import java.util.List;
    import java.util.Date;
////////////////////////////////////


/**
 *  更新字典
 *  Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
@Schema(description = "更新字典")
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
@Update
public class UpdateDictReq implements ServiceReq {

    private static final long serialVersionUID = -445779596L;

    @Schema(description = "id")
    @NotNull
    @Eq(require = true)
    private Long id;

    @Schema(description = "类型")
    private Type type;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "编码项")
    private String items;

    @Size(max = 768)
    @Schema(description = "名称")
    private String name;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Size(max = 512)
    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    @Size(max = 1800)
    @Schema(description = "备注")
    private String remark;


    public UpdateDictReq(Long id) {
        this.id = id;
    }
    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}