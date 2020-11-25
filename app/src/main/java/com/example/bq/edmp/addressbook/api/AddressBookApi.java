package com.example.bq.edmp.addressbook.api;

import com.example.bq.edmp.addressbook.bean.AddressBookSearchBean;
import com.example.bq.edmp.addressbook.bean.AddressBook_Bean_Data;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddressBookApi {

    //通讯录员工列表
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("employee/addressbooks")
    Observable<AddressBook_Bean_Data> getaddressbook(
            @Field("id") int id,
            @Field("sign") String sign
    );

    //查询 搜索 通讯录员工列表
    @FormUrlEncoded
    @Headers({"urlname:mdffx"})
    @POST("employee/addressbyname")
    Observable<AddressBookSearchBean> searchaddressbook(
            @Field("name") String name,
            @Field("sign") String sign
    );


}
