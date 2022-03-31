package com.xiezhixian.blog.mapper;

import com.xiezhixian.blog.pojo.Blog;
import com.xiezhixian.blog.pojo.BlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogMapper {
    int countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExampleWithBLOBs(BlogExample example);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    //我写的
    void saveContent(@Param("content") String content,@Param("id")Integer id);
    String getContent(@Param("id")Integer id);

    void saveDescribe(@Param("describe") String describe,@Param("id")Integer id);
    String getDescribe(@Param("id")Integer id);

    List<Blog> searchBlog(@Param("query")String query);

    void updateBlogViews(@Param("id")Integer id,@Param("views")Integer views);

    List<String> findGroupYear();
    List<Blog> selectBlogByYear(@Param("year")String year);
}