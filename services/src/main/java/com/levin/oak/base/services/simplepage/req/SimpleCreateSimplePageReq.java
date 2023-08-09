package com.levin.oak.base.services.simplepage.req;

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
import static com.levin.oak.base.entities.E_SimplePage.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 新增简单页面
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月10日 上午2:41:24, 代码生成哈希校验码：[13f802d861a9ef2331e2031f2b54b1cb]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = SimplePage.class, alias = E_SimplePage.ALIAS)
public class SimpleCreateSimplePageReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1598619295L;

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
    @InjectVar(
            domain = "dao",
            expectBaseType = String.class,
            converter = PrimitiveArrayJsonConverter.class,
            isRequired = "false")
    @Size(max = 1800)
    List<String> requireAuthorizations;

    @Schema(title = L_content)
    String content;

    @Schema(title = L_domain)
    @Size(max = 128)
    String domain;

    @Schema(title = L_name)
    @NotBlank
    @Size(max = 64)
    String name;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等
    }
}
