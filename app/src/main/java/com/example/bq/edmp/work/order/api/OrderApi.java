package com.example.bq.edmp.work.order.api;

import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.word.inventory.bean.InventoryTabBean;
import com.example.bq.edmp.work.detection.bean.DetectionTestingBean;
import com.example.bq.edmp.work.order.bean.CustomerBean;
import com.example.bq.edmp.work.order.bean.GoodsBean;
import com.example.bq.edmp.work.order.bean.HistoryBean;
import com.example.bq.edmp.work.order.bean.OrderDetailsBean;
import com.example.bq.edmp.work.order.bean.OrderTJBean;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;
import com.example.bq.edmp.work.order.bean.OrderZuoWuBean;
import com.example.bq.edmp.work.order.bean.StatusBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import io.reactivex.Observable;
import retrofit2.http.Path;

public interface OrderApi {

    //所有作物
    @Headers({"urlname:production"})
    @POST("system/query/allcrop")
    Observable<OrderZuoWuBean> getZuoWuData();

    //查询所有状态
    @Headers({"urlname:production"})
    @POST("order/status")
    Observable<StatusBean> getStatusData();

    //订单待提交列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/submitlist")
    Observable<OrderTJBean> getSubmitlist(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );


    //订单添加  （下一步）
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/newsave")
    Observable<BaseABean> getNewsave(
            @Field("address") String address,//送货地址
            @Field("contacts") String contacts,//联系人
            @Field("customerId") String customerId,//客户id
            @Field("mobTel") String mobTel,//联系方式
            @Field("region") String region,//区域id
            @Field("sign") String sign
    );


    //订单详情
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/show/{id}")
    Observable<OrderDetailsBean> getShow(
            @Path("id") String id,
            @Field("sign") String sign);


    //订单删除
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/delete/{id}")
    Observable<BaseABean> getDelete(
            @Path("id") String id,//订单id
            @Field("sign") String sign);

    //订单保存
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/save")
    Observable<BaseABean> getSave(
            @Field("address") String address,//送货地址
            @Field("contacts") String contacts,//联系人
            @Field("customerId") String customerId,//客户id
            @Field("id") String id,//订单id
            @Field("mobTel") String mobTel,//联系方式
            @Field("sign") String sign
    );


    //商品添加
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/commodity/newsave")
    Observable<BaseABean> getNewsaveGoods(
            @Field("ordersId") String ordersId,//订单id
            @Field("packagingId") String packagingId,//包装id
            @Field("price") String price,//价格
            @Field("qty") String qty,//提货量
            @Field("sign") String sign
    );


    //订单提交（并保存）
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/submit")
    Observable<BaseABean> getSubmit(
            @Field("address") String address,//送货地址
            @Field("contacts") String contacts,//联系人
            @Field("customerId") String customerId,//客户id
            @Field("id") String id,//订单id
            @Field("mobTel") String mobTel,//联系电话
            @Field("sign") String sign
    );


    //客户列表
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/customerlist")
    Observable<CustomerBean> getCustomerlist(
            @Field("name") String name,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("sign") String sign
    );

    //根据作物选择包装
    @FormUrlEncoded
    @Headers({"urlname:production"})
    @POST("system/query/packaging/crop")
    Observable<GoodsBean> getGoodslist(
            @Field("customerId") String customerId,//客户id
            @Field("id") String id,//作物id
            @Field("sign") String sign
    );


    //商品删除
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/commodity/delete")
    Observable<BaseABean> getDeleteShang(
            @Field("ordersId") String ordersId,//订单id
            @Field("packagingId") String packagingId,//包装id
            @Field("sign") String sign
    );

    //商品修改
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/commodity/save")
    Observable<BaseABean> getSaveShang(
            @Field("ordersId") String ordersId,//订单id
            @Field("packagingId") String packagingId,//包装id
            @Field("price") String price,//金额
            @Field("qty") String qty,//提货量
            @Field("sign") String sign
    );

    //订单跟踪
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/tracklist")
    Observable<OrderTrackingBean> getTracklist(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("sign") String sign
    );

    //历史订单
    @FormUrlEncoded
    @Headers({"urlname:marketing"})
    @POST("order/historylist")
    Observable<HistoryBean> getHistorylist(
            @Field("condition") String condition,//客户名称/订单号
            @Field("endTime") String endTime,//结束时间
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("startTime") String startTime,//添加时间
            @Field("sign") String sign
    );

}
