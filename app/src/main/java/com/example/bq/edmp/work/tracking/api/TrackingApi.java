package com.example.bq.edmp.work.tracking.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationDetailsBean;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationListBean;
import com.example.bq.edmp.work.modelfield.bean.YearsBean;
import com.example.bq.edmp.work.order.bean.OrderZuoWuBean;
import com.example.bq.edmp.work.tracking.bean.TrackingDereBean;
import com.example.bq.edmp.work.tracking.bean.TrackingListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TrackingApi {

    //示范  列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/track")
    Observable<TrackingListBean> getTrackingListData(
            @Field("page") int page,//
            @Field("pagerow") int pagerow,//
            @Field("status") String status,//
            @Field("sign") String sign
    );

    //示范  列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/show")
    Observable<TrackingDereBean> getTrackingDrainData(
            @Field("id") String id,
            @Field("sign") String sign
    );


    //活动删除
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/delete/{id}")
    Observable<BaseABean> getDetails(
            @Path("id") String id,
            @Field("sign") String sign);

}
