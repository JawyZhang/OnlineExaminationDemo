package com.boot.controller;

import com.boot.pojo.Student;
import com.boot.pojo.Teacher;
import com.boot.service.StudentService;
import com.boot.service.TeacherService;
import com.boot.utils.UrlCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Mango
 * @Date 2020-04-21 17:49
 */
@Controller
public class UserController {
    @Autowired
    private TeacherService teacherServiceImpl;
    @Autowired
    private StudentService studentServiceImpl;

    @RequestMapping("update_password")
    public String update_password(Model model, HttpServletRequest request, Integer id, String change_username, String change_password, String isStudent) {
        String tip = "";
        //如果有学生标记，进入该分支
        if ("true".equals(isStudent)) {
            Student student = new Student();
            student.setId(id);
            student.setUsername(change_username);
            student.setPassword(change_password);
            student.setStatus(0);
            if (studentServiceImpl.updateStudentById(student) == 1) {
                studentServiceImpl.updateStudentStatus(0, student.getId());
                //更新用户信息成功，清空原有信息，强制重新登录
                request.getSession().removeAttribute("user");
                //删除学生标记
                request.getSession().removeAttribute("isStudent");
            } else {
                tip = UrlCodeUtils.getUrlString("修改密码失败");
            }

            return "redirect:student_home?tip=" + tip;
        } else {
            Teacher teacher = new Teacher();
            teacher.setId(id);
            teacher.setUsername(change_username);
            teacher.setPassword(change_password);
            teacher.setStatus(1);
            if (teacherServiceImpl.updateTeacher(teacher) == 1) {
                request.getSession().removeAttribute("user");
            } else {
                tip = UrlCodeUtils.getUrlString("修改密码失败");
            }
            //如果id为1代表为管理员，进入该分支
            if (1 == id) {
                return "redirect:admin_teacher?pageNumber=" + request.getSession().getAttribute("systemPageNumber") + "&tip=" + tip;
            } else {
                return "redirect:teacher_home?tip=" + tip;
            }
        }
    }
}
