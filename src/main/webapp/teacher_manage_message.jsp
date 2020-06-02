<%--
  Created by IntelliJ IDEA.
  User: 16422
  Date: 2020/6/2
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理考试通知</title>
    <script type="text/javascript" src="bootstrap/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <style>
        body {
            padding-top: 50px;
            background: url("img/background2.jpg") no-repeat center 0px;
            background-size: cover;


            background-position: center 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
            -webkit-background-size: cover;
            -o-background-size: cover;
            -moz-background-size: cover;
            -ms-background-size: cover;
        }

        .table {

            background-color: ghostwhite;

        }

        .newBox {
            margin-top: 20px;
        }

        .checkbox {

            zoom: 120%;
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
            <ul class="nav navbar-nav">
                <li><a href="teacher_home">教师管理面板</a></li>
                <li class="active"><a href="teacher_before?username=${user.username}">考前操作</a></li>
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
    <div class="panel panel-default" style="margin-top: 20px">
        <div class="panel-heading">
            通知管理
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thread>
                    <tr>
                        <th>考试名称</th>
                        <th>查看通知列表</th>
                        <th>发送新通知</th>
                    </tr>
                </thread>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="edit_password.jsp"/>
</body>
</html>
