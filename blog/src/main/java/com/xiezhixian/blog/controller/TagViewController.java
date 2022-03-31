package com.xiezhixian.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiezhixian.blog.pojo.Blog;
import com.xiezhixian.blog.pojo.Tag;
import com.xiezhixian.blog.service.BlogService;
import com.xiezhixian.blog.service.BlogWIthTagService;
import com.xiezhixian.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <Description> TagViewController
 *
 * @author 26802
 * @version 1.0
 * @ClassName TagViewController
 * @taskId
 * @see com.xiezhixian.blog.controller
 */
@Controller
public class TagViewController {

    @Autowired
    private BlogWIthTagService blogWIthTagService;

    @GetMapping("/tag/{pn}")
    public String listByTag(@PathVariable("pn")Integer pn, @RequestParam("tagId")Integer tagId, Model model){
        List<Tag> tagList = blogWIthTagService.listTag();
        PageHelper.startPage(pn,5);
        List<Blog> blogList = blogWIthTagService.listBlogByTag(tagId);
        PageInfo pageInfo = new PageInfo(blogList,5);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tagId",tagId);
        model.addAttribute("tagList",tagList);
        return "tags";
    }

}
