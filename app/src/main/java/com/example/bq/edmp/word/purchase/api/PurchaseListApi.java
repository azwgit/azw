package com.example.bq.edmp.word.purchase.api;

import com.example.bq.edmp.word.purchase.bean.PurchaseListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by bq on 2020/11/6.
 */

public interface PurchaseListApi {


    //生产管理     收购记录
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/purchase/list")
    Observable<PurchaseListBean> getPurchaseData(
            @Field("beginTime") String beginTime, //开始时间
            @Field("code") String code, //单号
            @Field("endTime") String endTime, //结束时间
            @Field("orgId") String orgIds, //公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("varietyId") String varietyId,//品种id
            @Field("sign") String sign
    );

}
