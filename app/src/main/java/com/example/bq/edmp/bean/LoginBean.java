package com.example.bq.edmp.bean;

/**
 * Created by bq on 2020/10/30.
 */

public class LoginBean {

    /**
     * code : 200
     * msg : 登录成功
     * data : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0Njk2MzY1MSIsInN1YiI6IjE2LOadjuWbmywxLDYiLCJpc3MiOiJFRFAiLCJpYXQiOjE2MDQwMzg5MzcsImV4cCI6MTYwNDEyNTMzN30.Nhu_qfCdtSr1sfG5wOMHtFzCFxe84bgv6QN5Ak90Z8k
     */

    private String code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
