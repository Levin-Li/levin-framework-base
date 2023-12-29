package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.rbac.RbacUserObject;
import com.levin.oak.base.biz.rbac.RbacLoadService;
import com.levin.oak.base.entities.E_Org;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.org.OrgService;
import com.levin.oak.base.services.org.info.OrgInfo;
import com.levin.oak.base.services.org.req.QueryOrgReq;
import com.levin.oak.base.services.role.info.RoleInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.*;
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
    BizRoleService<Serializable> bizRoleService;

    final AntPathMatcher orgIdPathMatcher = new AntPathMatcher();


    private static BeanCopier orgInfoCopier = null;

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
     * 是否能访问所有部门
     *
     * @param userPrincipal
     * @return
     */
    @Override
    public boolean canAccessAllOrg(Serializable userPrincipal) {

        RbacUserObject<String> user = rbacLoadService.loadUser(userPrincipal);

        if (user.isSuperAdmin()
                || (user.isTenantAdmin() && StrUtil.isNotBlank(user.getTenantId()))) {
            return true;
        }

        List<RoleInfo> roleList = bizRoleService.loadUserRoleList(user);

        return roleList != null
                && !roleList.isEmpty()
                && roleList.stream().anyMatch(roleInfo -> Role.OrgDataScope.All.equals(roleInfo.getOrgDataScope()));
    }

    /**
     * 加载当前用户有权限访问的部门列表
     *
     * @param userPrincipal
     * @param assembleTree
     * @param rootIdList
     * @return
     */
    @Override
    public List<OrgInfo> loadOrgList(Serializable userPrincipal, boolean assembleTree, String... rootIdList) {
        Assert.notNull(userPrincipal, "用户未登录");
        return loadOrgList(() -> rbacLoadService.loadUser(userPrincipal), user -> bizRoleService.loadUserRoleList(user), assembleTree, rootIdList);
    }

    /**
     * 根据节点路径查找上级节点，不一定是直接的父节点，可能是祖先节点
     * <p>
     * // 选中最靠后的节点
     * //目标 1/2/3/4/5/6/7/8/9/10/
     * // 1/2/ 匹配但未选中
     * // 1/2/36/
     * // 1/2/3/4/5/ 匹配但未选中
     * // 7/8/9/  这个会被匹配并选中
     *
     * @param orgList
     * @param orgInfo
     * @return
     */
    public OrgInfo findUpNodeByNodePath(List<OrgInfo> orgList, OrgInfo orgInfo) {

        int lastedIndexOf = orgInfo.getNodePath().lastIndexOf('/');

        //如果是根节点
        if (lastedIndexOf == -1) {
            return null;
        }

        final String parentNodePath = orgInfo.getNodePath().substring(0, lastedIndexOf + 1);

        OrgInfo parent = null;

        int index = -1;

        for (OrgInfo info : orgList) {

            //如果是自己
            if (orgInfo == info || info.getId().equals(orgInfo.getId())) {
                continue;
            }

            //加上/以保证匹配的正确性
            String nodePath = info.getNodePath() + '/';

            if (nodePath.equals(parentNodePath)) {
                parent = info;
                break;
            }

            // 选中最靠后的节点
            //目标 1/2/3/4/5/6/7/8/9/10/
            // 1/2/ 匹配但未选中
            // 1/2/36/
            // 1/2/3/4/5/ 匹配但未选中
            // 7/8/9/  被匹配并选中

            //如果是匹配最多的一个保留
            int tempIndex = parentNodePath.indexOf(nodePath);

            //从头开始匹配的节点
            if (tempIndex >= 0) {

                tempIndex = tempIndex + nodePath.length();

                //选中最靠后的节点
                if (tempIndex > index) {
                    index = tempIndex;
                    parent = info;
                }
            }
        }

        return parent;

    }

    /**
     * 组装树形结构
     *
     * @param orgList
     * @param clearParent
     * @return
     */
    public List<OrgInfo> assembleTreeByNodePath(List<OrgInfo> orgList, boolean clearParent) {

        List<OrgInfo> tempList = new ArrayList<>(orgList);

        //先清空子节点
        orgList.forEach(org -> org.setChildren(new HashSet<>()));

        if (clearParent) {
            orgList.forEach(org -> org.setParent(null));
        }

        orgList.removeIf(orgInfo -> {

            //根据节点路径查找上级节点，不一定是直接的父节点，可能是祖先节点
            OrgInfo parent = findUpNodeByNodePath(tempList, orgInfo);

            if (parent == null) {
                return false;
            }

            //添加进去
            parent.getChildren().add(orgInfo);

            return true;

        });

        return orgList;
    }


    /**
     * 组装树形结构
     *
     * @param orgList
     * @return
     */
    public List<OrgInfo> loadOrgList(List<OrgInfo> orgList, boolean isCopy, boolean buildPathById, boolean clearParent, boolean assembleTree, String... rootIdList) {

        //根据parentId属性进行树形分组
        if (orgList == null || orgList.isEmpty()) {
            return Collections.emptyList();
        }

        //复制数据
        if (isCopy) {
            List<OrgInfo> tempList = new ArrayList<>(orgList.size());

            orgList.forEach(org -> {
                OrgInfo target = new OrgInfo();
                //@todo 优化复制性能
                BeanUtils.copyProperties(org, target, OrgInfo.Fields.parent, OrgInfo.Fields.children);
                tempList.add(target);
            });

            orgList = tempList;
        }


        //构建节点路径
        if (buildPathById) {

            Map<String, OrgInfo> tempMap = new LinkedHashMap<>();

            //填充数据
            orgList.forEach(org -> tempMap.put(org.getId(), org));

            //填充父节点
            orgList.forEach(org ->
                    //防止死循环
                    org.setParent(org.getId().equals(org.getParentId()) ? null : tempMap.get(org.getParentId()))
            );

            tempMap.clear();

            ///////////////////////////////////////////////////////////////////

            orgList.forEach(org -> {

                List<String> paths = new ArrayList<>();
                OrgInfo orgTemp = org;

                //防止死循环
                while (orgTemp != null
                        && !paths.contains(orgTemp.getId())) {
                    paths.add(orgTemp.getId());
                    orgTemp = orgTemp.getParent();
                }
                //倒序
                Collections.reverse(paths);
                //构建路径
                org.setNodePath(String.join("/", paths));
            });

            //
        }

        //只过滤出包含的节点
        Set<String> rootIdSet = (rootIdList != null && rootIdList.length > 0) ?
                Stream.of(rootIdList).filter(StrUtil::isNotBlank).collect(Collectors.toSet()) : Collections.emptySet();

        if (!rootIdSet.isEmpty()) {
            //删除路径中不包含指定节点的节点
            orgList.removeIf(org -> StrUtil.isBlank(org.getNodePath()) || rootIdSet.stream().noneMatch(id -> ("/" + org.getNodePath() + "/").contains("/" + id + "/")));
        }

        if (assembleTree) {
            orgList = assembleTreeByNodePath(orgList, clearParent);
        } else {
            //返回所有节点
            orgList.forEach(org -> org.setChildren(null).setParent(clearParent ? null : org.getParent()));
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
    @Override
    public List<OrgInfo> loadOrgList(Supplier<RbacUserObject<String>> userSupplier, Function<RbacUserObject<String>, List<RoleInfo>> userRoleSupplier, boolean assembleTree, String... rootIdList) {

        RbacUserObject<String> user = userSupplier.get();

        //加载所有的部门
        List<OrgInfo> orgList = loadOrgList(user.getTenantId());

        //如果是超级管理员，则返回所有部门
        if (user.isSuperAdmin()
                || (user.isTenantAdmin() && StrUtil.isNotBlank(user.getTenantId()))) {
            return loadOrgList(orgList, true, true, true, assembleTree, rootIdList);
        }

        //获取当前用户有权限访问的部门

        List<RoleInfo> roleList = userRoleSupplier.apply(user);

        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }

        //只要有一个角色是允许所有的部门，则返回所有部门
        if (roleList.stream().anyMatch(roleInfo -> Role.OrgDataScope.All.equals(roleInfo.getOrgDataScope()))) {
            return loadOrgList(orgList, true, true, true, assembleTree, rootIdList);
        }


        //////////////////////////////////////////////////////////////

        //全部的部门
        List<OrgInfo> resultList = loadOrgList(orgList, true, true, true, false, rootIdList);

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

        ///////////////////////////////////////////////
        if (user.getOrgId() != null) {

            String myOrgId = user.getOrgId().toString();

            if (StrUtil.isNotBlank(myOrgId)) {

                boolean myDept = roleList.stream()
                        //本部门
                        .anyMatch(roleInfo -> Role.OrgDataScope.OnlyMyDept.equals(roleInfo.getOrgDataScope()));

                boolean myChildren = roleList.stream()
                        //本部门
                        .anyMatch(roleInfo -> Role.OrgDataScope.OnlyChildren.equals(roleInfo.getOrgDataScope()));

                if (roleList.stream()
                        //本部门
                        .anyMatch(roleInfo -> Role.OrgDataScope.MyDeptAndChildren.equals(roleInfo.getOrgDataScope()))) {
                    myDept = myChildren = true;
                }

                if (myDept) {
                    orgIdList.add(myOrgId);
                }

                if (myChildren) {
                    orgIdList.add(("**/" + myOrgId + "/*/**"));
                }
            }
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

        //利用路径节点重新构建树形
        resultList = loadOrgList(resultList, false, false, true, assembleTree);

        return resultList;
    }


    @Override
    public void checkAccessible(Serializable userPrincipal, String tenantId, String parentId, String orgId) {

        RbacUserObject<String> user = rbacLoadService.loadUser(userPrincipal);

        Assert.notNull(user, "用户不能为空");

        //优化效率
        if (user.isSuperAdmin()) {
            return;
        }

        //租户ID必须相等
        Assert.isTrue(user.getTenantId().equals(tenantId), "非关联的租户[{}]", tenantId);

        //优化效率
        if (user.isTenantAdmin()) {
            return;
        }

        //只有租户管理员可以操作根节点
        Assert.isTrue(StrUtil.isNotBlank(parentId) || user.isTenantAdmin(), "组织机构上级节点不能为空");

        List<OrgInfo> orgList = getSelfProxy().loadOrgList(userPrincipal, false);

        Assert.notNull(orgList, "无可用的组织机构，请检查是否授权");
        Assert.isFalse(orgList.isEmpty(), "无可用的组织机构，请检查是否授权");

        Assert.isTrue(StrUtil.isBlank(parentId) || orgList.stream().anyMatch(org -> org.getId().equals(parentId)), "父组织机构[{}]未授权", parentId);

        Assert.isTrue(StrUtil.isBlank(orgId) || orgList.stream().anyMatch(org -> org.getId().equals(orgId)), "组织机构[{}]未授权", orgId);

    }
}
