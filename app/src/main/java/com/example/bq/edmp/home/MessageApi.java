package com.example.bq.edmp.home;

import com.example.bq.edmp.bean.AddressBean;
import com.example.bq.edmp.bean.Address_DepartmentBean;
import com.example.bq.edmp.bean.Address_StaffBean;
import com.example.bq.edmp.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MessageApi {

    //密码登录接口
    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> login(
            @Field("password") String password,
            @Field("username") String username,
            @Field("sign") String sign);


    //通讯录部门列表1
    @POST("org/addressbook")
    Observable<Address_DepartmentBean> addressbook_department();

    //通讯录员工列表2
    @POST("employee/addressbook")
    Observable<Address_StaffBean> addressbook_staff();

    //通讯录员工列表2
    @POST("employee/addressbooks")
    Observable<AddressBean> addressbook();

}
