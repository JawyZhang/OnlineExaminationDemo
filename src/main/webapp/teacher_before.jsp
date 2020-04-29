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
    <link rel="stylesheet" href="bootstrap/css/bootstrap-datetimepicker.min.css">
    <script src="bootstrap/js/bootstrap-datetimepicker.js"></script>
    <script src="bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

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
                <li class="active" ><a href="teacher_before.jsp">考前操作</a></li>
                <li><a href="teacher_within.jsp">考中管理</a></li>
                <li><a href="teacher_after.jsp">考后操作</a></li>
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
    <div class="col-md-12">
        <form role="form" class="newBox form-inline col-md-10 col-md-offset-1">

            <div class="form-group">

                <input type="text" class="form-control" id="examName" placeholder="请输入考试名称">
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="选择考试开始时间" id="from" readonly>
            </div>
            <div class="form-group">
                <div>至</div>
            </div>
            <div class="form-group">
                <input type="text" class="form-control " placeholder="选择考试结束时间" id="to" readonly>
            </div>

            <script>
                var today = new Date();
                $('#from').datetimepicker({
                    language: "zh-CN",
                    format: 'yyyy-mm-dd hh:ii',
                    autoclose: true,
                    todayBtn: true,
                    stratDate: today
                }).on('changeDate', function (ev) {
                    $('#to').datetimepicker('setStartDate', ev.date);
                });

                $('#to').datetimepicker({
                    language: "zh-CN",
                    format: 'yyyy-mm-dd hh:ii',
                    autoclose: true,
                    todayBtn: true,
                    stratDate: today
                }).on('changeDate', function (ev) {
                    $('#from').datetimepicker('setEndDate', ev.date);
                });
            </script>

            <div class="checkbox ">
                <label><input type="checkbox">自动开始</label>
            </div>
            <button type="submit" class="btn btn-default">添加考试</button>

        </form>


    </div>
    <HR width="80%" color=#987cb9 SIZE=3>
    <table class="table table-striped ">
        <thread>
            <tr>
                <th>考试名称</th>
                <th>考试时间</th>
                <th>创建人</th>
                <th>上传试卷</th>
                <th>自动开始</th>
                <th>进行中</th>
                <th>已结束</th>
                <th>已归档</th>
                <th>已清理</th>
                <th>编辑</th>
            </tr>
        </thread>
        <tbody>
        <c:forEach items="${exams}" var="exam">
            <tr>
                <td>${exam.name}</td>
                <td>${exam.beginTime}-${exam.finishTime}</td>
                <td>${exam.creater}</td>
                <td>
                    <button ref="#" class="button btn-primary">上传（仅未开始的考试显示）</button>
                </td>
                <td>${exam.isAutoBegin}</td>
                <td><input type="text" placeholder="可用JS判断也可做一个表项"></td>
                <td><input type="text" placeholder="可用JS判断也可做一个表项"></td>
                <td>${exam.isArchived}</td>
                <td>${exam.isCleaned}</td>
                <td>
                    <button href="#" class="button btn-primary">编辑</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>


    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>