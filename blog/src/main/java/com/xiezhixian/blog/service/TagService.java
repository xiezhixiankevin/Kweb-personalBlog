package com.xiezhixian.blog.service;

import com.xiezhixian.blog.pojo.Tag;
import com.xiezhixian.blog.pojo.TagNum;

import java.util.List;

/**
 * <Description> TypeService
 *
 * @author 26802
 * @version 1.0
 * @ClassName TypeService
 * @taskId
 * @see com.xiezhixian.blog.service
 */
public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Integer id);

    List<Tag> listTag();

    Integer updateTag(Tag tag);

    void deleteTag(Integer id);

    List<TagNum> listTagNum();
}
