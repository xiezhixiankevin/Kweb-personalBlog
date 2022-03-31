package com.xiezhixian.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <Description> TagNum
 *
 * @author 26802
 * @version 1.0
 * @ClassName TagNum
 * @taskId
 * @see com.xiezhixian.blog.pojo
 */
@AllArgsConstructor
@NoArgsConstructor
public class TagNum {

    private String tagName;
    private Integer numbers;


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }
}
