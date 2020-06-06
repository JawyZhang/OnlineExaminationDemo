package com.boot.service.impl;

import com.boot.mapper.StudentMapper;
import com.boot.pojo.Exam;
import com.boot.pojo.Message;
import com.boot.pojo.Student;
import com.boot.utils.MD5Utils;
import com.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-07 18:11
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student selectByUsernameAndPassword(Student student) {
        List<Student> student1List = studentMapper.selectByUsername(student.getUsername());
        Student student2 = null;
        for (Student student1:student1List) {
            if(MD5Utils.verify(student.getPassword(), String.valueOf(student1.getId()), student1.getPassword())){
                student2 = student1;
            }
        }
        return student2;
    }

    @Override
    public Student selectByIdAndPassword(Student student) {
        List<Student> student1List = studentMapper.selectById(student.getStu_no());
        Student student2 = null;
        for (Student student1:student1List) {
            if(MD5Utils.verify(student.getPassword(), String.valueOf(student1.getId()), student1.getPassword())){
                student2 = student1;
            }
        }
        return student2;
    }

    @Override
    public Integer updateStudentById(Student student) {
        student.setPassword(MD5Utils.md5(student.getPassword(), String.valueOf(student.getId())));
        return studentMapper.updateStudentById(student);
    }

    @Override
    public Integer updateStudentStatus(int status, int id) {
        return studentMapper.updateStudentStatus(status, id);
    }

    @Override
    public List<Exam> selectStudentAllExam(int stu_id) {
        return studentMapper.selectStudentAllExam(stu_id);
    }

    @Override
    public Integer updateStudentPaperAndSubmitStatus(int stu_id, int exam_id, String paper_path) {
        return studentMapper.updateStudentPaperAndSubmitStatus(stu_id, exam_id, paper_path);
    }

    @Override
    public List<Message> selectExamMessage(int exam_id) {
        return studentMapper.selectExamMessage(exam_id);
    }
}
