package com.boot.controller;

import com.boot.pojo.PageInfo;
import com.boot.pojo.Teacher;
import com.boot.service.TeacherService;
import com.boot.utils.UrlCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-03 18:43
 */
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherServiceImpl;

    @RequestMapping("admin_teacher")
    public String admin_teacher(Model model, HttpSession session, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "") String tip) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize((Integer) session.getAttribute("systemPageSize"));
        //使用分页查询
        model.addAttribute("pageInfo", teacherServiceImpl.selectAllByPage(pageInfo));
        session.setAttribute("systemPageNumber", pageNumber);
        if (tip.length() != 0) {
            model.addAttribute("tip", tip);
        }
        return "admin_teacher";
    }

    @RequestMapping("delete_teacher")
    public String delete_teacher(Integer id, Model model, HttpSession session) {
        String tip = "";
        if (teacherServiceImpl.deleteTeacher(id) == 1) {
            tip = UrlCodeUtils.getUrlString("删除用户成功");
        } else {
            tip = UrlCodeUtils.getUrlString("删除用户失败");
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }

    @RequestMapping("update_teacher")
    public String update_teacher(Teacher teacher, Model model, HttpSession session) {
        if (null == teacher.getIs_admin()) {
            teacher.setIs_admin("null");
        }
        String tip = "";
        if (teacherServiceImpl.updateTeacherInfo(teacher) == 1) {
            tip = UrlCodeUtils.getUrlString("更新教师信息成功");
        } else {
            tip = UrlCodeUtils.getUrlString("更新教师信息失败");
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }

    @RequestMapping("update_teacher_password")
    public String update_teacher_password(Model model, HttpServletRequest request, HttpServletResponse response, Integer id, String change_username, String change_password) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setUsername(change_username);
        teacher.setPassword(change_password);
        String tip = "";
        if (teacherServiceImpl.updateTeacher(teacher) == 1) {
            //更新用户信息成功，清空原有信息，强制重新登录
            request.getSession().removeAttribute("user");
        } else {
            tip = UrlCodeUtils.getUrlString("更新教师信息失败");
        }
        return "redirect:admin_teacher?pageNumber=" + request.getSession().getAttribute("systemPageNumber") + "&tip=" + tip;
    }

    @RequestMapping("add_teacher")
    public String add_teacher(Teacher teacher, Model model, HttpSession session) {
        if (null == teacher.getIs_admin()) {
            teacher.setIs_admin("null");
        }
        String tip = "";
        if (teacherServiceImpl.insertTeacher(teacher) == 1) {
            tip = UrlCodeUtils.getUrlString("新增教师信息成功");
        } else {
            tip = UrlCodeUtils.getUrlString("新增教师信息失败");
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }

    @RequestMapping("selectTeacher")
    public String selectTeacher(Model model, String condition) {
        if (condition.length() == 0) {
            return "redirect:admin_teacher?pageNumber=1";
        }
        List<Teacher> teachers = new ArrayList<>();
        teachers.addAll(teacherServiceImpl.selectByUsername(condition));
        teachers.addAll(teacherServiceImpl.selectByTeacherId(condition));
        model.addAttribute("condition", condition);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setTotal(teachers.size());
        pageInfo.setList(teachers);
        model.addAttribute("pageInfo", pageInfo);
        return "admin_teacher";
    }

    @RequestMapping("asAdmin")
    public String asAdmin(Model model, Integer id, HttpSession session) {
        String tip = "";
        if (teacherServiceImpl.asAdmin(id) > 0) {
            tip = UrlCodeUtils.getUrlString("设置管理员成功");
        } else {
            tip = UrlCodeUtils.getUrlString("设置管理员失败");
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }

    @RequestMapping("cancelAdmin")
    public String cancelAdmin(Model model, Integer id, HttpSession session) {
        String tip = "";
        if (teacherServiceImpl.cancelAdmin(id) > 0) {
            tip = UrlCodeUtils.getUrlString("取消管理员成功");
        } else {
            tip = UrlCodeUtils.getUrlString("取消管理员失败");
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }
}