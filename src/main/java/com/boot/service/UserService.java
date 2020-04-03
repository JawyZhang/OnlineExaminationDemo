package com.boot.service;

import com.boot.pojo.User;

/**
 * @Author Mango
 * @Date 2020-04-03 18:46
 */
public interface UserService {
    /**
     * 根据提供的用户信息查询该用户
     * @param user
     * @return
     */
    User selectUser(User user);
}
