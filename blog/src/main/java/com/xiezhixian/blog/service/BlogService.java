package com.xiezhixian.blog.service;

import com.xiezhixian.blog.pojo.Blog;
import com.xiezhixian.blog.pojo.Type;

import java.util.List;
import java.util.Map;

/**
 * <Description> BlogService
 *
 * @author 26802
 * @version 1.0
 * @ClassName BlogService
 * @taskId
 * @see com.xiezhixian.blog.service
 */
public interface BlogService {

    List<Blog> listBlog();

    List<Blog> listBlogByTypeId(Integer id);

    List<Blog> listBlogBySearch(String query,Integer pn);

    List<Blog> listBlogToView(Integer pn);

    Map<String,List<Blog>> listBlogByYear();
;
    List<Blog> listBlogSelective(String title,String type,Boolean recommend,int pn);

    void deleteBlog(Integer id);

    void updateBlog(Blog blog);

    void saveBlog(Blog blog);

    Blog selectBlogById(Integer id);

    Blog getAndConvert(Integer id);

    List<Blog> chooseRecommendBlogs(List<Blog> blogList);


}
