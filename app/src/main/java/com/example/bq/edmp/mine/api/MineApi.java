package com.example.bq.edmp.mine.api;

import com.example.bq.edmp.mine.bean.MineUserBean;
import com.example.bq.edmp.mine.bean.MineUserInfoBean;
import com.example.bq.edmp.mine.bean.StateBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by bq on 2020/11/17.
 */

public interface MineApi {

    //我的  页面用户基本信息
    @Headers({"urlname:manage"})
    @POST("current/user")
    Observable<MineUserBean> getUserData();

    //用户详细基本信息
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("employee/show/{id}")
    Observable<MineUserInfoBean> getMineUserData(
            @Path("id") String id,
            @Field("sign") String sign);

    //更换头像
    @Multipart
    @Headers({"urlname:manage"})
    @POST("current/user/saveimg")
    Observable<StateBean> personImg(
            @PartMap Map<String, RequestBody> file,
            @Part("type") String type,
            @Part("sign") String sing
    );

    //修改手机号
    @FormUrlEncoded
    @Headers({"urlname:manage"})
    @POST("current/user/savephone")
    Observable<StateBean> modificationPhone(
            @Field("mobTel") String mobTel,
            @Field("password") String password,
            @Field("sign") String sign
    );

    //修改座机
    @FormUrlEncoded
    @Headers({"urlname:manage"})
    @POST("current/user/savetelphone")
    Observable<StateBean> modificationSpecial(
            @Field("officeTel") String officeTel,
            @Field("sign") String sign
    );

    //修改邮箱
    @FormUrlEncoded
    @Headers({"urlname:manage"})
    @POST("current/user/saveemail")
    Observable<StateBean> modificationMail(
            @Field("email") String email,
            @Field("sign") String sign
    );

    //修改登录密码
    @FormUrlEncoded
    @Headers({"urlname:manage"})
    @POST("current/user/savepwd")
    Observable<StateBean> modificationPassword(
            @Field("newPassword") String newPassword,
            @Field("oldPassword") String oldPassword,
            @Field("sign") String sign
    );
}
