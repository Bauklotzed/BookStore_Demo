<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zed
  Date: 2017/11/29
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>列出所有分类</title>
    <meta charset="UTF-8">
</head>
<body style="text-align: center;">
    <h2>分类信息</h2>
    <table border="1" width="50%" align="center">
        <tr style="text-align: center;">
            <td>分类名称</td>
            <td>分类描述</td>
            <td>操作</td>
        </tr>
        <c:forEach var="c" items="${categories}">
            <tr style="text-align: center;">
                <td>${c.name}</td>
                <td>${c.description}</td>
                <td>
                    <a href="#">删除</a>
                    <a href="#">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
