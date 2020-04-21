package com.xp.blog.mapper;

import com.xp.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User login(@Param("username")String username,@Param("password")String password);
}
