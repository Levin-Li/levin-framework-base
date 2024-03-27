package com.levin.oak.base.controller;

import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.ui.annotation.CRUD;
import com.levin.oak.base.biz.bo.dict.StatDictReq;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.controller.base.dict.DictController;
import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.E_Dict;
import com.levin.oak.base.services.dict.req.CreateDictReq;
import com.levin.oak.base.services.dict.req.UpdateDictReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;


// POST: 创建一个新的资源，如用户资源，部门资源; PATCH: 修改资源的某个属性; PUT: 更新资源当中包含的全部属性; DELETE: 删除某项资源; GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。
// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。 所以一般插入新数据的时候使用post方法，更新数据库时用put方法

// Spring mvc 参数验证说明
// @Valid 只能用在controller, @Validated 可以用在其他被spring管理的类上。注意只有 @Valid 才支持对象嵌套验证，示例如下：
// @Valid
// @NotNull(groups = AdvanceInfo.class)
// private UserAddress userAddress;

/**
 * 字典业务控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月20日 下午5:52:52, 代码生成哈希校验码：[07b374794b621fe454f5c542a222b336]，请不要修改和删除此行内容。
 */

//生成的控制器
@RestController(PLUGIN_PREFIX + "BizDictController")
@RequestMapping(API_PATH + "Dict") //dict

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizDictController", havingValue = "true", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME + "-")

//类注解，@Tag的name属性关联权限的资源标识
@Tag(name = E_Dict.BIZ_NAME, description = E_Dict.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD

@Slf4j
public class BizDictController extends DictController {

    List<String> allowOpList = Arrays.asList(QUERY_LIST_ACTION, CREATE_ACTION, UPDATE_ACTION, DELETE_ACTION, VIEW_DETAIL_ACTION, BATCH_CREATE_ACTION, BATCH_UPDATE_ACTION, BATCH_DELETE_ACTION);

    @Autowired
    AuthService authService;

    /**
     * 检查请求
     *
     * @param action
     * @param req
     * @return
     */
    @Override
    protected <T> T checkRequest(String action, T req) {

        Assert.isTrue(allowOpList.contains(action), "不支持的操作-{}", action);

        return super.checkRequest(action, req);
    }


    /**
     * 检查列表项
     *
     * @param creator
     * @param itemList
     * @return
     */
    private List<Dict.Item> checkItemList(String creator, List<Dict.Item> itemList) {

        if (itemList != null
                && !itemList.isEmpty()) {

            //检查字典项编码的唯一性
            Map<String, String> temp = new HashMap<>(itemList.size());

            //检查字典项
            itemList.forEach(item -> {

                Assert.notBlank(item.getCode(), "字典项编码不能为空");
                Assert.notBlank(item.getName(), "字典项名称不能为空");

                // 检查字典项编码的唯一性
                Assert.isNull(temp.putIfAbsent(item.getCode(), item.getName()), "字典项[{}]的编码重复出现", item.getName());

                if (!StringUtils.hasText(item.getCreator())) {
                    item.setCreator(creator);
                }

                item.prePersist();

            });

        }

        return itemList;
    }

    /**
     * 新增
     *
     * @param req CreateDictEvt
     * @return ApiResp
     */
    @Override
    public ApiResp<String> create(@RequestBody @Valid CreateDictReq req) {

        RbacUserInfo<String> userInfo = authService.getUserInfo();

        req.setType(userInfo.isSuperAdmin() ? Dict.Type.System : Dict.Type.Custom);

        return super.create(req.setItemList(checkItemList(req.getOperatorId(), req.getItemList())));
    }

    /**
     * 更新
     *
     * @param req UpdateDictReq
     * @param id
     */
    @Override
    public ApiResp<Boolean> update(@RequestBody @Valid UpdateDictReq req, @PathVariable(required = false) String id) {

        //超级用户允许改更类型
        if (req.getType() != null) {
            RbacUserInfo<String> userInfo = authService.getUserInfo();
            if (!userInfo.isSuperAdmin()) {
                req.setType(null).removeUpdateField(E_Dict.type);
            }
        }

        return super.update(req.setItemList(checkItemList(req.getOperatorId(), req.getItemList())), id);
    }

    /**
     * 统计
     *
     * @param req QueryDictReq
     * @return ApiResp<StatDictReq.Result>
     */
    @GetMapping("stat") //默认开放
    @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
    public ApiResp<StatDictReq.Result> stat(@Valid StatDictReq req, SimplePaging paging) {

        req = checkRequest(STAT_ACTION, req);

        return ApiResp.ok(checkResponse(STAT_ACTION, bizDictService.stat(req, paging)));
    }

}
