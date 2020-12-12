package com.example.bq.edmp.work.grainmanagement.api;

import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.bean.PayReimbursementDetailsInfo;
import com.example.bq.edmp.activity.apply.bean.RevokeApplyBean;
import com.example.bq.edmp.activity.apply.bean.SelectReimbursementDetailsBean;
import com.example.bq.edmp.activity.apply.bean.UpdateRembursemenBean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsBean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsInfo;
import com.example.bq.edmp.activity.login.UserInfoBean;
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
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingOutDetailBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RawGrainManagementApi {

    //称重皮重详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/purchase/tare")
    Observable<TraeBean> getTraeDetail(
            @Field("code") String id,
            @Field("sign") String sign);

    //添加毛重
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/purchase/tare/add")
    Observable<BaseABean> addTrae(
            @Field("code") String id,
            @Field("tareWeight") String tareWeight,
            @Field("sign") String sign);

    //称重毛重详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/purchase/grossweight")
    Observable<GrossWeightBean> getGrossweightDetail(
            @Field("code") String id,
            @Field("sign") String sign);

    //添加毛重
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/purchase/grossweight/add")
    Observable<BaseABean> addGrossweight(
            @Field("code") String id,
            @Field("grossWeight") String grossWeight,
            @Field("sign") String sign);

    //出库详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/substock/show/{id}")
    Observable<WarehouseingOutDetailBean> getWareHousingOutDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //入库详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/addstock/show/{id}")
    Observable<WarehouseingDetailBean> getWareHousingDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //收购记录详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/purchase/{id}")
    Observable<AcquisitionBean> getAcquisitionDetail(
            @Path("id") String id,
            @Field("sign") String sign);

    //库存详情
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/stock")
    Observable<StockDetailBean> getStockDetail(
            @Field("itemId") String itemId,
            @Field("warehouseId") String warehouseId,
            @Field("sign") String sign);

    //卸货验证
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("/grain/purchase/checked")
    Observable<UnloadingVerificationBean> getUnloadingVerificationInfo(
            @Field("code") String id,
            @Field("sign") String sign);

    //新增收购
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/purchase/addnew")
    Observable<BaseABean> addAcquisitions(
            @Field("farmId") String id,
            @Field("farmerId") String testinformation,
            @Field("testPlanItemId") List<String> testingItemIdList,
            @Field("testingItemValue") List<String> testingItemValueList,
            @Field("varietyId") String varietyId,
            @Field("warehouseId") String warehouseId,
            @Field("sign") String sign);

    //查询当前用户所在农场的承包人
    @Headers({"urlname:production"})
    @POST("farm/farmer")
    Observable<ContractorListBean> getContractorList();

    //查询承包人所有签订品种
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("farmer/varieties/{id}")
    Observable<VarietiesListBean> getVarietiesList(
            @Path("id") String id,
            @Field("sign") String sign);

    //根据品种查询仓库
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("warehouse/forvariety/{id}")
    Observable<WareHouseListBean> getWarehouseList(
            @Field("id") String id,
            @Field("sign") String sign);

    //根据作物查询检测项
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("testplan/crop/{id}")
    Observable<TestingBeanList> getTestingList(
            @Path("id") String id,
            @Field("sign") String sign);
}
