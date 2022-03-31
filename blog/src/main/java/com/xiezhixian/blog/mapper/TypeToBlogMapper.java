package com.xiezhixian.blog.mapper;

import com.xiezhixian.blog.pojo.TypeToBlog;
import com.xiezhixian.blog.pojo.TypeToBlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeToBlogMapper {
    int countByExample(TypeToBlogExample example);

    int deleteByExample(TypeToBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TypeToBlog record);

    int insertSelective(TypeToBlog record);

    List<TypeToBlog> selectByExample(TypeToBlogExample example);

    List<Integer> selectBlogIdByTypeId(@Param("typeId") Integer typeId);

    TypeToBlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TypeToBlog record, @Param("example") TypeToBlogExample example);

    int updateByExample(@Param("record") TypeToBlog record, @Param("example") TypeToBlogExample example);

    int updateByPrimaryKeySelective(TypeToBlog record);

    int updateByPrimaryKey(TypeToBlog record);
}