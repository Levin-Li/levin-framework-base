package com.levin.oak.base.services.appclientfile.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.AppClientFile;
import com.levin.oak.base.entities.E_AppClientFile;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新客户端文件
 * Auto gen by simple-dao-codegen 2022-4-20 10:49:23
 */
@Schema(title = "更新客户端文件")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS)
//默认更新注解
@Update
public class UpdateAppClientFileReq extends MultiTenantReq {

    private static final long serialVersionUID = -1155395350L;

    @Schema(title = "id", required = true)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(title = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Size(max = 64)
    @Schema(title = "客户端类型")
    String clientType;

    @Size(max = 64)
    @Schema(title = "文件类型")
    String mimeType;

    @NotBlank
    @Schema(title = "文件路径")
    String path;

    @Schema(title = "文件内容")
    String content;

    @Size(max = 128)
    @Schema(title = "系统子域")
    String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称")
    String name;

    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    String pinyinName;

    @Schema(title = "更新时间")
    Date lastUpdateTime;

    @Schema(title = "排序代码")
    Integer orderCode;

    @Schema(title = "是否允许")
    Boolean enable;

    @Schema(title = "是否可编辑")
    Boolean editable;

    @Size(max = 512)
    @Schema(title = "备注")
    String remark;


    public UpdateAppClientFileReq(String id) {
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
