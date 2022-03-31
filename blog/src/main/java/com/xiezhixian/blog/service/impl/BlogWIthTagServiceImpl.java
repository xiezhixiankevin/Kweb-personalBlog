package com.xiezhixian.blog.service.impl;

import com.xiezhixian.blog.mapper.BlogMapper;
import com.xiezhixian.blog.mapper.TagToBlogMapper;
import com.xiezhixian.blog.pojo.*;
import com.xiezhixian.blog.service.BlogWIthTagService;
import com.xiezhixian.blog.service.TagService;
import com.xiezhixian.blog.service.TypeService;
import com.xiezhixian.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> BlogWIthTagServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName BlogWIthTagServiceImpl
 * @taskId
 * @see com.xiezhixian.blog.service.impl
 */
@Service
public class BlogWIthTagServiceImpl implements BlogWIthTagService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagToBlogMapper tagToBlogMapper;

    @Override
    public List<Tag> listTag() {
        List<Tag> tagList = tagService.listTag();
        for (Tag tag : tagList){
            TagToBlogExample tagToBlogExample = new TagToBlogExample();
            tagToBlogExample.createCriteria().andTagIdEqualTo(tag.getId());
            tag.setBlogNum(tagToBlogMapper.selectByExample(tagToBlogExample).size());
        }
        return tagList;
    }

    @Override
    public List<Blog> listBlogByTag(Integer id) {
        TagToBlogExample tagToBlogExample = new TagToBlogExample();
        tagToBlogExample.createCriteria().andTagIdEqualTo(id);
        List<TagToBlog> tagToBlogs = tagToBlogMapper.selectByExample(tagToBlogExample);
        BlogExample blogExample = new BlogExample();
        List<Integer> idlist = new ArrayList<>();
        List<Blog> blogList = new ArrayList<>();
        for (TagToBlog tagToBlog : tagToBlogs){
            idlist.add(tagToBlog.getBlogId());
        }
        if (!idlist.isEmpty()){
            blogExample.createCriteria().andIdIn(idlist);
            blogList = blogMapper.selectByExample(blogExample);
            for (Blog blog : blogList){
                blog.setContent(blogMapper.getContent(blog.getId()));
                blog.setDescribe( blogMapper.getDescribe(blog.getId()));
                blog.setUser(userService.getUserById(blog.getUserId()));
                initType(blog);
                initTags(blog);
            }
        }
        return blogList;
    }

    //初始化博客的tag信息
    private void initTags(Blog blog){
        //初始化blog的标签
        TagToBlogExample tagToBlogExample = new TagToBlogExample();
        tagToBlogExample.createCriteria().andBlogIdEqualTo(blog.getId());
        List<TagToBlog> tagToBlogs = tagToBlogMapper.selectByExample(tagToBlogExample);
        List<Tag> tagList = new ArrayList<>();
        for(TagToBlog tagToBlog : tagToBlogs){
            tagList.add(tagService.getTag(tagToBlog.getTagId()));
        }
        blog.setTaglist(tagList);
    }

    //初始化博客的type信息
    private void initType(Blog blog){
        Type type = typeService.getType(blog.getTypeId());
        blog.setType(type);
    }

}
