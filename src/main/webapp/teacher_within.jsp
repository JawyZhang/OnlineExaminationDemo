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
                        <th>应参加（人）</th>
                        <th>已登录（人）</th>
                        <th>未登录（人）</th>
                        <th>已提交（人）</th>
                        <th>未提交（人）</th>

                    </tr>
                </thread>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            学生信息
        </div>
        <div class="panel-body">
            <div class="panel panel-default">
                <div class="panel-heading">
                    添加单个学生
                </div>
                <div class="panel-body">
                    <form action="#" method="post">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input type="text" class="form-control " name="condition" placeholder="学号"/>
                            </div>
                            <div class="col-md-3">
                                <input type="text" class="form-control " name="condition" placeholder="姓名"/>
                            </div>
                            <div class="col-md-3">
                                <input type="text" class="form-control " name="condition" placeholder=‘班级"/>
                            </div>
                            <div class="col-md-3">
                                <input type="submit" class="btn btn-info btn-search" value="查找"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    查找学生信息
                </div>
                <div class="panel-body">
                    <form action="#" method="post">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input type="text" class="form-control " name="condition" placeholder="学号"/>
                            </div>
                            <div class="col-md-3">
                                <input type="text" class="form-control " name="condition" placeholder="姓名"/>
                            </div>
                            <div class="col-md-3">
                                <input type="text" class="form-control " name="condition" placeholder="班级"/>
                            </div>
                            <div class="col-md-3">
                                <input type="submit" class="btn btn-info btn-search" value="查找"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            登录锁定
        </div>
        <div class="panel-body">
            <form action="#" method="post">
                <div class="form-group">
                    <div class="col-md-3">
                        <input type="text" class="form-control " name="condition" placeholder="ip地址"/>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-info btn-search" value="查找(下方表格是点击查找后出现的）"/>
                    </div>

                </div>
            </form>
            <table class="table table-striped">
                <thread>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>班级</th>
                        <th>ip地址</th>
                        <th>加/解锁(是个按钮)</th>


                    </tr>
                </thread>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
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

</div>


</body>
</html>
