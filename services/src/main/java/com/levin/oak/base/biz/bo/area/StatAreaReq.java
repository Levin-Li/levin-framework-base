package com.levin.oak.base.biz.bo.area;

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

import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;
import com.levin.oak.base.entities.Area;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Area.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
import com.levin.oak.base.entities.Area.*;
////////////////////////////////////

/**
 * 统计区域
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:27, 代码生成哈希校验码：[5713113a274877ef6a3ba20e3ee6588b]，请不要修改和删除此行内容。
 *
 */
@Schema(title = STAT_ACTION + BIZ_NAME)
@Data
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS,
    //表连接
    //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
    resultClass = StatAreaReq.Result.class
)
public class StatAreaReq extends QueryAreaReq{

    private static final long serialVersionUID = -445860277L;

    //
    //@Schema(description = "是否按状态分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore
    //private boolean isGroupByStatus;

    //@Schema(description = "是否按日期分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore //
    //private boolean isGroupByDate;

    @PostConstruct
    public void preStat() {
    //@todo 统计之前初始化数据
    }

    @Schema(description = BIZ_NAME + "统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(description = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(description = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_Area.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        //@Schema(description = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_Area.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(description = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }
}
