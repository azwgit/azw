package com.example.bq.edmp.work.materialmanagement.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.SelecGoodsListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.VarietiesBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.materialmanagement.bean.MaterialListBean;
import com.example.bq.edmp.work.materialmanagement.bean.UserInfoBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MaterialManagementApi {

    //物料管理待提交列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/list")
    Observable<MaterialListBean> getMaterialManagementList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //采购添加
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/newsave")
    Observable<BaseABean> addMaterial(
            @Field("remark") String address,
            @Field("sign") String sign);

    //查询当前用户信息
    @Headers({"urlname:production"})
    @POST("materialpurchase/getuser")
    Observable<UserInfoBean> getUserInfo();



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
