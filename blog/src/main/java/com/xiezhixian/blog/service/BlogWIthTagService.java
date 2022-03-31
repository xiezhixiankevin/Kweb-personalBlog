package com.xiezhixian.blog.service;

import com.xiezhixian.blog.pojo.Blog;
import com.xiezhixian.blog.pojo.Tag;

import java.util.List;

/**
 * <Description> BlogWIthTagService
 *
 * @author 26802
 * @version 1.0
 * @ClassName BlogWIthTagService
 * @taskId
 * @see com.xiezhixian.blog.service
 */
public interface BlogWIthTagService {

    List<Tag> listTag();

    List<Blog> listBlogByTag(Integer id);

}
