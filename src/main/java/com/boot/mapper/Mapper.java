package com.boot.mapper;

import com.boot.pojo.Student;
import com.boot.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-15 22:10
 */
@Repository
public interface Mapper {
    List<Teacher> testSelectAllTeacher();
    Student selectStudentAllInfo(Student studnet);
    Teacher selectTeacherAllInfo(Teacher teacher);
}
