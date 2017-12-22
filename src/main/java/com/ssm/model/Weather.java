package com.ssm.model;

import java.util.Date;

public class Weather {
    private Integer id;

    private String city;

    private String img;

    private String temp;

    private String tempHigh;

    private String tempLow;

    private String windDirection;

    private String windLevel;

    private String windSpeed;

    private String totalPrecitation;

    private String date;

    private String week;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh == null ? null : tempHigh.trim();
    }

    public String getTempLow() {
        return tempLow;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow == null ? null : tempLow.trim();
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection == null ? null : windDirection.trim();
    }

    public String getWindLevel() {
        return windLevel;
    }

    public void setWindLevel(String windLevel) {
        this.windLevel = windLevel == null ? null : windLevel.trim();
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed == null ? null : windSpeed.trim();
    }

    public String getTotalPrecitation() {
        return totalPrecitation;
    }

    public void setTotalPrecitation(String totalPrecitation) {
        this.totalPrecitation = totalPrecitation == null ? null : totalPrecitation.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}