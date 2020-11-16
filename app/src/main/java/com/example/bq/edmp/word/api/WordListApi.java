package com.example.bq.edmp.word.api;

import com.example.bq.edmp.bean.AddressBean;
import com.example.bq.edmp.word.bean.AuditDspNumberBean;
import com.example.bq.edmp.word.bean.AuditListBean;
import com.example.bq.edmp.word.bean.FirstResult;
import com.example.bq.edmp.word.bean.SecondResult;
import com.example.bq.edmp.word.bean.SubmitListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by bq on 2020/11/6.
 */

public interface WordListApi {


    //报账列表
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/list")
    Observable<SubmitListBean> getSubmitData(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("sign") String sign
    );


    //报账列表，筛选数据
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/list")
    Observable<SubmitListBean> getScreenData(
            @Field("firstTime") String firstTime,
            @Field("lastTime") String lastTime,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("sign") String sign
    );

    //第一级列表
    @Headers({"urlname:mdffx"})
    @POST("role/leftFunc")
    Observable<FirstResult> getFirst();

    //第二三级列表
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("role/rightFunc")
    Observable<SecondResult> getSecond(
            @Field("id") String id,
            @Field("sign") String sign
    );


    //审批管理===待审批的
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("approvalflow/staylist")
    Observable<AuditListBean> getDsp(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //审批管理===我审批的
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("approvalflow/approvedlist")
    Observable<AuditListBean> getWsp(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //审批管理===我发起的
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("approvalflow/alreadylist")
    Observable<AuditListBean> getWfq(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //审批管理===待审批的，数量
    @Headers({"urlname:mdffx"})
    @POST("approvalflow/staycount")
    Observable<AuditDspNumberBean> getDspNumber();

}
