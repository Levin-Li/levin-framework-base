package com.levin.oak.base.services.user.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


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
 *  新增用户
 *  //Auto gen by simple-dao-codegen 2022-1-11 16:49:27
 */
@Schema(description = "新增用户")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
public class CreateUserReq extends MultiTenantReq {

    private static final long serialVersionUID = -445263479L;



    @Schema(description = "登录名" )
    @Size(max = 64)
    private String loginName;


    @Schema(description = "登录密码" )
    @Size(max = 256)
    private String password;


    @Schema(description = "手机号" )
    @Size(max = 20)
    private String telephone;


    @Schema(description = "邮箱" )
    @Size(max = 32)
    private String email;


    @Schema(description = "名称" )
    @Size(max = 64)
    private String name;


    @Schema(description = "昵称" )
    @Size(max = 32)
    private String nickname;


    @Schema(description = "头像" )
    private String avatar;


    @Schema(description = "性别" )
    private Sex sex;


    @Schema(description = "帐号标签json array" )
    @Size(max = 1800)
    private String tags;


    @Schema(description = "帐号类型" )
    private Category category;


    @Schema(description = "过期时间" )
    private Date expiredDate;


    @Schema(description = "帐号状态" , required = true)
    @NotNull
    private State state;


    @Schema(description = "工号" )
    @Size(max = 32)
    private String staffNo;


    @Schema(description = "岗位职级" )
    @Size(max = 128)
    private String jobPostCode;


    @Schema(description = "角色列表json数组" )
    @Size(max = 1800)
    private String roles;


    @Schema(description = "角色列表" )
    private List<String> roleList;



    @Schema(description = "微信 OpendId" )
    @Size(max = 128)
    private String wxOpenId;


    @Schema(description = "阿里 OpendId" )
    @Size(max = 128)
    private String aliOpenId;


    @Schema(description = "创建者" )
    @Size(max = 128)
    private String creator;


    @Schema(description = "创建时间" , required = true)
    @NotNull
    private Date createTime;


    @Schema(description = "更新时间" )
    private Date lastUpdateTime;


    @Schema(description = "排序代码" )
    private Integer orderCode;


    @Schema(description = "是否允许" , required = true)
    @NotNull
    private Boolean enable;


    @Schema(description = "是否可编辑" , required = true)
    @NotNull
    private Boolean editable;


    @Schema(description = "备注" )
    @Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
