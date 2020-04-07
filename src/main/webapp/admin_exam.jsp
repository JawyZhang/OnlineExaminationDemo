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

        .refreshBox{
            margin-top: 20px;
        }
        .searchBox{
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
                <li ><a href="admin_home.jsp">控制面板</a></li>
                <li ><a href="admin_teacher.jsp">教师管理</a></li>
                <li class="active"><a href="admin_exam.jsp">考试清理</a></li>
                <li><a href="admin_system.jsp">系统配置</a></li>
            </ul>
            <ul class="nav navbar-right navbar-nav">
                <li><a href="javascript:void(0);">欢迎，${user.username}</a></li>
                <li><a href="#" data-toggle="modal" data-target="#edit_password">修改口令</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="container " id="row" >
    <div class="col-md-3 col-md-offset-1">

        <form role="form" class="refreshBox">
            <div class="form-group col-md-3">
                <button type="submit" class="refreshBtn btn btn-primary">刷新</button>
            </div>
            <div class="checkbox col-md-9">
                <label>
                    <input type="checkbox">仅显示已结束的考试
                </label>
            </div>
        </form>
    </div>

    <div class="searchBox input-group col-md-3 col-md-offset-8">
        <input type="text" class="form-control"placeholder="请输入考试名称或考试编号" />
        <span class="input-group-btn">
               <button class="btn btn-info btn-search">查找</button>
            </span>
    </div>
    <HR  width="80%" color=#987cb9 SIZE=3>

    <table class="table table-striped " >
        <thread>
            <tr>
                <th>考试编号</th>
                <th>考试名称</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>是否结束</th>
                <th>清理</th>
            </tr>
        </thread>
        <tbody>
        <c:forEach items="${exams}" var="exam">
            <tr>
                <td>${exam.id}</td>
                <td>${exam.name}</td>
                <td>${exam.beginTime}</td>
                <td>${exam.finishTime}</td>
                <td>${exam.isFinished}</td>
                <td><button   href="#" class="button btn-primary">清理</button> </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>