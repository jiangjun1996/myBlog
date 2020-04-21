package com.xp.blog.config.controller;

import com.xp.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

public class AppBaseController {

    public static final String SESSION_IDENTIFY_CODE = null;

    private User user = null;

    @Autowired
    private EhCacheCacheManager cacheManager;

    /**
     * 获取用户信息
     * @return
     */
    public User getUser(){
        Cache.ValueWrapper valueWrapper = cacheManager.getCache("userInfo").get("user");
        user = (User)valueWrapper.get();
        return user;
    }
}
