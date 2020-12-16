package com.example.bq.edmp.work.finishedproduct.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedStockDetailBean;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedWareHousingOutDetailBean;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedWarehousingBean;
import com.example.bq.edmp.work.finishedproduct.bean.LogisticsListBean;
import com.example.bq.edmp.work.finishedproduct.bean.MachiningTaskDetailsBean;
import com.example.bq.edmp.work.finishedproduct.bean.SendGoodsDetailsBean;
import com.example.bq.edmp.work.finishedproduct.bean.VechicleListBean;
import com.example.bq.edmp.work.finishedproduct.bean.WarehouseListBean;
import com.example.bq.edmp.work.grainmanagement.bean.AcquisitionBean;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.grainmanagement.bean.GrossWeightBean;
import com.example.bq.edmp.work.grainmanagement.bean.StockDetailBean;
import com.example.bq.edmp.work.grainmanagement.bean.TestingBeanList;
import com.example.bq.edmp.work.grainmanagement.bean.TraeBean;
import com.example.bq.edmp.work.grainmanagement.bean.UnloadingVerificationBean;
import com.example.bq.edmp.work.grainmanagement.bean.VarietiesListBean;
import com.example.bq.edmp.work.grainmanagement.bean.WareHouseListBean;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingDetailBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FinishedProductApi {

    //查询物流列表
    @Headers({"urlname:production"})
    @POST("product/order/tpl/list")
    Observable<LogisticsListBean> getLogisticsList();
    //查询物流列表
    @Headers({"urlname:production"})
    @POST("order/truck")
    Observable<VechicleListBean> getVehicleList();
    //发货详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/order/show/{id}")
    Observable<SendGoodsDetailsBean> getSendGoodsDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //发货
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/order/sendout/affirmgoods")
    Observable<BaseABean> sendGoods(
            @Field("ordersId") String ordersId,
            @Field("remark") String remark,
            @Field("tplName") String tplName,
            @Field("tplNo") String tplNo,
            @Field("truckId") String truck_id,
            @Field("types") String types,
            @Field("sign") String sign);

    //加工任务详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/process/show")
    Observable<MachiningTaskDetailsBean> getMachiningTaskDetails(
            @Field("code") String code,
            @Field("packagingId") String packagingId,
            @Field("sign") String sign);
    //接受任务
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/process/accept")
    Observable<BaseABean> acceptTask(
            @Field("code") String code,
            @Field("sign") String sign);
    //终止加工
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/process/stop")
    Observable<BaseABean> stopTask(
            @Field("code") String code,
            @Field("sign") String sign);
    //删除任务
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/process/delete/{id}")

    Observable<BaseABean> deleteTask(
            @Path("id") String id,
            @Field("sign") String sign);
    //加工上报
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/processing")
    Observable<BaseABean> ReporTask(
            @Field("code") String code,
            @Field("cpwarehouseId") String cpwarehouseId,
            @Field("finishedQty") String finishedQty,
            @Field("fswarehouseId") String fswarehouseId,
            @Field("productWeight") String productWeight,
            @Field("ylwarehouseId") String ylwarehouseId,
            @Field("sign") String sign);
    //查询承包人所有签订品种
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("allwarehouse")
    Observable<WarehouseListBean> getWarehouseList(
            @Field("types") String types,
            @Field("sign") String sign);

    //库存详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/stock/show")
    Observable<FinishedStockDetailBean> getStockDetail(
            @Field("itemId") String packagingId,
            @Field("warehouseId") String warehouseId,
            @Field("sign") String sign);

    //入库详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/addstock/show/{id}")
    Observable<FinishedWarehousingBean> getWareHousingDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //出库详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/substock/show/{id}")
    Observable<FinishedWareHousingOutDetailBean> getWareHousingOutDetail(
            @Path("id") String id,
            @Field("sign") String sign);

}
