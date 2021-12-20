package com.jumaojiang.mapper;

import com.jumaojiang.pojo.Style;
import com.jumaojiang.pojo.StyleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StyleMapper {
    long countByExample(StyleExample example);

    int deleteByExample(StyleExample example);

    int deleteByPrimaryKey(Integer styleId);

    int insert(Style record);

    int insertSelective(Style record);

    List<Style> selectByExample(StyleExample example);

    Style selectByPrimaryKey(Integer styleId);

    int updateByExampleSelective(@Param("record") Style record, @Param("example") StyleExample example);

    int updateByExample(@Param("record") Style record, @Param("example") StyleExample example);

    int updateByPrimaryKeySelective(Style record);

    int updateByPrimaryKey(Style record);
}