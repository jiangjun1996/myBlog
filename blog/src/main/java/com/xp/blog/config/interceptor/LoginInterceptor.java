package com.xp.blog.config.interceptor;

import com.xp.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 继承处理器适配器。自定义登录拦截器，拦截controller
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EhCacheCacheManager cacheManager;

    /**
     * 在请求之前进行一系列操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断用户是否登录，登录了则可以正常访问，如果没有重定向到登录页面

        Cache.ValueWrapper cache = cacheManager.getCache("userInfo").get("user");

        if (cache != null && cache.get()!=null){
            logger.info("拦截器获取的user信息为-------------{}",cache.get());
            return true;
        }

        logger.info("eacache--------------失效");
        //request.getContextPath() 获取虚拟路径path,本项目为blog
        response.sendRedirect(request.getContextPath()+"/admin");
        return false;
    }
}
