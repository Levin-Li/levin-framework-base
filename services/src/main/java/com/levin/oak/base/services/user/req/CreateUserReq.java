package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增用户
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增用户")
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


    @Schema(title = "登录密码")
    @Size(max = 256)
    private String password;

    @Schema(title = "手机号")
    @Size(max = 20)
    private String telephone;

    @Schema(title = "邮箱")
    @Size(max = 32)
    private String email;

    @Schema(title = "名称")
    @Size(max = 64)
    private String name;

    @Schema(title = "昵称")
    @Size(max = 32)
    private String nickname;

    @Schema(title = "头像")
    private String avatar;

    @Schema(title = "性别")
    private Sex sex;

    @Schema(title = "标签列表")
    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> tagList;

    @Schema(title = "帐号类型")
    private Category category;

    @Schema(title = "过期时间")
    private Date expiredDate;

    @Schema(title = "帐号状态", required = true)
    @NotNull
    private State state;

    @Schema(title = "工号")
    @Size(max = 32)
    private String staffNo;

    @Schema(title = "岗位职级")
    @Size(max = 128)
    private String jobPostCode;

    @Schema(title = "角色列表")
    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> roleList;


    @Schema(title = "微信 OpendId")
    @Size(max = 128)
    private String wxOpenId;

    @Schema(title = "阿里 OpendId")
    @Size(max = 128)
    private String aliOpenId;

    @Schema(title = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(title = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(title = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(title = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(title = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(title = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(title = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
