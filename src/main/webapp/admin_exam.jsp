<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/2
  Time: 23:54
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

        .refreshBox {
            margin-top: 20px;
        }

        .searchBox {
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
            <ul class="nav navbar-nav">
                <li><a href="admin_home.jsp">控制面板</a></li>
                <li><a href="/admin_teacher">教师管理</a></li>
                <li class="active"><a href="/admin_exam">考试清理</a></li>
                <li><a href="/admin_system">系统配置</a></li>
            </ul>
            <ul class="nav navbar-right navbar-nav">
                <li><a href="javascript:void(0);">欢迎，${user.username}</a></li>
                <li><a href="#" data-toggle="modal" data-target="#edit_password">修改口令</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="container " id="row">
    <div class="col-md-3 col-md-offset-1">

        <form role="form" class="refreshBox">
            <div class="form-group col-md-3">
                <button type="submit" class="refreshBtn btn btn-primary" onclick="window.location.reload()">刷新</button>
            </div>
            <div class="checkbox col-md-9">
            </div>
        </form>
    </div>

    <div class="searchBox input-group col-md-3 col-md-offset-7">
        <form action="/selectExam" method="post">
            <div class="form-group">
                <div class="col-md-9">
                    <input type="text" class="form-control " name="condition" value="${condition}"
                           placeholder="请输入考试名称"/>
                </div>
                <div class="col-md-3">
                    <input type="submit" class="btn btn-info btn-search" value="查找"/>
                </div>
            </div>
        </form>
    </div>
    <HR width="80%" color=#987cb9 SIZE=3>

    <table class="table table-striped ">
        <thread>
            <tr>
                <th>考试编号</th>
                <th>考试名称</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>是否结束</th>
                <th>答案已被下载</th>
                <th>清理考试文件</th>
                <th>删除考试</th>
            </tr>
        </thread>
        <tbody>
        <c:forEach items="${exams}" var="exam">
            <tr>
                <td>${exam.exam_id}</td>
                <td>${exam.exam_name}</td>
                <td>${exam.start_time}</td>
                <td>${exam.finish_time}</td>
                <td>${exam.status>=2?"已结束":""}</td>
                <td>${exam.status==3?"已被下载":""}${exam.status==2?"未被下载":""}</td>
                <td><c:if test="${exam.status==3}"><a
                        href='cleanExam?exam_name=${exam.exam_name}&exam_id=${exam.exam_id}&paper_path=${exam.paper_path}&is_admin=1'
                        class="btn btn-warning"
                        onclick="return confirm('是否要清理该考试所有文件？')">清理考试文件</a></c:if>
                    <c:if test="${exam.status==4}"><label>已清理</label></c:if>
                </td>
                <td>
                    <c:if test="${exam.status==4}"><a href="deleteExam?exam_id=${exam.exam_id}&is_admin=1"
                                                      class='btn btn-danger'
                                                      onclick="return confirm('是否要删除该考试所有信息？')">删除该考试</a></c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>