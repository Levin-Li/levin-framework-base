package com.levin.oak.base.services.area.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.E_Area;
import com.levin.oak.base.services.commons.req.BaseReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 删除区域
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "删除区域")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS)
public class DeleteAreaReq extends BaseReq {

    private static final long serialVersionUID = -445860277L;


    @Schema(title = "编码集合")
    @In(value = E_Area.code, require = true)
    @NotEmpty
    private String[] codeList;

    public DeleteAreaReq(String... codeList) {
        this.codeList = codeList;
    }

    public DeleteAreaReq setCodeList(String... codeList) {
        this.codeList = codeList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
