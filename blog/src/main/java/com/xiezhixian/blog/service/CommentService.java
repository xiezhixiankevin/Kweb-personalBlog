package com.xiezhixian.blog.service;

import com.xiezhixian.blog.pojo.Comment;

import java.util.List;

/**
 * <Description> CommentService
 *
 * @author 26802
 * @version 1.0
 * @ClassName CommentService
 * @taskId
 * @see com.xiezhixian.blog.service
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Integer id);

    void saveComment(Comment comment);

}
