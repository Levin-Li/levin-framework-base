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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.JobPost;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_JobPost.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.oak.base.entities.JobPost.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 删除工作岗位
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月14日 下午3:54:12, 代码生成哈希校验码：[b1f49f3ea9962a761fd5462ddd6e7c82]，请不要修改和删除此行内容。
 *
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = JobPost.class, alias = E_JobPost.ALIAS)
public class DeleteJobPostReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1018878847L;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Schema(title = L_id + "集合", required = true, requiredMode = REQUIRED)
    @In(value = E_JobPost.id)
    @NotEmpty
    private String[] idList;

    public DeleteJobPostReq(String... idList) {
        this.idList = idList;
    }

    public DeleteJobPostReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
