package com.example.bq.edmp.utils.subscriber;

import android.content.Context;

import com.example.bq.edmp.base.BaseSubscriber;
import com.example.bq.edmp.exception.ApiException;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.NetworkUtil;


/**
 * Created by gaosheng on 2016/11/6.
 * 22:42
 * com.example.gaosheng.myapplication.subscriber
 */

public abstract class CommonSubscriber<T> extends BaseSubscriber<T> {

    private Context context;

    public CommonSubscriber(Context context) {
        this.context = context;
    }

    private static final String TAG = "CommonSubscriber";

    public CommonSubscriber() {

    }

    @Override
    public void onStart() {

        if (!NetworkUtil.isNetworkAvailable(context)) {
            LogUtils.e(TAG, "网络不可用,请检查网络");
        } else {
            LogUtils.e(TAG, "网络可用");
        }
    }



    @Override
    protected void onError(ApiException e) {
        if (!NetworkUtil.isNetworkAvailable(context)) {
            LogUtils.e(TAG, "网络不可用,请检查网络");
            e.message = "网络不可用,请检查网络";
        } else {
            LogUtils.e(TAG, "错误信息为 " + "code:" + e.code + "   message:" + e.message);
//            LogUtils.e(TAG, "网络可用");
        }
    }

    @Override
    public void onCompleted() {
        LogUtils.e(TAG, "成功了");
    }

}
