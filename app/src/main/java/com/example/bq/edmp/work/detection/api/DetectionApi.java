package com.example.bq.edmp.work.detection.api;


import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.detection.bean.DetectionCkBean;
import com.example.bq.edmp.work.detection.bean.DetectionDkBean;
import com.example.bq.edmp.work.detection.bean.DetectionParticularsBean;
import com.example.bq.edmp.work.detection.bean.DetectionPzBean;
import com.example.bq.edmp.work.detection.bean.DetectionRecorListBean;
import com.example.bq.edmp.work.detection.bean.DetectionTestingBean;
import com.example.bq.edmp.work.detection.bean.DetectonLxBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
 * 新增检测  api
 * */
public interface DetectionApi {


    //获取检测类型数据
    @Headers({"urlname:production"})
    @POST("testing/testplan")
    Observable<DetectonLxBean> getContractorList();

    //根据类型查询检测项
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("testing/testplan/{id}")
    Observable<DetectionTestingBean> getTestingList(
            @Path("id") String id,
            @Field("sign") String sign);

    //获取地块数据
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("testing/farmland")
    Observable<DetectionDkBean> getDkList(
            @Field("code") String code,
            @Field("sign") String sign
    );

    //获取仓库数据
    @Headers({"urlname:production"})
    @POST("system/query/allwarehouse")
    Observable<DetectionCkBean> getCkList();

    //获取品种数据
    @Headers({"urlname:production"})
    @POST("system/query/allvariety")
    Observable<DetectionPzBean> getPzList();


    //新增检测
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("testing/newsave")
    Observable<BaseABean> addAcquisitions(
            @Field("farmLandId") String farmLandId,//地块id
            @Field("testPlanItemId") List<String> testingItemIdList,//检测项
            @Field("testingItemValue") List<String> testingItemValueList,//检测值
            @Field("testPlanId") String testPlanId,//类型id
            @Field("varietyId") String varietyId,//品种id
            @Field("wareshouseId") String wareshouseId,//仓库id
            @Field("sign") String sign);


    //检测记录
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("testing/list")
    Observable<DetectionRecorListBean> getDataList(
            @Field("endTime") String endTime,//结束时间
            @Field("orgId") String orgId,//分子公司id
            @Field("page") int page,//
            @Field("pagerow") int pagerow,//
            @Field("startTime") String startTime,//开始时间
            @Field("testPlanId") String testPlanId,//检测类型查询
            @Field("varietyId") String varietyId,//品种id
            @Field("sign") String sign);

    //检测详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("testing/show/{id}")
    Observable<DetectionParticularsBean> getPartiularsList(
            @Path("id") String id,
            @Field("sign") String sign);
}
