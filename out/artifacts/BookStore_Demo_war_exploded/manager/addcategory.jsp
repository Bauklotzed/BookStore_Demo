<%--
  Created by IntelliJ IDEA.
  User: Zed
  Date: 2017/11/29
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>添加分类</title>
    <meta charset="UTF-8">
</head>
<body>
    <form action="${pageContext.request.contextPath}/manager/CategoryServlet?method=add" method="post">
        分类名称:<input type="text" name="name"><br>
        分类描述:<textarea rows="5" cols="40" name="description"></textarea>
        <input type="submit" value="添加">
    </form>
</body>
</html>
