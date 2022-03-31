package com.xiezhixian.blog.service;

import com.xiezhixian.blog.pojo.Type;
import com.xiezhixian.blog.pojo.TypeNum;

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
public interface TypeService {

    Type saveType(Type type);

    Type getType(Integer id);

    Type getTypeByName(String typeName);

    List<Type> listType();

    Integer updateType(Type type);

    void deleteType(Integer id);

    void setBlogNumToType(Type type);

    List<TypeNum> listTypeNum();
}
