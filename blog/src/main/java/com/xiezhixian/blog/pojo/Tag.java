package com.xiezhixian.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Integer id;

    private String name;

    /*非数据库字段*/
    private Integer BlogNum;

    public Integer getBlogNum() {
        return BlogNum;
    }

    public void setBlogNum(Integer blogNum) {
        BlogNum = blogNum;
    }

    public Tag(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}