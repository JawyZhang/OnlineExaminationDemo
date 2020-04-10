<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/9
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上机考试系统</title>
    <script type="text/javascript" src="bootstrap/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <style>
        body {
            padding-top: 50px;
            background: url("img/background2.jpg")  no-repeat center 0px;
            background-size: cover;



            background-position: center 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
            -webkit-background-size: cover;
            -o-background-size: cover;
            -moz-background-size: cover;
            -ms-background-size: cover;
        }

        .panel{
            margin-top: 20px;
        }


    </style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigaiton">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">上机考试系统</a>
        </div>

        <div class="collapse navbar-collapse">

            <ul class="nav navbar-right navbar-nav">
                <li><a href="javascript:void(0);">欢迎，${user.username}</a></li>
                <li><a href="#" data-toggle="modal" data-target="#edit_password">修改口令</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="container " id="row" style="margin: auto">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">
                正在进行的考试
            </h3>
        </div>
        <table class="table">
            <th class="col-md-3">考试名称</th><th class="col-md-3">开始时间</th><th class="col-md-3">剩余时间</th><th>提交情况</th>

        </table>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">
                已结束的考试
            </h3>
        </div>
        <table class="table">
            <th class="col-md-3">考试名称</th><th class="col-md-3">开始时间</th><th class="col-md-3">结束时间</th><th>提交情况</th>

        </table>
    </div>
    </div>
    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>

