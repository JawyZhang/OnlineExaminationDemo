<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/12
  Time: 20:58
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

        .detailBox {
            width: 150px;
            height: 150px;
            margin-left: auto;
            margin-right: auto;
        }

        .father {
            padding: 10px;
            background: #fff;
            margin-top: 100px;
            margin-left: auto;
            margin-right: 60px;
            width: 250px;
            height: 300px;
            border-radius: 15px;
            text-align: center;
            display: inline-block;
            vertical-align: center;
        }

        .item {
            padding: 20px;
            width: 100%;
            height: 100%;
            border: solid 1px black;
            margin-right: auto;
            margin-left: auto;
        }

        .btn{
            margin-top: 20px;
        }
        .myPanel {
            vertical-align: center;
            margin-right: auto;
            margin-left: auto;
            text-align: center;
            width: 1200px;
            height: 100%;
        }
    </style>
</head>
<body>
<c:if test="${tip != null}">
    <script>
        alert("${tip}");
    </script>
</c:if>
<nav class="navbar navbar-default navbar-fixed-top" role="navigaiton">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">上机考试系统</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active" ><a href="teacher_home">教师管理面板</a></li>
                <li><a href="teacher_before?username=${user.username}">考前操作</a></li>
                <li><a href="teacher_within">考中管理</a></li>
                <li><a href="teacher_after">考后操作</a></li>
            </ul>
            <ul class="nav navbar-right navbar-nav">
                <li><a href="javascript:void(0);">欢迎，${user.username}</a></li>
                <li><a href="#" data-toggle="modal" data-target="#edit_password">修改口令</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="container " id="row" style="margin: auto">
    <div class="myPanel">
        <div class="father">
            <div class="item">
                <div class="detailBox ">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">上传试卷</li>
                        <li class="list-group-item">学生名单导入</li>
                        <li class="list-group-item">开启考试</li>
                    </ul>
                </div>
                <a class="btn btn-primary btn-large" href="teacher_before?username=${user.username}"><i class="icon-comment icon-white"></i>考前操作</a>

            </div>


        </div>
        <div class=" father">
            <div class="item">
                <div class="detailBox">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">查看考试情况</li>
                        <li class="list-group-item">管理学生信息</li>
                        <li class="list-group-item">管理登陆锁定</li>
                        <li class="list-group-item">管理通知消息</li>
                    </ul>
                </div>
                <a class="btn btn-primary btn-large" href="teacher_within"><i class="icon-comment icon-white"></i>考中管理</a>
            </div>

        </div>
        <div class=" father">
            <div class="item">
                <div class="detailBox">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">结束考试</li>
                        <li class="list-group-item">打包下载</li>
                        <li class="list-group-item">导出信息</li>
                        <li class="list-group-item">清理删除考试</li>
                    </ul>
                </div>
                <a class="btn btn-primary btn-large" href="teacher_after"><i class="icon-comment icon-white"></i>考后操作</a>

            </div>


        </div>
    </div>
    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>