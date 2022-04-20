package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
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


    @Schema(description = "登录密码")
    @Size(max = 256)
    private String password;

    @Schema(description = "手机号")
    @Size(max = 20)
    private String telephone;

    @Schema(description = "邮箱")
    @Size(max = 32)
    private String email;

    @Schema(description = "名称")
    @Size(max = 64)
    private String name;

    @Schema(description = "昵称")
    @Size(max = 32)
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private Sex sex;

    @Schema(description = "标签列表")
    @Size(max = 1800)
    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> tagList;

    @Schema(description = "帐号类型")
    private Category category;

    @Schema(description = "过期时间")
    private Date expiredDate;

    @Schema(description = "帐号状态", required = true)
    @NotNull
    private State state;

    @Schema(description = "工号")
    @Size(max = 32)
    private String staffNo;

    @Schema(description = "岗位职级")
    @Size(max = 128)
    private String jobPostCode;

    @Schema(description = "角色列表")
    @Size(max = 1800)
    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> roleList;


    @Schema(description = "微信 OpendId")
    @Size(max = 128)
    private String wxOpenId;

    @Schema(description = "阿里 OpendId")
    @Size(max = 128)
    private String aliOpenId;

    @Schema(description = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(description = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(description = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(description = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(description = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(description = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(description = "备注")
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
