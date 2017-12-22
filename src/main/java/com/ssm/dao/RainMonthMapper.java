package com.ssm.dao;

import com.ssm.model.RainMonth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RainMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RainMonth record);

    int insertSelective(RainMonth record);

    RainMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RainMonth record);

    int updateByPrimaryKey(RainMonth record);

    Map selectByYearmonth(@Param("year") String year, @Param("month") String month); //根据年月获取id

    List getMonthPrec(String year); //获取今年的月降雨量
}