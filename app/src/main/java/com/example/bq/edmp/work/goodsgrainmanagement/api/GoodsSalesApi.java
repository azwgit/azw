package com.example.bq.edmp.work.goodsgrainmanagement.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.SelecGoodsListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.VarietiesBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
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

public interface GoodsSalesApi {

    //商品销售待提交列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/list")
    Observable<GoodsSalesManagementListBean> getGoodsSalesManagementList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("sign") String sign);

    //新增商品销售
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/newsave")
    Observable<BaseABean> addGoodsSales(
            @Field("address") String address,
            @Field("contacts") String contacts,
            @Field("customerId") String customerId,
            @Field("mobTel") String mobTel,
            @Field("orgId") String orgId,
            @Field("region") String region,
            @Field("warehouseId") String warehouseId,
            @Field("sign") String sign);

    //查询所有分子公司
    @Headers({"urlname:production"})
    @POST("system/query/allorg")
    Observable<SubsidiaryCompanyBean> getSubsidiaryCompanyList();

    //查询仓库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/allwarehouse")
    Observable<WareHouseListBean> getWarehouseList(
            @Field("orgId") String orgId,
            @Field("types") String types,
            @Field("sign") String sign);

    //商品列表
    @Headers({"urlname:production"})
    @POST("system/query/allitem")
    Observable<SelecGoodsListBean> getSalesList();

    //添加商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/newsaveitem")
    Observable<BaseABean> addGoods(
            @Field("cgOrderId") String cgOrderId,
            @Field("itemId") String itemId,
            @Field("price") String price,
            @Field("qty") String qty,
            @Field("sign") String sign);


    //新增商品详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/show/{id}")
    Observable<EditGoodSalesBean> getGoodsDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //删除商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/delitem")
    Observable<BaseABean> deleteGoods(
            @Field("cgOrderId") String cgOrderId,
            @Field("itemId") String itemId,
            @Field("sign") String sign);

    //修改商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/saveitem")
    Observable<BaseABean> updataGoods(
            @Field("cgOrderId") String cgOrderId,
            @Field("itemId") String itemId,
            @Field("price") String price,
            @Field("qty") String qty,
            @Field("sign") String sign);


    //商品粮销售删除
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/delete/{id}")
    Observable<BaseABean> deleteGoodsSales(
            @Path("id") String id,
            @Field("sign") String sign);


    //商品粮销售删除
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/submit/{id}")
    Observable<BaseABean> submitGoodsSales(
            @Path("id") String id,
            @Field("sign") String sign);


    //商品销售跟踪列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/tracklist")
    Observable<GoodsSalesManagementListBean> getGoodsSalesTracktList(
            @Field("orgId") String orgId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("varietyId") String varietyId,
            @Field("sign") String sign);

    //商品粮销售确认列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/confirmlist")
    Observable<GoodsSalesManagementListBean> getGoodsSalesConfirmtList(
            @Field("orgId") String orgId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("varietyId") String varietyId,
            @Field("sign") String sign);

    //品种列表
    @Headers({"urlname:production"})
    @POST("allot/commodity/allvariety")
    Observable<VarietiesBean> getVarietiesList();


    //确认出库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/subconfirm")
    Observable<BaseABean> confirmDelivery(
            @Field("code") String id,
            @Field("sign") String sign);

    //销售确认
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/confirm")
    Observable<BaseABean> salesConfirmation(
            @Field("code") String id,
            @Field("sign") String sign);
}
