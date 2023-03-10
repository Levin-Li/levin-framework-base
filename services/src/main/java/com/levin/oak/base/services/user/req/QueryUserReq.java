package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.logic.OR;
import com.levin.commons.dao.annotation.misc.Fetch;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.JsonStrLikeConverter;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.entities.User.Category;
import com.levin.oak.base.entities.User.Sex;
import com.levin.oak.base.entities.User.State;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 查询用户
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "查询用户")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS, resultClass = UserInfo.class)
public class QueryUserReq extends MultiTenantReq {

    private static final long serialVersionUID = -445263479L;

    @Ignore
    @Schema(description = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull

    @Schema(description = "id")
    private String id;


    //@Size(max = 256)

    @Schema(description = "登录密码")
    private String password;


    //@Size(max = 20)
    @Schema(description = "手机号")
    private String telephone;

    @Schema(description = "模糊匹配 - 手机号")
    @Contains
    private String containsTelephone;


    //@Size(max = 32)

    @Schema(description = "邮箱")
    private String email;


    //@Size(max = 64)

    @Schema(description = "名称")
    private String name;

    @Schema(description = "模糊匹配 - 名称")
    @Contains
    private String containsName;


    //@Size(max = 32)

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "模糊匹配 - 昵称")
    @Contains
    private String containsNickname;


    @Schema(description = "头像")
    private String avatar;


    @Schema(description = "性别")
    private Sex sex;

    @Schema(description = "模糊匹配 - 标签列表")
    @InjectVar(domain = "dao", expectBaseType = List.class, converter = JsonStrLikeConverter.class, isRequired = "false")
    @Contains
    @OR(autoClose = true)
    private List<String> containsTagList;


    @Schema(description = "帐号类型")
    private Category category;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteExpiredDate;

    @Schema(description = "小于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteExpiredDate;


    //@NotNull

    @Schema(description = "帐号状态")
    private State state;


    //@Size(max = 32)

    @Schema(description = "工号")
    private String staffNo;

    @Schema(description = "模糊匹配 - 工号")
    @Contains
    private String containsStaffNo;


    //@Size(max = 128)

    @Schema(description = "岗位职级")
    private String jobPostCode;

    @Schema(description = "模糊匹配 - 角色列表")
    @OR(autoClose = true)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = JsonStrLikeConverter.class, isRequired = "false")
    @Contains
    private List<String> containsRoleList;


    @Schema(description = "是否加载所属部门")
    @Fetch(attrs = E_User.org, condition = "#_val == true")
    private Boolean loadOrg;


    //@Size(max = 128)

    @Schema(description = "微信 OpendId")
    private String wxOpenId;


    //@Size(max = 128)

    @Schema(description = "阿里 OpendId")
    private String aliOpenId;


    //@Size(max = 128)

    @Schema(description = "创建者")
    private String creator;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    //@NotNull

    @Schema(description = "是否允许")
    private Boolean enable;


    //@NotNull

    @Schema(description = "是否可编辑")
    private Boolean editable;


    //@Size(max = 512)

    @Schema(description = "备注")
    private String remark;


    public QueryUserReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
