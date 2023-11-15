package com.levin.oak.base.services.notice.req;

import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import com.levin.commons.dao.annotation.Ignore;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.support.*;

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;
import java.io.Serializable;

import com.levin.oak.base.services.notice.info.*;
import com.levin.oak.base.entities.Notice;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Notice.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 统计通知
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月15日 下午6:26:11, 代码生成哈希校验码：[ee6e689d1b3648373b4cceae640c4189]，请不要修改和删除此行内容。
 */
@Schema(title = STAT_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(
        entityClass = Notice.class,
        alias = E_Notice.ALIAS,
        // 连接统计
        // joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn =
        // E_XXX.joinColumn)},
        resultClass = StatNoticeReq.Result.class)
public class StatNoticeReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1394869526L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 64)
    @Schema(title = L_category)
    String category;

    @Schema(title = L_contentType)
    ContentType contentType;

    @Schema(title = L_content)
    String content;

    @Schema(title = L_expiredDate, description = "大于等于" + L_expiredDate)
    @Gte
    Date gteExpiredDate;

    @Schema(title = L_expiredDate, description = "小于等于" + L_expiredDate)
    @Lte
    Date lteExpiredDate;

    // @Schema(title = L_expiredDate + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenExpiredDate;

    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain, description = D_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @JsonIgnore(value = true)
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    @Size(max = 128)
    @InjectVar(value = InjectConsts.USER_ID, isRequired = "false")
    @Schema(title = L_creator)
    String creator;

    @NotNull
    @Schema(title = L_createTime, description = "大于等于" + L_createTime)
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime, description = "小于等于" + L_createTime)
    @Lte
    Date lteCreateTime;

    // @Schema(title = L_createTime + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenCreateTime;

    @Schema(title = L_lastUpdateTime, description = "大于等于" + L_lastUpdateTime)
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = L_lastUpdateTime, description = "小于等于" + L_lastUpdateTime)
    @Lte
    Date lteLastUpdateTime;

    // @Schema(title = L_lastUpdateTime + "-日期范围")
    // @Between(paramDelimiter = "-")
    // String betweenLastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @NotNull
    @Schema(title = L_enable)
    Boolean enable;

    @NotNull
    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    public StatNoticeReq(String id) {
        this.id = id;
    }

    //
    // @Schema(description = "是否按状态分组统计")
    // @CtxVar //增加当前字段名称和字段值到环境变量中
    // @Ignore
    // private boolean isGroupByStatus;

    // @Schema(description = "是否按日期分组统计")
    // @CtxVar //增加当前字段名称和字段值到环境变量中
    // @Ignore //
    // private boolean isGroupByDate;

    @PostConstruct
    public void preStat() {
        // @todo 统计之前初始化数据
    }

    @Schema(description = BIZ_NAME + "统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result implements Serializable {

        // @Schema(description = "状态分组统计")
        // @GroupBy(condition = "#isGroupByStatus")
        // Status status;

        // @Schema(description = "时间分组统计")
        // @GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_Notice.createDate +
        // ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        // String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        // @Schema(description = "分类记录数")
        // @Count(fieldCases = {@Case(column = E_Notice.status, whenOptions = {@Case.When(whenExpr =
        // "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        // Integer caseCnt;

        // @Schema(description = "累计" , havingOp=Op.Gt)
        // @Sum
        // Double sumGmv;

    }
}
