package com.boot.controller;

import com.boot.pojo.Student;
import com.boot.pojo.Teacher;
import com.boot.service.StudentService;
import com.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private String login(HttpServletRequest request, HttpServletResponse response, Student student, Teacher teacher, HttpSession session, String rememberPassword, String asTeacher) {
        //rememberPassword和asTeacher勾选为None，未勾选为null
        Student isStudent = studentServiceImpl.selectByUsernameAndPassword(student);
        if (isStudent != null) {
            session.setAttribute("user", isStudent);
            //该属性用于修改密码时判断修改学生表还是老师表
            session.setAttribute("isStudent", "true");
            if ("None".equals(rememberPassword)) {
                rememberPassword(response, student.getUsername(), student.getPassword());
            } else {
                rememberPassword(response, "", "");
            }
            if(isStudent.getStatus() == 2){
                return "first_login";
            }
            studentServiceImpl.updateStudentStatus(1, isStudent.getId());
            return "redirect:student_home?stu_id="+isStudent.getId();
        } else{
            student.setStu_no(student.getUsername());
            isStudent = studentServiceImpl.selectByIdAndPassword(student);
            if (isStudent != null) {
                session.setAttribute("user", isStudent);
                //该属性用于修改密码时判断修改学生表还是老师表
                session.setAttribute("isStudent", "true");
                if ("None".equals(rememberPassword)) {
                    rememberPassword(response, student.getUsername(), student.getPassword());
                } else {
                    rememberPassword(response, "", "");
                }
                if(isStudent.getStatus() == 2){
                    return "first_login";
                }
                studentServiceImpl.updateStudentStatus(1, isStudent.getId());
                return "redirect:student_home?stu_id="+isStudent.getId();
            } else {
                Teacher isTeacher = teacherServiceImpl.selectByUsernameAndPassword(teacher);
                if (isTeacher != null) {
                    session.setAttribute("user", isTeacher);
                    //该属性用于修改密码时判断修改学生表还是老师表
                    session.setAttribute("isStudent", "false");
                    //如果ID为1并且不作为教师登录则跳转到管理员界面
                    if(isTeacher.getStatus() == 0){
                        return "first_login";
                    }
                    if (isTeacher.getIs_admin().equals("on") && asTeacher == null) {
                        //给管理员分页显示设置默认默认分页大小为每页10条数据
                        session.getServletContext().setAttribute("systemPageSize", 10);
                        //ID为1的教师是管理员
                        if ("None".equals(rememberPassword)) {
                            rememberPassword(response, teacher.getUsername(), teacher.getPassword());
                        } else {
                            rememberPassword(response, "", "");
                        }
                        return "admin_home";
                    }
                    if ("None".equals(rememberPassword)) {
                        rememberPassword(response, teacher.getUsername(), teacher.getPassword());
                    } else {
                        rememberPassword(response, "", "");
                    }
                    //通过用户名和密码的方式查询到教师信息
                    return "teacher_home";
                } else {
                    teacher.setTeacher_id(teacher.getUsername());
                    isTeacher = teacherServiceImpl.selectByTeacherIdAndPassword(teacher);
                    if (isTeacher != null) {
                        session.setAttribute("user", isTeacher);
                        //该属性用于修改密码时判断修改学生表还是老师表
                        session.setAttribute("isStudent", "false");
                        if(isTeacher.getStatus() == 0){
                            return "first_login";
                        }
                        if (isTeacher.getIs_admin().equals("on") && asTeacher == null) {
                            //给管理员分页显示设置默认默认分页大小为每页10条数据
                            session.getServletContext().setAttribute("systemPageSize", 10);
                            //ID为1的教师是管理员
                            if ("None".equals(rememberPassword)) {
                                rememberPassword(response, teacher.getUsername(), teacher.getPassword());
                            } else {
                                rememberPassword(response, "", "");
                            }
                            return "admin_home";
                        }
                        if ("None".equals(rememberPassword)) {
                            rememberPassword(response, teacher.getUsername(), teacher.getPassword());
                        } else {
                            rememberPassword(response, "", "");
                        }
                        //通过工号方式查询到教师信息
                        return "teacher_home";
                    }
                }
            }
        }
        request.setAttribute("tip", "密码或用户名错误，请重试！");
        //登录失败返回原来的登录信息
        request.setAttribute("user", student);
        return "index";
    }

    public void rememberPassword(HttpServletResponse response, String username, String password) {
        response.addCookie(new Cookie("username", username));
        response.addCookie(new Cookie("password", password));
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user instanceof Student) {
            //将学生的状态置为0，表示已登出
            studentServiceImpl.updateStudentStatus(0, ((Student) user).getId());
            //清除session中的学生标记
            session.removeAttribute("isStudent");
        }
        session.removeAttribute("user");
        return "index";
    }
}