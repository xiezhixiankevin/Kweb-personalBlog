package com.xiezhixian.blog.service.impl;

import com.xiezhixian.blog.mapper.CommentMapper;
import com.xiezhixian.blog.pojo.Comment;
import com.xiezhixian.blog.pojo.CommentExample;
import com.xiezhixian.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <Description> CommentServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName CommentServiceImpl
 * @taskId
 * @see com.xiezhixian.blog.service.impl
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Integer id) {
        //1.查到该博客的所有评论(不分子父评论)
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andBlogIdEqualTo(id);
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Comment comment : commentList){
            comment.setCreatetimeString(sdf1.format(comment.getCreatetime()));
        }
        //2.处理子父关系博客
        return dealChildAndParentComment(commentList);
    }

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    private List<Comment> dealChildAndParentComment(List<Comment> commentList){
        List<Comment> result = new ArrayList<>();
        //先处理顶级评论
        for(Comment comment : commentList){
           if (comment.getParentId()==-1){
               result.add(comment);
               //递归处理每个评论的次级评论
               dealChildComment(comment,comment,commentList);
           }
        }


        return result;
    }

    private void dealChildComment(Comment topComment,Comment dealComment,List<Comment> commentList){
        for (Comment comment : commentList){
            if (comment.getParentId()==dealComment.getId()){
                if (dealComment.getId()!= topComment.getId()){
                    comment.setReplyof(dealComment.getNickname());
                }
                //添加到顶级comment
                topComment.addChildComment(comment);
                //继续递归处理
                dealChildComment(topComment,comment,commentList);
            }
        }
    }

}
