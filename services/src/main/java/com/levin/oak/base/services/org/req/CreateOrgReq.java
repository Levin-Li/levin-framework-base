package com.levin.oak.base.services.org.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
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
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 新增机构
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月11日 下午5:00:20, 代码生成哈希校验码：[dd1b2e311643712765211a4e60c8fdf5]，请不要修改和删除此行内容。
 *
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
public class CreateOrgReq extends MultiTenantReq<CreateOrgReq> {

    private static final long serialVersionUID = -1399842458L;

    @Schema(title = L_parentId )
    @Size(max = 64)
    String parentId;

    @Schema(title = L_code , description = D_code )
    @Size(max = 64)
    String code;

    @Schema(title = L_icon )
    String icon;

    @Schema(title = L_state )
    @NotNull
    State state;

    @Schema(title = L_type )
    @NotNull
    Type type;

    @Schema(title = L_industries )
    @Size(max = 64)
    String industries;

    @Schema(title = L_areaCode )
    @Size(max = 64)
    String areaCode;

    @Schema(title = L_level , description = D_level )
    @Size(max = 128)
    String level;

    @Schema(title = L_category , description = D_category )
    @NotBlank
    @Size(max = 128)
    String category;

    @Schema(title = L_isExternal , description = D_isExternal )
    @NotNull
    Boolean isExternal;

    @Schema(title = L_contacts )
    @Size(max = 64)
    String contacts;

    @Schema(title = L_phones )
    @Size(max = 20)
    String phones;

    @Schema(title = L_emails )
    @Size(max = 32)
    String emails;

    @Schema(title = L_address )
    String address;

    @Schema(title = L_zipCode )
    @Size(max = 32)
    String zipCode;

    @Schema(title = L_extInfo )
    String extInfo;

    @Schema(title = L_nodePath , description = D_nodePath )
    @Size(max = 1800)
    String nodePath;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = L_pinyinName , description = D_pinyinName )
    @Size(max = 128)
    String pinyinName;

    @Schema(title = L_creator , hidden = true)
    @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Size(max = 128)
    String creator;

    @Schema(title = L_createTime , hidden = true)
    Date createTime;

    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;

    @Schema(title = L_orderCode )
    Integer orderCode;

    @Schema(title = L_enable )
    Boolean enable;

    @Schema(title = L_editable )
    Boolean editable;

    @Schema(title = L_remark )
    @Size(max = 512)
    String remark;

    @Schema(title = L_optimisticLock )
    Integer optimisticLock;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等

        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
    }
}
