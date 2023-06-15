package com.levin.oak.base.services.notice.req;

import io.swagger.v3.oas.annotations.media.Schema;
import com.levin.commons.dao.annotation.Ignore;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;

import java.util.*;

import com.levin.oak.base.services.notice.info.*;
import com.levin.oak.base.entities.Notice;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.Notice.*;

import java.util.Date;
import java.util.List;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
////////////////////////////////////

/**
 * 查询通知
 *
 * @Author Auto gen by simple-dao-codegen 2022-6-20 16:50:11
 */
@Schema(title = "查询通知")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Notice.class, alias = E_Notice.ALIAS, resultClass = NoticeInfo.class)
public class QueryNoticeReq extends MultiTenantReq {

    private static final long serialVersionUID = 1394869526L;

    @Schema(title = "是否包含公共数据", hidden = true)
    @Ignore
    private boolean isContainsPublicData = true;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    //@Ignore
    @Schema(title = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    OrderBy.Type orderDir;

    //@NotBlank
    //@Size(max = 64)
    @Schema(title = "id")
    String id;

    //@InjectVar()
    //@Size(max = 128)
    @Schema(title = "所有者ID", description = "所有者ID")
    String ownerId;

    //@Size(max = 64)
    @Schema(title = "通知类别")
    String category;

    @Schema(title = "通知内容类型")
    ContentType contentType;

    @Schema(title = "通知内容")
    String content;

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteExpiredDate;

    @Schema(title = "小于等于过期时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteExpiredDate;

    @Schema(title = "过期时间-日期范围，格式：yyyyMMdd-yyyyMMdd，大于等于且小余等于")
    @Between(paramDelimiter = "-", patterns = {"yyyyMMdd"})
    String betweenExpiredDate;

    //@Size(max = 128)
    @Schema(title = "系统域")
    String domain;

    //@NotBlank
    //@Size(max = 128)
    @Schema(title = "名称")
    String name;
    @Schema(title = "模糊匹配 - 名称")
    @Contains
    String containsName;

    //@Size(max = 128)
    //@InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "拼音名称", description = "拼音，格式Json数组：[全拼,简拼]")
    List<String> pinyinName;

    //@InjectVar()
    //@Size(max = 128)
    @Schema(title = "创建者")
    String creator;

    //@NotNull
    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteCreateTime;

    @Schema(title = "创建时间-日期范围，格式：yyyyMMdd-yyyyMMdd，大于等于且小余等于")
    @Between(paramDelimiter = "-", patterns = {"yyyyMMdd"})
    String betweenCreateTime;

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteLastUpdateTime;

    @Schema(title = "更新时间-日期范围，格式：yyyyMMdd-yyyyMMdd，大于等于且小余等于")
    @Between(paramDelimiter = "-", patterns = {"yyyyMMdd"})
    String betweenLastUpdateTime;

    @Schema(title = "排序代码")
    Integer orderCode;

    //@NotNull
    @Schema(title = "是否允许")
    Boolean enable;

    //@NotNull
    @Schema(title = "是否可编辑")
    Boolean editable;

    //@Size(max = 512)
    @Schema(title = "备注")
    String remark;

    public QueryNoticeReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }
}
