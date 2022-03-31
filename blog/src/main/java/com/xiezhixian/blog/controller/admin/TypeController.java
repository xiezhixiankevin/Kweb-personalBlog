package com.xiezhixian.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiezhixian.blog.pojo.Type;
import com.xiezhixian.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <Description> TypeController
 *
 * @author 26802
 * @version 1.0
 * @ClassName TypeController
 * @taskId
 * @see com.xiezhixian.blog.controller.admin
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     *获取分类列表
     * */
    @GetMapping("/Type/{pn}")
    public String listTypes(@PathVariable(value = "pn") Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Type> typeList = typeService.listType();
        PageInfo pageInfo = new PageInfo(typeList,5);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    /**
     * 新增分类
     * */
    @PostMapping("/Type")
    public String saveType(@RequestParam("typeName") String typeName){
        Type type = new Type();
        type.setName(typeName);
        Type returnType = typeService.saveType(type);
        if(returnType==null){
            //为空说明重复，插入不成功

        }else {
            //不为空说明插入成功

        }
        return "redirect:toSaveTypePage";
    }

    /**
     * 修改分类
     * */
    @PutMapping("/Type/{id}")
    public ModelAndView updateType(ModelAndView view,@PathVariable("id") Integer id, @RequestParam("typeName") String typeName){
        Type type = new Type();
        type.setName(typeName);
        type.setId(id);
        int row = typeService.updateType(type);
        if( row == 0){
            //为0说明不存在，修改不成功

        }else {
            //不为0说明修改成功

        }
        view.setViewName("redirect:/admin/Type/1");
        return view;
    }

    /**
     * 删除分类
     * */
    @DeleteMapping("/Type/{id}")
    public ModelAndView deleteType(ModelAndView view,@PathVariable("id") Integer id){
        typeService.deleteType(id);
        view.setViewName("redirect:/admin/Type/1");
        return view;
    }



    @GetMapping("/toSaveTypePage")
    public String toSaveTypePage(){
        return "admin/types-input";
    }

    @GetMapping("/Type/{id}/{typeName}")
    public String toUpdateTypePage(@PathVariable("id") Integer id,@PathVariable("typeName") String typeName,Model model){
        model.addAttribute("id",id);
        model.addAttribute("typeName",typeName);
        return "admin/types-update";
    }
}
