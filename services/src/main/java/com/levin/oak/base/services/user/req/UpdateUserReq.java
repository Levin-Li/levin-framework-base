package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
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

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新用户
 * Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "更新用户")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
//默认更新注解
@Update
public class UpdateUserReq extends MultiTenantReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(title = "id", required = true)
    @NotNull
    @Eq(require = true)
    private String id;

    @Schema(title = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 256)
    @Schema(title = "登录密码")
    private String password;

    @Size(max = 20)
    @Schema(title = "手机号")
    private String telephone;

    @Size(max = 32)
    @Schema(title = "邮箱")
    private String email;

    @Size(max = 64)
    @Schema(title = "名称")
    private String name;

    @Size(max = 32)
    @Schema(title = "昵称")
    private String nickname;

    @Schema(title = "头像")
    private String avatar;

    @Schema(title = "性别")
    private Sex sex;

    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class)
    @Schema(title = "标签列表")
    private List<String> tagList;

    @Schema(title = "帐号类型")
    private Category category;

    @Schema(title = "过期时间")
    private Date expiredDate;

    @Schema(title = "帐号状态")
    private State state;

    @Size(max = 32)
    @Schema(title = "工号")
    private String staffNo;

    @Size(max = 128)
    @Schema(title = "岗位职级")
    private String jobPostCode;

    @Size(max = 1800)
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class)
    @Schema(title = "角色列表")
    private List<String> roleList;

    @Size(max = 128)
    @Schema(title = "微信 OpendId")
    private String wxOpenId;

    @Size(max = 128)
    @Schema(title = "阿里 OpendId")
    private String aliOpenId;

    @Schema(title = "更新时间")
    private Date lastUpdateTime;

    @Schema(title = "排序代码")
    private Integer orderCode;

    @Schema(title = "是否允许")
    private Boolean enable;

    @Schema(title = "是否可编辑")
    private Boolean editable;

    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;


    public UpdateUserReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }

}
