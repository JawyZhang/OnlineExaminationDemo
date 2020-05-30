package com.boot.service;

import com.boot.pojo.Student;
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
    Student selectByUsernameAndPassword(Student student);

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
}
