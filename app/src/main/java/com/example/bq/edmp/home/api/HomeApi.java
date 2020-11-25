package com.example.bq.edmp.home.api;

import com.example.bq.edmp.home.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by bq on 2020/11/18.
 */

public interface HomeApi {

    //首页数据
    @Headers({"urlname:mdffx"})
    @POST("app/index")
    Observable<HomeBean> getHomeData();
}
