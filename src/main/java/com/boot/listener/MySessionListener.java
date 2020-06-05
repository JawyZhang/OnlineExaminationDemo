package com.boot.listener;

import com.boot.controller.SystemController;
import com.boot.pojo.Student;
import com.boot.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author Mango
 * @Date 2020-06-02 17:47
 */
@WebListener
public class MySessionListener implements HttpSessionListener {
    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        if (se.getSession().getServletContext().getAttribute("system") == null) {
            se.getSession().getServletContext().setAttribute("system", SystemController.getSystem());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Object user = se.getSession().getAttribute("user");
        if (user instanceof Student) {
            System.out.println(((Student) user).getUsername() + "学生会话过期");
            studentServiceImpl.updateStudentStatus(0, ((Student) user).getId());
            System.out.println("已清除登录状态");
        }
    }
}
