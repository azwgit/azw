package com.example.bq.edmp.home;

import com.example.bq.edmp.bean.AddressBean;
import com.example.bq.edmp.bean.Address_DepartmentBean;
import com.example.bq.edmp.bean.Address_StaffBean;
import com.example.bq.edmp.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MessageApi {

    //通讯录员工列表2
    @Headers({"urlname:mdffx"})
    @POST("employee/addressbooks")
    Observable<AddressBean> addressbook();

}
