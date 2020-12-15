package com.example.bq.edmp.work.library.api;

import com.example.bq.edmp.work.library.bean.ChuLibraryBean;
import com.example.bq.edmp.work.library.bean.CxLibraryBean;
import com.example.bq.edmp.work.library.bean.LibraryBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LibraryApi {


    //入库查询
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/addstock/list")
    Observable<LibraryBean> getRlibraryData(
            @Field("code") String code,//单号
            @Field("orgId") String orgId,//分司id
            @Field("packagingId") String packagingId,//包装id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("type2") String type2,//入库类型  （1采购入库 2加工入库 3调拨入库）
            @Field("varietyId") String varietyId,//品种id
            @Field("warehouseId") String warehouseId,//仓库id
            @Field("sign") String sign
    );


    //出库查询
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/substock/list")
    Observable<ChuLibraryBean> getClibraryData(
            @Field("code") String code,//单号
            @Field("orgId") String orgId,//分司id
            @Field("packagingId") String packagingId,//包装id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("type2") String type2,//入库类型  （1采购入库 2加工入库 3调拨入库）
            @Field("varietyId") String varietyId,//品种id
            @Field("warehouseId") String warehouseId,//仓库id
            @Field("sign") String sign
    );


    //库存查询
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("product/stock/list")
    Observable<CxLibraryBean> getCxlibraryData(
            @Field("orgId") String orgId,//分司id
            @Field("packagingId") String packagingId,//包装id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("varietyId") String varietyId,//品种id
            @Field("warehouseId") String warehouseId,//仓库id
            @Field("sign") String sign
    );

}
