package com.example.bq.edmp.work.returnsmanagement.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;
import com.example.bq.edmp.work.marketingactivities.bean.ActivityManagementListBean;
import com.example.bq.edmp.work.marketingactivities.bean.ActivitySiteBean;
import com.example.bq.edmp.work.marketingactivities.bean.DepartmengListBean;
import com.example.bq.edmp.work.marketingactivities.bean.HistoricalListBean;
import com.example.bq.edmp.work.marketingactivities.bean.MarketingActivitiesDetailsBean;
import com.example.bq.edmp.work.returnsmanagement.bean.ApplyForRefundBean;
import com.example.bq.edmp.work.returnsmanagement.bean.CustomerListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.DeliverGoodsListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.GoodsListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.PackagingListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.ReapplyReturnGoods;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnTrackingListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnsGoodsDetailsBean;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnsManagementListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReturnGoodsApi {

    //退货待提交列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/list")
    Observable<ReturnsManagementListBean> getReturnGoodsManagentList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //历史退货列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/history")
    Observable<ReturnTrackingListBean> getReturnGoodsHistoricalList(
            @Field("code") String code,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //发货列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/ordersendout")
    Observable<DeliverGoodsListBean> getSendGoodsList(
            @Field("customerId") String customerId,
            @Field("itemId") String packagingId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //发货详情
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/ordersendout/show")
    Observable<ApplyForRefundBean> getSendGoodsDetails(
            @Field("itemId") String id,
            @Field("ordersId") String itemId,
            @Field("sign") String sign);

    //退货详情
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/show/{id}")
    Observable<ReturnsGoodsDetailsBean> ReturnGoodsDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //退货添加
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/newsave")
    Observable<BaseABean> addReturnsGoods(
            @Field("cgCustomerId") String cgCustomerId,
            @Field("itemId") String itemId,
            @Field("ordersId") String ordersId,
            @Field("returnPrice") String returnPrice,
            @Field("returnQty") String returnQty,
            @Field("saleItemId") String saleItemId,
            @Field("salePrice") String salePrice,
            @Field("type") String type,
            @Field("types") String types,
            @Field("sign") String sign);

    //退货修改
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/save")
    Observable<BaseABean> updataReturnsGoods(
            @Field("cgCustomerId") String cgCustomerId,
            @Field("id") String id,
            @Field("itemId") String itemId,
            @Field("ordersId") String ordersId,
            @Field("returnPrice") String returnPrice,
            @Field("returnQty") String returnQty,
            @Field("saleItemId") String saleItemId,
            @Field("salePrice") String salePrice,
            @Field("type") String type,
            @Field("types") String types,
            @Field("sign") String sign);

    //退货跟踪
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST(" return/track")
    Observable<ReturnTrackingListBean> getReturnTrackingList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("sign") String sign);

    //查询客户
    @Headers({"urlname:marketing"})
    @POST("return/customer")
    Observable<CustomerListBean> getCustomerList();

    //查询包装
    @Headers({"urlname:marketing"})
    @POST("return/packaging")
    Observable<PackagingListBean> getPackagingList();

    //查询商品
    @Headers({"urlname:marketing"})
    @POST("return/item")
    Observable<GoodsListBean> getGoodsList();

    //带条件查询客户
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/customer")
    Observable<CustomerListBean> getCustomerTypeList(
            @Field("type") String id,
            @Field("sign") String sign);

    //删除退货单
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/delete/{id}")
    Observable<BaseABean> deleteReturnGoods(
            @Path("id") String id,
            @Field("sign") String sign);

    //重新申请退货
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("return/reapply/{id}")
    Observable<ReapplyReturnGoods> reapplyReturnGoods(
            @Path("id") String id,
            @Field("sign") String sign);

}
