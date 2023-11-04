<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8"/>
    <#--    <meta name="renderer" content="webkit">-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <title>${appName}登录</title>
    <link type="image/x-icon" rel="shortcut icon" href="${appLogo}"/>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>

    <#--    <link rel="stylesheet" type="text/css" href="css/toastr.min.css">-->
    <#--    <script src="js/toastr.min.js"></script>-->

    <#-- 警告框   http://www.bootstrapmb.com/tag/xiaoxitishi-->
    <#--    toastr.success('Hello world!', 'Toastr fun!'); /success 、 error、info、warning 第二个参数为标题可以省略-->

    <#--    //关闭警告框-->
    <#--    toastr.clear([toast]);-->

    <#--    //获取当前警告框-->
    <#--    toastr.active();-->

    <#--    //刷新警告框 第二个参数为超时时间-->
    <#--    toastr.refreshTimer(toast, 5000);-->


    <link rel="stylesheet" type="text/css" href="css/simple.message.css">
    <script src="js/simple.message.min.js"></script>

    <style type="text/css">

    </style>
</head>

<body style="overflow: hidden">

<div class="frame_register" style="height: 948px;">
    <div class="main_register">
        <div class="register_bg" style="height: 948px;">
            <img width="100%" src="login_1/bg.png">
        </div>
        <div class="canvas_bg" id="canvasBg">
            <canvas class="pg-canvas" width="1149" height="948"></canvas>
        </div>

        <!-----注册页面---->
        <div class="logo_window" style="height: 948px;">

            <div class="logo_bar">
                <img src="${appLogo}" alt=""/>
                <div class="title_bar">
                    <h1>${appName}</h1>
                </div>
            </div>

            <div class="logo_window1">
                <form name="userLoginActionForm" id="userLoginActionForm" method="POST" action="" target="_parent">
                    <div class="logo_window_L"
                         style="margin: 0 auto;float: inherit;box-shadow: 0 2px 6px rgba(92, 110, 136, .32);">
                        <span class="logo-form-title">用户登录</span>

                        <div class="register_form2">
                            <input placeholder="帐号/手机号/邮箱" class="user-name" onblur="updateCode(this)"
                                   id="account" type="text" maxlength="32"/>
                        </div>

                        <#if !enableSmsVerificationCode>
                            <div class="register_form2" style="position:relative;height:50px;margin-top:0.5rem;">
                                <input placeholder="密码" class="user-pass" type="password"
                                       id="password" maxlength="32"/>
                            </div>
                        </#if>

                        <div class="register_form3" style="margin-top:0.5rem;">
                            <input placeholder="验证码" id="verificationCode" type="text"
                                   style="width:250px" maxlength="${verificationCodeLen}"/>
                            <#--   freemark 语法-->
                            <#if enableSmsVerificationCode>
                                <a id="smsSendBtn" class="button_btn" style="width:100px;color:#f8f8f8;"
                                   title="点击发送验证码"
                                   onclick="updateCode(null,true)">发送验证码</a>
                            <#else>
                                <a href="javaScript:updateCode()" style="line-height:40px;text-align:center;"
                                   title="点击，换一张！">
                                    <span id="codeImgFailover">点击，换一张</span>
                                    <img id="codeImg" border="0" width="100" height="40" alt="点击，换一张！">
                                </a>
                            </#if>
                        </div>

                        <!--<div class="no_pwd">
                            <a href="/get_password_new.php" target="_blank">忘记密码？</a>
                        </div>-->

                        <div class="button_a">
                            <a class="login-btn" style="margin: 0 auto;display: block;" id="login_btn" title="点击登录"
                               onclick="doLogin()">登 录</a>
                        </div>

                    </div>

                    <#--                    <input type="hidden" name="is_cs" value="0"> -->
                    <#--                    <input type="hidden" id="is_get" name="is_get" value="1"> -->
                    <#--                    <input type="hidden" id="show_msg" name="show_msg" value=""> -->
                    <#--                    <input type="hidden" id="jz" name="jz" value="0"> -->
                    <#--                    <input type="hidden" id="no_new" name="no_new" value="0"> -->
                    <#--                    <input type="hidden" id="gobuy" name="gobuy" value="0"> -->

                </form>
            </div>

            <h3 class="info_bar">版权所有 ${(tenant.name)!} @Copyright 2023</h3>

        </div>
    </div>
</div>
<div class="windows_bg"></div>
<script>

    var enableSmsVerificationCode = ${enableSmsVerificationCode?string('true', 'false')};

    var verificationCodeDurationOfMinutes = ${verificationCodeDurationOfMinutes};

    var verificationCodeLen = ${verificationCodeLen};

    function toast_simple(mess) {
        var str = '<div class="mess"><span></span></div>';
        $("body").append(str);
        $(".mess").fadeIn().find("span").html(mess);
        setTimeout(function () {
            $(".mess").fadeOut();
        }, 2000)
    }

    function showError(msg) {
        // toastr.error(msg, '错误');
        // toastr.error(msg, '错误');

        Qmsg['error'](msg);
    }

    function toast(msg) {
        //toastr.success('Hello world!', 'Toastr fun!'); /success 、 error、info、warning 第二个参数为标题可以省略
        // toastr.info(msg, '提示');
        // toastr.info(msg, '提示');

        Qmsg['info'](msg);
    }

    function checkInput(inputView, reqLen, prompt) {

        if ((typeof inputView) === "string") {
            inputView = $(inputView)
        }

        let inputValue = inputView.val().trim();

        if (!inputValue || inputValue === "") {

            let placeholder = "" + inputView.attr('placeholder');

            if (!placeholder.startsWith("请输入")) {
                placeholder = placeholder + "不能为空！";
            }

            showError(prompt || placeholder);

            inputView.focus();

            return false;
        }

        if (reqLen && inputValue.length !== reqLen) {

            showError(prompt || "长度必须为" + reqLen + "位");

            inputView.focus();

            return false;
        }


        return inputValue;
    }

    var oldAccount = "";

    function updateCode(view, forceSendSms) {

        if (view) {
            //如果没有变化
            if (view.value.trim() === ""
                || oldAccount === view.value) {
                return
            } else {
                console.log("new account:" + oldAccount + " --> " + view.value)
                oldAccount = view.value;
            }
        }

        console.log($("#smsSendBtn").text())

        //如果是短信验证码
        if (enableSmsVerificationCode) {
            if (!forceSendSms || isSendSmsBtnDisable())
                //如果还是禁用，直接返回
                return;
        }

        var account = checkInput("#account");

        //如果是短信验证码
        if (account) {
            if (enableSmsVerificationCode) {
                sendSmsCode();
            } else {
                $("#codeImgFailover").hide()
                $("#codeImg").show()
                document.getElementById("codeImg").src = "${captchaApi}?account=" + account + "&t=" + (new Date()).getTime();
            }
        }
    }

    var stopSmsSending = false;

    function sending(seconds, clear) {

        if (clear) {
            stopSmsSending = false;
        }

        // var smsSendBtn = $("#smsSendBtn");

        if (seconds < 1 || stopSmsSending) {
            console.log("sending...stoped")
            enableSmsBtn();
            return;
        }

        disableSmsBtn(seconds);

        window.setTimeout("sending(" + (seconds - 1) + ")", 1000);
    }

    function isSendSmsBtnDisable() {
        return $("#smsSendBtn").text() !== "发送验证码";
    }

    function enableSmsBtn(stop) {

        stopSmsSending = stop

        var smsSendBtn = $("#smsSendBtn")
        smsSendBtn.text("发送验证码")
        smsSendBtn.attr('class', "button_btn")
    }

    function disableSmsBtn(seconds) {
        var smsSendBtn = $("#smsSendBtn")
        smsSendBtn.text("已发送 " + seconds + "s")
        smsSendBtn.attr('class', "button_btn_disabled")
    }

    function sendSmsCode() {

        if (window.isLogining) {
            return
        }

        console.log("1、sendSmsCode...")

        sending(90, true)

        var account = $("#account").val();

        window.isLogining = true;

        console.log("2、sendSmsCode...")

        $.ajax({
            url: "${sendSmsCodeApi}",
            type: "GET",
            data: {account},
            error: function (xhr, status, error) {

                window.isLogining = false;

                console.log(error)

                enableSmsBtn(true)

                showError(error)

            },
            success: function (result) {

                window.isLogining = false;

                console.log(result);

                if (result.successful) {

                    var verificationCodeView = $("#verificationCode");

                    let data = result.data;

                    if (typeof data === "string") {

                        if (data.startsWith("mock:")) {
                            //自动填充模拟的验证码
                            verificationCodeView.val(data.substring(5));
                        } else {
                            toast(data)
                        }
                    } else {
                        toast("短信已发送")
                    }

                    verificationCodeView.focus();

                } else if (result.msg) {
                    enableSmsBtn(true);
                    showError(result.msg);
                } else {
                    enableSmsBtn(true);
                    showError("短信发生失败2");
                }

            }
        });
    }

    function doLogin() {
        return checkInput("#account")
            //短信验证码，可以不用密码
            && (enableSmsVerificationCode || checkInput("#password"))
            && checkInput("#verificationCode", verificationCodeLen, "验证码必须为" + verificationCodeLen + "位")
            && login();
    }

    function login() {

        if (window.isLogining) {
            return
        }

        var verificationCodeType = enableSmsVerificationCode ? "sms" : "captcha";

        var account = $("#account").val();

        //短信验证码不要密码
        var password = enableSmsVerificationCode ? null : $("#password").val();

        var verificationCodeView = $("#verificationCode");
        var verificationCode = verificationCodeView.val();

        window.isLogining = true;

        $.ajax({
            url: "${loginApi}",
            type: "POST",
            data: {account, password, verificationCode, verificationCodeType},
            error: function (xhr, status, error) {
                window.isLogining = false;
                console.log(error)
                showError(error)
            },
            success: function (result) {

                window.isLogining = false;

                console.log(result);

                if (result.successful) {
                    location.reload()
                } else if (result.msg) {
                    if (result.msg.indexOf("验证码") !== -1) {
                        verificationCodeView.val('');//清空内容
                        updateCode();
                        verificationCodeView.focus();
                    }
                    showError(result.msg);
                } else {
                    showError("发生未知错误");
                }
            }
        });
    }


    function autoSize() {

        var width = window.screen.width;

        width >= 1366 ?
            (1366 === width && $(".Guide_con1").addClass("wrapper_1366"))
            : (width >= 1280 ? $(".Guide_con1").addClass("wrapper_1280") : $(".Guide_con1").addClass("wrapper_1024"))
    }

    $(document).ready(function () {

        $("#codeImg").hide()

        $("#account").focus();

        $("#account,#password,#verificationCode").keydown(function (e) {
            if (13 === e.keyCode)
                return doLogin(), !1
        });
    });

    $(function () {
        var canvasBg = $('#canvasBg');
        if (canvasBg.length && canvasBg.particleground) {
            canvasBg.particleground({
                //点和线性的颜色
                dotColor: '#fff',
                lineColor: '#f0f0f0'
            });
        }
    });

    //
    $(function () {
        autoSize();
        //updateCode();
        var e = $(window).height();
        $(window).height();
        $(".frame_register").css("height", e)
            , $(".main_register1").css("height", e)
            , $(".register_bg").css("height", e)
            , $(".web_L").css("height", e)
            , $(".Guide_l").css("height", e)
            , $("#class_max").css("height", e)
            , $(".register_c").css("height", e)
            , $(".logo_window").css("height", e)
            , $(".or_div").css("height", e)
    });

</script>

<script>
    //先定义画布样式名称
    _DynamicParticleEffectClass = "pg-canvas";
</script>

<#--    动态效果脚本1 -->
<#--    <script src="js/jquery.particleground.min.js" type="text/javascript"></script>-->

<#--    动态效果脚本2 -->
<script src="js/simple.particle.effect.js" type="text/javascript"></script>

</body>

</html>
