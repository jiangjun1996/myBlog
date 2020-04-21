package com.xp.blog.controller.admin;

import com.xp.blog.config.controller.AppBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TagController extends AppBaseController{


    @GetMapping("/toTagPage")
    public String toTagPage(Model model){
        model.addAttribute("user",getUser());
        return "admin/tag";
    }

    @GetMapping("/toTagInput")
    public String toTagInput(Model model){
        model.addAttribute("user",getUser());
        return "admin/tag-input";
    }
}
