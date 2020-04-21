package com.xp.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentMapper {

    void addComment(@Param("list")String[] list, @Param("email") String email);
}
