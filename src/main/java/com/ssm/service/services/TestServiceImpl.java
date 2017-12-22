package com.ssm.service.services;


import com.ssm.dao.*;
import com.ssm.model.Article;
import com.ssm.model.RainMonth;
import com.ssm.model.RainYear;
import com.ssm.model.Weather;
import com.ssm.service.interfaces.ITestService;
import com.ssm.util.CommonUtil;
import com.ssm.util.SysConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by dqq
 * 测试用service
 */
@Service
//@Transactional事务处理  propagation事务传播机制  REQUIRED是保证在事务中的代码只有在当前事务中运行，防止创建多个事务
/*@Transactional(propagation = Propagation.REQUIRED)*/
//注解式数据源，用来进行数据源切换
/*@DataSource(name = DataSource.DEFAULT_DATASOURCE)*/
public class TestServiceImpl implements ITestService {
    @Resource
    BaseMapper baseMapper;
    @Resource
    ArticleMapper articleMapper;
    @Resource
    WeatherMapper weatherMapper;
    @Resource
    RainMonthMapper rainMonthMapper;
    @Resource
    RainYearMapper rainYearMapper;

    @Override
    public Map<String, Object> getUserPaging() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tableName", " user ");
        paramMap.put("fields", " * ");
        paramMap.put("pageNow", SysConstants.PAGENOW);
        paramMap.put("pageSize", SysConstants.PAGESIZE);
/*      paramMap.put("wherecase",null);
        paramMap.put("orderField", null);*/
        paramMap.put("orderFlag", 1);
        this.baseMapper.getPaging(paramMap);
        paramMap.put("pagingList", this.baseMapper.getPaging(paramMap));
        return CommonUtil.removePaingMap(paramMap);
    }

    //根据景点id获取景点数据
    @Override
    public Article getTourist(String id) {
        Article tourist = articleMapper.selectByPrimaryKey(id);
        return tourist;
    }

    //获取所有景点数据
    @Override
    public List getAllTourist() {
//        return touristMapper.allTourist();
        return articleMapper.allTourist();
    }

    //天气insert
    @Override
    public int insertWeather(Weather weather) {
        return weatherMapper.insertSelective(weather);
    }

    @Override
    //数据库是否有某天的数据
    public String weatherByDate(){
        return weatherMapper.weatherByDate();
    }

    //数据库是否有某月降水量统计的数据
    @Override
    public Map rainMonthSelect(){
        return weatherMapper.rainMonthSelect();
    }
    //数据库是否有某年降水量统计的数据
    @Override
    public Map rainYearSelect(){
        return weatherMapper.rainYearSelect();
    }

    //根据年月获取id
    @Override
    public Map selectByYearmonth(String year,String month){
        return rainMonthMapper.selectByYearmonth(year,month);
    }
    //根据id获取月降水量
    @Override
    public RainMonth rainMonthById(int id){
        return rainMonthMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateRainMonth(RainMonth rainMonth){
        return rainMonthMapper.updateByPrimaryKeySelective(rainMonth);
    }
    @Override
    public int insertRainMonth(RainMonth rainMonth){
        return rainMonthMapper.insertSelective(rainMonth);
    }

    //根据年获取id
    @Override
    public Map selectByYear(String year){
        return rainYearMapper.selectByYear(year);
    }
    //根据id获取年降水量
    @Override
    public RainYear rainYearById(int id){
        return rainYearMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateRainYear(RainYear rainYear){
        return rainYearMapper.updateByPrimaryKeySelective(rainYear);
    }
    @Override
    public int insertRainYear(RainYear rainYear){
        return rainYearMapper.insertSelective(rainYear);
    }

    //获取今年月降雨量
    @Override
    public List getMonthPrec(String year){
        return rainMonthMapper.getMonthPrec(year);
    }

    //获取年降雨量
    @Override
    public List getYearPrec(){
        return rainYearMapper.getYearPrec();
    }
}
