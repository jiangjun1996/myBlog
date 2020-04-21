package com.xp.blog.service.impl;

import com.xp.blog.entity.User;
import com.xp.blog.mapper.UserMapper;
import com.xp.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户名密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {

        User user = userMapper.login(username, password);

        if (user != null){
            return user;
        }
        return null;
    }
}
