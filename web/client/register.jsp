<%--
  Created by IntelliJ IDEA.
  User: Zed
  Date: 2017/11/29
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>注册表单</title>
    <meta charset="UTF-8">
</head>
<body style="text-align: center;">
    <form action="${pageContext.request.contextPath}/client/RegisterServlet" method="post">
        用户名:<input type="text" name="username"><br>
        密码:<input type="password" name="password"><br>
        电话:<input type="text" name="phone"><br>
        手机:<input type="text" name="cellphone"><br>
        邮箱:<input type="text" name="email"><br>
        住址:<input type="text" name="address"><br>
        <input type="submit" value="注册">
    </form>

</body>
</html>
