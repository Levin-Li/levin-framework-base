package com.levin.oak.base.services.area.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
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
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月25日 13:59:27, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[290bacba803a3a062ecfcbf70e2b215d], 请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"code"})
@ToString(exclude = {"parent","children",})
@FieldNameConstants
@JsonIgnoreProperties({})
@Select
public class SimpleAreaInfo implements Serializable {

    private static final long serialVersionUID = -445860277L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_code)
    String code;

    @Schema(title = L_icon)
    String icon;

    @Size(max = 64)
    @Schema(title = L_parentCode)
    String parentCode;

    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_parent)
    AreaInfo parent;

    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_children)
    Set<AreaInfo> children;

    @NotNull
    @Schema(title = L_type)
    Type type;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;


}
