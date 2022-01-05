package com.levin.oak.base.services.area.req;

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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import java.util.Set;
    import com.levin.oak.base.entities.Area.*;
    import java.util.Date;
////////////////////////////////////

/**
 *  删除区域
 *  //Auto gen by simple-dao-codegen 2022-1-5 15:46:44
 */
@Schema(description = "删除区域")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS)
public class DeleteAreaReq extends BaseReq {

    private static final long serialVersionUID = -445860277L;

    @OR
    @Schema(description = "编码" , hidden = true)
    private String code;

    @END
    @Schema(description = "编码集合")
    @In(E_Area.code)
    @Validator(expr = "code != null || ( codeList != null &&  codeList.length > 0)" , promptInfo = "删除区域必须指定ID")
    private String[] codeList;


    public DeleteAreaReq(String code) {
        this.code = code;
    }

    public DeleteAreaReq(String... codeList) {
        this.codeList = codeList;
    }

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
