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
 *  删除字典
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
@Schema(description = "删除字典")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS)
public class DeleteDictReq implements ServiceReq {

    private static final long serialVersionUID = -445779596L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "id集合")
    @In(E_Dict.id)
    @Validator(expr = "id != null || ( ids != null &&  ids.length > 0)" , promptInfo = "删除字典必须指定ID")
    private Long[] ids;


    public DeleteDictReq(Long id) {
        this.id = id;
    }

    public DeleteDictReq(Long... ids) {
        this.ids = ids;
    }
}