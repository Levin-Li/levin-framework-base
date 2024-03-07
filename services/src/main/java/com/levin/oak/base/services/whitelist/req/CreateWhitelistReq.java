package com.levin.oak.base.services.whitelist.req;

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
import static com.levin.oak.base.entities.E_Whitelist.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 新增白名单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月7日 下午12:32:49, 代码生成哈希校验码：[8d567fe1bb2ba83116c0b40d655fa60f]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Whitelist.class, alias = E_Whitelist.ALIAS)
public class CreateWhitelistReq extends BaseReq {

    private static final long serialVersionUID = 1491943753L;

    @Schema(title = L_id , description = D_id )
    @NotBlank
    String id;

    @Schema(title = L_moduleId )
    @Size(max = 64)
    String moduleId;

    @Schema(title = L_moduleName )
    @Size(max = 64)
    String moduleName;

    @Schema(title = L_title )
    @NotBlank
    @Size(max = 255)
    String title;

    @Schema(title = L_domainList , description = D_domainList )
    @Size(max = 1800)
    String domainList;

    @Schema(title = L_regionList , description = D_regionList )
    @Size(max = 1800)
    String regionList;

    @Schema(title = L_ipList , description = D_ipList )
    @Size(max = 1800)
    String ipList;

    @Schema(title = L_osList , description = D_osList )
    String osList;

    @Schema(title = L_browserList , description = D_browserList )
    String browserList;

    @Schema(title = L_browserTypeList , description = D_browserTypeList )
    String browserTypeList;

    @Schema(title = L_methodList , description = D_methodList )
    @Size(max = 64)
    String methodList;

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
