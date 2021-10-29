package com.levin.oak.base.services.menures.req;

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

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.plugin.MenuItem.*;
    import com.levin.oak.base.entities.ResOperation;
    import java.util.List;
    import com.levin.oak.base.entities.MenuRes;
    import com.levin.oak.base.services.menures.info.*;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
 *  删除菜单
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "删除菜单")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)
public class DeleteMenuResReq implements ServiceReq {

    private static final long serialVersionUID = -887712701L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "id集合")
    @In(E_MenuRes.id)
    @Validator(expr = "id != null || ( ids != null &&  ids.length > 0)" , promptInfo = "删除菜单必须指定ID")
    private Long[] ids;


    public DeleteMenuResReq(Long id) {
        this.id = id;
    }

    public DeleteMenuResReq(Long... ids) {
        this.ids = ids;
    }
}