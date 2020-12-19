package com.example.bq.edmp.work.allocation.api;

import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.detection.bean.DetectionDkBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AllocationApi {

    //调拨     待提交
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/tosubmit")
    Observable<BaseAllocationBeam> getAllocationDTJ(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //调拨     审品中
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/approval")
    Observable<BaseAllocationBeam> getAllocationApprovalData(
            @Field("code") String code,//单号
            @Field("inOrgId") String inOrgId,//调入分公司id
            @Field("outOrgId") String outOrgId,//调出分公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );


    //调拨     调拨中
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/allotcentre")
    Observable<BaseAllocationBeam> getAllocationInData(
            @Field("code") String code,//单号
            @Field("inOrgId") String inOrgId,//调入分公司id
            @Field("outOrgId") String outOrgId,//调出分公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,//状态：0全部 4出库中 5在途	number	默认0
            @Field("sign") String sign
    );



    //调拨     已完成
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/accomplish")
    Observable<BaseAllocationBeam> getAllocationCompleteData(
            @Field("beginTime") String beginTime,//开始时间
            @Field("code") String code,//调拨单号
            @Field("endTime") String endTime,//结束时间
            @Field("inOrgId") String inOrgId,//调入分公司id
            @Field("outOrgId") String outOrgId,//调出分公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("types") String types,//调拨类型：1原粮调拨 2成品调拨 3转商调拨	number
            @Field("sign") String sign
    );

}
