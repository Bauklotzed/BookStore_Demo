<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zed
  Date: 2017/11/29
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>添加图书</title>
    <meta charset="UTF-8">
</head>
<body>
    <!-- form 中Enctype=multipart/form-data 的作用:http://zkliqiang.iteye.com/blog/779285 -->
    <form action="${pageContext.request.contextPath}/manager/BookServlet?method=add" method="post" enctype="multipart/form-data">
        <table frame="border" width="50%">
            <tr>
                <td>图书名称</td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td>作者</td>
                <td>
                    <input type="text" name="author">
                </td>
            </tr>
            <tr>
                <td>售价</td>
                <td>
                    <input type="text" name="price">
                </td>
            </tr>
            <tr>
                <td>图片</td>
                <td>
                    <input type="file" name="file">
                </td>
            </tr>
            <tr>
                <td>图书描述</td>
                <td>
                    <textarea rows="5" cols="40" name="description"></textarea>
                </td>
            </tr>
            <tr>
                <td>所属分类</td>
                <td>
                    <select name="category_id">
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="reset" value="清空">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
