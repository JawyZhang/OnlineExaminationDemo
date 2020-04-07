package com.boot.controller;

import com.boot.pojo.PageInfo;
import com.boot.pojo.Student;
import com.boot.pojo.Teacher;
import com.boot.service.StudentService;
import com.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @Author Mango
 * @Date 2020-04-03 18:44
 */
@Controller
public class LogInOutController {
    @Autowired
    private StudentService studentServiceImpl;
    @Autowired
    private TeacherService teacherServiceImpl;

    @RequestMapping("/login")
    private String login(Model model, Student student, Teacher teacher, HttpSession session) {
        Student isStudent = studentServiceImpl.selectByUsernameAndPassword(student);
        if (isStudent != null) {
            session.setAttribute("user", isStudent);
            session.removeAttribute("tip");
            //该页面暂时不存在
            return "学生页";
        } else {
            Teacher isTeacher = teacherServiceImpl.selectByUsernameAndPassword(teacher);
            if (isTeacher != null) {
                session.setAttribute("user", isTeacher);
                session.removeAttribute("tip");
                if (isTeacher.getTeacher_id() == null) {
                    ServletContext application = session.getServletContext();
                    PageInfo pageInfo = new PageInfo();
                    //默认每页显示10条数据
                    pageInfo.setPageSize(10);
                    application.setAttribute("pageInfo", pageInfo);
                    //工号为空的教师是管理员
                    return "admin_home";
                }
                //通过用户名和密码的方式查询到教师信息
                return "教师页";
            } else {
                teacher.setTeacher_id(teacher.getUsername());
                isTeacher = teacherServiceImpl.selectByTeacherIdAndPassword(teacher);
                if (isTeacher != null) {
                    session.setAttribute("user", isTeacher);
                    session.removeAttribute("tip");
                    //通过工号方式查询到教师信息
                    return "教师页";
                }
            }
        }
        session.setAttribute("tip", "密码或用户名错误，请重试！");
        return "index";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "index";
    }
}