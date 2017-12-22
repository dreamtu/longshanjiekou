package com.ssm.service.interfaces;



import com.ssm.model.Article;
import com.ssm.model.RainMonth;
import com.ssm.model.RainYear;
import com.ssm.model.Weather;

import java.util.List;
import java.util.Map;

/**
 * Created by dqq
 * 测试用的service接口
 */
public interface ITestService {
    Map<String, Object> getUserPaging();

    Article getTourist(String id);//根据景点id获取景点数据

    List getAllTourist();//获取所有景点

    int insertWeather(Weather weather); //天气insert

    String weatherByDate(); //数据库是否有某天的数据

    Map rainMonthSelect();//数据库是否有某月降水量统计的数据
    Map rainYearSelect();//数据库是否有某年降水量统计的数据

    Map selectByYearmonth(String year,String month); //根据年月获取id

    Map selectByYear(String year); //根据年获取id

    RainMonth rainMonthById(int id);//根据id获取月降水量
    int updateRainMonth(RainMonth rainMonth); //月降水量update
    int insertRainMonth(RainMonth rainMonth); //月降水量insert

    RainYear rainYearById(int id);//根据id获取年降水量
    int updateRainYear(RainYear rainYear); //年降水量update
    int insertRainYear(RainYear rainYear); //年降水量insert

    List getMonthPrec(String year); //获取本年月降雨量

    List getYearPrec(); //获取年降雨量

}
