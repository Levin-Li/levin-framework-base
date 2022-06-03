<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <title>${appName}管理后台登录</title>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery.particleground.min.js" type="text/javascript"></script>
</head>

<body style="overflow: hidden">
<div class="frame_register" style="height: 948px;">
    <div class="main_register">
        <div class="register_bg" style="height: 948px;">
            <img width="100%" src="img/bg.jpg">
        </div>
        <div class="canvas_bg" id="canvasBg">
            <canvas class="pg-canvas" width="1149" height="948"></canvas>
        </div>

        <!-----注册页面---->
        <div class="logo_window" style="height: 948px;">
            <div class="logo"></div>
            <div class="logo_window1">
                <form name="userLoginActionForm" id="userLoginActionForm" method="POST" action="" target="_parent">
                    <div class="logo_window_L" style="margin: 0 auto;float: inherit;">
                        <span class="logo-form-title">用户登录</span>
                        <div class="register_form2">
                            <input class="user-name" id="username" name="account" type="text" value="用户名" maxlength="20"
                                   onfocus="userInter('F','username',this);" onblur="userInter('B','username',this)">
                        </div>
                        <div class="register_form2" style="position:relative;height:50px">
                            <input class="user-pass" type="text" id="p_t" name="p_t" value="密码" onfocus="hide_pw()"
                                   style="z-index:2;position:absolute;">
                            <input class="user-pass" type="password" id="userpwd" name="password" maxlength="32"
                                   onblur="check_pw();" style="z-index:1;position:absolute;">
                        </div>
                        <div class="register_form3">
                            <input id="validatecode" name="verificationCode" type="text" placeholder="验证码" maxlength="6"
                                   onfocus="check_yzm(1)" onblur="check_yzm(2);">
                            <a href="javaScript:getVcode2()" title="点击，换一张！">
                                <img id="vcodesrc" name="vcodesrc" border="0" width="120" height="40" alt="点击，换一张！">
                            </a>
                        </div>
                        <!--<div class="no_pwd">
                            <a href="/get_password_new.php" target="_blank">忘记密码？</a>
                        </div>-->
                        <div class="button_a">
                            <a class="login-btn" style="margin: 0 auto;display: block;" id="login_bt" name="login_bt"
                               onclick="logincheck()">登录</a>
                        </div>
                    </div>

                    <input type="hidden" id="is_cs" name="is_cs" value="0">
                    <input type="hidden" id="is_get" name="is_get" value="1">
                    <input type="hidden" id="show_msg" name="show_msg" value="">
                    <input type="hidden" id="jz" name="jz" value="0">
                    <input type="hidden" id="no_new" name="no_new" value="0">
                    <input type="hidden" id="gobuy" name="gobuy" value="0">
                </form>
            </div>
        </div>
    </div>
</div>
<div class="windows_bg"></div>
<script>
    $(function () {
        var canvasBg = $('#canvasBg');
        if (canvasBg.length) {
            canvasBg.particleground({
                dotColor: '#fff',
                lineColor: '#f0f0f0'
            });
        }
    });

    function aa() {
        var e = window.screen.width;
        e >= 1366 ?
            (1366 == e && $(".Guide_con1").addClass("wrapper_1366"))
            : (e >= 1280 ? $(".Guide_con1").addClass("wrapper_1280") : $(".Guide_con1").addClass("wrapper_1024"))
    }

    function userInter(e, a, t) {
        "username" == a && ("F" == e ? "用户名" == t.value && (t.value = "") : "B" == e && "" == t.value && (t.value = "用户名"))
    }

    function check_yzm(e) {
        var a = $("#validatecode").val();
        1 == e && ("验证码" == a && $("#validatecode").val(""), $("#vcodesrc").show()), 2 == e && "" == a && $("#validatecode").val("验证码")
    }

    function hide_pw() {
        $("#p_t").hide(), $("#userpwd").focus()
    }

    function check_pw() {
        var e = $("#userpwd").val();
        "" == e && $("#p_t").show()
    }

    function logincheck() {
        var e = $("#username"),
            a = $("#userpwd"),
            t = $("#validatecode");
        return "" == $.trim(e.val()) || "用户名" == e.val() ? (alert("用户名不能为空！"), e.focus(), !1) : "" == a.val() ? (hide_pw(), alert("用户密码不能为空！"), !1) : "" == t.val() || "验证码" == t.val() ? (alert("请填写验证码！"), t.focus(), !1) : login()
    }

    function login() {

        if (window.isLogining) {
            return
        }

        var e = $("#username"),
            loginBtn = $("#login_bt"),
            a = $("#userpwd"),
            t = $("#validatecode");

        window.isLogining = true;

        $.ajax({
            url: "${loginApi}",
            type: "POST",
            data: {account: e.val(), password: a.val(), verificationCode: t.val()},
            error: function (xhr, status, error) {
                window.isLogining = false;
                alert(error)
                console.log(error)
            },
            success: function (result) {
                window.isLogining = false;
                if (result.successful) {
                    location.reload()
                } else if (result.msg) {
                    if (result.msg.indexOf("验证码") !== -1) {
                        getVcode2()
                        t.focus()
                        t.attr("value", '');//清空内容
                    }
                    alert(result.msg)
                } else {
                    alert("发生未知错误")
                }

                console.log(result)
            }
        });
    }

    function getVcode2() {
        document.getElementById("vcodesrc").src = "${captchaApi}?" + (new Date()).getTime()
    }

    $(document).ready(function () {
        $("#userpwd,#validatecode").keydown(function (e) {
            if (13 == e.keyCode)
                return logincheck(), !1
        })
    }), $(function () {
        aa();
        getVcode2();
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

</body>

</html>