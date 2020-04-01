<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
    <script type="text/javascript" src="bootstrap/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <title>登录页</title>
    <script type="text/javascript">
        $(function () {

        })
    </script>
</head>
<body style="background-image: url('img/background.jpg'); background-repeat: no-repeat; background-size: 100% 100%; background-attachment: fixed;">
<div class="container">
    <div class="jumbotron">
        <center>
            <h1>欢迎来到上机考试管理系统</h1>
        </center>
    </div>
    <div class="col-md-6 col-md-push-3" style="background: rgba(114,114,114,0.8);padding: 28px;">
        <form action="login" class="form-horizontal form-group-lg">
            <div class="form-group"><label for="username" class="col-sm-3 control-label" style="font-size: 1.5em;">用户名：</label>
                <div class="col-sm-8"><input type="text" class="form-control" id="username" name="username" /></div>
            </div>
            <div class="form-group" style="margin-top: 30px;"><label for="password" class="col-sm-3 control-label" style="font-size: 1.5em;">密 &nbsp; &nbsp;码：</label>
                <div class="col-sm-8"><input type="text" class="form-control" id="password" name="password" /></div>
            </div>
            <div class="form-group text-center" style="margin-top: 30px;">
                <input type="submit" class="btn btn-primary btn-lg" style="padding-left: 30px;padding-right: 30px;" value="登录" />
            </div>
        </form>
    </div>
</div>
<div class="col-md-12 text-center" style="font-size: 1.5em;position: fixed;bottom: 0;background: rgba(188,188,188,0.5);">上机考试管理系统 &copy; 2020</div>
</body>
</html>
<%--
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
        <c:otherwise><h1>前台到后台代码不通</h1>
            <br/>
            <a class="btn btn-info" href="show">测试数据库</a>
        </c:otherwise>
    </c:choose>
    <h1>从数据库查询到的项目组成员为：</h1>
    <c:forEach items="${users}" var="user">
        <h1>${user.username}</h1>
    </c:forEach>
    <button class="btn btn-primary">测试JS</button>
    <a class="btn btn-default" href="main">测试页面跳转</a></div>
</body>
</html>
--%>
