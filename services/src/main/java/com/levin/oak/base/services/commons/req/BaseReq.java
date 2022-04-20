package com.levin.oak.base.services.commons.req;


import com.levin.commons.service.domain.ServiceReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;


/**
 * 基本查询对象
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */
@Schema(description = "基本查询对象")
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class BaseReq
        implements
//        OrganizedObject ,
        ServiceReq {

//    @Schema(description = "组织ID" , hidden = true)
//    @InjectVar(value = InjectConsts.ORG_ID , isRequired = "false")
//    protected String orgId;

}
