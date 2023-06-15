package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.stat.Count;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.entities.User.Category;
import com.levin.oak.base.entities.User.Sex;
import com.levin.oak.base.entities.User.State;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 统计用户
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(title = "统计用户")
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
public class StatUserReq extends MultiTenantReq {

    private static final long serialVersionUID = -445263479L;


    //@NotNull

    @Schema(title = "id")
    String id;


    //@Size(max = 256)

    @Schema(title = "登录密码")
    String password;

    //@Size(max = 20)

    @Schema(title = "手机号")
    String telephone;
    @Schema(title = "模糊匹配 - 手机号")
    @Contains
    String containsTelephone;

    //@Size(max = 32)

    @Schema(title = "邮箱")
    String email;

    //@Size(max = 64)

    @Schema(title = "名称")
    String name;
    @Schema(title = "模糊匹配 - 名称")
    @Contains
    String containsName;

    //@Size(max = 32)

    @Schema(title = "昵称")
    String nickname;
    @Schema(title = "模糊匹配 - 昵称")
    @Contains
    String containsNickname;


    @Schema(title = "头像")
    String avatar;


    @Schema(title = "性别")
    Sex sex;

    //@Size(max = 1800)
    //@InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")

    @Schema(title = "标签列表")
    List<String> tagList;

    @Schema(title = "模糊匹配 - 标签列表")
    @Contains
    List<String> containsTagList;


    @Schema(title = "帐号类型")
    Category category;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteExpiredDate;

    @Schema(title = "小于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteExpiredDate;


    //@NotNull

    @Schema(title = "帐号状态")
    State state;

    //@Size(max = 32)

    @Schema(title = "工号")
    String staffNo;
    @Schema(title = "模糊匹配 - 工号")
    @Contains
    String containsStaffNo;

    //@Size(max = 128)

    @Schema(title = "岗位职级")
    String jobPostCode;

    //@Size(max = 1800)
    //@InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")

    @Schema(title = "角色列表")
    List<String> roleList;

    @Schema(title = "模糊匹配 - 角色列表")
    @Contains
    List<String> containsRoleList;


    //@Size(max = 128)

    @Schema(title = "微信 OpendId")
    String wxOpenId;

    //@Size(max = 128)

    @Schema(title = "阿里 OpendId")
    String aliOpenId;

    //@InjectVar()
    //@Size(max = 128)

    @Schema(title = "创建者")
    String creator;

    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteLastUpdateTime;


    @Schema(title = "排序代码")
    Integer orderCode;

    //@NotNull

    @Schema(title = "是否允许")
    Boolean enable;

    //@NotNull

    @Schema(title = "是否可编辑")
    Boolean editable;

    //@Size(max = 512)

    @Schema(title = "备注")
    String remark;

    public StatUserReq(String id) {
        this.id = id;
    }

    //
    //@Schema(title = "是否按状态分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore
    //private boolean isGroupByStatus;

    //@Schema(title = "是否按日期分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore //
    //private boolean isGroupByDate;


    @PostConstruct
    public void preStat() {
        //@todo 统计之前初始化数据
    }

    @Schema(title = "用户统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(title = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(title = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_User.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(title = "记录数")
        @Count
        Integer cnt;

        //@Schema(title = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_User.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(title = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
