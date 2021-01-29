package com.example.bq.edmp.work.reseller.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.reseller.bean.PzBean;
import com.example.bq.edmp.work.tracking.bean.TrackingDereBean;
import com.example.bq.edmp.work.tracking.bean.TrackingListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ResellerApi {

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

    //根据公司id查询仓库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/allwarehouse")
    Observable<WareHouseListBean> getWarehouseList(
            @Field("orgId") String orgId,
            @Field("types") String types,
            @Field("sign") String sign);


    //调拨详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/show/{id}")
    Observable<EditFinishedProductAllocationBean> getProudctAllocationDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //查询所有包装
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/allitem")
    Observable<AllpackageListBean> getAllpackageList(
            @Field("categoryFullId") String categoryFullId,
            @Field("sign") String sign
    );

    //查询所有商品粮
    @Headers({"urlname:production"})
    @POST("system/query/allitem")
    Observable<AllpackageListBean> getAllitemList();

    //转商待提交
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/tosubmit")
    Observable<BaseAllocationBeam> getAllocationDTJ(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //删除调拨商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/item/delete")
    Observable<BaseABean> deleteGoods(
            @Field("inItemId") String inItemId,
            @Field("outItemId") String outItemId,
            @Field("stockAllotId") String stockAllotId,
            @Field("sign") String sign);


    //添加转商商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/item/newsave")
    Observable<BaseABean> addTransferGoods(
            @Field("inItemId") String inItemId,
            @Field("outItemId") String outItemId,
            @Field("qty") String qty,
            @Field("stockAllotId") String stockAllotId,
            @Field("sign") String sign);


    //修改转商商品
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/item/save")
    Observable<BaseABean> addEtTransferGoods(
            @Field("inItemId") String inItemId,
            @Field("outItemId") String outItemId,
            @Field("qty") String qty,
            @Field("stockAllotId") String stockAllotId,
            @Field("sign") String sign);

    //删除转商
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/delete/{id}")
    Observable<BaseABean> deleteAllot(
            @Path("id") String id,
            @Field("sign") String sign);

    //转商提交
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/submit/{id}")
    Observable<BaseABean> submitAllot(
            @Path("id") String id,
            @Field("sign") String sign);

    //转商跟踪
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/commodity/tracklist")
    Observable<BaseAllocationBeam> getTrackList(
            @Field("code") String code,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") String status,
            @Field("sign") String sign
    );

    //转商跟踪  筛选
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/commodity/tracklist")
    Observable<BaseAllocationBeam> getTrackListShai(
            @Field("code") String code,
            @Field("inOrgId") String inOrgId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") String status,
            @Field("varietyId") String varietyId,
            @Field("sign") String sign
    );

    //转商处理
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/commodity/handlelist")
    Observable<BaseAllocationBeam> getHandleList(
            @Field("code") String code,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") String status,
            @Field("sign") String sign
    );

    //转商跟踪  筛选
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/commodity/handlelist")
    Observable<BaseAllocationBeam> getHandleListShai(
            @Field("code") String code,
            @Field("inOrgId") String inOrgId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") String status,
            @Field("varietyId") String varietyId,
            @Field("sign") String sign
    );

    //转商历史
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/accomplish")
    Observable<BaseAllocationBeam> getAllocationCompleteData(
            @Field("code") String code,//调拨单号
            @Field("inOrgId") String inOrgId,//
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("types") String types,//调拨类型：1原粮调拨 2成品调拨 3转商调拨	number
            @Field("varietyId") String varietyId,//
            @Field("sign") String sign
    );

    //转商重新申请
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/reapply/{id}")
    Observable<BaseABean> getGainDetails(
            @Path("id") String id,
            @Field("sign") String sign);


    //转商确认出库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/commodity/outsolve/{id}")
    Observable<BaseABean> getConfirm(
            @Path("id") String id,
            @Field("sign") String sign);


    //转商确认入库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allot/commodity/insolve/{id}")
    Observable<BaseABean> getConfirmIntData(
            @Path("id") String id,
            @Field("sign") String sign);


    //筛选品种
    @Headers({"urlname:production"})
    @POST("allot/commodity/allvariety")
    Observable<PzBean> getPzData();

    //筛选   分公司
    @Headers({"urlname:production"})
    @POST("system/query/allorg")
    Observable<CompanyBean> getCompanyData();

}
