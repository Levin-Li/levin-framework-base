package com.levin.oak.base.services.appclientfile.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
/////////////////////////////////////////////////////
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppClientFile.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 客户端文件
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午10:28:58, 代码生成哈希校验码：[4a3da6c04cabdb4176420dbcea260887]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
@Select
public class SimpleAppClientFileInfo implements Serializable {

    private static final long serialVersionUID = -1155395350L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 64)
    @Schema(title = L_clientType)
    String clientType;

    @Size(max = 128)
    @Schema(title = L_mimeType)
    String mimeType;

    @NotBlank
    @Schema(title = L_path, description = D_path)
    String path;

    @Schema(title = L_content, description = D_content)
    byte[] content;

    @InjectVar(value = "sysDomain", isRequired = "false", expectBaseType = String.class)
    @Size(max = 128)
    @Schema(title = L_domain, description = D_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @JsonIgnore(value = true)
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    @InjectVar(value = InjectConsts.ORG_ID, expectBaseType = String.class)
    @Size(max = 128)
    @Schema(title = L_orgId)
    String orgId;

    @InjectVar(value = InjectConsts.TENANT_ID, expectBaseType = String.class)
    @Size(max = 128)
    @Schema(title = L_tenantId)
    String tenantId;
}
