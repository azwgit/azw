package com.example.bq.edmp.work.finished.api;

import com.example.bq.edmp.work.finished.bean.MachineListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MachineApi {

    //====加工任务===========================================================================================================================

    //待确认
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/process/accept/list")
    Observable<MachineListBean> getDmachineData(
            @Field("code") String code,//单号
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //加工中
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/process/processing/list")
    Observable<MachineListBean> getJmachineData(
            @Field("code") String code,//单号
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );


    //a、b、c、d、e、f、g、h、i、j、k、l、m、n、o、p、q、r、s、t、u、v、w、x、y、z。
    //已完成
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/process/finished/list")
    Observable<MachineListBean> getYmachineData(
            @Field("code") String code,//单号
            @Field("endTime") String endTime,//结束时间
            @Field("orgId") String orgIds,//分公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("startTime") String startTime,//开始时间
            @Field("types") String types,//类型  1 备货加工 2按单加工
            @Field("varietyPackagingId") String varietyPackagingId,//包装id
            @Field("sign") String sign
    );

}
