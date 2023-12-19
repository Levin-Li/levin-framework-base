package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.rbac.RbacUserObject;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

import com.levin.oak.base.biz.rbac.RbacLoadService;
import com.levin.oak.base.services.role.info.RoleInfo;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
//import org.springframework.dao.*;

//import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.org.*;
import com.levin.oak.base.biz.bo.org.*;

import static com.levin.oak.base.services.org.OrgService.*;

import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;

import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表

import javax.annotation.PostConstruct;
import java.util.Set;
////////////////////////////////////

/**
 * 机构-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月12日 下午10:13:51, 代码生成哈希校验码：[f677ccb5962aa309232c648590fcc562]，请不要修改和删除此行内容。
 */

// 事务隔离级别
// Propagation.REQUIRED：默认的事务传播级别，它表示如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
// Propagation.SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
// Propagation.MANDATORY：（mandatory：强制性）如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
// Propagation.REQUIRES_NEW：表示创建一个新的事务，如果当前存在事务，则把当前事务挂起。也就是说不管外部方法是否开启事务，Propagation.REQUIRES_NEW 修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
// Propagation.NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
// Propagation.NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
// Propagation.NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于 PROPAGATION_REQUIRED。

@Service(PLUGIN_PREFIX + "BizOrgService")

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizOrgService", havingValue = "true", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_Org.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_Org.SIMPLE_CLASS_NAME}, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")
public class BizOrgServiceImpl extends BaseService implements BizOrgService {

    @Autowired
    OrgService orgService;

    @Autowired
    RbacLoadService<Serializable> rbacLoadService;

    @Autowired
    BizRoleService bizRoleService;

    final AntPathMatcher orgIdPathMatcher = new AntPathMatcher();

    protected BizOrgServiceImpl getSelfProxy() {
        return getSelfProxy(BizOrgServiceImpl.class);
    }

    @PostConstruct
    public void init() {
        orgIdPathMatcher.setCachePatterns(true);
    }

    /**
     * 加载租户的部门列表
     *
     * @param tenantId
     * @return
     */
    @Override
    public List<OrgInfo> loadOrgList(String tenantId, String parentId) {

        return simpleDao.findByQueryObj(
                new QueryOrgReq()
                        .setTenantId(tenantId)
                        .setParentId(parentId)
                        .setOrderBy(E_Org.orderCode)
                        .setOrderDir(OrderBy.Type.Desc)
        );

    }

    /**
     * 加载当前用户有权限访问的部门列表
     *
     * @param userPrincipal
     * @param loadLevel
     * @return
     */
    @Override
    public List<OrgInfo> loadOrgList(Serializable userPrincipal, int loadLevel) {
        return assembleOrgTree(() -> rbacLoadService.loadUser(userPrincipal), user -> bizRoleService.loadUserRoleList(user));
    }

    /**
     * 组装树形结构
     *
     * @param orgList
     * @return
     */
    public List<OrgInfo> assembleOrgTree(List<OrgInfo> orgList, boolean isCopy, boolean buildPath, boolean clearParent, boolean onlyRoot) {

        //根据parentId属性进行树形分组
        if (orgList == null || orgList.isEmpty()) {
            return Collections.emptyList();
        }

        //复制数据
        if (isCopy) {
            orgList = orgList.stream().map(
                    org -> BeanUtil.copyProperties(org, OrgInfo.class)
            ).collect(Collectors.toList());
        }

        //清楚旧数据，重新构建
        orgList.forEach(org ->
                org.setChildren(new HashSet<>())
                        .setNodePath(null)
                        .setParent(null)
        );

        Map<String, OrgInfo> tempMap = new LinkedHashMap<>();

        //填充数据
        orgList.forEach(org -> tempMap.put(org.getId(), org));

        //构建树形
        orgList.forEach(org -> {
            OrgInfo parent = tempMap.get(org.getId());
            if (parent != null) {
                parent.getChildren().add(org);
                org.setParent(parent);
            }
        });

        tempMap.clear();

        //构建节点路径
        if (buildPath) {
            orgList.forEach(org -> {
                List<String> paths = new ArrayList<>();
                OrgInfo orgTemp = org;

                while (orgTemp != null) {
                    paths.add(orgTemp.getId());
                    orgTemp = orgTemp.getParent();
                }
                //倒序
                Collections.reverse(paths);
                //构建路径
                org.setNodePath(String.join("/", paths));
            });
        }

        //清空parent
        if (clearParent) {
            orgList.forEach(org -> org.setParent(null));
        }

        if (onlyRoot) {
            //只返回根节点
            orgList.removeIf(org -> org.getParent() != null || (org.getNodePath() != null && org.getNodePath().contains("/")));
        }

        //防止Json toString 递归，清空父节点的属性。

        return orgList;

    }

    /**
     * 加载当前用户有权限访问的部门列表
     *
     * @param userSupplier
     * @param userRoleSupplier
     * @return
     */
    public List<OrgInfo> assembleOrgTree(Supplier<RbacUserObject<String>> userSupplier, Function<RbacUserObject<String>, List<RoleInfo>> userRoleSupplier) {

        RbacUserObject<String> user = userSupplier.get();

        //加载所有的部门
        List<OrgInfo> orgList = getSelfProxy().loadOrgList(user.getTenantId());

        //如果是超级管理员，则返回所有部门
        if (user.isSuperAdmin()
                || (user.isTenantAdmin() && StrUtil.isNotBlank(user.getTenantId()))) {
            return assembleOrgTree(orgList, true, true, true, true);
        }

        //获取当前用户有权限访问的部门

        List<RoleInfo> roleList = userRoleSupplier.apply(user);

        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }

        //只要有一个角色是允许所有的部门，则返回所有部门
        if (roleList.stream().anyMatch(roleInfo -> Role.OrgDataScope.All.equals(roleInfo.getOrgDataScope()))) {
            return assembleOrgTree(orgList, true, true, true, true);
        }

        String myOrgId = null;

        boolean isMyDeptAndChildren = false;
        ///////////////////////////////////////////////
        if (user.getOrgId() != null) {

            String orgId = user.getOrgId().toString();

            if (StrUtil.isNotBlank(orgId)) {

                boolean isMyDept = roleList.stream()
                        //本部门
                        .anyMatch(roleInfo -> Role.OrgDataScope.MyDept.equals(roleInfo.getOrgDataScope()));

                if (isMyDept) {
                    myOrgId = orgId;
                }

                isMyDeptAndChildren = roleList.stream()
                        //本部门级子部门
                        .anyMatch(roleInfo -> Role.OrgDataScope.MyDeptAndChildren.equals(roleInfo.getOrgDataScope()));

                if (isMyDeptAndChildren) {
                    myOrgId = orgId;
                }
            }
        }

        //////////////////////////////////////////////////////////////

        //全部的部门
        List<OrgInfo> resultList = assembleOrgTree(orgList, true, true, true, false);

        //分配的部门列表
        Set<String> orgIdList = roleList.stream()
                //指定部门的角色
                .filter(roleInfo -> Role.OrgDataScope.Assigned.equals(roleInfo.getOrgDataScope()))
                .map(RoleInfo::getAssignedOrgIdList)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(StringUtils::hasText)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        //
        if (StrUtil.isNotBlank(myOrgId)) {
            //添加匹配的逻辑
            orgIdList.add(isMyDeptAndChildren ? (myOrgId + "/**") : myOrgId);
        }

        //清除不需要的部门
        resultList.removeIf(
                //不保留的部门
                org -> !( //使用反条件，方便理解和提升性能
                        //保留的部门
                        orgIdList.contains(org.getId())
                                || orgIdList.stream().anyMatch(idPattern -> orgIdPathMatcher.isPattern(idPattern) && orgIdPathMatcher.match(idPattern, org.getNodePath()))
                )
        );

        //重新构建树形
        resultList = assembleOrgTree(resultList, false, true, true, true);

        return resultList;
    }


    /**
     * 更新记录，并返回更新是否成功
     *
     * @param userPrincipal
     * @param req
     * @param queryObjs     附加的查询条件或是更新内容
     * @return boolean 是否成功
     */
    @Override
    public boolean update(Serializable userPrincipal, UpdateOrgReq req, Object... queryObjs) {
        return false;
    }

    @Operation(summary = CREATE_ACTION)
    @Transactional
    //@Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#result)", key = CK_PREFIX + "#result") //创建也清除缓存，防止空值缓存的情况
    public String create(Serializable userPrincipal, CreateOrgReq req) {
        return orgService.create(req);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    //@Override
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX + "#id")
    public OrgInfo findById(String id) {
        return orgService.findById(id);
    }

    //调用本方法会导致不会对租户ID经常过滤，如果需要调用方对租户ID进行核查
    @Operation(summary = VIEW_DETAIL_ACTION)
    //@Override
    @Cacheable(unless = "#result == null", condition = "@spelUtils.isNotEmpty(#req.id)", key = CK_PREFIX + "#req.id")
    //#req.tenantId +
    public OrgInfo findById(Serializable userPrincipal, OrgIdReq req) {
        return orgService.findById(req);
    }

    @Operation(summary = UPDATE_ACTION)
    //@Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id")
//, beforeInvocation = true
    @Transactional
    public boolean update(Serializable userPrincipal, UpdateOrgReq req) {
        return orgService.update(req);
    }

    @Operation(summary = DELETE_ACTION)
    //@Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id")
    //#req.tenantId +  , beforeInvocation = true
    @Transactional
    public boolean delete(Serializable userPrincipal, OrgIdReq req) {
        return orgService.delete(req);
    }

    /**
     * 统计
     *
     * @param req
     * @param paging 分页设置，可空
     * @return StatOrgReq.Result
     */
    @Operation(summary = STAT_ACTION)
    public StatOrgReq.Result stat(StatOrgReq req, Paging paging) {
        return simpleDao.findOneByQueryObj(req, paging);
    }

    //@Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#key)", key = CK_PREFIX + "#key")
    public void clearCache(Object key) {
        orgService.clearCache(key);
    }

}
