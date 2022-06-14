package com.levin.oak.base.services.appclientfile.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.oak.base.entities.AppClientFile;
import com.levin.oak.base.entities.E_AppClientFile;
import com.levin.oak.base.services.appclientfile.info.AppClientFileInfo;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 查询客户端文件
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-20 10:49:23
 */
@Schema(description = "查询客户端文件")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppClientFile.class, alias = E_AppClientFile.ALIAS, resultClass = AppClientFileInfo.class)
public class QueryAppClientFileReq extends MultiTenantReq {

    private static final long serialVersionUID = -1155395350L;

    @Schema(description = "是否包含公共数据", hidden = true)
    @Ignore
    boolean isContainsPublicData = true;

    @Ignore
    @Schema(description = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    OrderBy.Type orderDir;


    //@NotNull

    @Schema(description = "id")
    String id;


    //@Size(max = 64)

    @Schema(description = "客户端类型")
    String clientType;


    //@Size(max = 64)

    @Schema(description = "文件类型")
    String mimeType;


    //@NotBlank

    @Schema(description = "文件路径")
    String path;


    @Schema(description = "文件内容")
    String content;


    //@Size(max = 128)

    @Schema(description = "系统子域")
    String domain;


    //@NotBlank
    //@Size(max = 128)

    @Schema(description = "名称")
    String name;

    @Schema(description = "模糊匹配 - 名称")
    @Contains
    String containsName;


    //@Size(max = 128)

    @Schema(description = "拼音，格式：全拼(简拼)")
    String pinyinName;

    @Schema(description = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    String containsPinyinName;


    //@InjectVar()
    //@Size(max = 128)

    @Schema(description = "创建者")
    String creator;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteLastUpdateTime;


    @Schema(description = "排序代码")
    Integer orderCode;


    //@NotNull

    @Schema(description = "是否允许")
    Boolean enable;


    //@NotNull

    @Schema(description = "是否可编辑")
    Boolean editable;


    //@Size(max = 512)

    @Schema(description = "备注")
    String remark;


    public QueryAppClientFileReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
