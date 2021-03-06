package com.example.bq.edmp.word.put_warehouse.api;

import com.example.bq.edmp.word.put_warehouse.bean.ChuKuWarehouseListBean;
import com.example.bq.edmp.word.put_warehouse.bean.RuKuWarehouseListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WarehouseApi {


    //生产管理   入库列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/addstock/list")
    Observable<RuKuWarehouseListBean> getWarehouseListData(
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


    //生产管理   出库列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/substock/list")
    Observable<ChuKuWarehouseListBean> getChuWarehouseListData(
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
}
