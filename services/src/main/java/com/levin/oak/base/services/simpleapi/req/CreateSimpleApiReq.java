package com.levin.oak.base.services.simpleapi.req;

// import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_SimpleApi.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.oak.base.entities.SimpleApi.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 新增简单动态接口
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午9:44:33, 代码生成哈希校验码：[dc3455f02773b91fd4ed4831dffa2998]，请不要修改和删除此行内容。
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
// @EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS)
public class CreateSimpleApiReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1021385738L;

    @Schema(title = L_methods, description = D_methods)
    @Size(max = 16)
    String methods;

    @Schema(title = L_language)
    @NotNull
    Language language;

    @Schema(title = L_type)
    @NotBlank
    @Size(max = 128)
    String type;

    @Schema(title = L_category)
    @NotBlank
    @Size(max = 128)
    String category;

    @Schema(title = L_groupName)
    @NotBlank
    @Size(max = 128)
    String groupName;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_path)
    @NotBlank
    @Size(max = 800)
    String path;

    @Schema(title = L_requireAuthorizations)
    @Size(max = 1800)
    @InjectVar(
            domain = "dao",
            isRequired = "false",
            converter = PrimitiveArrayJsonConverter.class,
            expectBaseType = String.class)
    List<String> requireAuthorizations;

    @Schema(title = L_content)
    String content;

    @Schema(title = L_domain, description = D_domain)
    @InjectVar(value = "sysDomain", isRequired = "false", expectBaseType = String.class)
    @Size(max = 128)
    String domain;

    @Schema(title = L_name)
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_optimisticLock)
    @JsonIgnore(value = true)
    Integer optimisticLock;

    @Schema(title = L_creator, hidden = true)
    // @Size(max = 128)
    // @InjectVar(value = InjectConsts.USER_ID, isRequired = "false", expectBaseType = String.class)
    String creator;

    @Schema(title = L_createTime, hidden = true)
    // @NotNull
    Date createTime;

    @Schema(title = L_lastUpdateTime, hidden = true)
    Date lastUpdateTime;

    @Schema(title = L_orderCode, hidden = true)
    Integer orderCode;

    @Schema(title = L_enable, hidden = true)
    // @NotNull
    Boolean enable;

    @Schema(title = L_editable, hidden = true)
    // @NotNull
    Boolean editable;

    @Schema(title = L_remark, hidden = true)
    // @Size(max = 512)
    String remark;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等

        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }
    }
}
