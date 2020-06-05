<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 16422
  Date: 2020/6/2
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理考试通知</title>
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
<div class="container " id="row" style="margin: auto">
    <div class="panel panel-default" style="margin-top: 20px">
        <div class="panel-heading">
            通知管理
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>考试名称</th>
                    <th>发送新通知</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${exam_name}</td>
                    <td><a href="#" data-toggle="modal" data-target="#add_message" class="btn btn-primary">添加新通知</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="panel panel-default" style="margin-top: 20px">
        <div class="panel-heading">
            通知列表
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="col-md-2">发布时间</th>
                    <th>通知详情</th>
                    <th class="col-md-2">操作</th>
                </tr>
                </thead>
                <tbody>
                <tbody>
                <c:forEach items="${messages}" var="message">
                    <tr>
                        <td>${message.time}</td>
                        <td>${message.detail}</td>
                        <td>
                            <button class="btn btn-warning" onclick="deleteMessage(${message.id},this)">删除该通知</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    function deleteMessage(message_id,element) {
        $.getJSON("/deleteMessage", {"id": message_id}, function (res) {
            if (res == true) {
                var tr=$(element).parent().parent();
                tr.empty();
                $("tr").remove(tr);
            }
        })
    }

    $(function () {
        $("#storage_message").click(function () {
            if ($("textarea[name='detail']").val().length != 0) {
                $("#form").submit();
            } else {
                alert("用户名或密码不能为空！！！");
                return false;
            }
        })
    })
</script>
<div class="modal fade" id="add_message" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改用户名和口令</h4>
            </div>
            <div class="modal-body">
                <form action="/addMessage" method="post" id="form">
                    <input type="hidden" name="exam_id" value="${exam_id}"/>
                    <input type="hidden" name="exam_name" value="${exam_name}"/>
                    <div class="form-group">
                        <label>通知内容</label>
                        <textarea name="detail" class="form-control" rows="3" style="resize: none"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="storage_message" class="btn btn-primary">保存修改</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="edit_password.jsp"/>
</body>
</html>
