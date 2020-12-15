package com.example.bq.edmp.word.inventory.api;

import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.InventoryBean;
import com.example.bq.edmp.word.inventory.bean.InventoryTabBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.baozhuang.BzBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by bq on 2020/11/6.
 */

public interface InventoryListApi {

    //生产管理   tab
    @Headers({"urlname:production"})
    @POST("system/query/allcrop")
    Observable<InventoryTabBean> getInventoryTabData();


    //生产管理   库存查询列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/stock/list")
    Observable<InventoryBean> getInventoryData(
            @Field("cropId") String cropId,//作物id
            @Field("itemId") String itemId,//	品种id
            @Field("orgId") String orgId,//	分公司id  grain/stock/list
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("warehouseId") String warehouseId,//	仓库id
            @Field("sign") String sign
    );

    //生产管理   库存查询　　筛选品种
    @Headers({"urlname:production"})
    @POST("system/query/allvariety")
    Observable<SxPzBean> getPzData();


    //生产管理   库存查询　　筛选   仓库
    @Headers({"urlname:production"})
    @POST("system/query/allwarehouse")
    Observable<CompanyBean> getCkData();

    //生产管理   库存查询　　筛选   分公司
    @Headers({"urlname:production"})
    @POST("system/query/allorg")
    Observable<CompanyBean> getCompanyData();

    //  库存查询　　筛选   分公司    包装id
    @Headers({"urlname:production"})
    @POST("system/query/allpackage")
    Observable<BzBean> getBzIdData();


}
