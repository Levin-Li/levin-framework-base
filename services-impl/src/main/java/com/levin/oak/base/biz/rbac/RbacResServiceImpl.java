package com.levin.oak.base.biz.rbac;

import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.plugin.Res;
import com.levin.commons.plugin.ResLoader;
import com.levin.commons.rbac.RbacUserObject;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.rbac.info.ActionInfo;
import com.levin.oak.base.biz.rbac.info.ModuleInfo;
import com.levin.oak.base.biz.rbac.info.ResInfo;
import com.levin.oak.base.biz.rbac.info.ResTypeInfo;
import com.levin.oak.base.entities.E_MenuRes;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.menures.req.QueryMenuResReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "RbacResService")
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "RbacResService", havingValue = "true", matchIfMissing = true)
@ResAuthorize(ignored = true)
public class RbacResServiceImpl implements RbacResService<Serializable> {

    @Autowired
    ApplicationContext context;

    @Autowired
    PluginManager pluginManager;

    @Autowired
    MenuResService menuResService;

    @Autowired
    BizRoleService bizRoleService;

    @Autowired
    RbacService<Serializable> rbacService;

    @Autowired
    RbacLoadService<Serializable> rbacLoadService;

    /**
     * 获取授权的菜单列表
     *
     * @param principal
     * @return
     */
    @Override
    public List<MenuResInfo> getAuthorizedMenuList(boolean isShowNotPermissionMenu, Serializable principal) {

        Assert.notNull(principal, "无效的用户标识");

        RbacUserObject<String> userInfo = rbacLoadService.loadUser(principal);

        Assert.notNull(userInfo, "无效的用户标识");

        //1、找出所有的菜单 包括公共菜单和租户自有菜单
//        List<MenuResInfo> menuRes = simpleDao.selectFrom(MenuRes.class)
//                .eq(E_MenuRes.enable, true)
//                //公共菜单和租户自有菜单
//                .isNullOrEq(E_MenuRes.tenantId, userInfo.getTenantId())
//                .orderBy(OrderBy.Type.Asc, E_MenuRes.orderCode)
//                .find(MenuResInfo.class);

        List<MenuResInfo> menuRes = menuResService.query(
                new QueryMenuResReq()
                        .setEnable(true)
                        .setContainsPublicData(true)
                        .setOrderBy(E_MenuRes.orderCode)
                        .setOrderDir(OrderBy.Type.Asc)
                        .setTenantId(userInfo.getTenantId()),
                new SimplePaging().setPageIndex(0).setPageSize(2000)
        ).getItems();

        final Map<String, MenuResInfo> cacheMap = new LinkedHashMap<>();

        //2、放入Map
        menuRes.forEach(m -> cacheMap.put(m.getId(), m));

        final Map<String, MenuResInfo> cacheMap2 = new LinkedHashMap<>(cacheMap);

        //3、构建菜单层级
        for (Map.Entry<String, MenuResInfo> entry : cacheMap.entrySet()) {

            MenuResInfo menu = entry.getValue();

            Serializable parentId = menu.getParentId();

            if (parentId == null) {
                continue;
            }

            menu.setParent(null);

            MenuResInfo parent = cacheMap2.get(parentId);

            if (parent.getChildren() == null) {
                parent.setChildren(new LinkedHashSet<>());
            }

            //设置关系
            parent.getChildren().add(menu);
        }

        ///////////////////////////////////////////////////////////////////////////
        //过滤出有权限的菜单，或是设置为enable = false

        List<String> roleList = rbacLoadService.getRoleList(principal);
        List<String> permissionList = rbacLoadService.getPermissionList(principal);

        for (Map.Entry<String, MenuResInfo> entry : cacheMap2.entrySet()) {
            //
            MenuResInfo info = entry.getValue();
            //获取菜单要求的权限
            List<String> requirePermissions = !StringUtils.hasText(info.getRequireAuthorizations()) ? Collections.emptyList()
                    : JsonStrArrayUtils.parse(info.getRequireAuthorizations(), StringUtils::hasText, (txt) -> txt);

            if (requirePermissions.isEmpty()
                    || rbacService.isAuthorized(roleList, permissionList, true, requirePermissions, null)) {
                //如果没有要求权限，或是权限都满足，要求显示菜单
                info.setEnable(true);
            } else if (isShowNotPermissionMenu && Boolean.TRUE.equals(info.getAlwaysShow())) {
                //菜单显示，但被禁用
                info.setEnable(false);
            } else {
                //如果没有权限
                MenuResInfo menuResInfo = cacheMap.remove(entry.getKey());
            }
        }

        //清除临时
        cacheMap2.clear();

        return cacheMap.values().stream()
                .filter(Objects::nonNull)
                .filter(m -> m.getParentId() == null)
                .filter(m -> m.getChildren() != null && !m.getChildren().isEmpty())
                .map(m -> clearTree(cacheMap, m))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 清除没有权限的菜单
     *
     * @param cacheMap
     * @param menuResInfo
     * @return
     */
    private MenuResInfo clearTree(Map<String, MenuResInfo> cacheMap, MenuResInfo menuResInfo) {

        if (menuResInfo == null || menuResInfo.getChildren() == null) {
            return menuResInfo;
        }

        for (MenuResInfo child : new ArrayList<>(menuResInfo.getChildren())) {

            if (!cacheMap.containsKey(child.getId())) {
                menuResInfo.getChildren().remove(child);
            }

            clearTree(cacheMap, child);
        }

        return menuResInfo;
    }

    /**
     * 获取资源授权清单
     *
     * @param principal 如果 principal 为null，则表示获取全部的
     * @return
     */
    @Override
    public List<ModuleInfo> getAuthorizedResList(Serializable principal) {

        //非Null并且是非空字符串
        boolean hasUser = principal != null && (!(principal instanceof CharSequence) || StringUtils.hasText(principal.toString()));

        List<String> roleList = hasUser ? rbacLoadService.getRoleList(principal) : Collections.emptyList();
        List<String> permissionList = hasUser ? rbacLoadService.getPermissionList(principal) : Collections.emptyList();

        //返回结果
        List<ModuleInfo> result = new LinkedList<>();

        //第一层循环 插件
        for (Plugin plugin : pluginManager.getInstalledPlugins()) {

            ModuleInfo moduleInfo = new ModuleInfo();

            BeanUtils.copyProperties(plugin, moduleInfo);

            //资源加载器
            ResLoader resLoader = plugin.getResLoader();

            if (resLoader == null) {
                continue;
            }

            //第二层循环 资源类型
            for (Identifiable resType : resLoader.getResTypes()) {

                ResTypeInfo typeInfo = new ResTypeInfo();

                BeanUtils.copyProperties(resType, typeInfo);

                //全部加入
                Collection<Res> resItems = resLoader.getResItems(resType.getId(), 0);

                //第三层循环 资源列表
                for (Res res : resItems) {

                    ResInfo resInfo = new ResInfo();

                    BeanUtils.copyProperties(res, resInfo, ResInfo.Fields.actionList);

                    String prefix = String.join(rbacService.getPermissionDelimiter(), res.getDomain().toString(), res.getType().toString(), res.getId().toString());

                    //第四层循环 资源操作
                    for (Res.Action action : new ArrayList<Res.Action>(res.getActionList())) {

                        ActionInfo actionInfo = new ActionInfo().setPermissionExpr(prefix + rbacService.getPermissionDelimiter() + action.getId());

                        BeanUtils.copyProperties(action, actionInfo);

                        if (hasUser) {
                            //加入拥有的权限
                            if (action.isIgnored()
                                    || action.isOnlyRequireAuthenticated()
                                    || rbacService.isAuthorized(roleList, permissionList, actionInfo.getPermissionExpr(), null)) {
//                                resInfo.getActionList().add(action);
                                resInfo.getActionList().add(actionInfo);
                            }
                        } else {
//                            resInfo.getActionList().add(action);
                            resInfo.getActionList().add(actionInfo);
                        }
                    }

                    //如果没有内容，不加入
                    if (!resInfo.getActionList().isEmpty()) {
                        typeInfo.getResList().add(resInfo);
                    }
                }

                //如果没有内容，不加入
                if (!typeInfo.getResList().isEmpty()) {
                    moduleInfo.getTypeList().add(typeInfo);
                }
            }

            //如果没有内容，不加入
            if (!moduleInfo.getTypeList().isEmpty()) {
                result.add(moduleInfo);
            }
        }

        return result;
    }
}
