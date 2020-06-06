package com.boot.mapper;

import com.boot.pojo.Exam;
import com.boot.pojo.Message;
import com.boot.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-07 16:57
 */
@Repository
public interface StudentMapper {
    /**
     * 根据用户名和密码查询学生信息
     *
     * @return
     */
    @Select("select * from student where username=#{username}")
    List<Student> selectByUsername(String username);

    /**
     * 根据学号和密码查询学生信息
     *
     * @return
     */
    @Select("select * from student where stu_no=#{stu_no}")
    List<Student> selectById(String student_id);

    /**
     * 根据传入的学生信息更新对应的学生信息
     *
     * @param student
     * @return
     */
    @Update("update student set username=#{username},password=#{password} where id=#{id}")
    Integer updateStudentById(Student student);

    /**
     * 更新学生状态
     *
     * @param status
     * @param id
     * @return
     */
    @Update("update student set status=#{status} where id=#{id}")
    Integer updateStudentStatus(int status, int id);

    /**
     * 根据学生ID获取该学生的所有考试信息
     *
     * @param stu_id
     * @return
     */
    @Results({
            @Result(property = "exam_id", column = "exam_id", id = true),
            @Result(property = "submit_status", one = @One(select = "selectSubmitStatus"), column = "{stu_id=stu_id,exam_id=exam_id}")
    })
    @Select("select *,#{stu_id} stu_id from exams where exam_id in (select exam_id from exam_student where stu_id=#{stu_id})")
    List<Exam> selectStudentAllExam(int stu_id);

    /**
     * 用于配合selectStudentAllExam查询考生的提交情况
     *
     * @param stu_id
     * @param exam_id
     * @return
     */
    @Select("select status from exam_student where stu_id=#{stu_id} and exam_id=#{exam_id}")
    Integer selectSubmitStatus(int stu_id, int exam_id);

    /**
     * 记录考生的试卷提交的试卷信息和提交状态
     *
     * @param stu_id
     * @param exam_id
     * @param paper_path
     * @return
     */
    @Update("update exam_student set status=1,paper_path=#{paper_path} where exam_id=#{exam_id} and stu_id=#{stu_id}")
    Integer updateStudentPaperAndSubmitStatus(int stu_id, int exam_id, String paper_path);

    /**
     * 查询指定考试的所有通知信息
     *
     * @param exam_id
     * @return
     */
    @Select("select * from messages where exam_id=#{exam_id}")
    List<Message> selectExamMessage(int exam_id);
}
