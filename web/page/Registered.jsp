<%--
  Created by IntelliJ IDEA.
  User: ttc
  Date: 17-12-28
  Time: 上午11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>


</head>
<body>

<div>

    <form action="/ServletMainPage" method="post" id="id" >  <%--连接到登陆页--%>
        <input type="text" name="username" placeholder="手机号或邮箱">

        <input type="password" class="input-wrapper border" name="password" placeholder="密码">

        <input type="submit" id="submit">
    </form>
</div>
</body>
</html>
