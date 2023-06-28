package com.levin.oak.base.services.org.info;

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
import static com.levin.oak.base.entities.E_Org.*;
////////////////////////////////////
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
 * 机构
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 下午4:18:31
 * 代码生成哈希校验码：[e8aede1598a4797ac80a8073593a1525]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"area","extInfo","parent","children",})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class OrgInfo implements Serializable {

    private static final long serialVersionUID = -1399842458L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String id;


    @Size(max = 64)
    @Schema(title = L_parentId )
    String parentId;


    @Size(max = 64)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 64)
    @Schema(title = L_code , description = D_code )
    String code;


    @Schema(title = L_icon )
    String icon;


    @NotNull
    @Schema(title = L_state , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    State state;


    @NotNull
    @Schema(title = L_type , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Type type;


    @Size(max = 64)
    @Schema(title = L_industries )
    String industries;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_areaCode , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String areaCode;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_area )
    AreaInfo area;


    @Size(max = 128)
    @Schema(title = L_level , description = D_level )
    String level;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category , description = D_category , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String category;


    @NotNull
    @Schema(title = L_isExternal , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean isExternal;


    @Size(max = 64)
    @Schema(title = L_contacts )
    String contacts;


    @Size(max = 20)
    @Schema(title = L_phones )
    String phones;


    @Size(max = 32)
    @Schema(title = L_emails )
    String emails;


    @Schema(title = L_address )
    String address;


    @Size(max = 32)
    @Schema(title = L_zipCode )
    String zipCode;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_extInfo )
    String extInfo;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_parent )
    OrgInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_children )
    Set<OrgInfo> children;


    @Size(max = 1800)
    @Schema(title = L_idPath , description = D_idPath )
    String idPath;


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
