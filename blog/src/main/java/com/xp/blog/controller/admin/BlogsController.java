package com.xp.blog.controller.admin;

import com.xp.blog.config.controller.AppBaseController;
import com.xp.blog.entity.Blog;
import com.xp.blog.entity.User;
import com.xp.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
@Controller
@RequestMapping("/admin")
public class BlogsController extends AppBaseController{

    private  Logger logger = LoggerFactory.getLogger(BlogsController.class);

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Autowired
    private UserService userService;


    /**
     * 跳转后台首页
     * @return
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 后台登录接口
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@RequestBody @RequestParam String username, @RequestParam String password){

        ModelAndView modelAndView = new ModelAndView();
        //判断用户名密码是否正确
        User user = userService.login(username, password);


        //正确跳转到后台管理index页面
        if (user!=null){

            cacheManager.getCache("userInfo").put("user", user);
            user.setPassword(null);
            modelAndView.addObject("user",user);
            modelAndView.setViewName("admin/index");
        }else { //不正确返回错误信息给页面，
            modelAndView.addObject("error","用户名或者密码错误");
            modelAndView.setViewName("admin/login");
        }

        return modelAndView;

    }


    /**
     * 后台博客操作
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(Model model){
        model.addAttribute("user",getUser());
        return "admin/blogs";
    }


    /**
     * 跳转到博客编辑页面
     * @param
     * @return
     */
    @GetMapping("/toBlogsInput")
    public String toBlogsInput(Model model){
        model.addAttribute("user",getUser());
        return "admin/blogs-input";
    }

    /**
     * 注销功能
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        cacheManager.getCache("userInfo").clear();
        return "admin/login";
    }



    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("user",getUser());
        return "admin/index_test";
    }

    @GetMapping("/identifyCode")
    public void identifyCode(HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        int width = 60, height = 34;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 想文字旋转一定的角度
            AffineTransform trans = new AffineTransform();
            int dir = random.nextBoolean() ? 1 : -1;
            trans.rotate(dir * random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
            // 缩放文字
            float scaleSize = random.nextFloat() + 0.8f;
            if (scaleSize > 1f)
                scaleSize = 1f;
            trans.scale(scaleSize, scaleSize);
            g.setTransform(trans);
            g.drawString(rand, 13 * i + 6, 24);

        }
        // 将认证码存入SESSION

        cacheManager.getCache("verifyCodeCache").put(SESSION_IDENTIFY_CODE, sRand);
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
