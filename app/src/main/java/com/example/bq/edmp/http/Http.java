package com.example.bq.edmp.http;


import android.content.Intent;
import android.util.Log;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.activity.login.LoginActivity;
import com.example.bq.edmp.bean.GetSessionResult;
import com.example.bq.edmp.bean.ResponseResult;
import com.example.bq.edmp.url.UrlHelper;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MyCookie;
import com.example.bq.edmp.utils.NetworkUtil;
import com.example.bq.edmp.utils.SpUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import okio.Buffer;
import okhttp3.Cache;

/**
 * Created by GaoSheng on 2016/9/14.
 */

public class Http {

    private static OkHttpClient client;
    private static HttpService httpService;
    private static volatile Retrofit retrofit;


    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static HttpService getHttpService() {
        if (httpService == null) {
            httpService = getRetrofit().create(HttpService.class);
        }
        return httpService;
    }


    /**
     * 设置公共参数
     */
    private static Interceptor addQueryParameterInterceptor() {
        Interceptor addQueryParameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
//                        .addQueryParameter("phoneSystem", "")
//                        .addQueryParameter("phoneModel", "")
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        return addQueryParameterInterceptor;
    }

    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        // Provide your custom header here
//                        .header("cookie", (String) SpUtils.get("JSESSIONID=",""))
                        .header("Access-Token", (String) SpUtils.get("accesstoken", ""))
                        .method(originalRequest.method(), originalRequest.body());

                Request request = requestBuilder.build();
                Response response = chain.proceed(request);

//                ResponseResult responseResult = new Gson().fromJson(result, ResponseResult.class);
//
//                if ("-96".equals(responseResult.getResult().getCode())) {

                //


//                if (isTokenExpired(response, request)) {//根据和服务端的约定判断token过期
//                    //同步请求方式，获取最新的Token
//                  getNewToken();
//                    //使用新的Token，创建新的请求
//                    Request newRequest = chain.request()
//                            .newBuilder()
//                            .header("cookie", "JSESSIONID=" + MyCookie.COOKIE)
//                            .build();
//                    //重新请求
//                    back chain.proceed(newRequest);
//                }


                if (isJsessionID(response, request)) {//根据和服务端的约定判断token过期
                    //同步请求方式，获取最新的Token
//                    getNewToken();
                    String newJsession = getNewJsession();
                    if (newJsession.equals("")) {
                        getLogin();
                    } else {
                        MyCookie.ACCESSTOKEN = newJsession;

                        //使用新的Token，创建新的请求
                        Request newRequest = chain.request()
                                .newBuilder()
                                .header("cookie", "JSESSIONID=" + newJsession)
                                .build();
                        //重新请求
                        return chain.proceed(newRequest);
                    }
                }


                return response;
            }
        };
        return headerInterceptor;
    }

    private static String getNewJsession() throws IOException {
//        Http.getHttpService().getSession(MyCookie.ACCESSTOKEN)
//                .compose(new CommonTransformer<GetSessionResult>())
//                .subscribe(new CommonSubscriber<GetSessionResult>(ProApplication.getmContext()) {
//                    @Override
//                    public void onNext(GetSessionResult getSessionResult) {
//                        if (getSessionResult.getResult().getCode().equals("200")){
//                            MyCookie.COOKIE = getSessionResult.getData().getAccessToken();
//                        }
//                    }
//                });
        String accesstoken = (String) SpUtils.get("accesstoken", "");
        String MD5Sign = MD5Util.MD5("accessToken=" + accesstoken + "&key=YjU5YTA3NzEtMDI2MS00YzhiLTljM2ItYzE2MTljZDQwNDNhNGExYjEzZTUtYmIx");
        HttpService.LocationService locationService = retrofit.create(HttpService.LocationService.class);
        Call<GetSessionResult> session = locationService.getSession((String) accesstoken, MD5Sign);
        retrofit2.Response<GetSessionResult> execute = session.execute();
        if (execute.isSuccessful()) {
            GetSessionResult loginResult = execute.body();
            if (!"200".equals(loginResult.getResult().getCode())) {
                Log.e("TAG", "拦截器-----" + loginResult.getResult().getMessage());
                getLogin();
            } else {
                return loginResult.getData().getJSESSIONID();
            }
        }
        return "";
    }


    private static boolean isJsessionID(Response response, Request request) {
        Charset UTF8 = Charset.forName("UTF-8");

        String result = null;
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                //Couldn't decode the response body; charset is likely malformed.无法解码响应体；字符集可能是畸形的。
            }
        }

        if (contentLength != 0) {
            result = buffer.clone().readString(charset);
            Log.i("shangchengmall---->>>", "-----TokenInterceptor----- :\nrequest url:" + request.url() + "\ntime:" + System.currentTimeMillis() + "\nbody:" + result + "\n");
        }


        ResponseResult responseResult = new Gson().fromJson(result, ResponseResult.class);

        if ("-98".equals(responseResult.getResult().getCode())) {
            return true;
        }
        return false;
    }

    private static void getNewToken() {

        getLogin();

    }

    private static void getLogin() {
        Intent intent = new Intent(ProApplication.getmContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ProApplication.getmContext().startActivity(intent);
    }

    private static boolean isTokenExpired(Response response, Request request) {
        Charset UTF8 = Charset.forName("UTF-8");

        String result = null;
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                //Couldn't decode the response body; charset is likely malformed.无法解码响应体；字符集可能是畸形的。
            }
        }

        if (contentLength != 0) {
            result = buffer.clone().readString(charset);
            Log.i("shangchengmall---->>>", "-----TokenInterceptor----- :\nrequest url:" + request.url() + "\ntime:" + System.currentTimeMillis() + "\nbody:" + result + "\n");
        }


        ResponseResult responseResult = new Gson().fromJson(result, ResponseResult.class);

        if ("-96".equals(responseResult.getResult().getCode())) {

            return true;
        }
        return false;
    }

    /**
     * 设置缓存
     */
    private static Interceptor addCacheInterceptor() {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtil.isNetworkAvailable(ProApplication.getmContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtil.isNetworkAvailable(ProApplication.getmContext())) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" +
                                    maxStale)
                            .removeHeader("nyn")
                            .build();
                }
                return response;
            }
        };
        return cacheInterceptor;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (Http.class) {
                if (retrofit == null) {
                    //添加一个log拦截器,打印所有的log
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    //设置 请求的缓存的大小跟位置
                    File cacheFile = new File(ProApplication.getmContext().getCacheDir(), "cache");
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小

                    client = new OkHttpClient
                            .Builder()
                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
                            .addInterceptor(addHeaderInterceptor()) // token过滤
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
                            .cache(cache)  //添加缓存
                            .connectTimeout(60l, TimeUnit.SECONDS)
                            .readTimeout(60l, TimeUnit.SECONDS)
                            .writeTimeout(60l, TimeUnit.SECONDS)
                            .build();

                    // 获取retrofit的实例
                    retrofit = new Retrofit
                            .Builder()
                            .baseUrl(UrlHelper.BASE_URL)  //自己配置
                            .client(client)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create()) //这里是用的fastjson的
                            .build();
                }
            }
        }
        return retrofit;
    }

}
