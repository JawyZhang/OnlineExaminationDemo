<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/9
  Time: 22:42
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
            background-size: cover;

            background: url("img/background2.jpg") no-repeat fixed center 0;
            -webkit-background-size: cover;
            -o-background-size: cover;
            -moz-background-size: cover;
            -ms-background-size: cover;
        }

        .myPanel{
            margin-top: 20px;
            border-radius: 15px;
            background-color: white;
            height:340px;
            width: 450px;
            margin-left: auto ;
            margin-right: auto ;
        }

        .downloadBox{
            margin-left: auto ;
            margin-right: auto ;
            width: 100%;
            height: 150px;
            text-align: center;

        }
        .uploadBox{
            margin-left: auto ;
            margin-right: auto ;
            width: 100%;
            height: 150px;
            text-align: center;

        }
        .image{
            width: 30px;
            height: 30px;
            margin-top: 60px;
        }
        .btn{
            margin-top: 60px;
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
<%--                <li><a href="javascript:void(0);">欢迎，${user.username}</a></li>--%>
                <li><a href="#" data-toggle="modal" data-target="#edit_password">修改口令</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="container " id="row" style="margin: auto">
    <div class="row">
        <div class="myPanel">
            <a href="student_home.jsp" style="margin: 10px"><<返回</a>
            <div class="downloadBox">
                <img src="img/download.png" class=" img-rounded  image">
                <button class="btn  btn-primary">下载试卷</button>
            </div>
            <HR width="80%" color=#987cb9 SIZE=3 >
            <div class="uploadBox">
                <img src="img/upload.png" class=" img-rounded  image">
                <button class="btn  btn-primary">上传试卷</button>
            </div>
        </div>
    </div>
<jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>
