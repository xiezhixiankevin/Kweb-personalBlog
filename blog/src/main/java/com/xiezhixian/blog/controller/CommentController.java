package com.xiezhixian.blog.controller;

import com.xiezhixian.blog.pojo.Comment;
import com.xiezhixian.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <Description> CommentController
 *
 * @author 26802
 * @version 1.0
 * @ClassName CommentController
 * @taskId
 * @see com.xiezhixian.blog.controller
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @GetMapping("/blog/comment/{id}")
    public List<Comment> listComment(@PathVariable("id")Integer id){
        List<Comment> commentList = commentService.listCommentByBlogId(id);
        Collections.reverse(commentList);
        return commentList;

    }

    @PostMapping("/blog/comment/{id}")
    @ResponseBody
    public List<Comment> saveComment(@PathVariable("id")Integer id,Comment comment){
        comment.setBlogId(id);
        comment.setId(null);
        commentService.saveComment(comment);
        List<Comment> commentList = commentService.listCommentByBlogId(id);
        Collections.reverse(commentList);
        return commentList;
    }

}
