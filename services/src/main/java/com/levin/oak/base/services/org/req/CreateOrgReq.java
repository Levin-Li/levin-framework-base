package com.levin.oak.base.services.org.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import com.levin.oak.base.entities.Org.*;
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////


/**
 *  新增机构
 *  //Auto gen by simple-dao-codegen 2021-11-15 15:01:48
 */
@Schema(description = "新增机构")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)
public class CreateOrgReq implements ServiceReq {

    private static final long serialVersionUID = -1399842458L;



    @Schema(description = "租户ID" )
    @InjectVar
    private Long tenantId;


    @Schema(description = "编码" )
    private String code;


    @Schema(description = "图标" )
    private String icon;


    @Schema(description = "状态" , required = true)
    @NotNull
    private State state;


    @Schema(description = "类型" , required = true)
    @NotNull
    private Type type;


    @Schema(description = "所属行业" )
    private String industries;


    @Schema(description = "区域编码" , required = true)
    @NotNull
    private String areaCode;



    @Schema(description = "机构级别" )
    private String level;


    @Schema(description = "机构类别" , required = true)
    @NotNull
    private String category;


    @Schema(description = "是否外部机构" , required = true)
    @NotNull
    private Boolean isExternal;


    @Schema(description = "联系人" )
    private String contacts;


    @Schema(description = "联系电话" )
    private String phones;


    @Schema(description = "联系邮箱" )
    private String emails;


    @Schema(description = "联系地址" )
    private String address;


    @Schema(description = "邮政编码" )
    private String zipCode;


    @Schema(description = "父ID" )
    private Long parentId;




    @Schema(description = "id路径， 使用|包围，如|1|3|15|" )
    @Size(max = 1800)
    private String idPath;


    @Schema(description = "名称" , required = true)
    @NotNull
    @Size(max = 512)
    private String name;


    @Schema(description = "创建者" )
    @Size(max = 512)
    private String creator;


    @Schema(description = "创建时间" , required = true)
    @NotNull
    private Date createTime;


    @Schema(description = "更新时间" )
    private Date lastUpdateTime;


    @Schema(description = "排序代码" )
    private Integer orderCode;


    @Schema(description = "是否允许" , required = true)
    @NotNull
    private Boolean enable;


    @Schema(description = "是否可编辑" , required = true)
    @NotNull
    private Boolean editable;


    @Schema(description = "备注" )
    @Size(max = 1800)
    private String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
