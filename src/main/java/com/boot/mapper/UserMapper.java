package com.boot.mapper;

import com.boot.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author Mango
 * @Date 2020-04-03 18:46
 */
@Repository
public interface UserMapper {
    /**
     * 根据提供的用户信息查询相应的用户信息
     *
     * @param user
     * @return
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    User selectUser(User user);
}