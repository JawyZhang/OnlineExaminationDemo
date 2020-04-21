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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Mango
 * @Date 2020-04-07 21:14
 */
@Controller
public class SystemController {
    private static boolean flag = true;
    private static Map<String, Object> system = new HashMap<>(5);
    @Autowired
    private TeacherService teacherServiceImpl;

    /**
     * 系统配置
     */
    @RequestMapping("admin_system")
    public String admin_system(Model model, HttpSession session) {
        if (flag) {
            system.put("taskTime", 1);
            system.put("systemPageSize", 10);
            session.setAttribute("systemPageSize", 10);
            system.put("examBeginTime", 60);
            system.put("minFileSize", 1024);
            system.put("maxFileSize", 102400);
            system.put("teacherClear", 0);
            session.setAttribute("system", system);
            flag = false;
        }
        return "admin_system";
    }

    /**
     * 系统配置更新
     */
    @RequestMapping("update_system")
    public String update_system(Model model, HttpSession session,
                                @RequestParam(defaultValue = "1") Integer taskTime,
                                @RequestParam(defaultValue = "10") Integer systemPageSize,
                                @RequestParam(defaultValue = "60") Integer examBeginTime,
                                @RequestParam(defaultValue = "1024") Integer minFileSize,
                                @RequestParam(defaultValue = "102400") Integer maxFileSize,
                                String teacherClear) {
        system.put("taskTime", taskTime);
        system.put("systemPageSize", systemPageSize);
        session.setAttribute("systemPageSize", systemPageSize);
        system.put("examBeginTime", examBeginTime);
        system.put("minFileSize", minFileSize);
        system.put("maxFileSize", maxFileSize);
        system.put("teacherClear", "lock".equals(teacherClear) ? 1 : 0);
        model.addAttribute("system", system);
        return "admin_system";
    }

    /**
     * 管理员之教师管理主页
     */
    @RequestMapping("admin_teacher")
    public String admin_teacher(Model model, HttpSession session,
                                @RequestParam(defaultValue = "1") Integer pageNumber,
                                @RequestParam(defaultValue = "") String tip/*tip用于接收其他控制器处理后的提示信息*/) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize((Integer) session.getAttribute("systemPageSize"));
        //使用分页查询
        model.addAttribute("pageInfo", teacherServiceImpl.selectAllByPage(pageInfo));
        //用于给其他控制器使用的属性：当前前台的页面值
        session.setAttribute("systemPageNumber", pageNumber);
        if (tip.length() != 0) {
            model.addAttribute("tip", tip);
        }
        return "admin_teacher";
    }

    /**
     * 删除单个教师
     */
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

    /**
     * 更新教师信息
     */
    @RequestMapping("update_teacher")
    public String update_teacher(Teacher teacher, Model model, HttpSession session) {
        //如果前台未勾选设置为管理员，则将管理员属性置空
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

    /**
     * 新增教师信息
     */
    @RequestMapping("add_teacher")
    public String add_teacher(Teacher teacher, Model model, HttpSession session) {
        //如果前台未勾选设置为管理员，则将管理员属性置空
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

    /**
     * 按条件筛选教师信息
     */
    @RequestMapping("selectTeacher")
    public String selectTeacher(Model model, String condition) {
        //如果条件框清空，则返回教师管理第一页
        if (condition.length() == 0) {
            return "redirect:admin_teacher?pageNumber=1";
        }
        List<Teacher> teachers = new ArrayList<>();
        //添加所有通过教师姓名查询到的教师信息
        teachers.addAll(teacherServiceImpl.selectByUsername(condition));
        //添加所有通过教师工号查询到的教师信息
        teachers.addAll(teacherServiceImpl.selectByTeacherId(condition));
        model.addAttribute("condition", condition);
        //按分页格式传递结果到前台
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setTotal(teachers.size());
        pageInfo.setList(teachers);
        model.addAttribute("pageInfo", pageInfo);
        return "admin_teacher";
    }

    /**
     * 单个设置为管理员
     */
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

    /**
     * 单个取消管理员
     */
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

    @RequestMapping("asAdminGroup")
    public String asAdminGroup(@RequestParam(value = "id[]") Integer[] ids, Model model, Integer id, HttpSession session) {
        String tip = "";
        for (int i = 0; i < ids.length; i++) {
            if (teacherServiceImpl.asAdmin(ids[i]) > 0) {
                tip += UrlCodeUtils.getUrlString(ids[i] + "设置管理员成功");
            } else {
                tip += UrlCodeUtils.getUrlString(ids[i] + "设置管理员失败");
            }
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }

    @RequestMapping("cancelAdminGroup")
    public String cancelAdminGroup(@RequestParam(value = "id[]") Integer[] ids, Model model, Integer id, HttpSession session) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String tip = "";
        for (int i = 0; i < ids.length; i++) {
            if (teacherServiceImpl.cancelAdmin(ids[i]) > 0) {
                tip += UrlCodeUtils.getUrlString(ids[i] + "设置管理员成功");
            } else {
                tip += UrlCodeUtils.getUrlString(ids[i] + "设置管理员失败");
            }
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }

    @RequestMapping("deleteAdminGroup")
    public String deleteAdminGroup(@RequestParam(value = "id[]") Integer[] ids, Model model, Integer id, HttpSession session) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String tip = "";
        for (int i = 0; i < ids.length; i++) {
            if (teacherServiceImpl.deleteTeacher(ids[i]) > 0) {
                tip += UrlCodeUtils.getUrlString(ids[i] + "设置管理员成功");
            } else {
                tip += UrlCodeUtils.getUrlString(ids[i] + "设置管理员失败");
            }
        }
        return "redirect:admin_teacher?pageNumber=" + session.getAttribute("systemPageNumber") + "&tip=" + tip;
    }
}
