package com.example.bq.edmp.http;


import com.example.bq.edmp.bean.GetSessionResult;
import com.example.bq.edmp.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by GaoSheng on 2016/9/13.
 * 网络请求的接口都在这里
 */

public interface HttpService {
    public interface LocationService {
        @GET("api/session")
        Call<GetSessionResult> getSession(
                @Query("accessToken") String accessToken,
                @Query("sign") String sign
        );
    }

    //密码登录接口
    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> login(
            @Field("password") String password,
            @Field("username") String username,
            @Field("sign") String sign

    );

    //获取验证码
    @FormUrlEncoded
    @POST("login/sendsms")
    Observable<LoginBean> gainAuthCode(
            @Field("username") String username,
            @Field("sign") String sign
    );


    //验证码登录接口
    @FormUrlEncoded
    @POST("login/bysms")
    Observable<LoginBean> logincode(
            @Field("smscode") String smscode,
            @Field("username") String username,
            @Field("sign") String sign

    );

}
