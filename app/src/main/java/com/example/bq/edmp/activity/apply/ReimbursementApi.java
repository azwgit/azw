package com.example.bq.edmp.activity.apply;

import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.bean.PayReimbursementDetailsInfo;
import com.example.bq.edmp.activity.apply.bean.RevokeApplyBean;
import com.example.bq.edmp.activity.apply.bean.SelectReimbursementDetailsBean;
import com.example.bq.edmp.activity.apply.bean.UpdateRembursemenBean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsBean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsInfo;
import com.example.bq.edmp.activity.login.UserInfoBean;
import com.example.bq.edmp.bean.AddressBean;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReimbursementApi {

    //费用报销保存
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/save")
    Observable<ApplyPayBean> submitReimburser(
            @Field("advanceLoan") String advanceloan,
            @Field("dates") String datas,
            @Field("tdReason") String tdReason,
            @Field("types") String types,
            @Field("sign") String sign);

    //开支项保存
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/save")
    Observable<ApplyPayBean> upDataReimburser(
            @Field("advanceLoan") String advanceloan,
            @Field("dates") String datas,
            @Field("id") String id,
            @Field("remark") String remark,
            @Field("tdReason") String tdReason,
            @Field("types") String types,
            @Field("sign") String sign);

    //费用报销保存并提交
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/newsave")
    Observable<ApplyPayBean> saveAndComintReimburser(
            @Field("advanceLoan") String advanceloan,
            @Field("dates") String datas,
            @Field("id") String id,
            @Field("remark") String remark,
            @Field("tdReason") String tdReason,
            @Field("types") String types,
            @Field("sign") String sign);

    //开支报销删除
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/item/delete")
    Observable<ApplyPayBean> deleteApplyPay(
            @Field("idx") String idx,
            @Field("reimburserId") String reimburserId,
            @Field("sign") String sign);
    //查询开支报销详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/item/show")
    Observable<SelectReimbursementDetailsBean> selectReimbureser(
            @Field("idx") String idx,
            @Field("reimburserId") String reimburserId,
            @Field("sign") String sign);

    //开支报销修改
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/item/save")
    Observable<UpdateRembursemenBean> updateReimbursement(
            @Field("amount") String amount,
            @Field("idx") String idx,
            @Field("name") String name,
            @Field("reimburserId") String reimburserId,
            @Field("sign") String sign);
    //删除单据
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/bill/delete")
    Observable<String> deleteImg(
            @Field("id") String id,
            @Field("sign") String sign);
    //删除单据
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/bill/delete")
    Observable<BaseABean> deleteIdBill(
            @Field("id") String id,
            @Field("itemType") String itemType,
            @Field("reimburserId") String reimburserId,
            @Field("sign") String sign);


    //申请支出报账详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/show/{id}")
    Observable<PayReimbursementDetailsInfo> getPayReimbursementDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //差旅报账详情 返回实体未确定 暂时复制一份
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/show/{id}")
    Observable<TravelDetailsInfo> getTravelDetails(
            @Path("id") String id,
            @Field("sign") String sign);

    //删除报账
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/del/{id}")
    Observable<PayReimbursementDetailsInfo> deleteApply(
            @Path("id") String id,
            @Field("sign") String sign);
    //撤销申请
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/recall/{id}")
    Observable<RevokeApplyBean> revokeApply(
            @Path("id") String id,
            @Field("sign") String sign);

    //复制申请单
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/copy/{id}")
    Observable<BaseABean> copyReimburser(
            @Path("id") String id,
            @Field("sign") String sign);
    //查询个人信息
    @Headers({"urlname:manage"})
    @POST("current/user")
    Observable<UserInfoBean> getUserInfo();

    //查询差旅日程信息详情
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/traveling/show")
    Observable<TravelDetailsBean> getTravelInfo(
            @Field("idx") String id,
            @Field("reimburserId") String reimburserId,
            @Field("sign") String sign);

    //差旅报销日程信息删除
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/traveling/delete")
    Observable<BaseABean> deleteTraveling(
            @Field("idx") String id,
            @Field("reimburserId") String reimburserId,
            @Field("sign") String sign);

    //修改差旅日程信息
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("reimburser/traveling/save")
    Observable<BaseABean> UpdataTraveling(
            @Field("arriveRegion") String arriveRegion,
            @Field("arriveTime") String arriveTime,
            @Field("days") String days,
            @Field("idx") String idx,
            @Field("reimburserId") String reimburserId,
            @Field("setOutRegion") String setOutRegion,
            @Field("setOutTime") String setOutTime,
            @Field("subsidy") String subsidy,
            @Field("transport") String transport,
            @Field("transportFee") String transportFee,
            @Field("sign") String sign);

}
