package com.boot.service;

import com.boot.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select * from teacher")
    List<Teacher> selectAll();

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
    @Select("select * from teacher where username=#{username}")
    List<Teacher> selectByUsername(String username);

    /**
     * 根据工号查询教师信息
     *
     * @param teacher_id
     * @return
     */
    @Select("select * from teacher where teacher_id=#{teacher_id}")
    List<Teacher> selectByTeacherId(String teacher_id);

    /**
     * 根据传入的ID值将指定教师设置为管理员
     *
     * @param id
     * @return
     */
    @Update("update teacher set is_admin=1 where id=#{id}")
    Integer asAdmin(Integer id);

    /**
     * 根据传入的ID值将指定教师取消管理员
     *
     * @param id
     * @return
     */
    @Update("update teacher set is_admin=0 where id=#{id}")
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
    @Update("update teacher set username=#{username},password=#{password},teacher_id=#{teacher_id},is_admin=#{is_admin} where id=#{id}")
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
    @Delete("delete teacher where id=#{id}")
    Integer deleteTeacher(Integer id);
}
