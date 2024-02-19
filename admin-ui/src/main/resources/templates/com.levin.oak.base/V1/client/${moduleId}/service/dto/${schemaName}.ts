/**
 * ${schema.label!}
 * ${schema.description!}
 *
 * ${schema.classType!}
 *
 * freeMarker语法：
 * http://freemarker.foofun.cn/dgui_template_exp.html
 *
 * <p>
 * eg.
 * <p>
 * @author Auto gen by simple-dao-codegen, @time: ${.now}, 代码生成哈希校验码：[]，请不要修改和删除此行内容。
 *
 */

<#list schemaImportList as imp>
import { ${imp} } from "./${imp}";
</#list>

<#if schema.enum>
export enum ${schema.name} {
    <#list schema.columnList as column>

    ${column.name} = '${column.label}',
    </#list>
}

<#else>
// @ts-ignore
export interface ${schema.typeDefine} <#if schema.superType?? || schema.interfaceList[0]??>extends ${schema.superType!}<#if schema.superType?? && schema.interfaceList[0]??>, </#if><#list schema.interfaceList as item> ${item!}<#sep>, </#list></#if>{

<#list schema.columnList as column>
     //${column.label} ${column.desc}
    ${column.name}<#if !column.requiredMode??>?</#if>${(column.typeDefinePrefix?length > 0) ? string(column.typeDefinePrefix,'')}${(column.readOnly) ? string('()','')}: ${column.typeDefine};

</#list>

}
</#if>