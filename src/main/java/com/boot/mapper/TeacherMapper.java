package com.boot.mapper;

import com.boot.pojo.*;
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
     * 选择指定考试所有已提交学生的ID
     * @param exam_id
     * @return
     */
    @Select("select stu_id from exam_student where exam_id=#{exam_id} and status=1")
    List<Integer> selectExamAllSubmitStu_id(int exam_id);

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
    @Select("select * from teacher where username=#{username}")
    List<Teacher> loginByUsername(String username);

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
            "<if test=\"password.length != 0\">,password=#{password}</if>" +
            ",teacher_id=#{teacher_id},is_admin=#{is_admin},status=#{status} <where> id=#{id} </where>" +
            "</script>")
    Integer updateTeacherInfo(Teacher teacher);

    /**
     * 根据传入的教师信息更新教师用户名和密码信息
     *
     * @param teacher
     * @return
     */
    @Update("update teacher set username=#{username},password=#{password},status=#{status} where id=#{id}")
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
            @Result(id = true, property = "exam_id", column = "exam_id"),
            @Result(property = "participate_count", one = @One(select = "selectExamStudentCount"), column = "exam_id"),
            @Result(property = "login_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=1}"),
            @Result(property = "unlogin_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=0}"),
            @Result(property = "submit_count", one = @One(select = "selectExamSubmitStatusCount"), column = "{exam_id=exam_id,status=1}"),
            @Result(property = "unsubmit_count", one = @One(select = "selectExamSubmitStatusCount"), column = "{exam_id=exam_id,status=0}")
    })
    @Select("select *,1,0 from exams where creater=#{username} and status=#{status}")
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
     * 配合selectStartExamsByUserName查询指定考试的考生提交情况
     *
     * @param exam_id
     * @param status
     * @return
     */
    @Select("select count(*) from exam_student where exam_id=#{exam_id} and status=#{status}")
    Integer selectExamSubmitStatusCount(int exam_id, int status);

    /**
     * 获取所有已开启的考试，管理员专用
     *
     * @return
     */
    @Results({
            @Result(id = true, property = "exam_id", column = "exam_id"),
            @Result(property = "participate_count", one = @One(select = "selectExamStudentCount"), column = "exam_id"),
            @Result(property = "login_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=1}"),
            @Result(property = "unlogin_count", one = @One(select = "selectExamStudentStatusCount"), column = "{exam_id=exam_id,status=0}"),
            @Result(property = "submit_count", one = @One(select = "selectExamSubmitStatusCount"), column = "{exam_id=exam_id,status=1}"),
            @Result(property = "unsubmit_count", one = @One(select = "selectExamSubmitStatusCount"), column = "{exam_id=exam_id,status=0}")
    })
    @Select("select *,1,0 from exams where status=#{status}")
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
    @Update("update exams set status=#{status} where exam_id=#{exam_id}")
    Integer updateExamStatus(int exam_id, int status);

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
    @Insert("insert into exam_student(id,exam_id,stu_id,status) values(default,#{exam_id},#{stu_id},0)")
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
    Integer firstOfAddStudent(Student student);

    /**
     * 根据传入的信息新增一个学生信息
     *
     * @param student
     * @return
     */
    @Select("select * from student where stu_no = #{stu_no}")
    Student locationOfAddStudent(Student student);

    /**
     * 根据传入的信息新增一个学生信息
     *
     * @param student
     * @return
     */
    @Update("update student set password=#{password} where id=#{id}")
    Integer Md5OfAddStudent(Student student);

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
    @Results({
            @Result(property = "ip", one = @One(select = "selectStudentIp"), column = "{exam_id=exam_id,stu_id=stu_id}")
    })
    @Select("select *,#{exam_id} exam_id,id stu_id from student where id in (select stu_id from exam_student where exam_id=#{exam_id}) limit #{pageStart},#{pageSize}")
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
     * 从exam_student表中查询考生的主机IP
     *
     * @param exam_id
     * @param stu_id
     * @return
     */
    @Select("select ip from exam_student where exam_id=#{exam_id} and stu_id=#{stu_id}")
    String selectStudentIp(int exam_id, int stu_id);

    /**
     * 绑定考生的主机IP
     *
     * @param stu_id
     * @param exam_id
     * @param ip
     * @return
     */
    @Update("update exam_student set ip=#{ip} where exam_id=#{exam_id} and stu_id=#{stu_id}")
    Integer updateIP(int stu_id, int exam_id, String ip);

    /**
     * 查询指定考试的指定考生信息
     *
     * @param exam_id
     * @param stu_no
     * @param username
     * @param class_room
     * @return
     */
    @Results({
            @Result(property = "ip", one = @One(select = "selectStudentIp"), column = "{exam_id=exam_id,stu_id=stu_id}")
    })
    @Select("select *,#{exam_id} exam_id,id stu_id from (select * from student where id in (select stu_id from exam_student where exam_id=#{exam_id})) es where es.stu_no=#{stu_no} and es.username=#{username} and es.class_room=#{class_room}")
    List<Student> selectExamStudentByNoAndUsernameAndClass(int exam_id, String stu_no, String username, String class_room);

    /**
     * 更新考生的信息
     *
     * @param exam_id
     * @param stu_id
     * @param stu_no
     * @param username
     * @param class_room
     * @param ip
     */
    @Update("update exam_student set ip=#{ip} where exam_id=#{exam_id} and stu_id=#{stu_id};update student set stu_no=#{stu_no},username=#{username},class_room=#{class_room} where id=#{stu_id}")
    Integer updateStudentExamInfo(int exam_id, int stu_id, String stu_no, String username, String class_room, String ip);

    /**
     * 查询需要加入到定时任务的考试
     *
     * @return
     */
    @Select("select * from exams where ((is_auto_begin=1 and status=0) or status=1) and paper_path !=''")
    List<Exam> selectScheduleExam();

    /**
     * 删除考试的所有考生
     *
     * @param exam_id
     * @return
     */
    @Delete("delete from exam_student where exam_id=#{exam_id}")
    Integer deleteExamStudent(int exam_id);

    /**
     * 开启考试
     *
     * @param exam_id
     * @param status
     * @param start_time
     * @return
     */
    @Update("update exams set status=#{status},start_time=#{start_time} where exam_id=#{exam_id}")
    Integer startExamStatus(int exam_id, int status, String start_time);

    /**
     * 查询指定考试的所有通知信息
     *
     * @param exam_id
     * @return
     */
    @Select("select * from messages where exam_id=#{exam_id}")
    List<Message> selectExamMessage(int exam_id);

    /**
     * 删除指定消息
     *
     * @param id
     * @return
     */
    @Delete("delete from messages where id=#{id}")
    Integer deleteMessage(int id);

    /**
     * 插入新的消息
     *
     * @param exam_id
     * @param time
     * @param detail
     * @return
     */
    @Insert("insert into messages values(default,#{exam_id},#{time},#{detail})")
    Integer addMessage(int exam_id, String time, String detail);

    /**
     * 查找指定名称的考试
     *
     * @param name
     * @return
     */
    @Select("select * from exams where exam_name=#{name}")
    Exam selectExam(String name);

    /**
     * 删除指定考试的所有通知
     *
     * @param exam_id
     * @return
     */
    @Delete("delete from messages where exam_id=#{exam_id}")
    Integer deleteAllMessage(int exam_id);

    /**
     * 查询是否有交卷的学生
     *
     * @param exam_id
     * @return
     */
    @Select("select count(*) from exam_student where exam_id=#{exam_id} and paper_path != ''")
    Integer selectAnswersCount(int exam_id);
}
