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
    <script>
        function fillInfo(element) {
            let ip = element.parentNode.previousSibling.previousSibling.textContent;
            let class_room = element.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.textContent;
            let username = element.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.textContent;
            let stu_no = element.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.textContent;
            let stu_id = element.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.textContent;
            $("#stu_id").val(stu_id);
            $("#stu_no").val(stu_no);
            $("#username").val(username);
            $("#class_room").val(class_room);
            if ("暂未下载该考试试卷" != ip) {
                $("#ip").val(ip);
            }
        }

        $(function () {
            $("#update_student").click(function () {
                if ($("#stu_no").val().length != 0 && $("#username").val().length != 0 && $("#class_room").val().length != 0) {
                    $("#student_form").submit();
                } else {
                    alert("学号，姓名或者班级不能为空！！！");
                    return false;
                }
            })
        })
    </script>
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
        <div class="panel-heading"> 通过Excel文件批量添加考生</div>

        <div class="panel-body">
            <div class="alert alert-info">
                <strong>请保证Excel文件为2003版的，即文件后缀名为xls的Excel文件，程序只会读取前三列，请保证前三列的按"学号-姓名-班级"的方式排列，表头可有可无！！！</strong>
            </div>
            <form action="/addStudentByExcel" method="post" enctype="multipart/form-data">
                <input type="hidden" name="exam_id" value="${exam_id}"/>
                <input type="file" name="filename" required/>
                <input type="submit" style="margin-top: 5px" class="btn btn-primary" value="批量添加">
            </form>
        </div>
    </div>
    <hr/>
    <div class="panel panel-default">
        <div class="panel-heading">通过手动输入逐个添加考生</div>
        <div class="panel-body">
            <div class="alert alert-info">
                <strong>注意：生成的学生账号会以姓名作为用户名，学号后六位作为默认密码(不足六位前面补零)</strong>
            </div>
            <form action="/addStudent" method="post">
                <input type="hidden" name="exam_id" value="${exam_id}"/>
                <label>学号：</label>
                <input name="stu_no" placeholder="请输入学号" required/>
                <label>姓名：</label>
                <input name="username" placeholder="请输入姓名" required/>
                <label>班级：</label>
                <input name="class_room" placeholder="请输入班级" required/>
                <input type="submit" class="btn btn-primary" value="添加"/>
            </form>
        </div>
    </div>
    <hr/>
    <div class="panel panel-default">
        <div class="panel-heading">查找考生信息</div>
        <div class="panel-body">
            <form action="/searchStudent" method="post">
                <input type="hidden" name="exam_id" value="${exam_id}"/>
                <label>学号：</label>
                <input name="stu_no" placeholder="请输入学号" value="${search.stu_no}" required/>
                <label>姓名：</label>
                <input name="username" placeholder="请输入姓名" value="${search.username}" required/>
                <label>班级：</label>
                <input name="class_room" placeholder="请输入班级" value="${search.class_room}" required/>
                <input type="submit" class="btn btn-primary" value="查找"/>
                <c:if test="${search!=null}">
                    <a href="/teacher_manage_student?exam_id=${exam_id}"
                       class="btn btn-primary">清除条件查看全部考生</a>
                </c:if>
            </form>
        </div>

        <hr/>
    <table class="table">
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>班级</th>
            <th>状态</th>
            <th>主机IP</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="student">
            <tr>
                <td style="display: none">${student.id}</td>
                <td>${student.stu_no}</td>
                <td>${student.username}</td>
                <td>${student.class_room}</td>
                <td>${student.status==0?"未登录":student.status==1?"已登录，未提交":student.status==2?"已提交":""}</td>
                <td>${(student.ip==null||student.ip=="")?"暂未下载该考试试卷":student.ip}</td>
                <td><a href="#" data-toggle="modal" data-target="#edit_student"
                       onclick="fillInfo(this)"
                       class="btn btn-primary">编辑考生信息</a></td>
                <td><a href="deleteExamStudent?stu_id=${student.id}&exam_id=${exam_id}"
                       class="btn btn-danger">删除该考生</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>当前页为：${pageInfo.pageNumber}，共${pageInfo.totalPage}页，数据总条数：${pageInfo.total}</p>
    <c:forEach begin="1" end="${pageInfo.totalPage}" varStatus="status">
        <c:if test="${pageInfo.pageNumber == status.count}"><a href="javascript:void(0);"
                                                               class="btn btn-default">${status.count}</a></c:if>
        <c:if test="${pageInfo.pageNumber != status.count}"><a
                href="/teacher_manage_student?pageNumber=${status.count}"
                class="btn btn-info">${status.count}</a></c:if>
    </c:forEach>
        <div class="modal fade" id="edit_student" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改考生信息</h4>
                </div>
                <div class="modal-body">
                    <form action="/update_student_exam" method="post" id="student_form">
                        <input type="hidden" name="exam_id" value="${exam_id}"/>
                        <input type="hidden" name="id" id="stu_id"/>
                        <div class="form-group">
                            <label>学号</label>
                            <input type="text" class="form-control" name="stu_no" id="stu_no"
                                   placeholder="请输入学号"
                                   required>
                        </div>
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" class="form-control" name="username" id="username"
                                   placeholder="请输入姓名"
                                   required>
                        </div>
                        <div class="form-group">
                            <label>班级</label>
                            <input type="text" class="form-control" name="class_room"
                                   id="class_room"
                                   placeholder="请输入班级"
                                   required>
                        </div>
                        <div class="form-group">
                            <label>主机IP</label>
                            <input type="text" class="form-control" name="ip" id="ip"
                                   placeholder="请输入指定IP，清空则清除绑定">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="update_student" class="btn btn-primary">保存修改</button>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
<jsp:include page="edit_password.jsp"/>
</body>
</html>
