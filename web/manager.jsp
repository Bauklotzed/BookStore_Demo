<%--
  Created by IntelliJ IDEA.
  User: Zed
  Date: 2017/11/29
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>后台首页</title>
    <meta charset="UTF-8">
</head>
<frameset rows="15%,*">
    <frame src="${pageContext.request.contextPath}/manager/left.jsp" name="left">
    <frame src="${pageContext.request.contextPath}/manager/right.jsp" name="right">
</frameset>
</html>
