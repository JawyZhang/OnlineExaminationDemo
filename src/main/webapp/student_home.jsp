<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        .panel {
            margin-top: 20px;
        }


    </style>
    <script>
        function calc_time() {
            setTimeout("time()", 1000 * 60);
        }

        function time() {
            var time = $("#rest_time").text();
            var value = time.slice(0, time.length - 2) - 1;
            $("#rest_time").text(value + "分钟");
            if (value > 1)
                setTimeout("time()", 1000 * 60);
            else {
                setTimeout("last()", 1000);
            }
        }

        var date, minute, minuteLastDigit;

        function last() {
            date = new Date();
            minute = ("" + date.getMinutes());
            minuteLastDigit = minute.substr(minute.length - 1);
            if (date.getSeconds() == 0 && (minuteLastDigit == 6 || minuteLastDigit == 5)) {
                $("#rest_time").text("0分钟");
                alert("考试结束");
                $(".submit_answer").empty();
            } else {
                setTimeout("last()", 1000);
            }
        }
    </script>
</head>
<body onload="calc_time()">
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
                正在进行的考试(重复提交会自动覆盖)
            </h3>
        </div>
        <table class="table">
            <tr>
                <th>考试名称</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>剩余时间</th>
                <th>提交情况</th>
                <th>查看通知</th>
                <th>试卷下载</th>
                <th>答案提交</th>
            </tr>
            <c:forEach items="${operating}" var="exam">
                <tr>
                    <td>${exam.exam_name}</td>
                    <td>${exam.start_time}</td>
                    <td>${exam.finish_time}</td>
                    <td id="rest_time">${exam.rest_time}</td>
                    <td>${exam.submit_status==0?"未提交":"已提交"}</td>
                    <td><a href="/getExamMessage?exam_id=${exam.exam_id}&stu_id=${user.id}" class="btn btn-info">查看通知</a></td>
                    <td>
                        <a href="/downloadPaper?exam_id=${exam.exam_id}&stu_id=${user.id}&exam_name=${exam.exam_name}&file=${exam.paper_path}"
                           class="btn btn-info">下载试卷</a></td>
                    <td class=" submit_answer">
                        <label for="exam${exam.exam_id}" class="btn btn-primary">提交答案</label>
                        <form action="/submit_paper" method="post" enctype="multipart/form-data" style="display: none">
                            <input type="hidden" name="exam_id" value="${exam.exam_id}"/>
                            <input type="hidden" name="stu_id" value="${user.id}"/>
                            <input type="hidden" name="stu_no" value="${user.stu_no}"/>
                            <input type="hidden" name="username" value="${user.username}"/>
                            <input type="hidden" name="exam_name" value="${exam.exam_name}"/>
                            <input id="exam${exam.exam_id}" type="file" name="filename" onchange="this.form.submit()"/>
                        </form>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">
                已结束的考试
            </h3>
        </div>
        <table class="table">
            <tr>
                <th class="col-md-3">考试名称</th>
                <th class="col-md-3">开始时间</th>
                <th class="col-md-3">结束时间</th>
                <th>提交情况</th>
            </tr>
            <c:forEach items="${finished}" var="exam">
                <tr>
                    <td>${exam.exam_name}</td>
                    <td>${exam.start_time}</td>
                    <td>${exam.finish_time}</td>
                    <td>${exam.submit_status==0?"未提交":"已提交"}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>

