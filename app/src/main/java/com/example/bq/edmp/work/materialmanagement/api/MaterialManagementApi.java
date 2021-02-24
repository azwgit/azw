package com.example.bq.edmp.work.materialmanagement.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.SelecGoodsListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.VarietiesBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.materialmanagement.bean.AddPurchaseGoodsBean;
import com.example.bq.edmp.work.materialmanagement.bean.EditMaterialBean;
import com.example.bq.edmp.work.materialmanagement.bean.MaterialListBean;
import com.example.bq.edmp.work.materialmanagement.bean.ProcurementDetailsBean;
import com.example.bq.edmp.work.materialmanagement.bean.ProcurementTrackingListFragmentBean;
import com.example.bq.edmp.work.materialmanagement.bean.QueryAllitemListBean;
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


    //采购详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/show/{id}")
    Observable<EditMaterialBean> getMaterialDetails(
            @Path("id") String id,
            @Field("sign") String sign);


    //删除采购
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/delete/{id}")
    Observable<BaseABean> deleteMaterial(
            @Path("id") String id,
            @Field("sign") String sign);


    //查询所有物料
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/allitem")
    Observable<QueryAllitemListBean> getQueryAllitem(
            @Field("categoryFullId") String categoryFullId,
            @Field("sign") String sign);

    //添加采购商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/itemnewsave")
    Observable<BaseABean> addPurchaseGoods(
            @Field("itemId") String itemId,
            @Field("materialPurchaseId") String materialPurchaseId,
            @Field("price") String price,
            @Field("qty") String qty,
            @Field("sign") String sign);

    //修改采购商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/itemsave")
    Observable<BaseABean> updataPurchaseGoods(
            @Field("itemId") String itemId,
            @Field("materialPurchaseId") String materialPurchaseId,
            @Field("price") String price,
            @Field("qty") String qty,
            @Field("sign") String sign);

    //删除附件
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/annexdelete/{id}")
    Observable<BaseABean> deleteEnclosure(
            @Path("id") String id,
            @Field("sign") String sign);


    //删除采购商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/itemdelete")
    Observable<BaseABean> deleteMaterialGoods(
            @Field("itemId") String itemId,
            @Field("materialPurchaseId") String materialPurchaseId,
            @Field("sign") String sign);


    //采购保存
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/save")
    Observable<BaseABean> updataMaterial(
            @Field("id") String id,
            @Field("remark") String remark,
            @Field("sign") String sign);


    //保存并提交
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/submit")
    Observable<BaseABean> saveAndSubmitMaterial(
            @Field("id") String id,
            @Field("remark") String remark,
            @Field("sign") String sign);


    //查询采购商品详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/itemshow")
    Observable<AddPurchaseGoodsBean> getPurchaseGoodsDetails(
            @Field("itemId") String itemId,
            @Field("materialPurchaseId") String materialPurchaseId,
            @Field("sign") String sign);


    //物料跟踪列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/tracklist")
    Observable<ProcurementTrackingListFragmentBean> getMaterialManagementList(
            @Field("itemId") String itemId,
            @Field("orgId") String orgId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("sign") String sign);

    //采购记录
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/historylist")
    Observable<ProcurementTrackingListFragmentBean> getMaterialHistoryList(
            @Field("itemId") String itemId,
            @Field("orgId") String orgId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //采购确认
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/confirmlist")
    Observable<ProcurementTrackingListFragmentBean> getMaterialConfirmList(
            @Field("itemId") String itemId,
            @Field("orgId") String orgId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);


    //筛选查询所有物料
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/allitem")
    Observable<VarietiesBean> getSelectQueryAllitem(
            @Field("categoryFullId") String categoryFullId,
            @Field("sign") String sign);


    //采购跟踪 采购确认 采购记录 详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/show/{id}")
    Observable<ProcurementDetailsBean> getMaterialDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //重新申请
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/reapply/{id}")
    Observable<BaseABean> reapplyMaterial(
            @Path("id") String id,
            @Field("sign") String sign);

    //完成采购
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("materialpurchase/finish")
    Observable<BaseABean> completeMaterila(
            @Field("id") String id,
            @Field("warehouseId") String warehouseId,
            @Field("sign") String sign);
}
