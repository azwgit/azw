package com.example.bq.edmp.work.marketing.bean;

import java.util.List;

public class CityBean {

    private int code;
    private String msg;
    private List<CityModel> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CityModel> getData() {
        return data;
    }

    public void setData(List<CityModel> data) {
        this.data = data;
    }
}
