package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Mango
 * @Date 2020-04-07 21:14
 */
@Controller
public class SystemController {
    private static boolean flag = true;
    private static Map<String, Object> system = new HashMap<>(5);

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
}
