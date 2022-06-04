package com.levin.oak.base.controller.admin;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.io.NioUtil;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.rbac.AuthorizationException;
import com.levin.commons.rbac.MenuItem;
import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacResService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.rbac.dto.AmisMenu;
import com.levin.oak.base.controller.rbac.dto.AmisResp;
import com.levin.oak.base.entities.E_SimplePage;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.services.commons.req.TenantShareReq;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.simplepage.SimplePageService;
import com.levin.oak.base.services.simplepage.info.SimplePageInfo;
import com.levin.oak.base.services.simplepage.req.QuerySimplePageReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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
@ResAuthorize(domain = ID, type = EntityConst.COMMON_TYPE_NAME, onlyRequireAuthenticated = true)
public class AmisController extends BaseController {

    @Resource
    RoleService roleService;

    @Resource
    RbacService rbacService;

    @Resource
    RbacResService rbacResService;

    @Resource
    AuthService authService;

    @Resource
    MenuResService menuResService;

    @Resource
    ResourceLoader resourceLoader;

    @Resource
    ServerProperties serverProperties;

    @Resource
    FrameworkProperties frameworkProperties;

    @Resource
    SimplePageService simplePageService;

    @Resource
    SimpleDao simpleDao;


    final LRUCache<String, Page> lruCache = CacheUtil.newLRUCache(10 * 1000, 5 * 60 * 1000);

    @Data
    @Accessors(chain = true, fluent = true)
    static class Page {
        String url;
        String content;
        List<String> requirePermissionList;
    }

    /**
     * 获取菜单列表
     *
     * @return ApiResp
     */
    @GetMapping("appMenuList")
    @Operation(tags = {"Amis支持"}, summary = "获取Amis菜单列表")
    public AmisResp getAmisAppMenuList(boolean isShowNotPermissionMenu) {

        AmisResp resp = AmisResp.builder().build();

        resp.getData().put(AmisMenu.DATA_KEY, Collections.emptyList());

        //获取页面地址
        String basePath = getContextPath() + API_PATH + "amis/page";

        List<MenuResInfo> authorizedMenuList = rbacResService.getAuthorizedMenuList(isShowNotPermissionMenu, authService.getLoginUserId());

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

        if (page.requirePermissionList == null
                || page.requirePermissionList.isEmpty()) {
            return;
        }

        boolean isAuthorized = rbacService.isAuthorized(true, page.requirePermissionList, (rp, info) -> {
            throw new AuthorizationException("page-" + page.url, "未授权的资源：" + rp);
        });

        if (!isAuthorized) {
            throw new AuthorizationException("page-" + page.url, "使用未授权的资源");
        }

    }

    private String getContent(Page page) {

        if (StringUtils.hasText(page.content)) {
            //检查权限
            checkAuthorize(page);
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return page.content();
    }

    /**
     * 获取菜单列表
     *
     * @return ApiResp
     */
    @GetMapping("page")
    @Operation(tags = {"Amis支持"}, summary = "获取Amis页面-5分钟刷新")
    public String page(String url, String type, String category, TenantShareReq shareReq) {

        Assert.hasText(url, "url必须指定");

        final String key = String.join("|", type, category, url);

        Page page = lruCache.get(key);

        if (page != null) {
            return getContent(page);
        }

        page = new Page().url(url);

        //页面信息 @todo 按租户过滤
        SimplePageInfo one = simplePageService.findOne(new QuerySimplePageReq()
                .setType(type)
                .setCategory(category)
                .setContainsPublicData(shareReq.isContainsPublicData())
                .setPath(url)
                .setOrderBy(E_SimplePage.tenantId)
                .setOrderDir(OrderBy.Type.Asc)
                .setTenantId(shareReq.getTenantId()));

        //如果是被禁用
        if (one != null && !Boolean.TRUE.equals(one.getEnable())) {
            httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
            return null;
        }

        //j
        if (one != null && StringUtils.hasText(one.getRequireAuthorizations())) {
            page.requirePermissionList = JsonStrArrayUtils.parse(one.getRequireAuthorizations(), StringUtils::hasText, (txt) -> txt);
        }

        if (one != null
                && StringUtils.hasText(one.getContent())) {
            page.content = one.getContent();
        } else {
            //读取本地文件
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:/templates/" + url + ".json");
            if (resource != null
                    && resource.isReadable()) {
                try {
                    page.content = NioUtil.read(resource.readableChannel(), Charset.forName("utf-8"));
                } catch (Exception e) {
                    log.warn("Read resource error" + url + "", e);
                }
            }
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
                amisMenu.setSchemaApi("jsonp:" + basePath + "?type=jsonp&url=" + URLEncoder.encode(item.getPath(), "utf-8"));
            } else {
                //固定参数
                amisMenu.setSchemaApi(basePath + "?type=json&url=" + URLEncoder.encode(item.getPath(), "utf-8"));
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
            item.setIcon(hasChildren ? "fa fa-cube" : "fal fa-bars");
        }

//        <i class="fal fa-bars"></i>

        if (hasChildren) {
            for (AmisMenu child : item.getChildren()) {
                //递归转换
                setDefaultIcon(child, deep + 1);
            }
        }
    }


}
