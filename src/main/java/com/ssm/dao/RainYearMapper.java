package com.ssm.dao;

import com.ssm.model.RainYear;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RainYearMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RainYear record);

    int insertSelective(RainYear record);

    RainYear selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RainYear record);

    int updateByPrimaryKey(RainYear record);

    Map selectByYear(@Param("year") String year); //根据年月获取id

    List getYearPrec(); //获取年降雨量
}