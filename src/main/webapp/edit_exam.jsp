<%--
  Created by IntelliJ IDEA.
  User: 16422
  Date: 2020/5/14
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    $(function () {
        $("#update_exam").click(function () {
            if ($("input[name='change_exam_name']").val().length != 0 && $("input[name='change_start_time']").val().length != 0 && $("input[name='change_finish_time']").val().length != 0) {
                $("#change_exam_form").submit();
            } else {
                alert("请将信息填写完整！！！");
                return false;
            }
        })
    })
</script>
<div class="modal fade" id="edit_exam" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">编辑考试信息</h4>
            </div>
            <div class="modal-body">
                <form action="/update_exam" method="post" id="change_exam_form">
                    <input type="hidden" name="exam_id"/>
                    <div class="form-group">
                        <label>考试名称</label>
                        <input type="text" class="form-control" name="change_exam_name" placeholder="请输入考试名称">
                    </div>
                    <div class="form-group">
                        <label>请选择考试开始时间</label>
                        <input type="text" class="form-control" id="change_start_time" name="change_start_time"
                               placeholder="选择考试开始时间" readonly>
                    </div>
                    <div class="form-group">
                        <label>请选择考试结束时间</label>
                        <input type="text" class="form-control" id="change_finish_time" name="change_finish_time"
                               placeholder="选择考试结束时间" readonly>
                    </div>
                    <script>
                        var today = new Date();
                        $('#change_start_time').datetimepicker({
                            language: "zh-CN",
                            format: 'yyyy-mm-dd hh:ii',
                            autoclose: true,
                            todayBtn: true,
                            stratDate: today
                        }).on('changeDate', function (ev) {
                            $('#change_finish_time').datetimepicker('setStartDate', ev.date);
                        });

                        $('#change_finish_time').datetimepicker({
                            language: "zh-CN",
                            format: 'yyyy-mm-dd hh:ii',
                            autoclose: true,
                            todayBtn: true,
                            stratDate: today
                        }).on('changeDate', function (ev) {
                            $('#change_start_time').datetimepicker('setEndDate', ev.date);
                        });
                    </script>
                    <label><input type="checkbox" name="change_is_auto_begin">自动开始</label>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="update_exam" class="btn btn-primary">保存修改</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
