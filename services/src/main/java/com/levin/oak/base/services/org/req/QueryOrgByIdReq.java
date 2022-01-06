package com.levin.oak.base.services.org.req;

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

import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Org.*;
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
*  ID 查询机构
*  //Auto gen by simple-dao-codegen 2022-1-6 10:16:40
*/
@Schema(description = "ID 查询机构")
@Data

    @AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
public class QueryOrgByIdReq extends MultiTenantReq {

private static final long serialVersionUID = -1399842458L;


    @Schema(description = "id" , required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;

    //public QueryOrgByIdReq(Long id) {
    //    this.id = id;
    //}

    /**
     * 获取缓存ID
     */
    @Schema(description = "缓存ID-内部使用" , hidden = true)
    public final String getCacheId() {

        String cid = id.toString() + ""  + tenantId.toString();

        return cid.trim().length() > 0 ? cid : null;

    }


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
