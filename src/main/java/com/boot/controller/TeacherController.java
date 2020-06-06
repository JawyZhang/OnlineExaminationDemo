package com.boot.controller;

import com.boot.pojo.*;
import com.boot.service.TeacherService;
import com.boot.utils.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.cs.ext.GBK;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        return "redirect:teacher_before?username=" + UrlCodeUtils.getUrlString(exam.getCreater());
    }

    @RequestMapping("uploadPaper")
    public String uploadPaper(HttpServletRequest request, int exam_id, MultipartFile filename) {
        if (filename.getSize() < (int) SystemController.getSystem().get("minFileSize")) {
            System.out.println("上传文件过小！！！");
            return "redirect:teacher_before?username=" + ((Teacher) (request.getSession().getAttribute("user"))).getUsername() + "&tip=" + UrlCodeUtils.getUrlString("文件过小");
        } else if (filename.getSize() > (int) SystemController.getSystem().get("maxFileSize")) {
            System.out.println("上传文件过大！！！");
            return "redirect:teacher_before?username=" + ((Teacher) (request.getSession().getAttribute("user"))).getUsername() + "&tip=" + UrlCodeUtils.getUrlString("文件过大");
        } else {
            String storagePath = UUID.randomUUID() + FileNameUtils.getExtName(filename.getOriginalFilename());
            String absolutePath = upload_path + storagePath;
            try {
                filename.transferTo(new File(absolutePath));
                teacherServiceImpl.setExamPaperPath(exam_id, storagePath);
            } catch (IOException e) {
                System.out.println("上传失败，失败原因：" + e.getMessage());
            }
        }
        return "redirect:teacher_before?username=" + UrlCodeUtils.getUrlString(((Teacher) (request.getSession().getAttribute("user"))).getUsername());
    }

    @RequestMapping("downloadPaper")
    public void downloadPaper(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "0") int stu_id, int exam_id, String exam_name, String file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(upload_path + file));
            ServletOutputStream outputStream = response.getOutputStream();
            //如果是学生下载试卷信息则将学生的主机IP与该考试绑定
            if ("true".equals(request.getSession().getAttribute("isStudent"))) {
                String ip = request.getRemoteAddr();
                String stu_ip = teacherServiceImpl.selectStudentIp(exam_id, stu_id);
                System.out.println(stu_ip + "\t" + ip);
                if (!(stu_ip == null || stu_ip.equals("")) && !ip.equals(stu_ip)) {
                    //如果查询到学生的绑定IP信息，并且跟当前IP不符合不会下载试卷，即不可参加该场考试
                    outputStream.write(("<html><head><script>" +
                            "var res=confirm('当前机器的IP和初次使用机器的IP不一致，试卷下载失败！！！');if(res==true||res==false){history.back();}" +
                            "</script></head><body></body></html>").getBytes("GBK"));
                    return;
                } else {
                    //如果未查询到学生的绑定IP信息，则在第一次下载试卷时绑定IP信息
                    teacherServiceImpl.updateIP(stu_id, exam_id, ip);
                }
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + UrlCodeUtils.getUrlString(exam_name) + FileNameUtils.getExtName(file));
            byte[] buffer = new byte[512];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
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
        return "redirect:teacher_before?username=" + UrlCodeUtils.getUrlString(((Teacher) (request.getSession().getAttribute("user"))).getUsername());
    }

    @RequestMapping("teacher_before")
    public String teacher_before(Model model, String username, @RequestParam(defaultValue = "") String tip) {
        List<Exam> exams = new ArrayList<>();
        if ("admin".equals(username)) {
            //查询状态为0(仅创建)的考试
            exams = teacherServiceImpl.selectAllExams(0);
        } else {
            //查询指定用户创建的状态为0(仅创建)的考试
            exams = teacherServiceImpl.selectExamsByUserName(username, 0);
        }
        model.addAttribute("exams", exams);
        if (tip.length() > 0) {
            model.addAttribute("tip", tip);
        }
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
        return "redirect:teacher_before?username=" + UrlCodeUtils.getUrlString(((Teacher) (request.getSession().getAttribute("user"))).getUsername());
    }

    @RequestMapping("start_exam")
    public String start_exam(HttpServletRequest request, int exam_id) {
        teacherServiceImpl.startExam(exam_id, TimeUtils.getCurrentTime());
        return "forward:teacher_within";
    }

    @RequestMapping("teacher_manage_student")
    public String teacher_manage_student(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNumber, int exam_id) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pageNumber);
        if (null != (Integer) request.getSession().getServletContext().getAttribute("systemPageSize")) {
            pageInfo.setPageSize((Integer) request.getSession().getServletContext().getAttribute("systemPageSize"));
        }
        request.setAttribute("pageInfo", teacherServiceImpl.selectExamAllStudentsByPage(exam_id, pageInfo));
        request.setAttribute("exam_id", exam_id);
        return "teacher_manage_student";
    }

    @RequestMapping("addStudentByExcel")
    public String addStudentByExcel(MultipartFile filename, int exam_id) {
        try {
            List<Student> students = ExcelUtils.readExcel(filename.getInputStream());
            for (Student temp : students) {
                Student student = teacherServiceImpl.selectStuByNo(temp.getStu_no());
                if (null == student) {
                    temp.setStatus(2);
                    teacherServiceImpl.addStudent(temp);
                    student = teacherServiceImpl.selectStuByNo(temp.getStu_no());
                }
                if (teacherServiceImpl.selectExamStuInfo(exam_id, student.getId()) == 0) {
                    teacherServiceImpl.addExamStuInfo(exam_id, student.getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:teacher_manage_student?exam_id=" + exam_id;
    }

    @RequestMapping("addStudent")
    public String addStudent(Student temp, int exam_id) {
        temp.setPassword(temp.getStu_no());
        Student student = teacherServiceImpl.selectStuByNo(temp.getStu_no());
        if (null == student) {
            temp.setStatus(2);
            teacherServiceImpl.addStudent(temp);
            student = teacherServiceImpl.selectStuByNo(temp.getStu_no());
        }
        if (teacherServiceImpl.selectExamStuInfo(exam_id, student.getId()) == 0) {
            teacherServiceImpl.addExamStuInfo(exam_id, student.getId());
        }
        return "redirect:teacher_manage_student?exam_id=" + exam_id;
    }

    @RequestMapping("deleteExamStudent")
    public String deleteExamStudent(int exam_id, int stu_id) {
        teacherServiceImpl.deleteExamStuInfo(exam_id, stu_id);
        return "forward:teacher_manage_student?exam_id=" + exam_id;
    }

    @RequestMapping("searchStudent")
    public String searchStudent(HttpServletRequest request, int exam_id, String stu_no, String username, String class_room) {
        List<Student> list = teacherServiceImpl.selectExamStudentByNoAndUsernameAndClass(exam_id, stu_no, username, class_room);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setList(list);
        pageInfo.setPageSize(list.size());
        pageInfo.setTotal(list.size());
        pageInfo.setTotalPage(1);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("exam_id", exam_id);
        Student search = new Student();
        search.setStu_no(stu_no);
        search.setUsername(username);
        search.setClass_room(class_room);
        request.setAttribute("search", search);
        return "teacher_manage_student";
    }

    @RequestMapping("teacher_within")
    public String teacher_within(Model model, HttpSession session) {
        List<Exam> exams = new ArrayList<>();
        String username = ((Teacher) (session.getAttribute("user"))).getUsername();
        if ("admin".equals(username)) {
            //查询状态为1(已开启)的考试
            exams = teacherServiceImpl.selectAllStartExams(1);
        } else {
            //查询指定用户创建的状态为1(已开启)的考试
            exams = teacherServiceImpl.selectStartExamsByUserName(username, 1);
        }
        model.addAttribute("exams", exams);
        return "teacher_within";
    }

    @RequestMapping("update_student_exam")
    public String update_student_exam(int exam_id, Student student) {
        teacherServiceImpl.updateStudentExamInfo(exam_id, student);
        return "forward:teacher_manage_student?exam_id=" + exam_id;
    }

    @RequestMapping("finish_exam")
    public String finish_exam(int exam_id) {
        teacherServiceImpl.finishExam(exam_id);
        return "forward:teacher_within";
    }

    @RequestMapping("teacher_after")
    public String teacher_after(Model model, HttpSession session) {
        List<Exam> exams = new ArrayList<>();
        String username = ((Teacher) (session.getAttribute("user"))).getUsername();
        if ("admin".equals(username)) {
            //查询状态为2(已结束),3(已下载答卷),4(已清理)的考试
            exams = teacherServiceImpl.selectAllStartExams(2);
            exams.addAll(teacherServiceImpl.selectAllStartExams(3));
            exams.addAll(teacherServiceImpl.selectAllStartExams(4));
        } else {
            //查询指定用户创建的状态为2(已结束),3(已下载答卷),4(已清理)的考试
            exams = teacherServiceImpl.selectStartExamsByUserName(username, 2);
            exams.addAll(teacherServiceImpl.selectStartExamsByUserName(username, 3));
            exams.addAll(teacherServiceImpl.selectStartExamsByUserName(username, 4));
        }
        model.addAttribute("exams", exams);
        return "teacher_after";
    }

    @RequestMapping("downloadAllAnswers")
    public void downloadAllAnswers(HttpServletResponse response, String exam_name, int exam_id) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            if (teacherServiceImpl.selectAnswersCount(exam_id) > 0) {
                String downloadFilename = URLEncoder.encode(exam_name + ".zip", "utf-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
                ZipOutputStream zos = new ZipOutputStream(outputStream);
                byte buffer[] = new byte[512];
                int len = 0;
                for (File file : new File(upload_path + exam_name).listFiles()) {
                    FileInputStream fis = new FileInputStream(file);
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    while ((len = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, len);
                    }
                    fis.close();
                }
                zos.flush();
                zos.close();
                teacherServiceImpl.downloadExam(exam_id);
            } else {
                outputStream.write(("<html><head><script>" +
                        "var res=confirm('没有考生上传试卷！');if(res==true||res==false){history.back();}" +
                        "</script></head><body></body></html>").getBytes("GBK"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("cleanExam")
    public String cleanExam(String exam_name, int exam_id, String paper_path, @RequestParam(defaultValue = "0") int is_admin) {
        System.out.println(upload_path + exam_name);
        System.out.println(upload_path + paper_path);
        //删除所有考生上传文件
        File dic = new File(upload_path + exam_name);
        if (dic.exists()) {
            for (File file : dic.listFiles()) {
                file.delete();
            }
            dic.delete();
        }
        //删除试卷文件
        File paper = new File(upload_path + paper_path);
        if (paper.exists()) {
            paper.delete();
        }
        //删除中间表中考试对应的所有考生
        teacherServiceImpl.deleteExamStudent(exam_id);
        //删除考试对应的通知信息
        teacherServiceImpl.deleteAllMessage(exam_id);
        //更改考试的状态
        teacherServiceImpl.cleanExam(exam_id);
        if (is_admin == 1) {
            return "redirect:admin_exam";
        }
        return "forward:teacher_after";
    }

    @RequestMapping("deleteExam")
    public String deleteExam(int exam_id, @RequestParam(defaultValue = "0") int is_admin) {
        teacherServiceImpl.deleteExam(exam_id);
        if (is_admin == 1) {
            return "redirect:admin_exam";
        }
        return "forward:teacher_after";
    }

    @RequestMapping("teacher_manage_message")
    public String teacher_manage_message(Model model, int exam_id, String exam_name) {
        List<Message> messages = teacherServiceImpl.selectExamMessage(exam_id);
        model.addAttribute("messages", messages);
        model.addAttribute("exam_id", exam_id);
        model.addAttribute("exam_name", exam_name);
        return "teacher_manage_message";
    }

    @RequestMapping("deleteMessage")
    public void deleteMessage(HttpServletResponse response, int id) {
        System.out.println(id);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            teacherServiceImpl.deleteMessage(id);
            outputStream.write("true".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("addMessage")
    public String addMessage(int exam_id, String exam_name, String detail) {
        teacherServiceImpl.addMessage(exam_id, TimeUtils.getCurrentHourAndMinute(), detail);
        return "redirect:teacher_manage_message?exam_id=" + exam_id + "&exam_name=" + UrlCodeUtils.getUrlString(exam_name);
    }

    @RequestMapping("deleteUselessExam")
    public String deleteUselessExam(HttpServletRequest request, int exam_id, String paper_path) {
        System.out.println(paper_path);
        teacherServiceImpl.deleteExam(exam_id);
        teacherServiceImpl.deleteExamStudent(exam_id);
        if (null != paper_path) {
            new File(upload_path + paper_path).delete();
        }
        return "redirect:teacher_before?username=" + UrlCodeUtils.getUrlString(((Teacher) (request.getSession().getAttribute("user"))).getUsername());
    }
}