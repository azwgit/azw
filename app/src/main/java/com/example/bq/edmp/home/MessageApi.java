package com.example.bq.edmp.home;

import com.example.bq.edmp.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MessageApi {

    //密码登录接口
    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> login(
            @Field("password") String password,
            @Field("username") String username,
            @Field("sign") String sign);

}
