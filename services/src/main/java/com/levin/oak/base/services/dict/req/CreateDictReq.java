package com.levin.oak.base.services.dict.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
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

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Dict.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.List;
import java.util.Date;
import com.levin.oak.base.entities.Dict.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.DefaultJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 新增字典
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月24日 下午9:39:10, 代码生成哈希校验码：[2a456923e236392d23fee6f91ee96251]，请不要修改和删除此行内容。
 *
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS)
public class CreateDictReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445779596L;

    @Schema(title = L_type )
    @NotNull
    Type type;

    @Schema(title = L_code )
    @NotBlank
    @Size(max = 256)
    String code;

    @Schema(title = L_itemList , description = D_itemList )
    @InjectVar(domain = "dao", isRequired = "false", converter = DefaultJsonConverter.class, expectBaseType = String.class)
    List<Item> itemList;

    @Schema(title = L_domain , description = D_domain )
    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    String domain;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_optimisticLock )
    @JsonIgnore(value=true)
    Integer optimisticLock;

    @Schema(title = L_creator , hidden = true)
    //@InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    //@Size(max = 128)
    String creator;

    @Schema(title = L_createTime , hidden = true)
    //@NotNull
    Date createTime;

    @Schema(title = L_lastUpdateTime , hidden = true)
    Date lastUpdateTime;

    @Schema(title = L_orderCode , hidden = true)
    Integer orderCode;

    @Schema(title = L_enable , hidden = true)
    //@NotNull
    Boolean enable;

    @Schema(title = L_editable , hidden = true)
    //@NotNull
    Boolean editable;

    @Schema(title = L_remark , hidden = true)
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等

        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
    }
}
