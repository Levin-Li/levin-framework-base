package com.levin.oak.base.controller.admin;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.bean.BeanUtil;
import com.levin.commons.dao.Case;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.rbac.MenuItem;
import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizSimplePageService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacResService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.rbac.dto.AmisMenu;
import com.levin.oak.base.controller.rbac.dto.AmisResp;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.simplepage.SimplePageService;
import com.levin.oak.base.services.simplepage.req.QuerySimplePageReq;
import com.levin.oak.base.utils.AmisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.*;


// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Tag(name = "Amis支持", description = "Amis服务")
@RestController(PLUGIN_PREFIX + "AmisController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "AmisController", matchIfMissing = true)
@RequestMapping(API_PATH + "amis")
@Slf4j
@Valid
@MenuResTag(false)
@ResAuthorize(domain = ID, type = EntityConst.COMMON_TYPE_NAME +"-amis" , onlyRequireAuthenticated = true)
public class AmisController extends BaseController {

    @Autowired //@DubboReference // @Autowired //@DubboReference
    RoleService roleService;

    @Autowired
    RbacService rbacService;

    @Autowired
    RbacResService rbacResService;

    @Autowired
    AuthService authService;

    @Autowired //@DubboReference // @Autowired
    MenuResService menuResService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired //@DubboReference // @Autowired
    SimplePageService simplePageService;

    @Autowired //@DubboReference // @Autowired
    BizSimplePageService bizSimplePageService;

    final LRUCache<String, Page> lruCache = CacheUtil.newLRUCache(10 * 1000, 5 * 60 * 1000);

    @Data
    @Accessors(chain = true)
    static class Page {

        Boolean enable;

        String path;

        String content;

        @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
        List<String> requireAuthorizations;
    }


    @PostConstruct
    public void init() {
        log.info("默认Amis服务支持启用...");
    }

    /**
     * 获取菜单列表
     *
     * @return ApiResp
     */
    @RequestMapping(value = "appMenuList", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(summary = "获取Amis菜单列表")
    public AmisResp getAmisAppMenuList(boolean isShowNotPermissionMenu) {

        AmisResp resp = AmisResp.builder().build();

        resp.getData().put(AmisMenu.DATA_KEY, Collections.emptyList());

        //获取页面地址
        String basePath = getContextPath() + API_PATH + "amis/page";

        List<MenuResInfo> authorizedMenuList = rbacResService.getAuthorizedMenuList(isShowNotPermissionMenu, authService.getLoginId());

        if (authorizedMenuList != null) {

            final AtomicInteger maxDeep = new AtomicInteger();
            final List<AmisMenu> rootMenuList = new ArrayList<>(3);

            List<AmisMenu> amisMenuList = authorizedMenuList.stream()
                    .map(item -> convert(item, basePath, 1, maxDeep, rootMenuList))
                    .collect(Collectors.toList());

            //如果层级小于3级
            if (frameworkProperties.isAutoAddAmisMenuRootNode()
                    && maxDeep.get() < 3) {

                AmisMenu amisMenu = new AmisMenu()
                        .setLabel("")
                        .setChildren(amisMenuList);

                amisMenuList = new ArrayList<>(2);
                amisMenuList.add(amisMenu);
            }

            if (rootMenuList.isEmpty()) {
                amisMenuList.add(
                        new AmisMenu()
                                .setLabel("首页")
                                .setUrl("/")
                                .setRedirect(getDefaultIndex(amisMenuList))
                                .setDefaultPage(true)
                );

            } else if (rootMenuList.size() > 1) {
                log.warn("菜单中有多于1个的根菜单: "
                        + rootMenuList.stream().map(AmisMenu::getLabel).collect(Collectors.toList()));
            }

            //设置默认图标
            amisMenuList.forEach(item -> setDefaultIcon(item, 1));

            resp.getData().put(AmisMenu.DATA_KEY, amisMenuList);
        }

        return resp;
    }

    private String getDefaultIndex(List<AmisMenu> amisMenuList) {

        //获取第二级菜单
        return amisMenuList.stream()
                .filter(AmisMenu::hasChildren)
                .flatMap(m -> m.getChildren().stream())
                .filter(m -> StringUtils.hasText(m.getUrl()))
                .findFirst()
                .map(AmisMenu::getUrl)
                .orElse(null);
    }

    private void checkAuthorize(Page page) {

        if (page.requireAuthorizations == null
                || page.requireAuthorizations.isEmpty()) {
            return;
        }

        boolean isAuthorized = rbacService.isAuthorized(authService.getLoginId(), true, page.requireAuthorizations, (rp, info) -> {
            throw new AuthorizationException("未授权的ui资源：" + rp + "," + page.getPath());
        });

        if (!isAuthorized) {
            throw new AuthorizationException("未授权的ui资源-" + page.getPath());
        }

    }

    private String getContent(Page page) {

        if (StringUtils.hasText(page.getContent())) {
            //检查权限
            checkAuthorize(page);
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return page.getContent();
    }

    /**
     * 从数据或是本地资源中获取amis页面内容
     * <p>
     * 同时会检查当前用户是否有这个权限
     *
     * @return ApiResp
     */
    @RequestMapping(value = "/{uiType}", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(summary = "获取AmisUI界面(5分钟刷新)")
    public String getUiContent(@PathVariable String uiType, String path, String type, String category, MultiTenantReq shareReq) {

        Assert.hasText(path, "path 必须指定");

        final String key = String.join("|", uiType, type, category, path);

        Page page = lruCache.get(key);

        if (page != null) {
            return getContent(page);
        }

//         page = new Page().setPath(path);

        //页面信息 @todo 按租户过滤
//        SimplePageInfo page = simplePageService.findOne(new QuerySimplePageReq()
//                .setType(type)
//                .setCategory(category)
//                .setContainsPublicData(shareReq.isContainsPublicData())
//                .setPath(path)
//                .setOrderBy(E_SimplePage.tenantId)
//                .setOrderDir(OrderBy.Type.Asc)
//                .setTenantId(shareReq.getTenantId()));

        final Class<? extends SimpleEntity> aClass = "page".equalsIgnoreCase(uiType) ? SimplePage.class : SimpleForm.class;

        page = BeanUtil.copyProperties(bizSimplePageService.findOnePage(
                new QuerySimplePageReq()
                        .setType(type)
                        .setCategory(category)
                        .setPath(path).setTenantId(shareReq.getTenantId())
        ), Page.class);

        //如果是被禁用
        if (page != null && !Boolean.TRUE.equals(page.getEnable())) {
            httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
            return null;
        }

        //如果没有查到记录
        if (page == null) {
            page = new Page().setPath(path);
        }

        if (!StringUtils.hasText(page.getContent())) {
            //读取本地文件
            page.content = AmisUtils.readAdminClassPathResource(path);
        }

        //有无内容都放入缓存，缓存时间5分钟
        lruCache.put(key, page);

        return getContent(page);
    }

    /**
     * 递归转换菜单
     *
     * @param item
     * @param basePath
     * @param deep
     * @param rootMenuList url 为 / 的菜单节点
     * @return
     */
    @SneakyThrows
    AmisMenu convert(MenuResInfo item, String basePath, int deep, AtomicInteger maxDeep, List<AmisMenu> rootMenuList) {

        if (deep < 1) {
            deep = 1;
        }

        if (deep > maxDeep.get()) {
            maxDeep.set(deep);
        }

        AmisMenu amisMenu = new AmisMenu().setLabel(item.getName())
                .setIcon(item.getIcon());

        if (StringUtils.hasText(item.getPath())) {

            amisMenu.setUrl(item.getPath());

            String params = item.getParams();
            if (!StringUtils.hasText(params)) {
                params = item.getPath();
            }

            if (MenuItem.ActionType.Redirect.equals(item.getActionType())) {
                amisMenu.setRedirect(params);
            } else if (MenuItem.ActionType.Rewrite.equals(item.getActionType())) {
                amisMenu.setRewrite(params);
            } else if (MenuItem.ActionType.NewWindow.equals(item.getActionType())) {
                amisMenu.setLink(params);
            } else if (MenuItem.ActionType.Jsonp.equals(item.getActionType())) {
                amisMenu.setSchemaApi("jsonp:" + basePath + "?type=jsonp&path=" + URLEncoder.encode(item.getPath(), "utf-8"));
            } else {
                //固定参数
                amisMenu.setSchemaApi(basePath + "?type=json&path=" + URLEncoder.encode(item.getPath(), "utf-8"));
            }
        }

        //是否是根菜单
        if (StringUtils.hasText(amisMenu.getUrl())
                && "/".equalsIgnoreCase(amisMenu.getUrl().trim())) {
            rootMenuList.add(amisMenu);
        }

        //转换子菜单
        if (item.getChildren() != null
                && !item.getChildren().isEmpty()) {

            amisMenu.setChildren(new ArrayList<>(item.getChildren().size()));

            for (MenuResInfo child : item.getChildren()) {
                //递归转换
                amisMenu.getChildren().add(convert(child, basePath, deep + 1, maxDeep, rootMenuList));
            }
        }

        return amisMenu;
    }


    /**
     * 设置默认图标
     *
     * @param item
     * @param deep
     */
    void setDefaultIcon(AmisMenu item, int deep) {

        //图标库
        //https://fontawesome.com/

        if (deep < 1) {
            deep = 1;
        }

        item.setDeepLevel(deep);

        boolean hasChildren = item.hasChildren();

        if (!StringUtils.hasText(item.getIcon()) && deep >= 2) {
            //如果是叶子节点
            //图标库
            //https://fontawesome.com/
            item.setIcon(hasChildren ? "fa fa-cube" : "fa fa-bars");
        }

        if (hasChildren) {
            for (AmisMenu child : item.getChildren()) {
                //递归转换
                setDefaultIcon(child, deep + 1);
            }
        }
    }

}
