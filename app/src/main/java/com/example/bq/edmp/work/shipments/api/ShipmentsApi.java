package com.example.bq.edmp.work.shipments.api;

import com.example.bq.edmp.work.shipments.bean.DshipmentsListBean;
import com.example.bq.edmp.work.shipments.bean.UserNameListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ShipmentsApi {


    //待发货
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/order/sendout")
    Observable<DshipmentsListBean> getDshipmentsData(
            @Field("customerId") String customerId,//客户id
            @Field("orgId") String orgId,//分司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //已发货
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/order/sendout/record")
    Observable<DshipmentsListBean> getYshipmentsData(
            @Field("beginTime") String beginTime,//开始时间
            @Field("code") String code,//订单号
            @Field("endTime") String endTime,//结束时间
            @Field("orgId") String orgId,//分司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign

    );

    //客户列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/order/customer/list")
    Observable<UserNameListBean> getUserNameData(
            @Field("name") String name,//姓名
            @Field("sign") String sign
    );
}
