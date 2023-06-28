package com.levin.oak.base.services.noticeprocesslog.req;

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
import static com.levin.oak.base.entities.E_NoticeProcessLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////


/**
 *  新增通知处理日志
 *  //Auto gen by simple-dao-codegen 2023年6月28日 上午9:18:57
 * 代码生成哈希校验码：[236df823d8ae118ddc44130b81941329]
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
@TargetOption(entityClass = NoticeProcessLog.class, alias = E_NoticeProcessLog.ALIAS)
public class CreateNoticeProcessLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -1991983093L;


    @Schema(title = L_ownerId  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String ownerId;

    @Schema(title = L_noticeId  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 128)
    String noticeId;

    @Schema(title = L_status  )
    @Size(max = 128)
    String status;

    @Schema(title = L_createTime  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    Date createTime;

    @Schema(title = L_remark  )
    @Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
