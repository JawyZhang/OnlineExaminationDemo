package com.boot.service.impl;

import com.boot.mapper.TeacherMapper;
import com.boot.pojo.*;
import com.boot.service.TeacherService;
import com.boot.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-03 18:46
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectAll() {
        return teacherMapper.selectAll();
    }

    @Override
    public PageInfo selectAllByPage(PageInfo pageInfo) {
        pageInfo.setPageStart((pageInfo.getPageNumber() - 1) * pageInfo.getPageSize());
        pageInfo.setTotal(teacherMapper.selectCount());
        int count = pageInfo.getTotal();
        pageInfo.setTotalPage(count % pageInfo.getPageSize() == 0 ? count / pageInfo.getPageSize() : count / pageInfo.getPageSize() + 1);
        pageInfo.setList(teacherMapper.selectAllByPage(pageInfo));
        return pageInfo;
    }

    @Override
    public Teacher selectByUsernameAndPassword(Teacher teacher) {
        List<Teacher> teacher1List = teacherMapper.loginByUsername(teacher.getUsername());
        Teacher teacher2 = null;
        for (Teacher teacher1 : teacher1List) {
            if (MD5Utils.verify(teacher.getPassword(), String.valueOf(teacher1.getId()), teacher1.getPassword())) {
                teacher2 = teacher1;
            }
        }
        return teacher2;
    }

    @Override
    public Teacher selectByTeacherIdAndPassword(Teacher teacher) {
        List<Teacher> teacher1List = teacherMapper.selectByTeacherId(teacher.getTeacher_id());
        Teacher teacher2 = null;
        for (Teacher teacher1 : teacher1List) {
            if (MD5Utils.verify(teacher.getPassword(), String.valueOf(teacher1.getId()), teacher1.getPassword())) {
                teacher2 = teacher1;
            }
        }
        return teacher2;
    }

    @Override
    public List<Teacher> selectByUsername(String username) {
        return teacherMapper.selectByUsername(username);
    }

    @Override
    public List<Teacher> selectByTeacherId(String teacher_id) {
        return teacherMapper.selectByTeacherId(teacher_id);
    }

    @Override
    public Integer asAdmin(Integer id) {
        return teacherMapper.asAdmin(id);
    }

    @Override
    public Integer cancelAdmin(Integer id) {
        return teacherMapper.cancelAdmin(id);
    }

    @Override
    public Integer insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Integer updateTeacherInfo(Teacher teacher) {
        teacher.setPassword(MD5Utils.md5(teacher.getPassword(), String.valueOf(teacher.getId())));
        return teacherMapper.updateTeacherInfo(teacher);
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        teacher.setPassword(MD5Utils.md5(teacher.getPassword(), String.valueOf(teacher.getId())));
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Integer deleteTeacher(Integer id) {
        return teacherMapper.deleteTeacher(id);
    }

    @Override
    public Integer addExam(Exam exam) {
        return teacherMapper.addExam(exam);
    }

    @Override
    public Integer updateExam(Exam exam) {
        return teacherMapper.updateExam(exam);
    }

    @Override
    public Integer setExamPaperPath(int exam_id, String paper_path) {
        return teacherMapper.setExamPaperPath(exam_id, paper_path);
    }

    @Override
    public List<Exam> selectExamsByUserName(String username, int status) {
        return teacherMapper.selectExamsByUserName(username, status);
    }

    @Override
    public List<Exam> selectAllExams(int status) {
        return teacherMapper.selectAllExams(status);
    }

    @Override
    public List<Exam> selectStartExamsByUserName(String username, int status) {
        return teacherMapper.selectStartExamsByUserName(username, status);
    }

    @Override
    public List<Exam> selectAllStartExams(int status) {
        return teacherMapper.selectAllStartExams(status);
    }

    @Override
    public String selectExamNameById(int exam_id) {
        return teacherMapper.selectExamNameById(exam_id);
    }

    @Override
    public Integer changeExamStatus(int exam_id, int status) {
        return teacherMapper.updateExamStatus(exam_id, status);
    }

    @Override
    public Integer startExam(int exam_id, String start_time) {
        return teacherMapper.startExamStatus(exam_id, 1, start_time);
    }

    @Override
    public Integer finishExam(int exam_id) {
        return teacherMapper.updateExamStatus(exam_id, 2);
    }

    @Override
    public Integer downloadExam(int exam_id) {
        return teacherMapper.updateExamStatus(exam_id, 3);
    }

    @Override
    public Integer cleanExam(int exam_id) {
        return teacherMapper.updateExamStatus(exam_id, 4);
    }

    @Override
    public Integer deleteExam(int exam_id) {
        return teacherMapper.updateExamStatus(exam_id, 5);
    }

    @Override
    public Integer selectExamStuInfo(int exam_id, int stu_id) {
        return teacherMapper.selectExamStuInfo(exam_id, stu_id);
    }

    @Override
    public Integer addExamStuInfo(int exam_id, int stu_id) {
        return teacherMapper.addExamStuInfo(exam_id, stu_id);
    }

    @Override
    public Integer deleteExamStuInfo(int exam_id, int stu_id) {
        return teacherMapper.deleteExamStuInfo(exam_id, stu_id);
    }

    @Override
    public Student selectStuByNo(String stu_no) {
        return teacherMapper.selectStuByNo(stu_no);
    }

    @Override
    public Integer addStudent(Student student) {
        teacherMapper.firstOfAddStudent(student);
        student = teacherMapper.locationOfAddStudent(student);
        student.setPassword(MD5Utils.md5(student.getPassword(), String.valueOf(student.getId())));
        return teacherMapper.Md5OfAddStudent(student);
    }

    @Override
    public List<Student> selectExamAllStudents(int exam_id) {
        return teacherMapper.selectExamAllStudents(exam_id);
    }

    @Override
    public PageInfo selectExamAllStudentsByPage(int exam_id, PageInfo pageInfo) {
        pageInfo.setPageStart((pageInfo.getPageNumber() - 1) * pageInfo.getPageSize());
        pageInfo.setTotal(teacherMapper.selectExamAllStudentsCount(exam_id));
        int count = pageInfo.getTotal();
        pageInfo.setTotalPage(count % pageInfo.getPageSize() == 0 ? count / pageInfo.getPageSize() : count / pageInfo.getPageSize() + 1);
        List<Student> students = teacherMapper.selectExamAllStudentsByPage(exam_id, pageInfo.getPageStart(), pageInfo.getPageSize());
        List<Integer> ids = teacherMapper.selectExamAllSubmitStu_id(exam_id);
        for (Student student : students) {
            if (ids.contains(student.getId())) {
                student.setStatus(2);
            }
        }
        pageInfo.setList(students);
        return pageInfo;
    }

    @Override
    public String selectStudentIp(int exam_id, int stu_id) {
        return teacherMapper.selectStudentIp(exam_id, stu_id);
    }

    @Override
    public Integer updateIP(int stu_id, int exam_id, String ip) {
        return teacherMapper.updateIP(stu_id, exam_id, ip);
    }

    @Override
    public List<Student> selectExamStudentByNoAndUsernameAndClass(int exam_id, String stu_no, String username, String class_room) {
        return teacherMapper.selectExamStudentByNoAndUsernameAndClass(exam_id, stu_no, username, class_room);
    }

    @Override
    public int updateStudentExamInfo(int exam_id, Student student) {
        return teacherMapper.updateStudentExamInfo(exam_id, student.getId(), student.getStu_no(), student.getUsername(), student.getClass_room(), student.getIp());
    }

    @Override
    public List<Exam> selectScheduleExam() {
        return teacherMapper.selectScheduleExam();
    }

    @Override
    public Integer deleteExamStudent(int exam_id) {
        return teacherMapper.deleteExamStudent(exam_id);
    }

    @Override
    public List<Message> selectExamMessage(int exam_id) {
        return teacherMapper.selectExamMessage(exam_id);
    }

    @Override
    public Integer deleteMessage(int id) {
        return teacherMapper.deleteMessage(id);
    }

    @Override
    public Integer addMessage(int exam_id, String time, String detail) {
        return teacherMapper.addMessage(exam_id, time, detail);
    }

    @Override
    public Exam selectExam(String name) {
        return teacherMapper.selectExam(name);
    }

    @Override
    public Integer deleteAllMessage(int exam_id) {
        return teacherMapper.deleteAllMessage(exam_id);
    }

    @Override
    public Integer selectAnswersCount(int exam_id) {
        return teacherMapper.selectAnswersCount(exam_id);
    }
}
