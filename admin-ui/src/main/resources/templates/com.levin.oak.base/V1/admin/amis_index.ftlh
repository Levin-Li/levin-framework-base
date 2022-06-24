<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>${appName}管理后台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, maximum-scale=1"
    />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>

    <link rel="stylesheet"
          title="default"
          href="amis@beta/sdk/sdk.css"
            <#--             href="https://unpkg.com/amis@beta/sdk/antd.css"-->
    />

    <link rel="stylesheet"
          href="amis@beta/sdk/helper.css"
            <#--          href="https://unpkg.com/amis@beta/sdk/helper.css"-->
    />

    <link rel="stylesheet" type="text/css" href="css/amis.css">

    <#--    <script src="https://unpkg.com/amis@beta/sdk/sdk.js"></script>-->
    <script src="amis@beta/sdk/sdk.js"></script>

    <#--    <script src="https://unpkg.com/vue@2"></script>-->

    <script src="vue/dist/vue.min.js"></script>

    <#--    <script src="https://unpkg.com/history@4.10.1/umd/history.js"></script>-->
    <script src="history@4.10.1/umd/history.production.min.js"></script>


    <style>
        html,
        body,
        .app-wrapper {
            position: relative;
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        :root {
            /*左侧菜单栏宽度*/
            --Layout-aside-width: 15rem;
            /*--Avatar-width:1.6rem;*/
            ${amisRootCss}
        }
    </style>
</head>

<!--  https://aisuda.bce.baidu.com/amis/zh-CN/docs/start/getting-started#sdk-->


<body>
<div id="root" class="app-wrapper"></div>
<script>
    (function () {

        let amisLib = amisRequire('amis');
        let amis = amisRequire('amis/embed');
        const match = amisRequire('path-to-regexp').match;

        // amisLib.addRule(
        //     // 校验名
        //     'notEmpty',
        //     // 校验函数，values 是表单里所有表单项的值，可用于做联合校验；value 是当前表单项的值
        //     (values, value) => {
        //         return value && value.trim().length() > 0;
        //     },
        //     // 出错时的报错信息
        //     '不能为空'
        // );

        // 如果想用 browserHistory 请切换下这处代码, 其他不用变
        // const history = History.createBrowserHistory();
        const history = History.createHashHistory();

        window._amisLib = amisLib;

        function getContextPath() {
            let index = location.pathname.lastIndexOf("/");
            return index === -1 ? "" : location.pathname.substring(0, index);
        }

        //假设当前页面是根路径
        window._HOST_ROOT_URL = location.protocol + "//" + location.host;

        window._CONTEXT_PATH = getContextPath();

        window._APP_ROOT_URL = _HOST_ROOT_URL + _CONTEXT_PATH;

        window._OAK_BASE_API = _CONTEXT_PATH + "/com.levin.oak.base/V1/api";

        window._adminLogout = function () {
            amisLib.confirm("确定要退出？", "提示", "确定")
                .then((ok) => {
                    if (ok) {
                        window.location = window._HOST_ROOT_URL + "?action=relogin";
                    }
                })
        }

        console.log("_OAK_BASE_API:" + _OAK_BASE_API)

        const app = {
            type: 'app',
            brandName: '${appName}',
            logo: '${appLogo}',
            api: '${appMenuApi}',

            header: {
                type: 'tpl',
                inline: false,
                className: 'w-full',
                tpl: '<div class="flex justify-between">' +
                    '<div>'
                    //左侧
                    + '欢迎使用'
                    + '</div>' +
                    /////////////////////////////////////////////////////////////
                    '<div>' +
                    //右侧
                    '<i class="m-1 fa fa-user"> </i>' +
                    '<span class="w-24 overflow-hidden">${user.name}</span>' +
                    '<i class="m-1 fa fa-sign-out" onclick = "_adminLogout()"> </i>' +
                    '</div>' +
                    '</div>'
            },

            header: {
                type: "flex",
                //自动占满空间
                className: "flex-auto flex-nowrap",
                items: [
                    {
                        type: "flex",
                        className: "flex-nowrap",
                        items: [{
                            type: "tpl",
                            tpl: '<div><span>欢迎使用</span></div>'
                        }]
                    },
                    {
                        type: "flex",
                        className: "flex-auto flex-nowrap",
                        justify: "flex-end",
                        items: [

                            {
                                type: "service",
                                schemaApi: "HEAD_"
                                // api: '',
                            },

                            {
                                "type": "dropdown-button",
                                className: "ml-3 w-6",
                                // "label": "超级管理员",
                                "trigger": "hover",
                                "level": "link",
                                "icon": "text-dark text-lg fa fa-user",
                                "hideCaret": true,
                                // size:"lg",
                                //  align:"center",
                                "buttons": [
                                    {
                                        icon: "ml-3 fa fa-key",
                                        "type": "button",
                                        "label": "修改密码",
                                        "level": "primary",
                                        "actionType": "dialog",
                                        showErrorMsg:false,
                                        "dialog": {
                                            "title": "修改密码",
                                            "body": {
                                                "type": "form",
                                                "api": 'put:OAK_BASE_API/rbac/updatePwd',
                                                rules: [
                                                    {
                                                        //必须满足的规则
                                                        "rule": "data.password && data.password === data.password2",
                                                        "message": "新密码2次输入不一致",
                                                        "name": [
                                                            "password",
                                                            "password2"
                                                        ]
                                                    }
                                                ],
                                                "body": [
                                                    {
                                                        //https://aisuda.bce.baidu.com/amis/zh-CN/components/form/formitem#%E6%A0%BC%E5%BC%8F%E6%A0%A1%E9%AA%8C
                                                        "type": "input-password",
                                                        "name": "oldPassword",
                                                        "label": "旧密码",
                                                        trimContents: true,
                                                        required: true,
                                                        validations: "minLength:6"
                                                    },
                                                    {
                                                        "type": "input-password",
                                                        "name": "password",
                                                        trimContents: true,
                                                        required: true,
                                                        "label": "新密码",
                                                        validations: "minLength:6"
                                                    },
                                                    {
                                                        "type": "input-password",
                                                        "name": "password2",
                                                        trimContents: true,
                                                        required: true,
                                                        "label": "新密码确认",
                                                        validations: "minLength:6"
                                                    }
                                                ]
                                            }
                                        }
                                    }
                                    // ,
                                    // {
                                    //     "type": "button",
                                    //     "label": "按钮2"
                                    // },
                                    // {
                                    //     "type": "button",
                                    //     "label": "按钮3"
                                    // }
                                ]
                            },

                            {
                                type: "tpl",
                                className: "ml-1",
                                tpl: '<span class="font-normal" style="cursor:pointer;">${user.name}</span>',

                                badge: {
                                    className: "ml-1 mr-5",
                                    id: "home_user_inbox",
                                    mode: "text",
                                    // animation: true,
                                    // size:10,
                                    overflowCount: 9,
                                    text: 22
                                }
                            },

                            {
                                type: "action",
                                //持：ajax、link、url、drawer、dialog、confirm、cancel、prev、next、copy、close。
                                // "actionType": "url",
                                // className:"border-none bg-transparent w-3",
                                // confirmText:"你确定要退出？",
                                //  icon: "fa fa-sign-out",
                                onClick: 'window._adminLogout();',
                                tooltip: "退出登录",

                                body: {
                                    type: "icon",
                                    icon: "ml-8 mr-4 text-lg fa fa-sign-out"
                                }

                            }
                        ]
                    }
                ]

            },

            //   footer: '<div class="p-2 text-center bg-light">底部区域</div>',
            //   asideBefore: '<div class="p-2 text-center">菜单前面区域</div>',
            asideAfter: '<div class="p-2 text-center"></div>'
        };


        function normalizeLink(to, location = history.location) {

            to = to || '';

            if (to && to[0] === '#') {
                to = location.pathname + location.search + to;
            } else if (to && to[0] === '?') {
                to = location.pathname + to;
            }

            const idx = to.indexOf('?');
            const idx2 = to.indexOf('#');
            let pathname = ~idx
                ? to.substring(0, idx)
                : ~idx2
                    ? to.substring(0, idx2)
                    : to;
            let search = ~idx ? to.substring(idx, ~idx2 ? idx2 : undefined) : '';
            let hash = ~idx2 ? to.substring(idx2) : location.hash;

            if (!pathname) {
                pathname = location.pathname;
            } else if (pathname[0] != '/' && !/^https?\:\/\//.test(pathname)) {
                let relativeBase = location.pathname;
                const paths = relativeBase.split('/');
                paths.pop();
                let m;
                while ((m = /^\.\.?\//.exec(pathname))) {
                    if (m[0] === '../') {
                        paths.pop();
                    }
                    pathname = pathname.substring(m[0].length);
                }
                pathname = paths.concat(pathname).join('/');
            }

            return pathname + search + hash;
        }

        function isCurrentUrl(to, ctx) {
            if (!to) {
                return false;
            }
            const pathname = history.location.pathname;
            const link = normalizeLink(to, {
                ...location,
                pathname,
                hash: ''
            });

            if (!~link.indexOf('http') && ~link.indexOf(':')) {
                let strict = ctx && ctx.strict;
                return match(link, {
                    decode: decodeURIComponent,
                    strict: typeof strict !== 'undefined' ? strict : true
                })(pathname);
            }

            return decodeURI(pathname) === link;
        }

        console.log("_APP_ROOT_URL:" + _APP_ROOT_URL)

        let amisInstance = amis.embed(
            '#root',
            app,
            {
                location: history.location
            },
            {
                // watchRouteChange: fn => {
                //   return history.listen(fn);
                // },
                updateLocation: (location, replace) => {
                    location = normalizeLink(location);
                    if (location === 'goBack') {
                        return history.goBack();
                    } else if (
                        (!/^https?\:\/\//.test(location) &&
                            location ===
                            history.location.pathname + history.location.search) ||
                        location === history.location.href
                    ) {
                        // 目标地址和当前地址一样，不处理，免得重复刷新
                        return;
                    } else if (/^https?\:\/\//.test(location) || !history) {
                        return (window.location.href = location);
                    }

                    history[replace ? 'replace' : 'push'](location);
                },
                jumpTo: (to, action) => {
                    if (to === 'goBack') {
                        return history.goBack();
                    }

                    to = normalizeLink(to);

                    if (isCurrentUrl(to)) {
                        return;
                    }

                    if (action && action.actionType === 'url') {
                        action.blank === false
                            ? (window.location.href = to)
                            : window.open(to, '_blank');
                        return;
                    } else if (action && action.blank) {
                        window.open(to, '_blank');
                        return;
                    }

                    if (/^https?:\/\//.test(to)) {
                        window.location.href = to;
                    } else if (
                        (!/^https?\:\/\//.test(to) &&
                            to === history.pathname + history.location.search) ||
                        to === history.location.href
                    ) {
                        // do nothing
                    } else {
                        history.push(to);
                    }
                },
                isCurrentUrl: isCurrentUrl,
                theme: 'cxd',
                // theme: 'antd',
                replaceText: {

                    // window._HOST_ROOT_URL = location.protocol + "//" + location.host;
                    // window._CONTEXT_PATH = getContextPath();
                    // window._APP_ROOT_URL = _HOST_ROOT_URL + _CONTEXT_PATH;
                    // window._OAK_BASE_API = _CONTEXT_PATH + "/com.levin.oak.base/V1/api";

                    //支持配置中的 URL 地址替换
                    HOST: window.location.hostname,
                    APP_ROOT_URL: window._APP_ROOT_URL,
                    CONTEXT_PATH: window._CONTEXT_PATH,
                    OAK_BASE_API: window._OAK_BASE_API
                }
            }
        );

        console.log("_CONTEXT_PATH:" + _CONTEXT_PATH)

        //
        history.listen(state => {
            amisInstance.updateProps({
                location: state.location || state
            });
        });
    })();
</script>
</body>
</html>
