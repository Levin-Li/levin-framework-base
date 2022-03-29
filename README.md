# levin-framework-base


1、支持多租户
   支持类：BizTenantService，可重写实现
   支持租户设置自定义域名：DomainInterceptor 
  
2、支持控制器变量注入
   支持类：InjectVarService，可重写实现   
   
3、支持RBAC

   AuthService 认证服务接口，包括登录，登出，获取用户角色列表，权限列表等。可重写实现。   
   
   RbacService 授权服务，包括方法授权检查，获取用户可分配的权限清单，获取用户可访问的采集清单。
   
   ResAuthorize 基于注解的访问鉴权
   
   AuthorizeAnnotationInterceptor 基于注解的访问鉴权控制器拦截
   
   
4、支持Amis前端
      
      