package com.example.bq.edmp.work.modelfield.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.detection.bean.DetectionTestingBean;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationDetailsBean;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationListBean;
import com.example.bq.edmp.work.modelfield.bean.YearsBean;
import com.example.bq.edmp.work.order.bean.CustomerBean;
import com.example.bq.edmp.work.order.bean.GoodsBean;
import com.example.bq.edmp.work.order.bean.HistoryBean;
import com.example.bq.edmp.work.order.bean.OrderDetailsBean;
import com.example.bq.edmp.work.order.bean.OrderTJBean;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;
import com.example.bq.edmp.work.order.bean.OrderZuoWuBean;
import com.example.bq.edmp.work.order.bean.StatusBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DemonstrationApi {

    //所有作物
    @Headers({"urlname:production"})
    @POST("system/query/allcrop")
    Observable<OrderZuoWuBean> getZuoWuData();

    //品种
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/variety/crop")
    Observable<OrderZuoWuBean> getPZData(
            @Field("cropId") String cropId,
            @Field("sign") String sign
    );


    //种植区域
    @Headers({"urlname:marketing"})
    @POST("customer/region")
    Observable<String> getProvinceList();

    //年度列表
    @Headers({"urlname:marketing"})
    @POST("demonstration/years")
    Observable<YearsBean> getYearsList();

    //示范  添加
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("demonstration/newsave")
    Observable<BaseABean> setSubimtData(
            @Field("address") String address,//详细地址
            @Field("companyName") String companyName,//示范单位
            @Field("cropId") String cropId,//作物id
            @Field("regionId") String regionId,//区域id
            @Field("varietyId") String varietyId,//品种id
            @Field("years") String years,//年份
            @Field("sign") String sign
    );

    //示范  列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("demonstration/list")
    Observable<DemonstrationListBean> getListData(
            @Field("cropId") String cropId,//作物id  全部传 0
            @Field("page") int page,//
            @Field("pagerow") int pagerow,//
            @Field("regionId") String regionId,//区域id
            @Field("varietyId") String varietyId,//品种id
            @Field("years") String years,//年份
            @Field("sign") String sign
    );

    //删除示范信息
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("demonstration/info/delete")
    Observable<BaseABean> getDeleteData(
            @Field("demonstrationId") String demonstrationId,
            @Field("idx") String idx,
            @Field("sign") String sign
    );

    //修改示范信息
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("demonstration/info/save")
    Observable<BaseABean> getDeleteShiaFan(
            @Field("demonstrationId") String demonstrationId,
            @Field("idx") String idx,
            @Field("title") String title,
            @Field("sign") String sign
    );

    //根据类型查询检测项
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("demonstration/show/{id}")
    Observable<DemonstrationDetailsBean> getDetailsData(
            @Path("id") String id,
            @Field("sign") String sign);


    //示范图片删除
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("demonstration/info/deleteimg/{id}")
    Observable<BaseABean> getDetailsImg(
            @Path("id") String id,
            @Field("sign") String sign);
}
