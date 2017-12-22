package com.ssm.dao;

import com.ssm.model.Weather;

import java.util.Map;

public interface WeatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weather record);

    int insertSelective(Weather record);

    Weather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weather record);

    int updateByPrimaryKey(Weather record);

    String weatherByDate();//数据库是否有某天的数据

    Map rainMonthSelect();//数据库是否有某月降水量统计的数据
    Map rainYearSelect();//数据库是否有某年降水量统计的数据
}