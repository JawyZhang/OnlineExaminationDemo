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
                <li><a href="teacher_within">考中管理</a></li>
                <li class="active"><a href="teacher_after">考后操作</a></li>
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
            考试详情
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>考试名称</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建人</th>
                    <th>状态</th>
                    <th>打包下载考生答案</th>
                    <c:if test="${system.teacherClear==1||user.username=='admin'||user.is_admin=='on'}">
                        <th>清理考试文件</th>
                        <th>删除考试</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${exams}" var="exam">
                    <tr>
                        <td>${exam.exam_name}</td>
                        <td>${exam.start_time}</td>
                        <td>${exam.finish_time}</td>
                        <td>${exam.creater}</td>
                        <td>${exam.status>=2?"已结束":exam.status==1?"已开启":exam.status==0?"已创建":""}</td>
                        <td>
                            <c:if test="${exam.status==2||exam.status==3}"><a
                                    href="downloadAllAnswers?exam_name=${exam.exam_name}&exam_id=${exam.exam_id}"
                                    class="btn btn-primary"<c:if test="${exam.status==2}"> onclick="reload()"</c:if>>打包下载</a></c:if>
                            <c:if test="${exam.status==4}"><label>文件已被清理</label></c:if>
                        </td>
                        <c:if test="${system.teacherClear==1||user.username=='admin'||user.is_admin=='on'}">
                            <td>
                                <c:if test="${exam.status==3}"><a
                                        href='cleanExam?exam_name=${exam.exam_name}&exam_id=${exam.exam_id}&paper_path=${exam.paper_path}'
                                        class="btn btn-warning"
                                        onclick="return confirm('是否要清理该考试所有文件？')">清理考试文件</a></c:if>
                                <c:if test="${exam.status==4}"><label>已清理</label></c:if>
                            </td>
                            <td>
                                <c:if test="${exam.status==4}"><a href="deleteExam?exam_id=${exam.exam_id}"
                                                                  class='btn btn-danger'
                                                                  onclick="return confirm('是否要删除该考试所有信息？')">删除该考试</a></c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <script>
                function reload() {
                    setTimeout("window.location.reload()", 1000);
                }
            </script>
        </div>
    </div>
</div>
<jsp:include page="edit_password.jsp"/>
</body>
</html>
