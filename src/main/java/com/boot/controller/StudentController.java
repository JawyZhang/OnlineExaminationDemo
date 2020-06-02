package com.boot.controller;

import com.boot.pojo.Exam;
import com.boot.pojo.Teacher;
import com.boot.service.StudentService;
import com.boot.utils.FileNameUtils;
import com.boot.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
                exam.setFinish_time((finishTime - currentTime) / 1000 / 60 + "分钟");
                operating.add(exam);
            } else {
                finished.add(exam);
            }
        }
        model.addAttribute("operating", operating);
        model.addAttribute("finished", finished);
        return "student_home";
    }

    @RequestMapping("submit_paper")
    public String submit_paper(int exam_id, int stu_id, String stu_no, String username, String exam_name, MultipartFile filename) {
        String storagePath = exam_name + "/" + exam_name + "-" + stu_no + "-" + username + FileNameUtils.getExtName(filename.getOriginalFilename());
        String absolutePath = upload_path + storagePath;
        try {
            File root = new File(upload_path + exam_name);
            if (!root.exists()) {
                root.mkdirs();
            }
            filename.transferTo(new File(absolutePath));
            studentServiceImpl.updateStudentPaperAndSubmitStatus(stu_id, exam_id, storagePath);
        } catch (IOException e) {
            System.out.println("上传失败，失败原因：" + e.getMessage());
        }
        return "redirect:student_home?stu_id=" + stu_id;
    }
}
