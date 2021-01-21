package com.example.bq.edmp.work.modelfield.bean;

import java.util.List;

public class YearsBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [2020,2021,2022]
     */

    private String code;
    private String msg;
    private List<String> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
