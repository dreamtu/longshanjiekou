package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.ssm.model.Article;
import com.ssm.model.RainMonth;
import com.ssm.model.RainYear;
import com.ssm.model.Weather;
import com.ssm.service.interfaces.ITestService;
import com.ssm.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

import static com.ssm.util.HttpUtils.sendPost;

/**
 * Created by Administrator on 2016/12/28 0028.
 * 测试用Controller
 */
@RestController // @RestController是@Controller和@ResponseBody的结合体，两个标注合并起来的作用
@RequestMapping("/test")
public class TestController {
    //日志类
    private static Logger logger = Logger.getLogger(TestController.class);
    @Resource
    ITestService testService;


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String test() {
        return "你好啊SSM框架";
    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Map<String, Object> test1() {
        logger.info("TestController=======================" + JSON.toJSONString(testService.getUserPaging()));
        return CommonUtil.resultJSON("200", "执行成功", testService.getUserPaging());
    }

    //根据ID获取景点
    @RequestMapping(value = "/tourist/{id}", method = RequestMethod.GET)
    public Article tourist(@PathVariable("id") String id) {
        return testService.getTourist(id);
    }

    //获取所有景点
    @RequestMapping(value = "/touristList", method = RequestMethod.GET)
    public List touristList() {
        return testService.getAllTourist();
    }

    /*    客户端流程：如果需要登录首先跳到oauth2服务端进行登录授权，
        成功后服务端返回auth code，然后客户端使用auth code去服务器端换取access token，
        最好根据access token获取用户信息进行客户端的登录绑定*/
    @RequestMapping(value = "/getWeather", method = RequestMethod.GET)
    public Map weather() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("key", "2d344a85f1f64f4c8065a3b0efbafaa9");
        parameters.put("location", "莒县");
        String weatherString =sendPost("https://free-api.heweather.com/s6/weather/now", parameters);

        //获取的数据放入rec_weather表中
        Map<String,Object> maps = JSON.parseObject(weatherString); //字符串json转换成json对象

        for(String key : maps.keySet()){
            String value = String.valueOf(maps.get(key));//HeWeather6的value
            List valueList = JSON.parseArray(value);//数组list
            Map<String,Object> mapList = JSON.parseObject(valueList.get(0).toString()); //字符串json转换成json对象
            System.out.println(mapList);
            Map<String,Object> basic = (Map<String,Object>)mapList.get("basic");
            Map<String,Object> update = (Map<String,Object>)mapList.get("update");
            Map<String,Object> now = (Map<String,Object>)mapList.get("now");

            String date = update.get("loc").toString().substring(0,10);//XXXX-XX-XX
            String year = update.get("loc").toString().substring(0,4);//XXXX
            String month = update.get("loc").toString().substring(5,7);//XX

            String baseDate = testService.weatherByDate(); //数据库是否有这条数据 XXXX-XX-xx 获取的数据放入rec_weather表中
            Map rainMonthSelect = testService.rainMonthSelect(); //rec_weather 数据库是否有这条数据 XXXX  XX 获取的数据放入rec_rain_month表中
            Map rainYearSelect = testService.rainYearSelect(); //rec_weather 数据库是否有这条数据 XXXX 获取的数据放入rec_rain_year表中
            //获取的数据放入rec_weather表中
            if(!baseDate.equals(date)){  //数据库没有的话插入
                Weather weather = new Weather();
                weather.setCity(basic.get("location").toString()); //城市
                weather.setImg(now.get("cond_code").toString()); //图片
                weather.setTemp(now.get("tmp").toString()); //温度
                /*weather.setTempHigh(now.get("temphigh").toString()); //最高温度
                weather.setTempLow(now.get("templow").toString()); //最低温度*/
                weather.setWindDirection(now.get("wind_dir").toString()); //风向
                weather.setWindLevel(now.get("wind_sc").toString()); //风向等级
                weather.setWindSpeed(now.get("wind_spd").toString()); //风速
                weather.setTotalPrecitation(now.get("pcpn").toString()); //降雨量
                weather.setDate(update.get("loc").toString());  //日期
                weather.setWeek(now.get("cond_txt").toString()); //晴 阴
                weather.setUpdatetime(new Date()); //更新时间
                testService.insertWeather(weather);
            }

            //月降水量统计 当数据存在时 update
            Map rainMonthId = testService.selectByYearmonth(year,month);
            if(rainMonthSelect != null && rainMonthId != null){
                RainMonth rainMonUpd = testService.rainMonthById(Integer.parseInt(rainMonthId.get("id").toString()));
                rainMonUpd.setTotalPrecitation(rainMonthSelect.get("total_precitation").toString());
                rainMonUpd.setUpdatetime(new Date());
                testService.updateRainMonth(rainMonUpd);
            }else{//当数据不存在时 insert
                RainMonth rainMonIns = new RainMonth();
                rainMonIns.setCity(basic.get("location").toString()); //城市
                rainMonIns.setYear(year); //年
                rainMonIns.setMonth(month); //月
                rainMonIns.setTotalPrecitation(now.get("pcpn").toString()); //降水量
                rainMonIns.setCreatetime(new Date());
                testService.insertRainMonth(rainMonIns);
            }

            //年降水量统计 当数据存在时 update
            Map rainYearId = testService.selectByYear(year);
            if(rainYearSelect != null && rainYearId != null){
                RainYear rainYearUpd = testService.rainYearById(Integer.parseInt(rainYearId.get("id").toString()));
                rainYearUpd.setTotalPrecitation(rainYearSelect.get("total_precitation").toString());
                rainYearUpd.setUpdatetime(new Date());
                testService.updateRainYear(rainYearUpd);
            }else{//当数据不存在时 insert
                RainYear rainYearIns = new RainYear();
                rainYearIns.setCity(basic.get("location").toString()); //城市
                rainYearIns.setYear(year); //年
                rainYearIns.setTotalPrecitation(now.get("pcpn").toString()); //降水量
                rainYearIns.setCreatetime(new Date());
                testService.insertRainYear(rainYearIns);
            }

        }

        return maps;
    }

    //获取月降水量
    @RequestMapping(value = "/monthPrec", method = RequestMethod.GET)
    public List monthPrecList() {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));//XXXX 今年
        return testService.getMonthPrec(year);
    }

    //获取年降水量
    @RequestMapping(value = "/yearPrec", method = RequestMethod.GET)
    public List yearPrecList() {
        return testService.getYearPrec();
    }
}
