package com.levin.oak.base.services.jobpost.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.JobPost;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_JobPost.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.oak.base.entities.JobPost.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新工作岗位
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:27, 代码生成哈希校验码：[4dd65f48ef84f1682ed54c84a9825e37]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = JobPost.class, alias = E_JobPost.ALIAS)

public class UpdateJobPostReq extends SimpleUpdateJobPostReq {

    private static final long serialVersionUID = 1018878847L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    public UpdateJobPostReq() {
    }

    public UpdateJobPostReq(String id) {
        this.id = id;
    }

    public UpdateJobPostReq(String id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateJobPostReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateJobPostReq of(String id, boolean forceUpdate){
        return new UpdateJobPostReq(id, forceUpdate);
    }

}
