package com.example.bq.edmp.login;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {

    //密码登录接口
    @FormUrlEncoded
    @Headers({"urlname:manage"})
    @POST("login")
    Observable<LoginBean> login(
            @Field("password") String password,
            @Field("username") String username,
            @Field("sign") String sign);
    //发送短信验证码
    @FormUrlEncoded
    @Headers({"urlname:manage"})
    @POST("login/sendsms")
    Observable<LoginBean> sendSms(
            @Field("username") String phone,
            @Field("sign") String sign);
    //短信登錄
    @FormUrlEncoded
    @Headers({"urlname:manage"})
    @POST("login/bysms")
    Observable<LoginBean> smsLogin(
            @Field("username") String phone,
            @Field("smscode") String code,
            @Field("sign") String sign);

    //短信登錄
    @Headers({"urlname:manage"})
    @POST("current/user/loginout")
    Observable<BaseABean> putLogin();

}
