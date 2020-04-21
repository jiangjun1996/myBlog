package com.xp.blog.service;

import com.xp.blog.entity.User;

public interface UserService {
    User login(String username,String password);
}
