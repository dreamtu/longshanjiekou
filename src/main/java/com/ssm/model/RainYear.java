package com.ssm.model;

import java.util.Date;

public class RainYear {
    private Integer id;

    private String city;

    private String year;

    private String totalPrecitation;

    private Date createtime;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getTotalPrecitation() {
        return totalPrecitation;
    }

    public void setTotalPrecitation(String totalPrecitation) {
        this.totalPrecitation = totalPrecitation == null ? null : totalPrecitation.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}