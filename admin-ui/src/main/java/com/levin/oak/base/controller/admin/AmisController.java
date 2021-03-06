package com.levin.oak.base.controller.admin;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import com.levin.commons.dao.Case;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.rbac.AuthorizationException;
import com.levin.commons.rbac.MenuItem;
import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacResService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.codegen.UiCodeGen;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.rbac.dto.AmisMenu;
import com.levin.oak.base.controller.rbac.dto.AmisResp;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.CommonReq;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.simplepage.SimplePageService;
import com.levin.oak.base.utils.AmisUtils;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.*;


// POST: ?????????????????????????????????????????????????????????
// PATCH: ???????????????????????????
// PUT: ???????????????????????????????????????
// DELETE: ??????????????????
// GET: ???????????????????????????

// ??????????????????????????????????????????????????????idempotence????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

// http?????????????????????put???get???delete????????????????????????????????????post?????????????????????
// ??????????????????????????????????????????post??????????????????????????????put??????
// @Valid????????????controller???@Validated?????????????????????spring??????????????????

@Tag(name = "Amis??????", description = "Amis??????")
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

    @Resource
    UiCodeGen uiCodeGen;


    final LRUCache<String, Page> lruCache = CacheUtil.newLRUCache(10 * 1000, 5 * 60 * 1000);

    @Data
    @Accessors(chain = true)
    static class Page {

        Boolean enable;

        String path;

        String content;

        @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
        List<String> requireAuthorizations;
    }

    /**
     * ??????????????????
     *
     * @return ApiResp
     */
    @RequestMapping(value = "appMenuList", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(tags = {"Amis??????"}, summary = "??????Amis????????????")
    public AmisResp getAmisAppMenuList(boolean isShowNotPermissionMenu) {

        AmisResp resp = AmisResp.builder().build();

        resp.getData().put(AmisMenu.DATA_KEY, Collections.emptyList());

        //??????????????????
        String basePath = getContextPath() + API_PATH + "amis/page";

        List<MenuResInfo> authorizedMenuList = rbacResService.getAuthorizedMenuList(isShowNotPermissionMenu, authService.getLoginId());

        if (authorizedMenuList != null) {

            final AtomicInteger maxDeep = new AtomicInteger();
            final List<AmisMenu> rootMenuList = new ArrayList<>(3);

            List<AmisMenu> amisMenuList = authorizedMenuList.stream()
                    .map(item -> convert(item, basePath, 1, maxDeep, rootMenuList))
                    .collect(Collectors.toList());

            //??????????????????3???
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
                                .setLabel("??????")
                                .setUrl("/")
                                .setRedirect(getDefaultIndex(amisMenuList))
                                .setDefaultPage(true)
                );

            } else if (rootMenuList.size() > 1) {
                log.warn("??????????????????1???????????????: "
                        + rootMenuList.stream().map(AmisMenu::getLabel).collect(Collectors.toList()));
            }

            //??????????????????
            amisMenuList.forEach(item -> setDefaultIcon(item, 1));

            resp.getData().put(AmisMenu.DATA_KEY, amisMenuList);
        }

        return resp;
    }

    private String getDefaultIndex(List<AmisMenu> amisMenuList) {

        //?????????????????????
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

        boolean isAuthorized = rbacService.isAuthorized(true, page.requireAuthorizations, (rp, info) -> {
            throw new AuthorizationException("ui-" + page.getPath(), "????????????ui?????????" + rp);
        });

        if (!isAuthorized) {
            throw new AuthorizationException("ui-" + page.getPath(), "??????????????????ui??????");
        }

    }

    private String getContent(Page page) {

        if (StringUtils.hasText(page.getContent())) {
            //????????????
            checkAuthorize(page);
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return page.getContent();
    }

    /**
     * ????????????????????????????????????amis????????????
     * <p>
     * ????????????????????????????????????????????????
     *
     * @return ApiResp
     */
    @RequestMapping(value = "{uiType}", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(tags = {"Amis??????"}, summary = "??????Amis UI??????-5????????????")
    public String getUiContent(@PathVariable String uiType, String path, String type, String category, CommonReq shareReq) {

        Assert.hasText(path, "path ????????????");

        final String key = String.join("|", uiType, type, category, path);

        Page page = lruCache.get(key);

        if (page != null) {
            return getContent(page);
        }

//         page = new Page().setPath(path);

        //???????????? @todo ???????????????
//        SimplePageInfo page = simplePageService.findOne(new QuerySimplePageReq()
//                .setType(type)
//                .setCategory(category)
//                .setContainsPublicData(shareReq.isContainsPublicData())
//                .setPath(path)
//                .setOrderBy(E_SimplePage.tenantId)
//                .setOrderDir(OrderBy.Type.Asc)
//                .setTenantId(shareReq.getTenantId()));

        final Class<? extends SimpleEntity> aClass = "page".equalsIgnoreCase(uiType) ? SimplePage.class : SimpleForm.class;

        page = simpleDao.selectFrom(aClass)
                //  .disableEmptyValueFilter()
                .eq(E_SimpleEntity.type, type)
                .eq(E_SimpleEntity.category, category)
                .eq(E_SimpleEntity.path, path)
                .isNullOrEq(E_SimpleEntity.tenantId, shareReq.getTenantId())
                //??????,???????????????
                .orderBy(OrderBy.Type.Desc, new Case()
                        .when(E_SimpleEntity.tenantId + " IS NULL", "0")
                        .elseExpr("1")
                        .toString("(", ")")
                )
                .findOne(Page.class);

        //??????????????????
        if (page != null && !Boolean.TRUE.equals(page.getEnable())) {
            httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
            return null;
        }

        //????????????????????????
        if (page == null) {
            page = new Page().setPath(path);
        }

        if (!StringUtils.hasText(page.getContent())) {
            //??????????????????
            page.content = AmisUtils.readAdminClassPathResource(path);
        }

        //??????????????????????????????????????????5??????
        lruCache.put(key, page);

        return getContent(page);
    }

    /**
     * ??????????????????
     *
     * @param item
     * @param basePath
     * @param deep
     * @param rootMenuList url ??? / ???????????????
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
                //????????????
                amisMenu.setSchemaApi(basePath + "?type=json&path=" + URLEncoder.encode(item.getPath(), "utf-8"));
            }
        }

        //??????????????????
        if (StringUtils.hasText(amisMenu.getUrl())
                && "/".equalsIgnoreCase(amisMenu.getUrl().trim())) {
            rootMenuList.add(amisMenu);
        }

        //???????????????
        if (item.getChildren() != null
                && !item.getChildren().isEmpty()) {

            amisMenu.setChildren(new ArrayList<>(item.getChildren().size()));

            for (MenuResInfo child : item.getChildren()) {
                //????????????
                amisMenu.getChildren().add(convert(child, basePath, deep + 1, maxDeep, rootMenuList));
            }
        }

        return amisMenu;
    }


    /**
     * ??????????????????
     *
     * @param item
     * @param deep
     */
    void setDefaultIcon(AmisMenu item, int deep) {

        //?????????
        //https://fontawesome.com/

        if (deep < 1) {
            deep = 1;
        }

        item.setDeepLevel(deep);

        boolean hasChildren = item.hasChildren();

        if (!StringUtils.hasText(item.getIcon()) && deep >= 2) {
            //?????????????????????
            //?????????
            //https://fontawesome.com/
            item.setIcon(hasChildren ? "fa fa-cube" : "fa fa-bars");
        }

        if (hasChildren) {
            for (AmisMenu child : item.getChildren()) {
                //????????????
                setDefaultIcon(child, deep + 1);
            }
        }
    }

}
