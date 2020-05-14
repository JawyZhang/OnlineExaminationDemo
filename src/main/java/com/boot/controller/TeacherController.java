package com.boot.controller;

import com.boot.pojo.Exam;
import com.boot.pojo.Teacher;
import com.boot.service.TeacherService;
import com.boot.utils.FileNameUtils;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author Mango
 * @Date 2020-04-03 18:43
 */
@Controller
public class TeacherController {
    @Value("${upload_path}")
    String upload_path;
    @Autowired
    private TeacherService teacherServiceImpl;

    @RequestMapping("teacher_home")
    public String teacher_home(Model model, String tip) {
        model.addAttribute("tip", tip);
        return "teacher_home";
    }

    @RequestMapping("addExam")
    public String addExam(Model model, Exam exam, String is_auto_begin) {
        if ("on".equals(is_auto_begin)) {
            exam.setIs_auto_begin(true);
        }
        if (teacherServiceImpl.addExam(exam) > 0) {
            model.addAttribute("tip", "添加考试信息成功");
        } else {
            model.addAttribute("tip", "添加考试信息失败");
        }
        return "redirect:teacher_before?username=" + exam.getCreater();
    }

    @RequestMapping("uploadPaper")
    public String uploadPaper(HttpServletRequest request, int exam_id, MultipartFile filename) {
        if (filename.getSize() < (int) SystemController.getSystem().get("minFileSize")) {
            request.setAttribute("tip", "上传文件过小！！！");
        } else if (filename.getSize() > (int) SystemController.getSystem().get("maxFileSize")) {
            request.setAttribute("tip", "上传文件过大！！！");
        } else {
            String storagePath = UUID.randomUUID() + FileNameUtils.getExtName(filename.getOriginalFilename());
            String absolutePath = upload_path + storagePath;
            try {
                filename.transferTo(new File(absolutePath));
                teacherServiceImpl.setExamPaperPath(exam_id, storagePath);
                request.setAttribute("tip", "上传成功");
            } catch (IOException e) {
                System.out.println("上传失败，失败原因：" + e.getMessage());
                request.setAttribute("tip", "上传失败");
            }
        }
        return "redirect:teacher_before?username=" + ((Teacher) (request.getSession().getAttribute("user"))).getUsername();
    }

    @RequestMapping("downloadPaper")
    public void downloadPaper(HttpServletResponse response, String exam_name, String file) {
        //response.setHeader("Content-Disposition", "attachment;filename=" + exam_name + FileNameUtils.getExtName(file));
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(upload_path + file));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[512];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("deletePaper")
    public String deletePaper(HttpServletRequest request, int exam_id, String file) {
        if (teacherServiceImpl.setExamPaperPath(exam_id, "") > 0) {
            new File(upload_path + file).delete();
            request.setAttribute("tip", "删除成功");
        } else {
            request.setAttribute("tip", "删除失败");
        }
        return "redirect:teacher_before?username=" + ((Teacher) (request.getSession().getAttribute("user"))).getUsername();
    }

    @RequestMapping("teacher_before")
    public String teacher_before(Model model, String username) {
        List<Exam> exams = new ArrayList<>();
        if ("admin".equals(username)) {
            exams = teacherServiceImpl.selectAllExams();
        } else {
            exams = teacherServiceImpl.selectExamsByUserName(username);
        }
        model.addAttribute("exams", exams);
        return "teacher_before";
    }

    @RequestMapping("update_exam")
    public String update_exam(HttpServletRequest request, int exam_id, String change_exam_name, String change_start_time, String change_finish_time, String change_is_auto_begin) {
        Exam exam = new Exam();
        exam.setExam_id(exam_id);
        exam.setExam_name(change_exam_name);
        exam.setStart_time(change_start_time);
        exam.setFinish_time(change_finish_time);
        if ("on".equals(change_is_auto_begin)) {
            exam.setIs_auto_begin(true);
        }
        System.out.println(exam);
        if (teacherServiceImpl.updateExam(exam) > 0) {
            request.setAttribute("tip", "修改成功");
        } else {
            request.setAttribute("tip", "修改失败");
        }
        return "redirect:teacher_before?username=" + ((Teacher) (request.getSession().getAttribute("user"))).getUsername();
    }
}