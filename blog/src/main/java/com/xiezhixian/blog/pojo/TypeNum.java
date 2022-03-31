package com.xiezhixian.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <Description> TypeNum
 *
 * @author 26802
 * @version 1.0
 * @ClassName TypeNum
 * @taskId
 * @see com.xiezhixian.blog.pojo
 */
@AllArgsConstructor
@NoArgsConstructor
public class TypeNum {

    private String typeName;
    private Integer numbers;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }
}
