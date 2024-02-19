
/**
 * ${module.label}
 * <p>
 * eg.
 * <p>
 * @author Auto gen by simple-dao-codegen, @time: ${.now}, 代码生成哈希校验码：[]，请不要修改和删除此行内容。
 *
 */

<#list module.serviceList as service>
export * from "./${service.name}Service";
</#list>
