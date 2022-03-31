package com.xiezhixian.blog.controller;

import com.github.pagehelper.PageInfo;
import com.xiezhixian.blog.pojo.*;
import com.xiezhixian.blog.service.BlogService;
import com.xiezhixian.blog.service.TagService;
import com.xiezhixian.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <Description> IndexController
 *
 * @author 26802
 * @version 1.0
 * @ClassName IndexController
 * @taskId
 * @see com.xiezhixian.blog.controller
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String toIndex(){
        return "forward:/1";
    }

    @GetMapping("/{pn}")
    public String index(@PathVariable(value = "pn",required = false)Integer pn, Model model){

        if(pn==null) pn = 1;
        List<Blog> blogList = blogService.listBlogToView(pn);
        PageInfo pageInfo = new PageInfo(blogList,5);

        List<Tag> tagList = tagService.listTag();
        List<Type> typeList = typeService.listType();
        for (Type type : typeList){
            typeService.setBlogNumToType(type);
        }

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tagList",tagList);
        model.addAttribute("typeList",typeList);
        model.addAttribute("recommendBlogs",blogService.chooseRecommendBlogs(blogList));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(Model model, @PathVariable("id")Integer id){
        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/login")
    public String login(){

        return "admin/login";
    }


}
