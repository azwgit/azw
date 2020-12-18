package com.example.bq.edmp.work.inventorytransfer.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.inventorytransfer.bean.GoodsDetailsBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.VarittiesListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AllocationApi {


    //根据公司id查询仓库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/allwarehouse")
    Observable<WareHouseListBean> getWarehouseList(
            @Field("orgId") String orgId,
            @Field("types") String types,
            @Field("sign") String sign);


    //查询所有分子公司
    @Headers({"urlname:production"})
    @POST("system/query/allorg")
    Observable<SubsidiaryCompanyBean> getSubsidiaryCompanyList();

    //申请调拨
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/newsave")
    Observable<BaseABean> addAllot(
            @Field("inOrgId") String inOrgId,
            @Field("inWarehouse") String inWarehouse,
            @Field("outOrgId") String outOrgId,
            @Field("outWarehouse") String outWarehouse,
            @Field("reason") String reason,
            @Field("types") String types,
            @Field("sign") String sign);

    //查询所有包装
    @Headers({"urlname:production"})
    @POST("system/query/allpackage")
    Observable<AllpackageListBean> getAllpackageList();

    //查询所有包装
    @Headers({"urlname:production"})
    @POST("system/query/allvariety")
    Observable<VarittiesListBean> getVarietiesList();

    //添加调拨商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/item/newsave")
    Observable<BaseABean> addTransferGoods(
            @Field("inItemId") String inItemId,
            @Field("qty") String qty,
            @Field("stockAllotId") String stockAllotId,
            @Field("sign") String sign);

    //调拨详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/show/{id}")
    Observable<EditFinishedProductAllocationBean> getProudctAllocationDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //根据公司id查询仓库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/delete/{id}")
    Observable<BaseABean> deleteAllot(
            @Path("id") String id,
            @Field("sign") String sign);

    //调拨提交
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/submit/{id}")
    Observable<BaseABean> submitAllot(
            @Path("id") String id,
            @Field("sign") String sign);

    //删除调拨商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/item/delete")
    Observable<BaseABean> deleteGoods(
            @Field("inItemId") String inItemId,
            @Field("stockAllotId") String stockAllotId,
            @Field("sign") String sign);

    //调拨商品详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/item/show")
    Observable<GoodsDetailsBean> getGoodsDetails(
            @Field("inItemId") String inItemId,
            @Field("stockAllotId") String stockAllotId,
            @Field("sign") String sign);

    //修改调拨商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/item/save")
    Observable<BaseABean> updateTransferGoods(
            @Field("inItemId") String inItemId,
            @Field("qty") String qty,
            @Field("stockAllotId") String stockAllotId,
            @Field("sign") String sign);
}
