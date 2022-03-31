package com.xiezhixian.blog.service.impl;

import com.xiezhixian.blog.mapper.TagMapper;
import com.xiezhixian.blog.mapper.TagToBlogMapper;
import com.xiezhixian.blog.pojo.Tag;
import com.xiezhixian.blog.pojo.TagExample;
import com.xiezhixian.blog.pojo.TagNum;
import com.xiezhixian.blog.pojo.TagToBlogExample;
import com.xiezhixian.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> TagServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName TagServiceImpl
 * @taskId
 * @see com.xiezhixian.blog.service.impl
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagToBlogMapper tagToBlogMapper;

    @Override
    @Transactional
    public Tag saveTag(Tag Tag) {
        //判断是否以及存在相同标签了
        TagExample TagExample = new TagExample();
        TagExample.createCriteria().andNameEqualTo(Tag.getName().trim());  //通过trim()去除首尾空格
        if(!tagMapper.selectByExample(TagExample).isEmpty()){
            return null;
        }else{
            tagMapper.insert(Tag);
            return Tag;
        }
    }

    @Override
    public Tag getTag(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tagList = tagMapper.selectByExample(null);
        for(Tag tag : tagList){
            tag.setBlogNum(countTagNumOfBlog(tag));
        }
        return tagList;
    }

    @Override
    @Transactional
    public Integer updateTag(Tag Tag) {
        return tagMapper.updateByPrimaryKey(Tag);
    }

    @Override
    @Transactional
    public void deleteTag(Integer id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    private Integer countTagNumOfBlog(Tag tag){
        TagToBlogExample tagToBlogExample = new TagToBlogExample();
        tagToBlogExample.createCriteria().andTagIdEqualTo(tag.getId());
        return tagToBlogMapper.selectByExample(tagToBlogExample).size();
    }

    @Override
    public List<TagNum> listTagNum() {
        List<TagNum> tagNumList = new ArrayList<>();
        List<Tag> tagList = tagMapper.selectByExample(null);
        for(Tag tag : tagList){
            TagNum tagNum = new TagNum();
            tagNum.setTagName(tag.getName());
            tagNum.setNumbers(countTagNumOfBlog(tag));
            tagNumList.add(tagNum);
        }
        return tagNumList;
    }

}
