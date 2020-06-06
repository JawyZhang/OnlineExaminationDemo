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
<nav class="navbar navbar-default navbar-fixed-top" role="navigaiton">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">上机考试系统</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active" ><a href="teacher_before.jsp">考前操作</a></li>
                <li><a href="teacher_within.jsp">考中管理</a></li>
                <li><a href="teacher_after.jsp">考后操作</a></li>
            </ul>
            <ul class="nav navbar-right navbar-nav">
                <li><a href="javascript:void(0);">欢迎，${user.username}</a></li>
                <li><a href="#" data-toggle="modal" data-target="#edit_password">修改口令</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
            <c:forEach items="${student.course}" var="course">
                        <tr>
                            <td>#{course.course_name}</td>
                            <td><input type="checkbox" class="select" name="${teacher.id}"/></td>
                            <td>${teacher.teacher_id}</td>
                            <td>${teacher.username}</td>
                            <td><a href="#" class="edit" name="${teacher.id}" data-toggle="modal" data-target="#edit_teacher">修改</a>
                            </td>
                            <td><a href="/delete_teacher?id=${teacher.id}">删除</a></td>
                            <td>
                                <c:if test="${teacher.is_admin=='on'}">
                                    <a href="/cancelAdmin?id=${teacher.id}" class="button btn-warning">取消管理员</a>
                                </c:if>
                                <c:if test="${teacher.is_admin=='null'}">
                                    <a href="/asAdmin?id=${teacher.id}" class="button btn-primary">设为管理员</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
        </div>

    </div>
</nav>
<div class="container " id="row" style="margin: auto">

    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>