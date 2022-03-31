package com.xiezhixian.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiezhixian.blog.mapper.BlogMapper;
import com.xiezhixian.blog.mapper.TagToBlogMapper;
import com.xiezhixian.blog.pojo.*;
import com.xiezhixian.blog.service.BlogService;
import com.xiezhixian.blog.service.TagService;
import com.xiezhixian.blog.service.TypeService;
import com.xiezhixian.blog.service.UserService;
import com.xiezhixian.blog.utils.MarkdownUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <Description> BlogServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName BlogServiceImpl
 * @taskId
 * @see com.xiezhixian.blog.service.impl
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final Integer DESCRIBE_LENGTH = 150;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserService userService;
    
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @Autowired
    private TagToBlogMapper tagToBlogMapper;

    @Override
    public List<Blog> listBlog() {

        return blogMapper.selectByExample(null);
    }

    @Override
    public List<Blog> listBlogByTypeId(Integer id) {
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTypeIdEqualTo(id);
        List<Blog> blogList = blogMapper.selectByExample(blogExample);
        for (Blog blog : blogList){
            blog.setContent(blogMapper.getContent(blog.getId()));
            blog.setDescribe( blogMapper.getDescribe(blog.getId()));
            blog.setUser(userService.getUserById(blog.getUserId()));
            initTags(blog);
            initType(blog);
        }
        return blogList;
    }

    @Override
    public List<Blog> listBlogBySearch(String query,Integer pn) {
        PageHelper.startPage(pn,5);
        List<Blog> blogList = blogMapper.searchBlog("%" + query + "%");
        for (Blog blog : blogList){
            blog.setContent(blogMapper.getContent(blog.getId()));
            blog.setDescribe( blogMapper.getDescribe(blog.getId()));
            blog.setUser(userService.getUserById(blog.getUserId()));
            initTags(blog);
            initType(blog);
        }
        return blogList;
    }

    @Override
    public List<Blog> listBlogToView(Integer pn) {
        PageHelper.startPage(pn,5);
        List<Blog> blogList = blogMapper.selectByExample(null);
        for (Blog blog : blogList){
            blog.setContent(blogMapper.getContent(blog.getId()));
            blog.setDescribe( blogMapper.getDescribe(blog.getId()));
            blog.setUser(userService.getUserById(blog.getUserId()));
            initTags(blog);
            initType(blog);
        }
        return blogList;
    }

    @Override
    public Map<String, List<Blog>> listBlogByYear() {
        List<String> years = blogMapper.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years){
            map.put(year,blogMapper.selectBlogByYear(year));
        }
        return map;
    }

    @Override
    public List<Blog> listBlogSelective(String title, String type,Boolean recommend,int pn) {

        Type typePojo = typeService.getTypeByName(type);


        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria();
        criteria.andTitleLike("%"+title+"%");

        if(typePojo!=null){
            /*
            *  List<Integer> blogId = typeToBlogMapper.selectBlogIdByTypeId(typePojo.getId());
                    if(blogId.isEmpty()){
                    blogId.add(-1);
                    }
                     criteria.andIdIn(blogId);
            *
            * */
           criteria.andTypeIdEqualTo(typePojo.getId());

        }
        if(recommend){
            criteria.andRecommendEqualTo(true);
        }

        PageHelper.startPage(pn,5);
        List<Blog> blogList = blogMapper.selectByExample(blogExample);


        return blogList;
    }

    @Override
    @Transactional
    public void deleteBlog(Integer id) {

        //删除掉blog关联的所有tag信息
        TagToBlogExample tagToBlogExample = new TagToBlogExample();
        tagToBlogExample.createCriteria().andBlogIdEqualTo(id);
        tagToBlogMapper.deleteByExample(tagToBlogExample);
        //删除blog
        blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void updateBlog(Blog blog) {
        //处理blog描述信息
        initDescribe(blog);

        //先删除再插入
        TagToBlogExample tagToBlogExample = new TagToBlogExample();
        tagToBlogExample.createCriteria().andBlogIdEqualTo(blog.getId());
        tagToBlogMapper.deleteByExample(tagToBlogExample);
        //插入标签数据
        for (int i = 0; i < blog.getTaglist().size(); i++) {
            tagToBlogMapper.insert(new TagToBlog(blog.getTaglist().get(i).getId(),blog.getId()));
        }

        blog.setUpdatetime(new Date());
        blogMapper.updateByPrimaryKeySelective(blog);
        blogMapper.saveContent(blog.getContent(),blog.getId());
        blogMapper.saveDescribe(blog.getDescribe(),blog.getId());
    }

    @Override
    @Transactional
    public void saveBlog(Blog blog) {
        //处理blog描述信息
        initDescribe(blog);

        blogMapper.insertSelective(blog);
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTitleEqualTo(blog.getTitle()).andTypeIdEqualTo(blog.getTypeId()).andViewsEqualTo(0);
        List<Blog> blogs = blogMapper.selectByExample(blogExample);

//        typeToBlogMapper.insert(new TypeToBlog(blog.getTypeId(),blogs.get(0).getId()));

        for (int i = 0; i < blog.getTaglist().size(); i++) {
            tagToBlogMapper.insert(new TagToBlog(blog.getTaglist().get(i).getId(),blogs.get(0).getId()));
        }

    }

    @Override
    public Blog selectBlogById(Integer id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);

        //初始化blog的标签
        TagToBlogExample tagToBlogExample = new TagToBlogExample();
        tagToBlogExample.createCriteria().andBlogIdEqualTo(id);
        List<TagToBlog> tagToBlogs = tagToBlogMapper.selectByExample(tagToBlogExample);
        List<Tag> tagList = new ArrayList<>();
        for(TagToBlog tagToBlog : tagToBlogs){
            tagList.add(tagService.getTag(tagToBlog.getTagId()));
        }
        blog.setTaglist(tagList);

        //初始化user
        User user = userService.getUserById(blog.getUserId());
        blog.setUser(user);

        return blog;
    }

    @Override
    @Transactional
    public Blog getAndConvert(Integer id) {

        Blog blog = blogMapper.selectByPrimaryKey(id);
        blogMapper.updateBlogViews(id,blog.getViews()+1);
        blog = blogMapper.selectByPrimaryKey(id);
        //初始化blog的标签
        TagToBlogExample tagToBlogExample = new TagToBlogExample();
        tagToBlogExample.createCriteria().andBlogIdEqualTo(id);
        List<TagToBlog> tagToBlogs = tagToBlogMapper.selectByExample(tagToBlogExample);
        List<Tag> tagList = new ArrayList<>();
        for(TagToBlog tagToBlog : tagToBlogs){
            tagList.add(tagService.getTag(tagToBlog.getTagId()));
        }
        blog.setTaglist(tagList);

        //初始化user
        User user = userService.getUserById(blog.getUserId());
        blog.setUser(user);
        //格式化markdown
        String content = blog.getContent();

        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public List<Blog> chooseRecommendBlogs(List<Blog> blogList){
        List<Blog> result = new ArrayList<>();
        for (Blog blog : blogList){
            if (blog.getRecommend()){
                result.add(blog);
            }
        }
        return result;
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

    //截取博客描述信息
    private void initDescribe(Blog blog){
        String content = blog.getContent();
        if (content.length()<DESCRIBE_LENGTH){
            blog.setDescribe(content);
        }else {
            blog.setDescribe(content.substring(0,DESCRIBE_LENGTH));
        }
    }


}
