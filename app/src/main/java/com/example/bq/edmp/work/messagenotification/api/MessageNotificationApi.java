package com.example.bq.edmp.work.messagenotification.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.SelecGoodsListBean;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.VarietiesBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.messagenotification.bean.MessageDetailsBean;
import com.example.bq.edmp.work.messagenotification.bean.MessageManagementListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageNotificationApi {

    //消息列表
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("message/list")
    Observable<MessageManagementListBean> getMessagetList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign);

    //消息详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("message/show")
    Observable<MessageDetailsBean> getMessageDetails(
            @Field("id") String id,
            @Field("sign") String sign);

}
