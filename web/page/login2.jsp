<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="../css/login.css">
<script >

</script>
</head>
<body>
<div class="form">
    <table class="main-container">
        <tr>
            <td colspan="2">
                <h1 class="logo-big">知乎</h1>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <h2 class="subtitle">${Success}</h2>
            </td>${Success1}
        </tr>

        <tr>
            <td colspan="2">
                <h2 class="subtitle" id="h2"></h2>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="group-inputs">
                    <div id="haha"></div>

                    <!--添加表单标记-->
                    <form action="/ServletRegister" id="userinfo" method="post">
                        <input type="text" name="name"
                               class="input-wrapper" id="username"
                               placeholder="手机号或邮箱">
                        <span class="usernametips" id="nametips"></span>

                        <input type="password" name="password"
                               class="input-wrapper border" id="password"
                               placeholder="密码">
                        <span class="passwordtips" id="passwordtips"></span>

                        <input type="text" name="captcha"
                               class="input-wrapper border no-border-b" id="captcha"
                               placeholder="验证码">
                        <span class="yzmtips" id="yzmtips"></span>

                        <img src="../images/captcha.gif" alt="验证码"
                             class="captcha" width="100" height="38">

                        <!--添加表单标记-->
                    </form>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <!-- 点击登录按钮时，提交表单  form="userinfo" -->
                <button type="submit" id="button" form="userinfo" class="sign" >登录</button>

                <!--其他提交方式-->
                <!--<input type="submit" value="登录" class="sign" >-->
                <!--button标记不加type属性，默认提交表单-->
                <!--<button class="sign" form="userinfo">登录</button>-->
            </td>
        </tr>

        <tr>
            <td>
                <a href="register.html" class="nav-slider right-side">注册</a>
            </td>
            <td>
                <a href="#" class="nav-slider left-side">修改密码</a>
            </td>
        </tr>
    </table>
    <footer>
        <span>© 2017 知乎</span>
    </footer>
</div>
</body>
</html>
