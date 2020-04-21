package com.xp.blog.config.controller;

import com.xp.blog.config.exception.NotFindException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类
 */
//ControllerAdvice包含@Component。可以被扫描到。统一处理异常。
@ControllerAdvice
public class ControllerExpectionHandler extends AppBaseController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    //ExceptionHandler用在方法上面表示遇到这个异常就执行以下方法。
    public ModelAndView expectionHandler(HttpServletRequest request,Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        //当遇到HttpStatus.NOT_FOUND,直接抛出自定义异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        logger.error("Request URL : {},Exception : {}",request.getRequestURL(),e.getMessage());

        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.addObject("user",getUser());
        modelAndView.setViewName("error/error");

        return modelAndView;
    }
}
