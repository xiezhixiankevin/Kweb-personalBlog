package com.xiezhixian.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiezhixian.blog.pojo.Blog;
import com.xiezhixian.blog.pojo.BlogExample;
import com.xiezhixian.blog.pojo.Type;
import com.xiezhixian.blog.service.BlogService;
import com.xiezhixian.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <Description> TypeViewController
 *
 * @author 26802
 * @version 1.0
 * @ClassName TypeViewController
 * @taskId
 * @see com.xiezhixian.blog.controller.admin
 */
@Controller
public class TypeViewController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/type/{pn}")
    public String listByType(@PathVariable("pn")Integer pn, @RequestParam("typeId")Integer typeId, Model model){
        PageHelper.startPage(pn,5);
        List<Blog> blogList = blogService.listBlogByTypeId(typeId);
        PageInfo pageInfo = new PageInfo(blogList,5);

        //获取分类并设置数量
        List<Type> typeList = typeService.listType();
        for (Type type : typeList){
            typeService.setBlogNumToType(type);
        }

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeId",typeId);
        model.addAttribute("typeList",typeList);
        return "types";
    }
}
