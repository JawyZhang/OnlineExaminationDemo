<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 16422
  Date: 2020/5/30
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理考生信息</title>
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
<h1>通过Excel文件批量添加考生</h1>
<form action="/addStudentByExcel" method="post" enctype="multipart/form-data">
    <input type="hidden" name="exam_id" value="${exam_id}"/>
    <label>请保证Excel文件为2003版的，程序只会读取前三列，请保证前三列的按"学号-姓名-班级"的方式排列，表头可有可无，即文件后缀名为xls的Excel文件！！！</label><br>
    <input type="file" name="filename" required/>
    <input type="submit" value="添加">
</form>
<h1>通过手动输入逐个添加</h1>
<form action="/addStudent" method="post">
    <input type="hidden" name="exam_id" value="${exam_id}"/>
    <label for="stu_no">学号：</label>
    <input id="stu_no" name="stu_no" placeholder="请输入学号" required/>
    <label for="username">姓名：</label>
    <input id="username" name="username" placeholder="请输入姓名" required/>
    <label for="class_room">班级：</label>
    <input id="class_room" name="class_room" placeholder="请输入班级" required/>
    <input type="submit" value="添加"/>
</form>
<p>生成的学生账号会以姓名作为用户名，学号后六位作为默认密码(不足六位前面补零)</p>
<table>
    <thead>
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>班级</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageInfo.list}" var="student">
        <tr>
            <td>${student.stu_no}</td>
            <td>${student.username}</td>
            <td>${student.class_room}</td>
            <td><a href="deleteExamStudent?stu_id=${student.id}&exam_id=${exam_id}" class="btn btn-danger">删除该考生</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>当前页为：${pageInfo.pageNumber}，共${pageInfo.totalPage}页，数据总条数：${pageInfo.total}</p>
<c:forEach begin="1" end="${pageInfo.totalPage}" varStatus="status">
    <c:if test="${pageInfo.pageNumber == status.count}"><a href="javascript:void(0);"
                                                           class="btn btn-default">${status.count}</a></c:if>
    <c:if test="${pageInfo.pageNumber != status.count}"><a href="/teacher_manage_student?pageNumber=${status.count}"
                                                           class="btn btn-info">${status.count}</a></c:if>
</c:forEach>
<jsp:include page="edit_password.jsp"/>
</body>
</html>
