package com.levin.oak.base.services.accesslog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.In;
import com.levin.commons.dao.annotation.Lte;
import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.E_AccessLog;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 删除访问日志
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "删除访问日志")
@Data

//@AllArgsConstructor

@NoArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
public class DeleteAccessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1030736962L;

    @Schema(title = "id集合")
    @In(value = E_AccessLog.id)
    private Long[] idList;


    @Schema(title = "请求的域名")
    private String domain;

    @Schema(title = "访问者")
    private String visitor;

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;

    @Schema(title = "标题")
    private String title;

    public DeleteAccessLogReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteAccessLogReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
