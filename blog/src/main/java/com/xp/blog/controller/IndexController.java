package com.xp.blog.controller;

import com.xp.blog.config.controller.AppBaseController;
import com.xp.blog.config.exception.NotFindException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController extends AppBaseController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String index(){
        return "index";
    }






}
