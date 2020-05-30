package com.boot.mapper;

import com.boot.pojo.Student;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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
    @Select("select * from student where username=#{username} and password=#{password}")
    Student selectByUsernameAndPassword(Student student);

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
     * @param status
     * @param stu_id
     * @return
     */
    @Update("update student set status=#{status} where id=#{stu_id}")
    Integer updateStudentStatus(int status, int stu_id);
}
