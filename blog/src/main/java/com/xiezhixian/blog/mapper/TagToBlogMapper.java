package com.xiezhixian.blog.mapper;

import com.xiezhixian.blog.pojo.TagToBlog;
import com.xiezhixian.blog.pojo.TagToBlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagToBlogMapper {
    int countByExample(TagToBlogExample example);

    int deleteByExample(TagToBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TagToBlog record);

    int insertSelective(TagToBlog record);

    List<TagToBlog> selectByExample(TagToBlogExample example);

    TagToBlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagToBlog record, @Param("example") TagToBlogExample example);

    int updateByExample(@Param("record") TagToBlog record, @Param("example") TagToBlogExample example);

    int updateByPrimaryKeySelective(TagToBlog record);

    int updateByPrimaryKey(TagToBlog record);
}