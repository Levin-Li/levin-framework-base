package com.levin.oak.base.services.notice.info;

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
import static com.levin.oak.base.entities.E_Notice.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;

////////////////////////////////////

/**
 * 通知
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:43, 代码生成哈希校验码：[c3102dc4a0a9685f79c17991ce9a68a5]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({tenantId})
@Select
public class SimpleNoticeInfo implements Serializable {

    private static final long serialVersionUID = 1394869526L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 128)
    @Schema(title = L_ownerId)
    String ownerId;

    @Size(max = 64)
    @Schema(title = L_category)
    String category;

    @Schema(title = L_contentType)
    ContentType contentType;

    @Schema(title = L_content)
    String content;

    @Schema(title = L_expiredDate)
    Date expiredDate;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_orgId)
    String orgId;

    @Size(max = 128)
    @Schema(title = L_tenantId)
    String tenantId;
}