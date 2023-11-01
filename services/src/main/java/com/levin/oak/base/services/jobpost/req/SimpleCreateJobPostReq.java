package com.levin.oak.base.services.jobpost.req;

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
import static com.levin.oak.base.entities.E_JobPost.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.levin.oak.base.entities.JobPost.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 新增工作岗位
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:43, 代码生成哈希校验码：[c1a4fa5b81fd74b106edca7e287bfe64]，请不要修改和删除此行内容。
 *
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
@TargetOption(entityClass = JobPost.class, alias = E_JobPost.ALIAS)
public class SimpleCreateJobPostReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1018878847L;

    @Schema(title = L_code )
    @NotBlank
    @Size(max = 64)
    String code;

    @Schema(title = L_type )
    @NotNull
    Type type;

    @Schema(title = L_domain , description = D_domain )
    @Size(max = 128)
    String domain;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_optimisticLock )
    Integer optimisticLock;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等
    }
}
