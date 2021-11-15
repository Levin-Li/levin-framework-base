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
 *  @Author Auto gen by simple-dao-codegen 2021-11-15 15:08:50
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
public class QueryUserReq implements ServiceReq  {

    private static final long serialVersionUID = -445263479L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "登录名")
    private String loginName;


    @Schema(description = "登录密码")
    private String password;


    @Schema(description = "手机号")
    private String phone;


    @Schema(description = "邮箱")
    private String email;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "昵称")
    private String nickName;


    @Schema(description = "头像")
    private String avatar;


    @Schema(description = "性别")
    private Sex sex;


    @Schema(description = "帐号标签")
    private String tags;


    @Schema(description = "帐号类型")
    private Category category;


    @Schema(description = "最小过期时间")
    @Gte(E_User.expiredDate)
    private Date minExpiredDate;

    @Schema(description = "最大过期时间")
    @Lte(E_User.expiredDate)
    private Date maxExpiredDate;



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


    @Schema(description = "所属部门ID")
    private String orgId;


    @Schema(description = "加载所属部门")
    @Fetch(attrs = E_User.org, condition = "#_val == true")
    private Boolean loadOrg;


    @Schema(description = "微信 OpendId")
    private String wxOpenId;


    @Schema(description = "阿里 OpendId")
    private String aliOpenId;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_User.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_User.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_User.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_User.lastUpdateTime)
    private Date maxLastUpdateTime;



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
