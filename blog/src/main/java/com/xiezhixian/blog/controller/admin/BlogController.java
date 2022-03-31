package com.xiezhixian.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiezhixian.blog.pojo.Blog;
import com.xiezhixian.blog.pojo.Tag;
import com.xiezhixian.blog.pojo.Type;
import com.xiezhixian.blog.pojo.User;
import com.xiezhixian.blog.service.BlogService;
import com.xiezhixian.blog.service.TagService;
import com.xiezhixian.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <Description> BlogController
 *
 * @author 26802
 * @version 1.0
 * @ClassName BlogController
 * @taskId
 * @see com.xiezhixian.blog.controller.admin
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 搜索所有博客
     * */
    @GetMapping("/blog/{pn}")
    @ResponseBody
    public PageInfo listBlog(@PathVariable("pn") Integer pn){
        PageHelper.startPage(pn,5);
        List<Blog> blogList = blogService.listBlog();
        PageInfo pageInfo = new PageInfo(blogList,5);
        return pageInfo;
    }

    /**
     * 条件搜索所有博客
     * */
    @GetMapping("/searchBlog/{pn}")
    @ResponseBody
    public PageInfo listBlog(@PathVariable("pn") Integer pn
            , @RequestParam("title") String title
            ,@RequestParam("type")String type,@RequestParam("ifcheck") Boolean ifcheck){

        List<Blog> blogList = blogService.listBlogSelective(title,type,ifcheck,pn);
        PageInfo pageInfo = new PageInfo(blogList,5);
        return pageInfo;
    }

    /**
     * 页面跳转
     * */
    @GetMapping("/toBlog")
    public String toBlog(){
        return "admin/blogs";
    }

    /**
     * 跳转页面
     * */
    @GetMapping("/toAddBlog")
    public String toAddBlog(Model model){
        List<Type> typeList = typeService.listType();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);
        return "admin/blogs-input";
    }


    /**
     * 搜索所有类型
     * */
    @GetMapping("/blog/types")
    @ResponseBody
    public List<Type> listType(){
        List<Type> typeList = typeService.listType();
        return typeList;
    }

    /**
     * 新增博客
     * */
    @PostMapping("/blog")
    public ModelAndView saveBlog(Blog blog, @RequestParam("tags") String tags, HttpSession session, ModelAndView modelAndView){


        List<Tag> tagList = new ArrayList<>();
        if (!tags.equals("") && !tags.equals(" ")){
            String[] tagStr = tags.split(",");
            for(String tag : tagStr){
                tagList.add(new Tag(Integer.parseInt(tag)));
            }
        }
        blog.setTaglist(tagList);
        User user = (User) session.getAttribute("user");

        if(blog.getId()!=null){ //判断是新增还是更新
            blog.setUserId(user.getId());
            blogService.updateBlog(blog);
        }else {

            blog.setUserId(user.getId());
            blogService.saveBlog(blog);
        }
        modelAndView.setViewName("redirect:/admin/toBlog");
        return modelAndView;
    }

    /**
     * 获取数据并跳转到更新博客页面
     * */
    @GetMapping("/updateBlog/{id}")
    public String updateBlog(@PathVariable("id") Integer id,Model model){

        Blog blog = blogService.selectBlogById(id);
        blog.init();
        List<Type> typeList = typeService.listType();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("blog",blog);
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);

        return "admin/blogs-update";
    }

    /**
     * 删除博客
     * */
    @ResponseBody
    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable("id") Integer id){
        blogService.deleteBlog(id);
        return "删除成功";
    }

}
