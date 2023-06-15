package com.levin.oak.base.services.jobpost.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.E_JobPost;
import com.levin.oak.base.entities.JobPost;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.jobpost.info.JobPostInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 工作岗位 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */

@Schema(title = "工作岗位 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = JobPost.class, alias = E_JobPost.ALIAS, resultClass = JobPostInfo.class)
public class JobPostIdReq extends MultiTenantReq {

    private static final long serialVersionUID = 1018878847L;


    @Schema(title = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected String id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
