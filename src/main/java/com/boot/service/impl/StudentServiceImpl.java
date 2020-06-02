package com.boot.service.impl;

import com.boot.mapper.StudentMapper;
import com.boot.pojo.Exam;
import com.boot.pojo.Student;
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
        return studentMapper.selectByUsernameAndPassword(student);
    }

    @Override
    public Integer updateStudentById(Student student) {
        return studentMapper.updateStudentById(student);
    }

    @Override
    public Integer updateStudentStatus(int status, int stu_id) {
        return studentMapper.updateStudentStatus(status, stu_id);
    }

    @Override
    public List<Exam> selectStudentAllExam(int stu_id) {
        return studentMapper.selectStudentAllExam(stu_id);
    }

    @Override
    public Integer updateStudentPaperAndSubmitStatus(int stu_id, int exam_id, String paper_path) {
        return studentMapper.updateStudentPaperAndSubmitStatus(stu_id, exam_id, paper_path);
    }
}
