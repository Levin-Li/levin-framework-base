package com.levin.oak.base.biz.bo.accesswhitelist;

import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.stat.*;

import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;

import com.levin.oak.base.services.accesswhitelist.req.*;
import com.levin.oak.base.services.accesswhitelist.info.*;
import com.levin.oak.base.entities.vo.UrlAcl;

import static com.levin.oak.base.entities.E_AccessWhitelist.*;

////////////////////////////////////
//自动导入列表

////////////////////////////////////

/**
 * 统计访问白名单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月9日 下午10:46:12, 代码生成哈希校验码：[ed20d59e81621aa91075465ef815937d]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = UrlAcl.class, alias = E_AccessWhitelist.ALIAS,
    //表连接
    //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
    resultClass = StatAccessWhitelistReq.Result.class
)
public class StatAccessWhitelistReq extends QueryAccessWhitelistReq{

    private static final long serialVersionUID = 101397189L;

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
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_AccessWhitelist.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        //@Schema(description = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_AccessWhitelist.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(description = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }
}
