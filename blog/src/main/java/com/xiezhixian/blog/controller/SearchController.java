package com.xiezhixian.blog.controller;

import com.github.pagehelper.PageInfo;
import com.xiezhixian.blog.pojo.Blog;
import com.xiezhixian.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <Description> SearchController
 *
 * @author 26802
 * @version 1.0
 * @ClassName SearchController
 * @taskId
 * @see com.xiezhixian.blog.controller
 */
@Controller
public class SearchController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/search/{pn}")
    public String searchBlog(@RequestParam("query")String query, @PathVariable("pn") Integer pn, Model model){
        List<Blog> blogList = blogService.listBlogBySearch(query, pn);
        PageInfo pageInfo = new PageInfo(blogList,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

}
