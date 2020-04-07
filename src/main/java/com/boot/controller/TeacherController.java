package com.boot.controller;

import com.boot.pojo.Teacher;
import com.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String admin_teacher(Model model) {
        model.addAttribute("teachers", teacherServiceImpl.selectAll());
        return "admin_teacher";
    }

    @RequestMapping("delete_teacher")
    public String delete_teacher(Integer id, Model model) {
        if (teacherServiceImpl.deleteTeacher(id) == 1) {
            model.addAttribute("tip", "删除成功");
        } else {
            model.addAttribute("tip", "删除失败");
        }
        model.addAttribute("teachers", teacherServiceImpl.selectAll());
        return "redirect:admin_teacher";
    }

    @RequestMapping("update_teacher")
    public String update_teacher(Teacher teacher, Model model) {
        //新增时勾选为管理员读取到on
        if ("on".equals(teacher.getIs_admin())) {
            teacher.setIs_admin(1 + "");
        } else {
            teacher.setIs_admin(0 + "");
        }
        if (teacher.getPassword().length() == 0) {//如果密码长度为0，代表密码不变，将密码项置空
            teacher.setPassword(null);
        }
        if (teacherServiceImpl.updateTeacherInfo(teacher) == 1) {
            model.addAttribute("tip", "更新教师信息成功");
        } else {
            model.addAttribute("tip", "更新教师信息失败");
        }
        model.addAttribute("teachers", teacherServiceImpl.selectAll());
        return "redirect:admin_teacher";
    }

    @RequestMapping("update_teacher_password")
    public String update_teacher_password(Teacher teacher, Model model) {
        if (teacherServiceImpl.updateTeacher(teacher) == 1) {
            model.addAttribute("tip", "更新教师信息成功");
        } else {
            model.addAttribute("tip", "更新教师信息失败");
        }
        model.addAttribute("teachers", teacherServiceImpl.selectAll());
        return "redirect:admin_teacher";
    }

    @RequestMapping("add_teacher")
    public String add_teacher(Teacher teacher, Model model) {
        //新增时勾选为管理员读取到on
        if ("on".equals(teacher.getIs_admin())) {
            teacher.setIs_admin(1 + "");
        } else {
            teacher.setIs_admin(0 + "");
        }
        if (teacherServiceImpl.insertTeacher(teacher) == 1) {
            model.addAttribute("tip", "新增教师信息成功");
        } else {
            model.addAttribute("tip", "新增教师信息失败");
        }
        model.addAttribute("teachers", teacherServiceImpl.selectAll());
        return "redirect:admin_teacher";
    }

    @RequestMapping("selectTeacher")
    public String selectTeacher(Model model, String condition) {
        List<Teacher> teachers = new ArrayList<>();
        if (condition.length() == 0) {
            teachers.addAll(teacherServiceImpl.selectAll());
        } else {
            teachers.addAll(teacherServiceImpl.selectByUsername(condition));
            teachers.addAll(teacherServiceImpl.selectByTeacherId(condition));
        }
        model.addAttribute("teachers", teachers);
        return "admin_teacher";
    }

    @RequestMapping("asAdmin")
    public String asAdmin(Model model, Integer id) {
        if (teacherServiceImpl.asAdmin(id) > 0) {
            model.addAttribute("tip", "设置管理员成功");
            model.addAttribute("teachers", teacherServiceImpl.selectAll());
        } else {
            model.addAttribute("tip", "设置管理员失败成功");
        }
        return "redirect:admin_teacher";
    }

    @RequestMapping("cancelAdmin")
    public String cancelAdmin(Model model, Integer id) {
        if (teacherServiceImpl.cancelAdmin(id) > 0) {
            model.addAttribute("tip", "取消管理员成功");
            model.addAttribute("teachers", teacherServiceImpl.selectAll());
        } else {
            model.addAttribute("tip", "取消管理员失败成功");
        }
        return "redirect:admin_teacher";
    }
}