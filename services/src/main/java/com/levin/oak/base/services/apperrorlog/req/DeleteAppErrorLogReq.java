package com.levin.oak.base.services.apperrorlog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.In;
import com.levin.commons.dao.annotation.Lte;
import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.E_AppErrorLog;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 删除应用错误日志
 * //Auto gen by simple-dao-codegen 2022-3-29 22:58:02
 */
@Schema(description = "删除应用错误日志")
@Data

//@AllArgsConstructor

@NoArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)
public class DeleteAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;

    @Schema(description = "id集合")
    @In(value = E_AppErrorLog.id)
    @NotEmpty
    private Long[] idList;

    @Schema(description = "模块ID")
    private String moduleId;

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于发生时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteOccurTime;

    @Schema(description = "小于等于发生时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteOccurTime;

    @Schema(description = "模糊匹配 - 标题")
    @Contains
    private String containsTitle;

    @Schema(description = "错误级别")
    @In
    private List<String> inErrorLevel;

    @Schema(description = "模糊匹配 - 根异常类型")
    @Contains
    private String containsRootExceptionType;

    public DeleteAppErrorLogReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteAppErrorLogReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
