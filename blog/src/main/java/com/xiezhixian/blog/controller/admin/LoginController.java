package com.xiezhixian.blog.controller.admin;

import com.xiezhixian.blog.pojo.User;
import com.xiezhixian.blog.service.UserService;
import com.xiezhixian.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * <Description> LoginController
 *
 * @author 26802
 * @version 1.0
 * @ClassName LoginController
 * @taskId
 * @see com.xiezhixian.blog.controller.admin
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if(user == null){
            attributes.addFlashAttribute("message","用户名或密码错误");
            //此处不能用model因为重定向会重置请求域
            return "redirect:/admin";
        }else {
            user.setPassword(null);  //不要把密码传到浏览器，不安全
            session.setAttribute("user",user);
            return "admin/index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user"); //清除user信息
        return "redirect:/admin";
    }

}
