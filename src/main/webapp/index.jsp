<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
    <script type="text/javascript" src="bootstrap/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <title>首页</title>
    <script type="text/javascript">
        $(function () {
            $("button").click(function () {
                alert("JS引用成功");
            })
        })
    </script>
</head>
<body>
<div style="width: 600px;margin: 0 auto">
    <c:choose>
        <c:when test="${users != null}"><h1>前台到后台代码正常</h1></c:when>
        <c:otherwise><h1>前台到后台代码不通</h1></c:otherwise>
    </c:choose>
    <h1>从数据库查询到的项目组成员为：</h1>
    <c:forEach items="${users}" var="user">
        <h1>${user.username}</h1>
    </c:forEach>
    <button class="btn btn-primary" onclick="test()">测试JS</button>
    <a class="btn btn-info" href="main">测试页面跳转</a></div>
</body>
</html>