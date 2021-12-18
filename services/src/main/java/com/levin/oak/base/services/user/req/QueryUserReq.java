package com.levin.oak.base.services.user.req;

import io.swagger.v3.oas.annotations.media.Schema;

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

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.user.info.*;
import com.levin.oak.base.entities.User;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.User.*;
    import java.util.Date;
    import java.util.List;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
////////////////////////////////////

/**
 *  查询用户
 *  @Author Auto gen by simple-dao-codegen 2021-12-18 11:15:49
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
@TargetOption(entityClass = User.class, alias = E_User.ALIAS
, resultClass = UserInfo.class)
public class QueryUserReq extends MultiTenantReq{

    private static final long serialVersionUID = -445263479L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "登录名")
    private String loginName;


    @Schema(description = "登录密码")
    private String password;


    @Schema(description = "手机号")
    private String telephone;


    @Schema(description = "邮箱")
    private String email;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "昵称")
    private String nickname;


    @Schema(description = "头像")
    private String avatar;


    @Schema(description = "性别")
    private Sex sex;


    @Schema(description = "帐号标签")
    private String tags;


    @Schema(description = "帐号类型")
    private Category category;


    @Schema(description = "大于等于过期时间")
    @Gte
    private Date gteExpiredDate;

    @Schema(description = "小于等于过期时间")
    @Lte
    private Date lteExpiredDate;



    @Schema(description = "帐号状态")
    private State state;


    @Schema(description = "工号")
    private String staffNo;


    @Schema(description = "岗位职级")
    private String jobPostCode;


    @Schema(description = "角色列表")
    private String roles;


    @Schema(description = "角色列表")
    private List<String> roleList;


    @Schema(description = "是否加载所属部门")
    @Fetch(attrs = E_User.org, condition = "#_val == true")
    private Boolean loadOrg;


    @Schema(description = "微信 OpendId")
    private String wxOpenId;


    @Schema(description = "阿里 OpendId")
    private String aliOpenId;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "大于等于创建时间")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间")
    @Lte
    private Date lteCreateTime;



    @Schema(description = "大于等于更新时间")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间")
    @Lte
    private Date lteLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryUserReq(Long id) {
        this.id = id;
    }
}
