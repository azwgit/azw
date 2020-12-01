package com.example.bq.edmp.work.grainmanagement;

import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.bean.PayReimbursementDetailsInfo;
import com.example.bq.edmp.activity.apply.bean.RevokeApplyBean;
import com.example.bq.edmp.activity.apply.bean.SelectReimbursementDetailsBean;
import com.example.bq.edmp.activity.apply.bean.UpdateRembursemenBean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsBean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsInfo;
import com.example.bq.edmp.activity.login.UserInfoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RawGrainManagementApi {

    //称重皮重详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/purchase/tare/{id}")
    Observable<BaseABean> getTraeDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //添加毛重
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/purchase/tare/add")
    Observable<BaseABean> addTrae(
            @Field("id") String id,
            @Field("tareWeight") String tareWeight,
            @Field("sign") String sign);

    //称重毛重详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/purchase/grossweight/{id}")
    Observable<BaseABean> getGrossweightDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //添加毛重
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/purchase/grossweight/add")
    Observable<BaseABean> addGrossweight(
            @Field("id") String id,
            @Field("grossWeight") String grossWeight,
            @Field("sign") String sign);

    //出库详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/substock/{id}")
    Observable<BaseABean> getWareHousingOutDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //入库详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/addstock/{id}")
    Observable<BaseABean> getWareHousingDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //收购记录详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/purchase/{id}")
    Observable<BaseABean> getAcquisitionDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //库存详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/stock/{id}")
    Observable<BaseABean> getStockDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //卸货验证
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("/grain/purchase/checked/{id}")
    Observable<BaseABean> getUnloadingVerificationInfo(
            @Path("id") String id,
            @Field("sign") String sign);

    //新增收购
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("grain/purchase/addnew")
    Observable<BaseABean> add(
            @Path("cropId") String id,
            @Field("farmerId") String testinformation,
            @Field("testingItemId[]") List<String> testingItemIdList,
            @Field("testingItemValue[]") List<String> testingItemValueList,
            @Field("varietyId") String varietyId,
            @Field("warehouseId") String warehouseId,
            @Field("sign") String sign);
}
