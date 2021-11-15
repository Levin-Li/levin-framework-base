package com.levin.oak.base.services.simpleapi.req;

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

import com.levin.oak.base.entities.SimpleApi;
import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.SimpleApi.*;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  删除动态API
 *  //Auto gen by simple-dao-codegen 2021-11-15 15:01:48
 */
@Schema(description = "删除动态API")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS)
public class DeleteSimpleApiReq implements ServiceReq {

    private static final long serialVersionUID = 1021385738L;

    @OR
    @Schema(description = "id" , hidden = true)
    private Long id;

    @END
    @Schema(description = "id集合")
    @In(E_SimpleApi.id)
    @Validator(expr = "id != null || ( idList != null &&  idList.length > 0)" , promptInfo = "删除动态API必须指定ID")
    private Long[] idList;


    public DeleteSimpleApiReq(Long id) {
        this.id = id;
    }

    public DeleteSimpleApiReq(Long... idList) {
        this.idList = idList;
    }
}
