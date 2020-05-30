package com.boot.service;

import com.boot.pojo.Exam;
import com.boot.pojo.PageInfo;
import com.boot.pojo.Student;
import com.boot.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-03 18:46
 */
public interface TeacherService {
    /**
     * 查询全部老师
     *
     * @return
     */
    List<Teacher> selectAll();

    /**
     * 分页查询
     *
     * @return
     */
    PageInfo selectAllByPage(PageInfo pageInfo);

    /**
     * 根据用户名和密码查询对应的教师信息
     *
     * @param teacher
     * @return
     */
    Teacher selectByUsernameAndPassword(Teacher teacher);

    /**
     * 根据工号和密码查询对应的教师信息
     *
     * @param teacher
     * @return
     */
    Teacher selectByTeacherIdAndPassword(Teacher teacher);

    /**
     * 根据用户名查询教师信息
     *
     * @param username
     * @return
     */
    List<Teacher> selectByUsername(String username);

    /**
     * 根据工号查询教师信息
     *
     * @param teacher_id
     * @return
     */
    List<Teacher> selectByTeacherId(String teacher_id);

    /**
     * 根据传入的ID值将指定教师设置为管理员
     *
     * @param id
     * @return
     */
    Integer asAdmin(Integer id);

    /**
     * 根据传入的ID值将指定教师取消管理员
     *
     * @param id
     * @return
     */
    Integer cancelAdmin(Integer id);

    /**
     * 根据传入的信息新建教师信息
     *
     * @param teacher
     * @return
     */
    Integer insertTeacher(Teacher teacher);

    /**
     * 根据传入的教师信息更新教师全部信息
     *
     * @param teacher
     * @return
     */
    Integer updateTeacherInfo(Teacher teacher);

    /**
     * 根据传入的教师信息更新教师用户名和密码信息
     *
     * @param teacher
     * @return
     */
    Integer updateTeacher(Teacher teacher);

    /**
     * 根据ID删除教师信息
     *
     * @param id
     * @return
     */
    Integer deleteTeacher(Integer id);

    /**
     * 添加考试信息
     *
     * @param exam
     * @return
     */
    Integer addExam(Exam exam);

    /**
     * 更改考试信息
     *
     * @param exam
     * @return
     */
    Integer updateExam(Exam exam);

    /**
     * 更新试卷信息
     *
     * @return
     */
    Integer setExamPaperPath(int exam_id, String paper_path);

    /**
     * 根据教师姓名获取该教师的所有指定状态的考试
     *
     * @param username
     * @return
     */
    List<Exam> selectExamsByUserName(String username, int status);

    /**
     * 获取所有指定状态的考试，管理员专用
     *
     * @return
     */
    List<Exam> selectAllExams(int status);

    /**
     * 根据教师姓名获取该教师的已开启的考试
     *
     * @param username
     * @return
     */
    List<Exam> selectStartExamsByUserName(String username, int status);

    /**
     * 获取所有已开启的考试，管理员专用
     *
     * @return
     */
    List<Exam> selectAllStartExams(int status);
    /**
     * 根据考试ID获取考试名称
     *
     * @param exam_id
     * @return
     */
    String selectExamNameById(int exam_id);

    /**
     * 开启考试，将考试信息的状态改为1
     *
     * @param exam_id
     * @return
     */
    Integer startExam(int exam_id);

    /**
     * 根据考试号和学生号查询中间表信息
     *
     * @param exam_id
     * @param stu_id
     * @return
     */
    Integer selectExamStuInfo(int exam_id, int stu_id);

    /**
     * 根据传入的考试ID和学生ID添加记录到中间表中
     *
     * @param exam_id
     * @param stu_id
     * @return
     */
    Integer addExamStuInfo(int exam_id, int stu_id);

    /**
     * 将指定考生从指定考试中删除
     *
     * @param exam_id
     * @param stu_id
     * @return
     */
    Integer deleteExamStuInfo(int exam_id, int stu_id);

    /**
     * 根据学号查询指定学生是否已经存在
     *
     * @param stu_no
     * @return
     */
    Student selectStuByNo(String stu_no);

    /**
     * 根据传入的信息新增一个学生信息
     *
     * @param student
     * @return
     */
    Integer addStudent(Student student);

    /**
     * 查询指定考试的所有考生
     *
     * @param exam_id
     * @return
     */
    List<Student> selectExamAllStudents(int exam_id);

    /**
     * 分页查询指定考试的所有考生
     *
     * @param exam_id
     * @param pageInfo
     * @return
     */
    PageInfo selectExamAllStudentsByPage(int exam_id, PageInfo pageInfo);

    /**
     * 绑定考生的主机IP
     * @param stu_id
     * @param exam_id
     * @param ip
     * @return
     */
    Integer updateIP(int stu_id,int exam_id,String ip);
}
