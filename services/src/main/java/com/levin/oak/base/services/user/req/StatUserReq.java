package com.levin.oak.base.services.user.req;

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

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;
import java.io.Serializable;

import com.levin.oak.base.services.user.info.*;
import com.levin.oak.base.entities.User;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.support.InjectConsts;
    import com.levin.commons.service.domain.InjectVar;
    import com.levin.oak.base.entities.User.*;
    import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
    import java.util.Date;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
////////////////////////////////////

/**
 *  统计用户
 *  @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(description = "统计用户")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS,
     //连接统计
    //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
    resultClass = StatUserReq.Result.class
)
public class StatUserReq extends MultiTenantReq{

    private static final long serialVersionUID = -445263479L;


    //@NotNull

    @Schema(description = "id")
     Long id;

    //@Size(max = 64)

    @Schema(description = "登录名")
     String loginName;

    //@Size(max = 256)

    @Schema(description = "登录密码")
     String password;

    //@Size(max = 20)

    @Schema(description = "手机号")
     String telephone;
    @Schema(description = "模糊匹配 - 手机号")
    @Contains
     String containsTelephone;

    //@Size(max = 32)

    @Schema(description = "邮箱")
     String email;

    //@Size(max = 64)

    @Schema(description = "名称")
     String name;
    @Schema(description = "模糊匹配 - 名称")
    @Contains
     String containsName;

    //@Size(max = 32)

    @Schema(description = "昵称")
     String nickname;
    @Schema(description = "模糊匹配 - 昵称")
    @Contains
     String containsNickname;


    @Schema(description = "头像")
     String avatar;


    @Schema(description = "性别")
     Sex sex;

    //@Size(max = 1800)
    //@InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")

    @Schema(description = "标签列表")
     List<String> tagList;
    @Schema(description = "模糊匹配 - 标签列表")
    @Contains
     List<String> containsTagList;


    @Schema(description = "帐号类型")
     Category category;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
     Date gteExpiredDate;

    @Schema(description = "小于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
     Date lteExpiredDate;


    //@NotNull

    @Schema(description = "帐号状态")
     State state;

    //@Size(max = 32)

    @Schema(description = "工号")
     String staffNo;
    @Schema(description = "模糊匹配 - 工号")
    @Contains
     String containsStaffNo;

    //@Size(max = 128)

    @Schema(description = "岗位职级")
     String jobPostCode;

    //@Size(max = 1800)
    //@InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")

    @Schema(description = "角色列表")
     List<String> roleList;
    @Schema(description = "模糊匹配 - 角色列表")
    @Contains
     List<String> containsRoleList;



    //@Size(max = 128)

    @Schema(description = "微信 OpendId")
     String wxOpenId;

    //@Size(max = 128)

    @Schema(description = "阿里 OpendId")
     String aliOpenId;

    //@InjectVar()
    //@Size(max = 128)

    @Schema(description = "创建者")
     String creator;

    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
     Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
     Date lteCreateTime;



    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
     Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
     Date lteLastUpdateTime;



    @Schema(description = "排序代码")
     Integer orderCode;

    //@NotNull

    @Schema(description = "是否允许")
     Boolean enable;

    //@NotNull

    @Schema(description = "是否可编辑")
     Boolean editable;

    //@Size(max = 512)

    @Schema(description = "备注")
     String remark;

    public StatUserReq(Long id) {
        this.id = id;
    }

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

    @Schema(description = "用户统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(description = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(description = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_User.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        //@Schema(description = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_User.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(description = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
