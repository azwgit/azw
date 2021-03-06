package com.example.bq.edmp.work.marketingactivities.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountDetails;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;
import com.example.bq.edmp.work.marketing.bean.CustomerDetailsBean;
import com.example.bq.edmp.work.marketing.bean.CustomerManagementListBean;
import com.example.bq.edmp.work.marketing.bean.ProvinceAndCityListBean;
import com.example.bq.edmp.work.marketingactivities.bean.ActivityManagementListBean;
import com.example.bq.edmp.work.marketingactivities.bean.ActivitySiteBean;
import com.example.bq.edmp.work.marketingactivities.bean.CustomerListBean;
import com.example.bq.edmp.work.marketingactivities.bean.DepartmengListBean;
import com.example.bq.edmp.work.marketingactivities.bean.HistoricalListBean;
import com.example.bq.edmp.work.marketingactivities.bean.MarketingActivitiesDetailsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MarketingActivitiesApi {

    //查询合作客户
    @Headers({"urlname:marketing"})
    @POST("activity/customer")
    Observable<CustomerListBean> getCustomerList();

    //查询实施部门
    @Headers({"urlname:marketing"})
    @POST("activity/org")
    Observable<DepartmengListBean> getDepartmentList();

    //历史活动列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/history")
    Observable<HistoricalListBean> getHistoryList(
            @Field("name") String name,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //活动删除
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/delete/{id}")
    Observable<BaseABean> deleteActivities(
            @Path("id") String id,
            @Field("sign") String sign);

    //附件删除
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/deleteitem/{id}")
    Observable<BaseABean> deleteAttachment(
            @Path("id") String id,
            @Field("sign") String sign);

    //活动待提交列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/list")
    Observable<ActivityManagementListBean> getActivitieNosubmitList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //活动详情
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/show")
    Observable<MarketingActivitiesDetailsBean> getActivitieDetails(
            @Field("id") String page,
            @Field("type") String pagerow,
            @Field("sign") String sign);
    //活动详情
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/show")
    Observable<ActivitySiteBean> getActivitySiteDetail(
            @Field("id") String page,
            @Field("type") String pagerow,
            @Field("sign") String sign);

    //活动保存
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/newsave")
    Observable<CustomerAccountListBean> submitActivities(
            @Field("address") int address,
            @Field("advanceLoan") int advanceLoan,
            @Field("customerId") int customerId,
            @Field("deptId") int deptId,
            @Field("endTime") int endTime,
            @Field("id") int id,
            @Field("name") int name,
            @Field("purpose") int purpose,
            @Field("region") int region,
            @Field("responsiblePeople") int responsiblePeople,
            @Field("startTime") int startTime,
            @Field("sign") String sign);

    //活动修改
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("activity/save")
    Observable<CustomerAccountListBean> updataActivities(
            @Field("address") int address,
            @Field("advanceLoan") int advanceLoan,
            @Field("customerId") int customerId,
            @Field("deptId") int deptId,
            @Field("endTime") int endTime,
            @Field("id") int id,
            @Field("name") int name,
            @Field("purpose") int purpose,
            @Field("region") int region,
            @Field("responsiblePeople") int responsiblePeople,
            @Field("startTime") int startTime,
            @Field("sign") String sign);

}
