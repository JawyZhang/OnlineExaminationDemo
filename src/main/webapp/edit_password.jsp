<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 16422
  Date: 2020/4/3
  Time: 20:10
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
        $("#update_password").click(function () {
            if($("input[name='change_password']").val() != $("input[name='reassure_password']").val()){
                alert("两次密码不一致！！！");
                return false;
            }
            if ($("input[name='change_username']").val().length != 0 && $("input[name='change_password']").val().length != 0) {
                $("#form").submit();
            } else {
                alert("用户名或密码不能为空！！！");
                return false;
            }
        })
    })
</script>
<div class="modal fade" id="edit_password" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改用户名和口令</h4>
            </div>
            <div class="modal-body">
                <form action="/update_password" method="post" id="form">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="isStudent" value="${isStudent}">
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" name="change_username" value="${user.username}"
                               placeholder="请输入用户名">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" class="form-control" name="change_password" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <label>再次确认</label>
                        <input type="password" class="form-control" name="reassure_password" placeholder="请输入密码">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="update_password" class="btn btn-primary">保存修改</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
