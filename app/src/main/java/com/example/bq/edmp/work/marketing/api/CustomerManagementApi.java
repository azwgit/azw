package com.example.bq.edmp.work.marketing.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.inventorytransfer.bean.GoodsDetailsBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.VarittiesListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.marketing.bean.CityBean;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountDetails;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;
import com.example.bq.edmp.work.marketing.bean.CustomerDetailsBean;
import com.example.bq.edmp.work.marketing.bean.CustomerManagementListBean;
import com.example.bq.edmp.work.marketing.bean.ProvinceAndCityListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerManagementApi {
    //新增客户
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("system/query/allwarehouse")
    Observable<BaseABean> addCustomer(
            @Field("businessLicenseImg") String businessLicenseImg,
            @Field("businessLicenseNumber") String businessLicenseNumber,
            @Field("contactAddress") String contactAddress,
            @Field("contacts") String contacts,
            @Field("mobTel") String mobTel,
            @Field("name") String name,
            @Field("region") String region,
            @Field("remark") String remark,
            @Field("sign") String sign);

    //删除客户
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("customer/delete/{id}")
    Observable<BaseABean> deleteCustomer(
            @Path("id") String remark,
            @Field("sign") String sign);

    //修改客户
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("system/query/allwarehouse")
    Observable<BaseABean> updateCustomer(
            @Field("businessLicense") String businessLicense,
            @Field("businessLicenseImg") String businessLicenseImg,
            @Field("businessLicenseNumber") String businessLicenseNumber,
            @Field("contactAddress") String contactAddress,
            @Field("contacts") String contacts,
            @Field("id") String id,
            @Field("mobTel") String mobTel,
            @Field("name") String name,
            @Field("remark") String remark,
            @Field("sign") String sign);

    //客户详情
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("customer/show/{id}")
    Observable<CustomerDetailsBean> getCustomerDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //客户列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("customer/list")
    Observable<CustomerManagementListBean> getCustomerList(
            @Field("cityId") String cityId,
            @Field("countyId") String countyId,
            @Field("name") String name,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("provinceId") String provinceId,
            @Field("sign") String sign);

    //查询省市区
    @Headers({"urlname:marketing"})
    @POST("customer/region")
    Observable<String> getProvinceList();

    //查询客户标签列表
    @Headers({"urlname:marketing"})
    @POST("customer/tags")
    Observable<String> getCustomerTagsList();


    //客户账户列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("customer/account/list")
    Observable<CustomerAccountListBean> getCustomerAccountList(
            @Field("name") String name,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //客户账户列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("customer/account/show")
    Observable<CustomerAccountDetails> getCustomerAccountDetails(
            @Field("customerId") String name,
            @Field("types") int types,
            @Field("sign") String sign);

    //省市区查询
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("customer/queryregion")
    Observable<ProvinceAndCityListBean> getProvinceAndCityList(
            @Field("parentId") String id,
            @Field("sign") String sign);

}
