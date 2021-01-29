package com.example.bq.edmp.work.grainsale.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.word.put_warehouse.bean.ChuKuWarehouseListBean;
import com.example.bq.edmp.word.put_warehouse.bean.RuKuWarehouseListBean;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedStockDetailBean;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingDetailBean;
import com.example.bq.edmp.work.grainsale.bean.SaleDeliveryBean;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryBean;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryDetailsBean;
import com.example.bq.edmp.work.grainsale.bean.SaleStockBean;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseBean;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseDetailBean;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.reseller.bean.PzBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SaleApi {


    //销售   入库列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/addstock/list")
    Observable<SaleWarehouseBean> getWarehouseListData(
            @Field("beginTime") String beginTime, //开始时间
            @Field("code") String code, //单号
            @Field("endTime") String endTime, //结束时间
            @Field("orgId") String orgId, //公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("varietyId") String varietyId,//品种id
            @Field("warehouseId") String warehouseId,//仓库id
            @Field("sign") String sign
    );

    //入库详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/addstock/show/{id}")
    Observable<SaleWarehouseDetailBean> getWareHousingDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //销售   出库列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/substock/list")
    Observable<SaleDeliveryBean> getChuWarehouseListData(
            @Field("beginTime") String beginTime, //开始时间
            @Field("code") String code, //单号
            @Field("endTime") String endTime, //结束时间
            @Field("orgId") String orgId, //公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("varietyId") String varietyId,//品种id
            @Field("warehouseId") String warehouseId,//仓库id
            @Field("sign") String sign
    );

    //出库详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/substock/show/{id}")
    Observable<SaleWarehouseDetailBean> getDeliveryDetail(
            @Path("id") String id,
            @Field("sign") String sign);



    //销售   库存列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/stock/list")
    Observable<SaleStockBean> getStockData(
            @Field("itemId") String itemId, //品种id
            @Field("orgId") String orgId, //公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("warehouseId") String warehouseId,//仓库id
            @Field("sign") String sign
    );

    //库存详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/stock/show")
    Observable<FinishedStockDetailBean> getStockDetail(
            @Field("itemId") String packagingId,
            @Field("warehouseId") String warehouseId,
            @Field("sign") String sign);

    //销售   历史列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/historylist")
    Observable<SaleHistoryBean> getHistoryData(
            @Field("code") String code, //单号
            @Field("orgId") String orgId, //公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("varietyId") String varietyId,//品种id
            @Field("sign") String sign
    );

    //销售   历史列表详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("cgorder/show/{id}")
    Observable<SaleHistoryDetailsBean> getHistoryDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //　筛选品种
    @Headers({"urlname:production"})
    @POST("allot/commodity/allvariety")
    Observable<PzBean> getPzData();



}
