package com.levin.oak.base.services.role.req;

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
import static com.levin.oak.base.entities.E_Role.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.Role.*;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import java.util.Set;
import java.io.Serializable;
import com.levin.commons.service.support.InjectConst;
import java.util.List;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.oak.base.services.role.info.*;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 新增角色
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:05, 代码生成哈希校验码：[b9f1d37037a07e37111b4b02da330702]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS)
public class CreateRoleReq extends MultiTenantReq<CreateRoleReq> {

    private static final long serialVersionUID = -445356492L;

    @Schema(title = L_domain , description = D_domain )
    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    String domain;

    @Schema(title = L_parentId )
    @Size(max = 128)
    String parentId;

    @Schema(title = L_extendable )
    Boolean extendable;

    @Schema(title = L_mutex )
    Boolean mutex;

    @Schema(title = L_userLimit )
    Integer userLimit;

    @Schema(title = L_precondition , description = D_precondition )
    @Size(max = 1800)
    String precondition;

    @Schema(title = L_code )
    @NotBlank
    @Size(max = 128)
    String code;

    @Schema(title = L_expiredDate )
    Date expiredDate;

    @Schema(title = L_icon )
    String icon;

    @Schema(title = L_orgDataScope , description = D_orgDataScope )
    @NotNull
    OrgDataScope orgDataScope;

    @Schema(title = L_assignedOrgIdList , description = D_assignedOrgIdList )
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    List<String> assignedOrgIdList;

    @Schema(title = L_permissionList , description = D_permissionList )
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    List<String> permissionList;

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
