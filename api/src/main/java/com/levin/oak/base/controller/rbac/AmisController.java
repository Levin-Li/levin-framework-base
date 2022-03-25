package com.levin.oak.base.controller.rbac;

import com.levin.commons.rbac.MenuItem;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.rbac.dto.AmisMenu;
import com.levin.oak.base.controller.rbac.dto.AmisResp;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.rbac.AuthService;
import com.levin.oak.base.services.rbac.RbacService;
import com.levin.oak.base.services.role.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.API_PATH;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "AmisController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "AmisController", matchIfMissing = true)
@RequestMapping(API_PATH + "rbac")
@Tag(name = "权限认证", description = "权限管理")
@Slf4j
@Valid
public class AmisController extends BaseController {

    @Autowired
    RoleService roleService;

    @Autowired
    RbacService rbacService;

    @Autowired
    AuthService authService;

    /**
     * 获取菜单列表
     *
     * @return ApiResp
     */
    @GetMapping("amisAppMenuList")
    @Operation(tags = {"权限认证"}, summary = "获取Amis菜单列表")
    public AmisResp getAmisAppMenuList(boolean isShowNotPermissionMenu) {

        AmisResp resp = AmisResp.builder().build();

        resp.getData().put(AmisMenu.DATA_KEY, Collections.emptyList());

        if (!authService.isLogin()) {
            return resp.setStatus(9).setMsg("未登录");
        }

        List<MenuResInfo> authorizedMenuList = rbacService.getAuthorizedMenuList(isShowNotPermissionMenu, authService.getLoginUserId());

        if (authorizedMenuList != null) {
            resp.getData().put(AmisMenu.DATA_KEY,
                    authorizedMenuList.parallelStream().map(this::convert).collect(Collectors.toList())
            );
        }

        return resp;
    }


    /**
     * 递归转换菜单
     *
     * @param item
     * @return
     */
    AmisMenu convert(MenuResInfo item) {

        AmisMenu amisMenu = new AmisMenu().setLabel(item.getName())
                .setIcon(item.getIcon())
                //固定加上index
                .setUrl((nullSafe(item.getPath(), item.getDomain()) + "/index").replace("//", "/"));

        if (MenuItem.ActionType.Redirect.equals(item.getActionType())) {
            amisMenu.setRedirect(item.getParams());
        } else if (MenuItem.ActionType.NewWindow.equals(item.getActionType())) {
            amisMenu.setLink(item.getParams());
        } else {
            //固定参数
            amisMenu.setSchemaApi(amisMenu.getUrl().replace("/api/", "/amis-ui/"));
        }

        //转换子菜单
        if (item.getChildren() != null
                && !item.getChildren().isEmpty()) {

            amisMenu.setChildren(new ArrayList<>(item.getChildren().size()));

            for (MenuResInfo child : item.getChildren()) {
                //递归转换
                amisMenu.getChildren().add(convert(child));
            }
        }

        return amisMenu;
    }

    String nullSafe(String value, String defaultValue) {
        return StringUtils.hasText(value) ? value : defaultValue;
    }

}
