package com.levin.oak.base.services.org.req;

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

import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Org.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 统计机构
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 上午2:26:21, 代码生成哈希校验码：[2dd9b29a9b7207d3fa21e4494bf53b5b]，请不要修改和删除此行内容。
 */
@Schema(title = STAT_ACTION + BIZ_NAME)
@Data
// @AllArgsConstructor
@NoArgsConstructor
// @Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(
        entityClass = Org.class,
        alias = E_Org.ALIAS,
        // 连接统计
        // joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn =
        // E_XXX.joinColumn)},
        resultClass = StatOrgReq.Result.class)
public class StatOrgReq extends QueryOrgReq {

    private static final long serialVersionUID = -1399842458L;

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
        // @GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_Org.createDate +
        // ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        // String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        // @Schema(description = "分类记录数")
        // @Count(fieldCases = {@Case(column = E_Org.status, whenOptions = {@Case.When(whenExpr =
        // "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        // Integer caseCnt;

        // @Schema(description = "累计" , havingOp=Op.Gt)
        // @Sum
        // Double sumGmv;

    }
}
