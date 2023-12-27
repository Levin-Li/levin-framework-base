package com.levin.oak.base.controller.commons;

import cn.hutool.core.lang.ClassScanner;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ClassUtil;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizFileStorageService;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacResService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.commons.dto.EnumInfo;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.services.commons.req.MultiTenantOrgReq;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.LinkedHashMap;
import java.util.Map;

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

@RestController(PLUGIN_PREFIX + "FileStorageController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "FileStorageController", havingValue = "true", matchIfMissing = true)
@RequestMapping(API_PATH + "fss")

@Slf4j
@Valid
@ResAuthorize(domain = ID, type = EntityConst.COMMON_TYPE_NAME+ "-")
@Tag(name = "文件存储", description = "文件存储服务")
@MenuResTag(false)
public class FileStorageController extends BaseController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    RbacResService rbacResService;

    @Autowired
    AuthService authService;

    @Autowired
    BizTenantService bizTenantService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    PluginManager pluginManager;

    @Autowired
    BizFileStorageService fileStorageService;

    @PostMapping("uploadSingleFile")
    @Operation(summary = "上传单个文件", description = "返回访问Url路径")
    public ApiResp<String> uploadSingleFile(MultiTenantOrgReq req, HttpServletRequest request) {

        if (request instanceof MultipartRequest) {

            Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();

            if (fileMap.size() < 1) {
                return ApiResp.error("请上传文件");
            } else if (fileMap.size() > 1) {
                return ApiResp.error("一次只能上传一个文件");
            }

            return ApiResp.ok(fileStorageService.upload(req.getTenantId(), null, fileMap.values().stream().findFirst().get()));
        }

        return ApiResp.error("请上传文件");
    }

    @PostMapping("uploadFiles")
    @Operation(summary = "上传多个文件", description = "返回访问Url路径")
    public ApiResp<Map<String, String>> uploadFiles(MultiTenantOrgReq req, HttpServletRequest request) {

        if (request instanceof MultipartRequest) {

            Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();

            if (fileMap.size() < 1) {
                return ApiResp.error("请上传文件");
            }

            Map<String, String> result = new LinkedHashMap<>();

            fileMap.forEach((fn, file) -> {
                result.put(fn, fileStorageService.upload(req.getTenantId(), null, file));
            });

            return ApiResp.ok(result);

        }

        return ApiResp.error("请上传文件");
    }
}
