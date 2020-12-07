package com.example.bq.edmp.word.inventory.api;

import com.example.bq.edmp.word.bean.AuditDspNumberBean;
import com.example.bq.edmp.word.bean.AuditListBean;
import com.example.bq.edmp.word.bean.FirstResult;
import com.example.bq.edmp.word.bean.SecondResult;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.word.inventory.bean.CkBean;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.InventoryBean;
import com.example.bq.edmp.word.inventory.bean.InventoryTabBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;

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
    @POST("allcrop")
    Observable<InventoryTabBean> getInventoryTabData();

       //生产管理   库存查询列表
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("grain/stock/list")
    Observable<InventoryBean> getInventoryData(
            @Field("cropId") String cropId,//作物id
            @Field("orgIds") String orgIds,//	分公司id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("varietyId") String varietyId,//	品种id
            @Field("warehouseId") String warehouseId,//	仓库id
            @Field("sign") String sign
    );

    //生产管理   库存查询　　筛选品种
    @Headers({"urlname:production"})
    @POST("allvariety")
    Observable<SxPzBean> getPzData();


    //生产管理   库存查询　　筛选   仓库
    @Headers({"urlname:production"})
    @POST("allwarehouse")
    Observable<CompanyBean> getCkData();

    //生产管理   库存查询　　筛选   分公司
    @Headers({"urlname:production"})
    @POST("allorg")
    Observable<CompanyBean> getCompanyData();

}
