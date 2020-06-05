package com.boot.service;

import com.boot.pojo.Exam;
import com.boot.pojo.Message;
import com.boot.pojo.Student;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-07 18:10
 */
public interface StudentService {
    /**
     * 根据用户名和密码查询学生信息
     *
     * @return
     */
    Student selectByUsernameAndPassword(Student student);

    /**
     * 根据用户名和密码查询学生信息
     *
     * @return
     */
    Student selectByIdAndPassword(Student student);

    /**
     * 根据传入的学生信息更新对应的学生信息
     *
     * @param student
     * @return
     */
    Integer updateStudentById(Student student);

    /**
     * 更新学生状态
     * @param status
     * @param stu_id
     * @return
     */
    Integer updateStudentStatus(int status, int stu_id);

    /**
     * 根据学生ID获取该学生的所有考试信息
     * @param stu_id
     * @return
     */
    List<Exam> selectStudentAllExam(int stu_id);

    /**
     * 记录考生的试卷提交的试卷信息和提交状态
     *
     * @param stu_id
     * @param exam_id
     * @param paper_path
     * @return
     */
    Integer updateStudentPaperAndSubmitStatus(int stu_id, int exam_id, String paper_path);

    /**
     * 查询指定考试的所有通知信息
     *
     * @param exam_id
     * @return
     */
    List<Message> selectExamMessage(int exam_id);
}
