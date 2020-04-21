package com.xp.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xp.blog.config.controller.AppBaseController;
import com.xp.blog.entity.Type;
import com.xp.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController extends AppBaseController{
    @Autowired
    private TypeService typeService;

    @GetMapping("/toTypePage")
    public String toTypePage(Model model,@RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize){
        model.addAttribute("user",getUser());
        PageHelper.startPage(pageNum, pageSize);
        List<Type> typeList = typeService.getAllType();
        PageInfo pageInfo = new PageInfo(typeList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",typeList);
        return "admin/type";
    }

    @GetMapping("/toTypeInput")
    public String toTagInput(Model model){
        model.addAttribute("user",getUser());
        return "admin/type-input";
    }

    @GetMapping("/editerTypeInput")
    public String editerTypeInput(Model model,Integer id){
        model.addAttribute("user",getUser());
        Type type = typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "admin/editer-type";
    }


    @PostMapping("/addOrUpdateType")
    public String addOrUpdateType(Model model,Type type,HttpServletRequest request,HttpServletResponse response) throws Exception {

        model.addAttribute("user",getUser());
        if (type!= null && type.getId()!=null){
           typeService.updateTypeById(type);
           response.sendRedirect(request.getContextPath()+"/admin/toTypePage");
        }else {

            Type typeByName = typeService.getTypeByName(type.getTypeName());
            if (typeByName==null){
                typeService.addType(type);
                response.sendRedirect(request.getContextPath()+"/admin/toTypePage");
            }else {
                model.addAttribute("error","该分类名称已存在");
            }

        }

        return "/admin/type-input";


    }


    @GetMapping("/deleteType")
    public void deleteType(Model model, Integer id, HttpServletRequest request,HttpServletResponse response) throws IOException {

        try {
            typeService.deleteTypeById(id);
            model.addAttribute("msg","删除成功");
        } catch (Exception e) {
            model.addAttribute("msg","删除失败");
        }


        response.sendRedirect(request.getContextPath()+"/admin/toTypePage");
    }

}
