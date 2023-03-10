package com.levin.oak.base.services.appclientfile.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.AppClientFile;
import com.levin.oak.base.entities.E_AppClientFile;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增客户端文件
 * //Auto gen by simple-dao-codegen 2022-4-20 10:49:23
 */
@Schema(description = "新增客户端文件")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS)
public class CreateAppClientFileReq extends MultiTenantReq {

    private static final long serialVersionUID = -1155395350L;


    @Schema(description = "客户端类型")
    @Size(max = 64)
    String clientType;

    @Schema(description = "文件类型")
    @Size(max = 64)
    String mimeType;

    @Schema(description = "文件路径", required = true)
    @NotBlank
    String path;

    @Schema(description = "文件内容")
    String content;

    @Schema(description = "系统子域")
    @Size(max = 128)
    String domain;

    @Schema(description = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(description = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    String pinyinName;

    @Schema(description = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    String creator;

    @Schema(description = "创建时间", hidden = true)
    //@NotNull
    Date createTime;

    @Schema(description = "更新时间", hidden = true)
    Date lastUpdateTime;

    @Schema(description = "排序代码", hidden = true)
    Integer orderCode;

    @Schema(description = "是否允许", hidden = true)
    //@NotNull
    Boolean enable;

    @Schema(description = "是否可编辑", hidden = true)
    //@NotNull
    Boolean editable;

    @Schema(description = "备注", hidden = true)
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
