package com.boot.mapper;

import com.boot.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-03 18:42
 */
@Repository
public interface TeacherMapper {
    /**
     * 查询所有教师信息
     *
     * @return
     */
    @Select("select * from user where status=1")
    List<User> selectAllTeacher();

    /**
     * 根据ID删除指定教师信息
     *
     * @param id
     * @return
     */
    @Delete("delete from user where id=#{id}")
    Integer deleteTeacher(Integer id);

    /**
     * 根据传入用户信息更新用户信息
     *
     * @param user
     * @return
     */
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    Integer updateTeacher(User user);
}

