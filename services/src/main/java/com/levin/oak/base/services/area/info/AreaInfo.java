package com.levin.oak.base.services.area.info;

import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
/////////////////////////////////////////////////////
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Area.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Area.*;
import java.util.Date;
////////////////////////////////////

/**
 * 区域
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 上午12:31:51
 * 代码生成哈希校验码：[c3fee894c7e81464e0e3e06ff8a7bd52]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"code"})
@ToString(exclude = {"parent","children",})
@FieldNameConstants

public class AreaInfo implements Serializable {

    private static final long serialVersionUID = -445860277L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_code , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String code;


    @Schema(title = L_icon )
    String icon;


    @Size(max = 64)
    @Schema(title = L_parentCode )
    String parentCode;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_parent )
    AreaInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_children )
    Set<AreaInfo> children;


    @NotNull
    @Schema(title = L_type , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Type type;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String name;


    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName )
    String pinyinName;


    @Size(max = 128)
    @Schema(title = L_creator )
    String creator;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date createTime;


    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;


    @Schema(title = L_orderCode )
    Integer orderCode;


    @NotNull
    @Schema(title = L_enable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean enable;


    @NotNull
    @Schema(title = L_editable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = L_remark )
    String remark;


}
