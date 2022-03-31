package com.xiezhixian.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;

    private String nickname;

    private String email;

    private String content;

    private String avatar;

    private Date createtime = new Date();

    private Integer blogId;

    private Integer parentId;

    private String createtimeString;

    //非数据库字段
    private List<Comment> childList = new ArrayList<>();
    private String replyof;

    public String getReplyof() {
        return replyof;
    }

    public void setReplyof(String replyof) {
        this.replyof = replyof;
    }

    public void addChildComment(Comment comment){
        this.childList.add(comment);
    }

    public List<Comment> getChildList() {
        return childList;
    }

    public void setChildList(List<Comment> childList) {
        this.childList = childList;
    }

    public String getCreatetimeString() {
        return createtimeString;
    }

    public void setCreatetimeString(String createtimeString) {
        this.createtimeString = createtimeString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}