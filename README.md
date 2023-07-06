# levin-framework-base

多租户、插件式Levin框架中的基础模块。

1、支持多租户
   支持类：BizTenantService，可重写实现
   支持租户设置自定义域名：DomainInterceptor 
  
2、支持控制器变量注入
   支持类：InjectVarService，可重写实现。每个控制器的请求参数都会自动注入。   
   
3、支持RBAC，支持严密的安全拦截

   AuthService 认证服务接口，包括登录，登出，获取用户角色列表，权限列表等。可重写实现。   
   
   RbacService 授权服务，包括方法授权检查，获取用户可分配的权限清单，获取用户可访问的采集清单。
   
   ResAuthorize 基于注解的访问鉴权控制
   
   AuthorizeAnnotationInterceptor 基于注解的访问鉴权控制器拦截
   
   超管角色：是一个特殊的角色，租户管理，地区管理等公共数据必须超管才能操作。
    
   模块中菜单、和区域支持公共数据，租户可以使用公共数据的同时拥有私有数据。
   
   在RBAC中为角色分配权限时，角色的权限范围必须是当前用户所拥有的权限。
   在RBAC中为用户分配角色时，当前用户所拥有的权限能覆盖的角色，才允许被分配，比如当前用户不是超级管理员，不能为自己或是其它用户分配租户管理的权限。
   
   
4、支持调度任务 
   
5、支持访问任务    
   
6、支持Amis前端

7、支持常见的OSS，短信和支付基本商业业务

#### 框架配置文件
 
        plugin:
          com:
            levin:
              oak:
                base:
                  framework:
                    #自带后台的访问路径
                    admin-path: /
                    #控制器访问控制
                    controller-acl:
                      exclude-path-patterns: "/v3/api-docs/**"
                    sign:
                      enable: false
                    log:
                      exclude-path-patterns: /v3/api-docs/**,/**/api/amis/page
                    #资源访问控制
                    resourcesAcl:
                      - includePathPatterns: "/templates/com.levin.oak.base/**"
                        denied: true
                      - exclude-path-patterns: /**,/doc.html,/swagger-ui/**,/webjars/**
                    #是开启短信验证码
                 #  enable-sms-verification-code: false
                    #是开启模拟短信验证码
                    enable-mock-sms-send: true

