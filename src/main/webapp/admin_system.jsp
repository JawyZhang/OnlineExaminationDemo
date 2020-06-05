<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/2
  Time: 23:55
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

        .form-horizontal {
            margin-top: 20px;
            background: #fff;
            padding-bottom: 40px;
            border-radius: 15px;
            text-align: center;
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
                <li><a href="admin_home.jsp">控制面板</a></li>
                <li><a href="/admin_teacher">教师管理</a></li>
                <li><a href="/admin_exam">考试清理</a></li>
                <li class="active"><a href="/admin_system">系统配置</a></li>
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
    <div class="row">
        <div class="col-md-8 col-md-offset-2 ">
            <form class="form-horizontal" method="post" action="update_system">
                <fieldset>
                    <legend>修改系统设置</legend>
                    <div class="form-group">
                        <label class="col-md-3 control-label">后台任务时间</label>
                        <div class="col-md-7">
                            <input class="form-control" type="number" name="taskTime" value="${system.taskTime}"
                                   placeholder="指定扫描考试信息的间隔时间，单位：分钟，空值提交设置为默认值1"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">分页查询记录条数</label>
                        <div class="col-md-7">
                            <input class="form-control" type="number" name="systemPageSize"
                                   value="${system.systemPageSize}"
                                   placeholder="指定分页查询时每页显示记录的默认值（查询页面中可以更改），空值提交设置为默认值10"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">手动开启考试时间阈值</label>
                        <div class="col-md-7">
                            <input class="form-control" type="number" name="examBeginTime" max="60" min="0"
                                   value="${system.examBeginTime}" placeholder="指定手工开启考试允许的最大提前量，单位：分钟，空值提交设置为最大值60"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">上传文件字节数下限</label>
                        <div class="col-md-7">
                            <input class="form-control" type="number" name="minFileSize" value="${system.minFileSize}"
                                   placeholder="指定上传文件的大小下限（字节），空值提交设置为默认值1024"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">上传文件字节数上限</label>
                        <div class="col-md-7">
                            <input class="form-control" type="number" name="maxFileSize" value="${system.maxFileSize}"
                                   placeholder="指定上传文件的大小上限（字节），空值提交设置为默认值102400，最大值为204800"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-7">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="lock" name="teacherClear"<c:if
                                        test="${system.teacherClear==1}"> checked</c:if> id="lock"/>教师可以清理和删除考试
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                            <input class="btn btn-primary" type="submit" value="确认修改"/>
                            <input class="btn btn-warning" type="reset" value="恢复默认"/>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>