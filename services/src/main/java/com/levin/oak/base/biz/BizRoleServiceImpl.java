package com.levin.oak.base.biz;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.IsNull;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.entities.EntityConst.MAINTAIN_ACTION;


@Service(PLUGIN_PREFIX + "BizRoleService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizRoleService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)
public class BizRoleServiceImpl implements BizRoleService {

    @Resource
    protected SimpleDao simpleDao;

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    @Override
    public List<String> getRolePermissionList(String tenantId, List<String> roleCodeList) {
        return new ArrayList<>(
                //获取指定用户的权限列表
                simpleDao.selectFrom(Role.class)
                        .select("distinct " + E_Role.permissionList)
                        .eq(E_Role.enable, true)
                        .in(E_Role.code, roleCodeList)
                        //公共角色和自有角色，只能二选一
                        .appendByAnnotations(StringUtils.hasText(tenantId), E_Role.tenantId, tenantId, Eq.class)
                        .appendByAnnotations(tenantId == null, E_Role.tenantId, null, IsNull.class)
                        .find(String.class)
                        .stream()
                        .filter(StringUtils::hasText)
                        //JSON 转换
                        .flatMap(json -> (JsonStrArrayUtils.<String>parse(json, null, null)).stream())
//                .map(json -> (List<ResPermission>) gson.fromJson(json, resPermissionListType))
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toSet())
        );
    }
}
