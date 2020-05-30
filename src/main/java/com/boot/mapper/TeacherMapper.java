package com.boot.mapper;

import com.boot.pojo.Exam;
import com.boot.pojo.PageInfo;
import com.boot.pojo.Student;
import com.boot.pojo.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-07 17:01
 */
@Repository
public interface TeacherMapper {
    /**
     * 查询全部老师
     *
     * @return
     */
    @Select("select * from teacher where teacher_id is not null")
    List<Teacher> selectAll();

    /**
     * 分页查询
     *
     * @return
     */
    @Select("select * from teacher where teacher_id is not null limit #{pageStart},#{pageSize}")
    List<Teacher> selectAllByPage(PageInfo pageInfo);

    /**
     * 查询表格中所有的教师总个数
     *
     * @return
     */
    @Select("select count(*) from teacher where teacher_id is not null")
    Integer selectCount();

    /**
     * 根据用户名和密码查询对应的教师信息
     *
     * @param teacher
     * @return
     */
    @Select("select * from teacher where username=#{username} and password=#{password}")
    Teacher selectByUsernameAndPassword(Teacher teacher);

    /**
     * 根据工号和密码查询对应的教师信息
     *
     * @param teacher
     * @return
     */
    @Select("select * from teacher where teacher_id=#{teacher_id} and password=#{password}")
    Teacher selectByTeacherIdAndPassword(Teacher teacher);

    /**
     * 根据用户名查询教师信息
     *
     * @param username
     * @return
     */
    @Select("select * from teacher where username=#{username} and teacher_id is not null")
    List<Teacher> selectByUsername(String username);

    /**
     * 根据工号查询教师信息
     *
     * @param teacher_id
     * @return
     */
    @Select("select * from teacher where teacher_id=#{teacher_id} and teacher_id is not null")
    List<Teacher> selectByTeacherId(String teacher_id);

    /**
     * 根据传入的ID值将指定教师设置为管理员
     *
     * @param id
     * @return
     */
    @Update("update teacher set is_admin='on' where id=#{id}")
    Integer asAdmin(Integer id);

    /**
     * 根据传入的ID值将指定教师取消管理员
     *
     * @param id
     * @return
     */
    @Update("update teacher set is_admin='null' where id=#{id}")
    Integer cancelAdmin(Integer id);

    /**
     * 根据传入的信息新建教师信息
     *
     * @param teacher
     * @return
     */
    @Insert("insert into teacher values(default,#{username},'123456',#{teacher_id},#{is_admin})")
    Integer insertTeacher(Teacher teacher);

    /**
     * 根据传入的教师信息更新教师全部信息
     *
     * @param teacher
     * @return
     */
    @Update("<script>" +
            "update teacher set username=#{username}" +
            "<if test='password.length != 0'>,password=#{password}</if>" +
            ",teacher_id=#{teacher_id},is_admin=#{is_admin} where id=#{id}" +
            "</script>")
    Integer updateTeacherInfo(Teacher teacher);

    /**
     * 根据传入的教师信息更新教师用户名和密码信息
     *
     * @param teacher
     * @return
     */
    @Update("update teacher set username=#{username},password=#{password} where id=#{id}")
    Integer updateTeacher(Teacher teacher);

    /**
     * 根据ID删除教师信息
     *
     * @param id
     * @return
     */
    @Delete("delete from teacher where id=#{id}")
    Integer deleteTeacher(Integer id);

    /**
     * 添加考试信息
     *
     * @param exam
     * @return
     */
    @Insert("insert into exams(course_id,exam_name,start_time,finish_time,creater,is_auto_begin,status) values(1,#{exam_name},#{start_time},#{finish_time},#{creater},#{is_auto_begin},0)")
    Integer addExam(Exam exam);

    /**
     * 更改考试信息
     *
     * @param exam
     * @return
     */
    @Update("update exams set exam_name=#{exam_name},start_time=#{start_time},finish_time=#{finish_time},is_auto_begin=#{is_auto_begin} where exam_id=#{exam_id}")
    Integer updateExam(Exam exam);

    /**
     * 更新试卷信息
     *
     * @return
     */
    @Update("update exams set paper_path=#{paper_path} where exam_id=#{exam_id}")
    Integer setExamPaperPath(int exam_id, String paper_path);

    /**
     * 根据教师姓名获取该教师的所有指定状态的考试
     *
     * @param username
     * @return
     */
    @Select("select * from exams where creater=#{username} and status=#{status}")
    List<Exam> selectExamsByUserName(String username, int status);

    /**
     * 获取所有指定状态的考试，管理员专用
     *
     * @return
     */
    @Select("select * from exams where status=#{status}")
    List<Exam> selectAllExams(int status);

    /**
     * 根据教师姓名获取该教师的已开启的考试
     *
     * @param username
     * @return
     */
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "participate_count", one = @One(select = "selectExamStudentCount"), column = "exam_id"),
            @Result(property = "login_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=1}"),
            @Result(property = "unlogin_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=0}"),
            @Result(property = "submit_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=3}"),
            @Result(property = "unsubmit_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=2}")
    })
    @Select("select *,1,0,3,2 from exams where creater=#{username} and status=#{status}")
    List<Exam> selectStartExamsByUserName(String username, int status);

    /**
     * 配合selectStartExamsByUserName查询指定考试的考试人数
     *
     * @param exam_id
     * @return
     */
    @Select("select count(*) from exam_student where exam_id=#{exam_id}")
    Integer selectExamStudentCount(int exam_id);

    /**
     * 配合selectStartExamsByUserName查询指定状态的考生
     *
     * @param exam_id
     * @param status
     * @return
     */
    @Select("select count(*) from (select * from student where id in (select stu_id from exam_student where exam_id=#{exam_id})) student where student.status=#{status}")
    Integer selectExamStudentStatusCount(int exam_id, int status);

    /**
     * 获取所有已开启的考试，管理员专用
     *
     * @return
     */
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "participate_count", one = @One(select = "selectExamStudentCount"), column = "exam_id"),
            @Result(property = "login_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=1}"),
            @Result(property = "unlogin_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=0}"),
            @Result(property = "submit_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=3}"),
            @Result(property = "unsubmit_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=2}")
    })
    @Select("select *,1,0,3,2 from exams where status=#{status}")
    List<Exam> selectAllStartExams(int status);

    /**
     * 根据考试ID获取考试名称
     *
     * @param exam_id
     * @return
     */
    @Select("select exam_name from exams where exam_id=#{exam_id}")
    String selectExamNameById(int exam_id);

    /**
     * 开启考试，将考试信息的状态改为1
     *
     * @param exam_id
     * @return
     */
    @Update("update exams set status=1 where exam_id=#{exam_id}")
    Integer startExam(int exam_id);

    /**
     * 根据考试号和学生号查询中间表信息
     *
     * @param exam_id
     * @param stu_id
     * @return
     */
    @Select("select count(*) from exam_student where exam_id=#{exam_id} and stu_id=#{stu_id}")
    Integer selectExamStuInfo(int exam_id, int stu_id);

    /**
     * 根据传入的考试ID和学生ID添加记录到中间表中
     *
     * @param exam_id
     * @param stu_id
     * @return
     */
    @Insert("insert into exam_student values(default,#{exam_id},#{stu_id})")
    Integer addExamStuInfo(int exam_id, int stu_id);

    /**
     * 将指定考生从指定考试中删除
     *
     * @param exam_id
     * @param stu_id
     * @return
     */
    @Delete("delete from exam_student where exam_id=#{exam_id} and stu_id=#{stu_id}")
    Integer deleteExamStuInfo(int exam_id, int stu_id);

    /**
     * 根据学号查询指定学生是否已经存在
     *
     * @param stu_no
     * @return
     */
    @Select("select * from student where stu_no=#{stu_no}")
    Student selectStuByNo(String stu_no);

    /**
     * 根据传入的信息新增一个学生信息
     *
     * @param student
     * @return
     */
    @Insert("insert into student values(default,#{stu_no},#{username},#{password},#{class_room},0)")
    Integer addStudent(Student student);

    /**
     * 查询指定考试的所有考生
     *
     * @param exam_id
     * @return
     */
    @Select("select * from student where id in (select stu_id from exam_student where exam_id=#{exam_id})")
    List<Student> selectExamAllStudents(int exam_id);

    /**
     * 分页查询指定考试的所有考生
     *
     * @param exam_id
     * @param pageStart
     * @param pageSize
     * @return
     */
    @Select("select * from student where id in (select stu_id from exam_student where exam_id=#{exam_id}) limit #{pageStart},#{pageSize}")
    List<Student> selectExamAllStudentsByPage(int exam_id, int pageStart, int pageSize);

    /**
     * 给分页查询提供数据总条数
     *
     * @param exam_id
     * @return
     */
    @Select("select count(*) from student where id in (select stu_id from exam_student where exam_id=#{exam_id})")
    Integer selectExamAllStudentsCount(int exam_id);

    /**
     * 绑定考生的主机IP
     * @param stu_id
     * @param exam_id
     * @param ip
     * @return
     */
    @Update("update exam_student set ip=#{ip} where exam_id=#{exam_id} and stu_id=#{stu_id}")
    Integer updateIP(int stu_id,int exam_id,String ip);
}
