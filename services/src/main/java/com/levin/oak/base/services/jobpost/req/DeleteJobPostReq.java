package com.levin.oak.base.services.jobpost.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.JobPost;
import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.JobPost.*;
    import java.util.Date;
////////////////////////////////////

/**
 *  删除工作岗位
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "删除工作岗位")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = JobPost.class, alias = E_JobPost.ALIAS)
public class DeleteJobPostReq implements ServiceReq {

    private static final long serialVersionUID = 1018878847L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "id集合")
    @In(E_JobPost.id)
    @Validator(expr = "id != null || ( ids != null &&  ids.length > 0)" , promptInfo = "删除工作岗位必须指定ID")
    private Long[] ids;


    public DeleteJobPostReq(Long id) {
        this.id = id;
    }

    public DeleteJobPostReq(Long... ids) {
        this.ids = ids;
    }
}
