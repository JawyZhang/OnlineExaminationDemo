<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li class="active"><a href="teacher_before?$username=${user.username}">考前操作</a></li>
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
    <div class="col-md-12">
        <form role="form" class="newBox form-inline col-md-10 col-md-offset-1" action="addExam" method="post">
            <input type="hidden" name="creater" value="${user.username}"/>
            <div class="form-group">

                <input type="text" class="form-control" id="examName" name="exam_name" placeholder="请输入考试名称" required>
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="选择考试开始时间" id="from" name="start_time" readonly
                       required>
            </div>
            <div class="form-group">
                <div>至</div>
            </div>
            <div class="form-group">
                <input type="text" class="form-control " placeholder="选择考试结束时间" id="to" name="finish_time" readonly
                       required>
            </div>

            <script>
                $(function () {
                    $(".deletePaper").click(function () {
                        exam_id = $(this).attr('exam_id')
                        file = $(this).attr('file')
                        $.ajax({
                            type: 'POST',
                            url: "/deletePaper",
                            data: {'exam_id': exam_id,
                                    'file': file},
                            success: function (rs) {
                                window.location.reload();
                            },
                            error: function (err) {
                                alert(err);
                            },
                        });
                    })
                })


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

                function checkFromAndToTime() {
                    var from = $("#from").val();
                    var to = $("#to").val();
                    if (from.length > 0 && to.length > 0) {
                        return true;
                    }
                    alert("请选择考试的开始时间和结束时间！")
                    return false;
                }
            </script>

            <div class="checkbox ">
                <label><input type="checkbox" name="is_auto_begin">自动开始</label>
            </div>
            <button type="submit" class="btn btn-default" onclick="return checkFromAndToTime()">添加考试</button>

        </form>

    </div>
    <HR width="80%" color=#987cb9 SIZE=3>
    <table class="table table-striped">
        <thread>
            <tr>
                <th>考试名称</th>
                <th>考试开始时间</th>
                <th>考试结束时间</th>
                <th>创建人</th>
                <th>试卷信息</th>
                <th>自动开始</th>
                <th>编辑考试</th>
                <th>管理考生信息</th>
                <th>开启考试</th>
                <th>删除考试</th>
            </tr>
        </thread>
        <tbody>
        <c:forEach items="${exams}" var="exam">
            <tr>
                <td id="${exam.exam_id}_exam_name">${exam.exam_name}</td>
                <td id="${exam.exam_id}_start_time">${exam.start_time}</td>
                <td id="${exam.exam_id}_finish_time">${exam.finish_time}</td>
                <td>${exam.creater}</td>
                <td>
                    <c:if test="${exam.paper_path != null&&exam.paper_path.length()!=0}">
                        <a href="/downloadPaper?exam_id=${exam.exam_id}&exam_name=${exam.exam_name}&file=${exam.paper_path}"
                           class="btn btn-info">下载预览</a>
                        <a exam_id=${exam.exam_id} file=${exam.paper_path}  class="btn btn-danger deletePaper"
                           onclick="">删除</a>
                    </c:if>
                    <c:if test="${exam.paper_path == null||exam.paper_path.length()==0}">
                        <form action="uploadPaper" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="exam_id" value="${exam.exam_id}"/>
                            <input type="file" style="display: none;" id="${exam.exam_id}" name="filename"
                                   onchange="this.form.submit();"/>
                            <label for="${exam.exam_id}" class="btn btn-warning">选择试卷文件上传</label>
                        </form>
                    </c:if>
                </td>
                <td id="${exam.exam_id}_is_auto_begin">${exam.is_auto_begin}</td>
                <td>
                    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#edit_exam"
                       onclick="setContent(${exam.exam_id},'${exam.exam_id}_start_time','${exam.exam_id}_finish_time','${exam.exam_id}_exam_name','${exam.exam_id}_is_auto_begin')">编辑</a>
                </td>
                <td><a href="/teacher_manage_student?exam_id=${exam.exam_id}" class="btn btn-primary">管理考生</a></td>
                <td>
                    <c:if test="${exam.paper_path != null&&exam.paper_path.length()!=0}">
                        <a href="/start_exam?exam_id=${exam.exam_id}" class="btn btn-primary"
                           onclick="return checkTime(${system.examBeginTime},'${exam.start_time}','${exam.finish_time}')">开始考试</a>
                    </c:if>
                </td>
                <td><a href="/deleteUselessExam?exam_id=${exam.exam_id}&paper_path=${exam.paper_path}" class="btn btn-danger" onclick="return confirm('是否删除该考试？')">删除考试</a></td>
            </tr>
        </c:forEach>
        <script type="text/javascript">
            function checkTime(before_time, start_time, finish_time) {
                var date = new Date();
                var times = ["" + date.getFullYear(), "" + (date.getMonth() + 1), "" + date.getDay(), "" + date.getHours(), "" + date.getMinutes()];
                var current = formatTime(times);
                if (current > finish_time) {
                    alert("考试时间已过，请修改考试时间！");
                    return false;
                }
                if (start_time > calcTime(times, before_time)) {
                    alert("考试不可开始，最早提前" + before_time + "分钟");
                    return false;
                }
            }

            function formatTime(times) {
                for (index in times) {
                    if (times[index].length == 1) {
                        times[index] = "0" + times[index];
                    }
                }
                return times[0] + "-" + times[1] + "-" + times[2] + " " + times[3] + ":" + times[4];
            }

            function calcTime(times, before_time) {
                if (before_time == 60) {
                    times[3] = "" + (parseInt(times[3]) + 1);
                } else {
                    if (parseInt(times[4]) + before_time >= 60) {
                        times[3] = "" + (parseInt(times[3]) + 1);
                        times[4] = "" + (parseInt(times[4]) + before_time - 60);
                    } else {
                        times[4] = "" + (parseInt(times[4]) + before_time);
                    }
                }
                return formatTime(times);
            }

            function setContent(exam_id, start_time_id, finish_time_id, exam_name_id, is_auto_begin_id) {
                $("input[name='exam_id']").val(exam_id);
                $("input[name='change_exam_name']").val($("#" + exam_name_id).text());
                $("input[name='change_start_time']").val($("#" + start_time_id).text());
                $("input[name='change_finish_time']").val($("#" + finish_time_id).text());
                $("input[name='change_is_auto_begin']")[0].checked = false;
                if ($("#" + is_auto_begin_id).text() == "true") {
                    $("input[name='change_is_auto_begin']")[0].checked = true;
                }
            }
        </script>
        </tbody>

    </table>

    <jsp:include page="edit_exam.jsp"/>
    <jsp:include page="edit_password.jsp"/>
</div>
</body>
</html>