package com.xiezhixian.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <Description> AboutController
 *
 * @author 26802
 * @version 1.0
 * @ClassName AboutController
 * @taskId
 * @see com.xiezhixian.blog.controller
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
