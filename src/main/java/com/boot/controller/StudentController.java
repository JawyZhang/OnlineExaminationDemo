package com.boot.controller;

import com.boot.pojo.Exam;
import com.boot.pojo.Message;
import com.boot.pojo.Teacher;
import com.boot.service.StudentService;
import com.boot.service.TeacherService;
import com.boot.utils.FileNameUtils;
import com.boot.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author Mango
 * @Date 2020-04-21 18:20
 */
@Controller
public class StudentController {
    @Value("${upload_path}")
    String upload_path;
    @Autowired
    private StudentService studentServiceImpl;
    @Autowired
    private TeacherService teacherServiceImpl;

    @RequestMapping("student_home")
    public String student_home(Model model, String tip, int stu_id) {
        model.addAttribute("tip", tip);
        List<Exam> operating = new ArrayList<>();
        List<Exam> finished = new ArrayList<>();
        List<Exam> exams = studentServiceImpl.selectStudentAllExam(stu_id);
        for (Exam exam : exams) {
            if (exam.getStatus() == 1) {
                long currentTime = System.currentTimeMillis();
                long finishTime = TimeUtils.getMillisTime(exam.getFinish_time());
                exam.setRest_time((finishTime - currentTime) / 1000 / 60 + "分钟");
                operating.add(exam);
            } else if (exam.getStatus() == 2 || exam.getStatus() == 3) {
                finished.add(exam);
            }
        }
        model.addAttribute("operating", operating);
        model.addAttribute("finished", finished);
        return "student_home";
    }

    @RequestMapping("submit_paper")
    public void submit_paper(HttpServletRequest request, HttpServletResponse response, int exam_id, int stu_id, String stu_no, String username, String exam_name, MultipartFile filename) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            if ("true".equals(request.getSession().getAttribute("isStudent"))) {
                String ip = request.getRemoteAddr();
                String stu_ip = teacherServiceImpl.selectStudentIp(exam_id, stu_id);
                System.out.println(stu_ip + "\t" + ip);
                if (!(stu_ip == null || stu_ip.equals("")) && !ip.equals(stu_ip)) {
                    //如果查询到学生的绑定IP信息，并且跟当前IP不符合不会下载试卷，即不可参加该场考试
                    outputStream.write(("<html><head><script>" +
                            "var res=confirm('当前机器的IP和初次使用机器的IP不一致，答案提交失败！！！');if(res==true||res==false){history.back();}" +
                            "</script></head><body></body></html>").getBytes("GBK"));
                    return;
                }
                String storagePath = exam_name + "/" + exam_name + "-" + stu_no + "-" + username + FileNameUtils.getExtName(filename.getOriginalFilename());
                String absolutePath = upload_path + storagePath;
                File root = new File(upload_path + exam_name);
                if (!root.exists()) {
                    root.mkdirs();
                }
                filename.transferTo(new File(absolutePath));
                studentServiceImpl.updateStudentPaperAndSubmitStatus(stu_id, exam_id, storagePath);
                outputStream.write(("<html><head><script>" +
                        "var res=confirm('提交成功');if(res==true||res==false){history.back();}" +
                        "</script></head><body></body></html>").getBytes("GBK"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("getExamMessage")
    public String getExamMessage(Model model, int exam_id, int stu_id) {
        List<Message> messages = studentServiceImpl.selectExamMessage(exam_id);
        model.addAttribute("messages", messages);
        model.addAttribute("stu_id", stu_id);
        return "student_message";
    }
}
