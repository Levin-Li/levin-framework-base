/**
 * ${service.label}
 * <p>
 * freeMarker语法：
 * http://freemarker.foofun.cn/dgui_template_exp.html
 * eg.
 * <p>
 * @author Auto gen by simple-dao-codegen, @time: ${.now}, 代码生成哈希校验码：[]，请不要修改和删除此行内容。
 *
 */

import { Service,RequestService } from "../../request";
import { axios, AxiosRequestConfig } from "axios";

<#list serviceImportList as imp>
import { ${imp} } from "./dto/${imp}";
</#list>

@Service("${service.pathPrefix!}")
export class ${serviceName} extends RequestService {

<#list service.apiList as api>
    // @ts-ignore
    async ${api.name}${(api.returnTDefinePrefix?length > 0) ? string(api.returnTDefinePrefix, '')}(<#list api.paramList as param>${param.name}<#if !param.requiredMode??>?</#if>: ${param.typeDefine}<#sep>, </#list>): Promise<${api.returnTypeDefine}> {
        return this.request({
            url: "${api.path?first}",
            method: "${api.method?first}",
            data:{<#list api.paramList as param>${(param.simpleType || param.typeDefine?starts_with('Array<'))?string('','...')}${param.name}<#sep>, </#list>}
        });
    }
</#list>

}