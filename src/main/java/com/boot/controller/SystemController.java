package com.boot.controller;

import com.boot.pojo.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Mango
 * @Date 2020-04-07 21:14
 */
@Controller
public class SystemController {
    @RequestMapping("admin_system")
    public String admin_system(Model model, HttpSession session) {
        Map<String, Object> system = new HashMap<>();
        ServletContext servletContext = session.getServletContext();
        system.put("pageSize", ((PageInfo) servletContext.getAttribute("pageInfo")).getPageSize());
        model.addAttribute("system", system);
        return "admin_system";
    }

    @RequestMapping("update_system")
    public String update_system(Model model, HttpSession session,@RequestParam(defaultValue = "10") Integer pageSize) {
        ((PageInfo)session.getServletContext().getAttribute("pageInfo")).setPageSize(pageSize);
        Map<String, Object> system = new HashMap<>();
        system.put("pageSize",pageSize);
        model.addAttribute("system", system);
        return "admin_system";
    }
}
