<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/2
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上机考试系统</title>
    <script type="text/javascript" src="bootstrap/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script type="text/javascript">
        $(function () {
            $(".edit").click(function () {
                let _this = $(this);
                $("#id").val(_this.attr("name"));
                $("#username").val(_this.parent().prev().html());
            })
            $("#submit").click(function () {
                if ($("#username").val().length != 0 && $("#password").val().length != 0) {
                    if ($("#id").val().length != 0) {
                        $("#form")[0].action = "/update_teacher";
                        $("#form")[0].submit();
                        alert("修改成功。");
                    } else {
                        $("#form")[0].action = "/add_teacher";
                        $("#form")[0].submit();
                        alert("新增成功。");
                    }
                } else {
                    alert("用户名或者密码不能为空！！！");
                    return false;
                }
            })
        })
    </script>
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
        .table{

            background-color: ghostwhite;

        }
        .addBtn{
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
                <li><a href="admin_home.jsp">控制面板</a></li>
                <li class="active"><a href="/admin_teacher">教师管理</a></li>
                <li><a href="admin_exam.jsp">考试清理</a></li>
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
<div class="container " id="row">
    <div class="col-md-3 col-md-offset-1">
    <button class="addBtn btn btn-primary" data-toggle="modal" data-target="#edit_teacher">新增教师</button>
    </div>
    <c:if test="${tip!=null}"><p>${tip}</p></c:if>
    <div class="searchBox input-group col-md-3 col-md-offset-8">
        <input type="text" class="form-control"placeholder="请输入教师姓名或工号" />
        <span class="input-group-btn">
               <button class="btn btn-info btn-search">查找</button>
            </span>
    </div>
    <HR  width="80%" color=#987cb9 SIZE=3>

    <table class="table table-striped " >
        <thread>
            <tr>
                <th>工号</th>
                <th>姓名</th>
                <th>修改信息</th>
                <th>删除用户</th>
                <th>设为管理员</th>
            </tr>
        </thread>
        <tbody>
        <c:forEach items="${teachers}" var="teacher">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.username}</td>
                <td><a href="#" class="edit" name="${teacher.id}" data-toggle="modal" data-target="#edit_teacher">修改</a>
                </td>
                <td><a href="delete_teacher?id=${teacher.id}">删除</a></td>
                <td><button   href="#" class="button btn-primary">设为管理员</button> </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <div class="modal fade" id="edit_teacher" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改教师信息</h4>
                </div>
                <div class="modal-body">
                    <form method="post" id="form">
                        <input type="hidden" id="id" name="id"/>
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                        </div>
                        <div class="form-group">
                            <label for="password">密&nbsp;&nbsp;码</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="请输入密码">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">同时设为管理员
                            </label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="submit" class="btn btn-primary">保存修改</button>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>