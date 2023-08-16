package com.levin.oak.base.services.appclientfile.req;

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
import static com.levin.oak.base.entities.E_AppClientFile.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 新增客户端文件
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月16日 下午1:12:56, 代码生成哈希校验码：[3ac6f3e8909c247c93e7540722cebb2f]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS)
public class CreateAppClientFileReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -1155395350L;

    @Schema(title = L_clientType)
    @Size(max = 64)
    String clientType;

    @Schema(title = L_mimeType)
    @Size(max = 128)
    String mimeType;

    @Schema(title = L_path, description = D_path)
    @NotBlank
    String path;

    @Schema(title = L_content, description = D_content)
    byte[] content;

    @Schema(title = L_domain)
    @Size(max = 128)
    String domain;

    @Schema(title = L_name)
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    @Schema(title = L_creator, hidden = true)
    // @Size(max = 128)
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
