<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jawy
  Date: 2020/4/2
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
                $("#teacher_id").val(_this.parent().prev().prev().html());
                if (_this.attr("is_admin")=="on") {
                    $("#is_admin").attr("checked", true);
                }
            })
            $("#submit").click(function () {
                if ($("#username").val().length != 0 && $("#teacher_id").val().length != 0) {
                    if ($("#id").val().length != 0) {
                        $("#form")[0].action = "/update_teacher";
                        $("#form")[0].submit();
                    } else {
                        $("#form")[0].action = "/add_teacher";
                        $("#form")[0].submit();
                    }
                } else {
                    alert("用户名或者工号不能为空！！！");
                    return false;
                }
            })
            $("#selectedDelete").click(function () {
                var postModel = [];
                $("input[class='select']").each(function (i) {
                    if ($(this).is(':checked')) {
                        postModel.push($(this).attr('name'));
                    }
                })
                $.ajax({
                    type: 'POST',
                    url: "/deleteAdminGroup",
                    data: {'id': postModel},
                    success: function (rs) {
                        window.location.reload();
                    },
                    error: function (err) {
                        alert(err);
                    },
                });
            })
            $("#selectedAsAdmin").click(function () {
                var postModel = [];
                $("input[class='select']").each(function (i) {
                    if ($(this).is(':checked')) {
                        postModel.push($(this).attr('name'));
                    }
                })
                $.ajax({
                    type: 'POST',
                    url: "/asAdminGroup",
                    data: {'id': postModel},
                    success: function (rs) {
                        window.location.reload();
                    },
                    error: function (err) {
                        alert(err);
                    },
                });
            })
            $("#selectedCancelAdmin").click(function () {
                var postModel = [];
                $("input[class='select']").each(function (i) {
                    if ($(this).is(':checked')) {
                        postModel.push($(this).attr('name'));
                    }
                })
                $.ajax({
                    type: 'POST',
                    url: "/cancelAdminGroup",
                    data: {'id': postModel},
                    success: function (rs) {
                        window.location.reload();
                    },
                    error: function (err) {
                        alert(err);
                    },
                });
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

        .table {

            background-color: ghostwhite;

        }

        .addBtn {
            margin-top: 30px;

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
                <li class="active"><a href="#">教师管理</a></li>
                <li><a href="/admin_exam">考试清理</a></li>
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
    <div class="addBtn col-md-3 col-md-offset-1">
        <button class=" btn btn-primary" data-toggle="modal" data-target="#edit_teacher">新增教师</button>
    </div>
    <div class="searchBox input-group col-md-3 col-md-offset-7">
        <form action="/selectTeacher" method="post">
            <div class="form-group">
                <div class="col-md-9">
                    <input type="text" class="form-control " name="condition" value="${condition}"
                           placeholder="请输入教师姓名或工号"/>
                </div>
                <div class="col-md-3">
                    <input type="submit" class="btn btn-info btn-search" value="查找"/>
                </div>
            </div>
        </form>
    </div>
    <HR width="80%" color=#987cb9 SIZE=3>
    <center><span>${tip}</span></center>
    <table class="table table-striped ">
        <thread>
            <tr>
                <th>选择</th>
                <th>工号</th>
                <th>姓名</th>
                <th>修改信息</th>
                <th>删除用户</th>
                <th>设为管理员</th>
            </tr>
        </thread>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="teacher">
            <tr>
                <td><input type="checkbox" class="select" name="${teacher.id}"/></td>
                <td>${teacher.teacher_id}</td>
                <td>${teacher.username}</td>
                <td><a href="#" class="edit btn btn-primary" name="${teacher.id}" is_admin="${teacher.is_admin}" data-toggle="modal" data-target="#edit_teacher">修改</a>
                </td>
                <td><a href="/delete_teacher?id=${teacher.id}" class="btn btn-danger"
                       onclick="return confirm('是否要删除该用户？')">删除</a></td>
                <td>
                    <c:if test="${teacher.is_admin=='on'}">
                        <a href="/cancelAdmin?id=${teacher.id}" class="btn btn-warning">取消管理员</a>
                    </c:if>
                    <c:if test="${teacher.is_admin=='null'}">
                        <a href="/asAdmin?id=${teacher.id}" class="btn btn-primary">设为管理员</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input id="selectedDelete" type="submit" class="btn btn-info btn-search" value="删除选中教师"/>
    <input id="selectedAsAdmin" type="submit" class="btn btn-info btn-search" value="一键设置管理员"/>
    <input id="selectedCancelAdmin" type="submit" class="btn btn-info btn-search" value="一键撤销管理员"/>
    <hr/>
    <p>当前页为：${pageInfo.pageNumber}，共${pageInfo.totalPage}页，数据总条数：${pageInfo.total}</p>
    <c:forEach begin="1" end="${pageInfo.totalPage}" varStatus="status">
        <c:if test="${pageInfo.pageNumber == status.count}"><a href="javascript:void(0);"
                                                               class="btn btn-default">${status.count}</a></c:if>
        <c:if test="${pageInfo.pageNumber != status.count}"><a href="/admin_teacher?pageNumber=${status.count}"
                                                               class="btn btn-info">${status.count}</a></c:if>
    </c:forEach>
    <div class="modal fade" id="edit_teacher" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">教师信息</h4>
                </div>
                <div class="modal-body">
                    <form method="post" id="form">
                        <input type="hidden" id="id" name="id"/>
                        <div class="form-group">
                            <label for="username">教师姓名</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="请输入密码，新增时不写默认为123456">
                        </div>
                        <div class="form-group">
                            <label for="teacher_id">工 号</label>
                            <input type="text" class="form-control" id="teacher_id" name="teacher_id"
                                   placeholder="请输入工号">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="is_admin" id="is_admin">同时设为管理员
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