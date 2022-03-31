package com.xiezhixian.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiezhixian.blog.pojo.Tag;
import com.xiezhixian.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <Description> TagController
 *
 * @author 26802
 * @version 1.0
 * @ClassName TagController
 * @taskId
 * @see com.xiezhixian.blog.controller.admin
 */
@RequestMapping("/admin")
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     *获取分类列表
     * */
    @GetMapping("/tag/{pn}")
    public String listTags(@PathVariable(value = "pn") Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Tag> TagList = tagService.listTag();
        PageInfo pageInfo = new PageInfo(TagList,5);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/Tags";
    }

    /**
     * 新增分类
     * */
    @PostMapping("/tag")
    public String saveTag(@RequestParam("tagName") String tagName){
        Tag tag = new Tag();
        tag.setName(tagName);
        Tag returnTag = tagService.saveTag(tag);
        if(returnTag==null){
            //为空说明重复，插入不成功

        }else {
            //不为空说明插入成功

        }
        return "redirect:toSaveTagPage";
    }

    /**
     * 修改分类
     * */
    @PutMapping("/tag/{id}")
    public ModelAndView updateTag(ModelAndView view, @PathVariable("id") Integer id, @RequestParam("tagName") String tagName){
        Tag tag = new Tag();
        tag.setName(tagName);
        tag.setId(id);
        int row = tagService.updateTag(tag);
        if( row == 0){
            //为0说明不存在，修改不成功

        }else {
            //不为0说明修改成功

        }
        view.setViewName("redirect:/admin/tag/1");
        return view;
    }

    /**
     * 删除分类
     * */
    @DeleteMapping("/tag/{id}")
    public ModelAndView deleteTag(ModelAndView view,@PathVariable("id") Integer id){
        tagService.deleteTag(id);
        view.setViewName("redirect:/admin/tag/1");
        return view;
    }



    @GetMapping("/toSaveTagPage")
    public String toSaveTagPage(){
        return "admin/tags-input";
    }

    @GetMapping("/tag/{id}/{tagName}")
    public String toUpdateTagPage(@PathVariable("id") Integer id,@PathVariable("tagName") String tagName,Model model){
        model.addAttribute("id",id);
        model.addAttribute("tagName",tagName);
        return "admin/tag-update";
    }
    
}
