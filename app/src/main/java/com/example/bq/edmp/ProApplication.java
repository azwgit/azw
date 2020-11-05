package com.example.bq.edmp;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.config.OkHttpConfig;
import com.allen.library.interfaces.BuildHeadersListener;
import com.example.bq.edmp.utils.SpUtils;
import com.weavey.loading.lib.LoadingLayout;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;


/**
 * Created by GaoSheng on 2016/9/13.
 * 应用,主要用来做一下初始化的操作
 */

public class ProApplication extends Application {
    private static Context mContext;
    private String a = "git 测试";
    // 保存所有的Activity
    private List<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        setOkHttpconfig();
        initLodingLayout();

        AutoLayoutConifg.getInstance().useDeviceSize();
    }

    private void initLodingLayout() {
        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                /*.setErrorImage(R.mipmap.ic_launcher)
                .setEmptyImage(R.mipmap.ic_launcher)
                .setLoadingPageLayout(R.layout.loading)
                .setNoNetworkImage(R.mipmap.ic_launcher)*/
                .setAllTipTextColor(android.R.color.darker_gray)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(android.R.color.darker_gray)
                .setReloadButtonWidthAndHeight(50, 40);
    }

    /**
     * @return 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }

    /**
     * 添加activity到activityList集合中
     *
     * @param activity 每一個activity
     */
    public void addActivity(Activity activity) {
        if (activityList == null) {
            activityList = new ArrayList<Activity>();
        }
        activityList.add(activity);
    }

    public int getListSize() {
        if (activityList != null) {
            return activityList.size();
        }
        return 0;
    }

    public void removeActivity(Activity activity) {
        if (activityList != null) {

            if (activityList.contains(activity)) {
                activityList.remove(activity);
            }
        }

    }

    /**
     * 清空列表，取消引用
     */
    public void clearActivity() {
        activityList.clear();
    }

    /**
     * app退出
     */
    public void exit() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing() && activity != null) {
                activity.finish();
            }
        }
        clearActivity();
        System.exit(0);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Activity activity) {

        if (activity != null) {
            activityList.remove(activity);
            activity.finish();
            activity = null;

        }

    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (int i = 0; i < activityList.size(); i++) {

            if (activityList.get(i).getClass().equals(cls)) {
                finishActivity(activityList.get(i));
            }
        }

    }

    private void setOkHttpconfig() {
        OkHttpClient okHttpClient = new OkHttpConfig
                .Builder(this)
                //全局的请求头信息
                .setHeaders(new BuildHeadersListener() {
                    @Override
                    public Map<String, String> buildHeaders() {
                        String token = (String) SpUtils.get("UserInfo", "");
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("access-token", token);
                        return hashMap;
                    }
                })
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
                .setCache(true)
                .setHasNetCacheTime(10)//默认有网络时候缓存60秒
                .setNoNetCacheTime(3600)//默认有网络时候缓存3600秒
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setDebug(true)
                .build();
        RxHttpUtils
                .getInstance()
                .init(this)
                .config()
                //使用自定义factory的用法
                //.setCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.setConverterFactory(ScalarsConverterFactory.create(),GsonConverterFactory.create(GsonAdapter.buildGson()))
                //配置全局baseUrl
                .setBaseUrl("http://192.168.0.188:8080/")
                //开启全局配置
                .setOkClient(okHttpClient);

    }
}
