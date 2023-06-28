package com.levin.oak.base.services.org.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;

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
import static com.levin.oak.base.entities.E_Org.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Org.*;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Set;
import java.util.Date;
////////////////////////////////////


/**
 *  新增机构
 *  //Auto gen by simple-dao-codegen 2023年6月28日 上午9:18:57
 * 代码生成哈希校验码：[4fd9bbfeca39e9b90e3e84ed7d177553]
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
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
public class CreateOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;


    @Schema(title = L_parentId  )
    @Size(max = 64)
    String parentId;

    @Schema(title = L_code , description = D_code  )
    @Size(max = 64)
    String code;

    @Schema(title = L_icon  )
    String icon;

    @Schema(title = L_state  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    State state;

    @Schema(title = L_type  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    Type type;

    @Schema(title = L_industries  )
    @Size(max = 64)
    String industries;

    @Schema(title = L_areaCode  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 64)
    String areaCode;


    @Schema(title = L_level , description = D_level  )
    @Size(max = 128)
    String level;

    @Schema(title = L_category , description = D_category  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String category;

    @Schema(title = L_isExternal  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    Boolean isExternal;

    @Schema(title = L_contacts  )
    @Size(max = 64)
    String contacts;

    @Schema(title = L_phones  )
    @Size(max = 20)
    String phones;

    @Schema(title = L_emails  )
    @Size(max = 32)
    String emails;

    @Schema(title = L_address  )
    String address;

    @Schema(title = L_zipCode  )
    @Size(max = 32)
    String zipCode;

    @Schema(title = L_extInfo  )
    String extInfo;



    @Schema(title = L_idPath , description = D_idPath  )
    @Size(max = 1800)
    String idPath;

    @Schema(title = L_name  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = L_pinyinName , description = D_pinyinName  )
    @Size(max = 128)
    String pinyinName;

    @Schema(title = L_creator , hidden = true )
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    String creator;

    @Schema(title = L_createTime , hidden = true )
    //@NotNull
    Date createTime;

    @Schema(title = L_lastUpdateTime , hidden = true )
    Date lastUpdateTime;

    @Schema(title = L_orderCode , hidden = true )
    Integer orderCode;

    @Schema(title = L_enable , hidden = true )
    //@NotNull
    Boolean enable;

    @Schema(title = L_editable , hidden = true )
    //@NotNull
    Boolean editable;

    @Schema(title = L_remark , hidden = true )
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
