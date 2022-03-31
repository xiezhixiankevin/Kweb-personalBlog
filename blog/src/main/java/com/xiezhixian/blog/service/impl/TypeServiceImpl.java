package com.xiezhixian.blog.service.impl;

import com.xiezhixian.blog.mapper.BlogMapper;
import com.xiezhixian.blog.mapper.TypeMapper;
import com.xiezhixian.blog.pojo.*;
import com.xiezhixian.blog.service.BlogService;
import com.xiezhixian.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> TypeServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName TypeServiceImpl
 * @taskId
 * @see com.xiezhixian.blog.service.impl
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    @Transactional
    public Type saveType(Type type) {
        //判断是否以及存在相同标签了
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andNameEqualTo(type.getName().trim());  //通过trim()去除首尾空格
        if(!typeMapper.selectByExample(typeExample).isEmpty()){
            return null;
        }else{
            typeMapper.insert(type);
            return type;
        }
    }

    @Override
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Type getTypeByName(String typeName) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andNameEqualTo(typeName);
        List<Type> types = typeMapper.selectByExample(typeExample);
        if(types.isEmpty()){
            return null;
        }
        return types.get(0);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public Integer updateType(Type type) {
        return typeMapper.updateByPrimaryKey(type);
    }

    @Override
    @Transactional
    public void deleteType(Integer id) {
        typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void setBlogNumToType(Type type) {
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTypeIdEqualTo(type.getId());
        type.setBlogNum(blogMapper.selectByExample(blogExample).size());
    }

    private Integer countTypeNumOfBlog(Type type){
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTypeIdEqualTo(type.getId());
        return blogMapper.selectByExample(blogExample).size();
    }

    @Override
    public List<TypeNum> listTypeNum() {
        List<TypeNum> typeNumList = new ArrayList<>();
        List<Type> typeList = typeMapper.selectByExample(null);
        for(Type type : typeList){
            TypeNum typeNum = new TypeNum();
            typeNum.setTypeName(type.getName());
            typeNum.setNumbers(countTypeNumOfBlog(type));
            typeNumList.add(typeNum);
        }
        return typeNumList;
    }
}
