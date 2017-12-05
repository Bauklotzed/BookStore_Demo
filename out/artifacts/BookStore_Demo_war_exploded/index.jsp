<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
  <title>前台首页</title>
  <meta charset="utf-8">
</head>

<frameset rows="17%,*">
    <frame src="${pageContext.request.contextPath }/client/head.jsp" name="head">
    <frame src="${pageContext.request.contextPath }/client/IndexServlet?method=getAll" name="body">
</frameset>

</html>
