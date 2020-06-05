<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/12
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上机考试系统</title>
    <script type="text/javascript" src="bootstrap/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap-datetimepicker.min.css">
    <script src="bootstrap/js/bootstrap-datetimepicker.js"></script>
    <script src="bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

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
                <li><a href="teacher_home">教师管理面板</a></li>
                <li><a href="teacher_before?username=${user.username}">考前操作</a></li>
                <li class="active"><a href="teacher_within">考中管理</a></li>
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
            考试进行情况
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thread>
                    <tr>
                        <th>考试名称</th>
                        <th>结束时间</th>
                        <th>应参加（人）</th>
                        <th>已登录（人）</th>
                        <th>未登录（人）</th>
                        <th>已提交（人）</th>
                        <th>未提交（人）</th>
                        <th>管理考生信息</th>
                        <th>管理考试通知</th>
                        <th>结束考试</th>
                    </tr>
                </thread>
                <tbody>
                <c:forEach items="${exams}" var="exam">
                    <tr>
                        <c:set var="exam_id" value="${exam.exam_id}"/>
                        <td>${exam.exam_name}</td>
                        <td>${exam.finish_time}</td>
                        <td>${exam.participate_count}</td>
                        <td>${exam.login_count}</td>
                        <td>${exam.unlogin_count}</td>
                        <td>${exam.submit_count}</td>
                        <td>${exam.unsubmit_count}</td>
                        <td><a href="/teacher_manage_student?exam_id=${exam.exam_id}" class="btn btn-primary">管理考生</a>
                        </td>
                        <td><a href="/teacher_manage_message?exam_id=${exam.exam_id}&exam_name=${exam.exam_name}" class="btn btn-primary">管理通知</a>
                        </td>
                        <td><a href="/finish_exam?exam_id=${exam.exam_id}" class="btn btn-danger"
                               onclick="return confirm('是否结束考试？')">结束考试</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="edit_password.jsp"/>
</body>
</html>
