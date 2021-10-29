package com.levin.oak.base.services.org.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.*;


////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Org.*;
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////


/**
 *  更新机构
 *  Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "更新机构")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
//默认更新注解
@Update
public class UpdateOrgReq implements ServiceReq {

    private static final long serialVersionUID = -1399842458L;

    @Schema(description = "id")
    @NotNull
    @Eq(require = true)
    private Long id;

//    @Schema(description = "租户ID")
//    private Long tenantId;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "状态")
    private State state;

    @Schema(description = "类型")
    private Type type;

    @Schema(description = "所属行业")
    private String industries;

    @Schema(description = "区域编码")
    private String areaCode;

    @Schema(description = "机构级别")
    private String level;

    @Schema(description = "机构类别")
    private String category;

    @Schema(description = "是否外部机构")
    private Boolean isExternal;

    @Schema(description = "联系人")
    private String contacts;

    @Schema(description = "联系电话")
    private String phones;

    @Schema(description = "联系邮箱")
    private String emails;

    @Schema(description = "联系地址")
    private String address;

    @Schema(description = "邮政编码")
    private String zipCode;

    @Schema(description = "父ID")
    private Long parentId;

    @Size(max = 1800)
    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;

    @Size(max = 512)
    @Schema(description = "名称")
    private String name;

    @Size(max = 512)
    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    @Size(max = 1800)
    @Schema(description = "备注")
    private String remark;


    public UpdateOrgReq(Long id) {
        this.id = id;
    }
    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}