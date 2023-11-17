package com.levin.oak.base.services.user.req;

import static com.levin.oak.base.entities.EntityConst.*;

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
import com.levin.commons.service.support.*;

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.user.info.*;
import com.levin.oak.base.entities.User;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_User.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.oak.base.entities.User.*;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import java.util.Date;
import com.levin.oak.base.entities.Org;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 查询用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 下午5:31:51, 代码生成哈希校验码：[5492e3efc6d76ff854e01c026f00e9f8]，请不要修改和删除此行内容。
 */
@Schema(title = QUERY_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS, resultClass = UserInfo.class)
public class QueryUserReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445263479L;

    @Ignore
    @Schema(title = "排序字段")
    String orderBy;

    // @Ignore
    @Schema(title = "排序方向")
    @SimpleOrderBy(
            expr = "orderBy + ' ' + orderDir",
            condition = "#isNotEmpty(orderBy) && #isNotEmpty(orderDir)",
            remark = "生成排序表达式")
    @OrderBy(
            value = createTime,
            condition = "#isEmpty(orderBy) || #isEmpty(orderDir)",
            order = Integer.MAX_VALUE,
            desc = "默认按时间排序")
    OrderBy.Type orderDir;

    @Schema(title = L_id)
    String id;

    @Schema(title = L_telephone, description = D_telephone)
    String telephone;

    @Schema(title = "模糊匹配-" + L_telephone, description = D_telephone)
    @Contains
    String containsTelephone;

    @Schema(title = L_email, description = D_email)
    String email;

    @JsonIgnore(value = true)
    @Schema(title = L_password)
    String password;

    @Schema(title = L_nickname)
    String nickname;

    @Schema(title = "模糊匹配-" + L_nickname)
    @Contains
    String containsNickname;

    @Schema(title = L_avatar)
    String avatar;

    @Schema(title = L_sex)
    Sex sex;

    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class, isRequired = "false")
    @Schema(title = L_tagList)
    List<String> tagList;

    @Schema(title = L_category)
    Category category;

    @Schema(title = L_expiredDate, description = L_expiredDate + "大于等于字段值")
    @Gte
    Date gteExpiredDate;

    @Schema(title = L_expiredDate, description = L_expiredDate + "小于等于字段值")
    @Lte
    Date lteExpiredDate;

    @Schema(title = L_expiredDate + "-日期范围")
    @Between
    String betweenExpiredDate;

    @Schema(title = L_state)
    State state;

    @Schema(title = L_staffNo)
    String staffNo;

    @Schema(title = "模糊匹配-" + L_staffNo)
    @Contains
    String containsStaffNo;

    @Schema(title = L_jobPostCode)
    String jobPostCode;

    @OR(autoClose = true)
    @Contains
    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class, isRequired = "false")
    @Schema(title = L_roleList)
    List<String> roleList;

    @Schema(title = "是否加载" + L_org)
    @Fetch(attrs = E_User.org, condition = "#_val == true")
    Boolean loadOrg;

    @Schema(title = L_wxOpenId)
    String wxOpenId;

    @Schema(title = L_aliOpenId)
    String aliOpenId;

    @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain, description = D_domain)
    String domain;

    @Schema(title = L_name)
    String name;

    @JsonIgnore(value = true)
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Schema(title = L_creator)
    String creator;

    @Schema(title = L_createTime, description = L_createTime + "大于等于字段值")
    @Gte
    Date gteCreateTime;

    @Schema(title = L_createTime, description = L_createTime + "小于等于字段值")
    @Lte
    Date lteCreateTime;

    @Schema(title = L_createTime + "-日期范围")
    @Between
    String betweenCreateTime;

    @Schema(title = L_lastUpdateTime, description = L_lastUpdateTime + "大于等于字段值")
    @Gte
    Date gteLastUpdateTime;

    @Schema(title = L_lastUpdateTime, description = L_lastUpdateTime + "小于等于字段值")
    @Lte
    Date lteLastUpdateTime;

    @Schema(title = L_lastUpdateTime + "-日期范围")
    @Between
    String betweenLastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Schema(title = L_remark)
    String remark;

    public QueryUserReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        // @todo 查询之前初始化数据
    }
}
