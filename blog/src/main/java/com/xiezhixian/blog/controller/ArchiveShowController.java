package com.xiezhixian.blog.controller;

import com.xiezhixian.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <Description> ArchiveShowController
 *
 * @author 26802
 * @version 1.0
 * @ClassName ArchiveShowController
 * @taskId
 * @see com.xiezhixian.blog.controller
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.listBlogByYear());
        model.addAttribute("blogCount",blogService.listBlog().size());
        return "archives";
    }
}
