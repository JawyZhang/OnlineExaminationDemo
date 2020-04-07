package com.boot.service;

import com.boot.pojo.Student;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
