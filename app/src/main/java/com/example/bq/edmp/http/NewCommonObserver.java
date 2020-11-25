package com.example.bq.edmp.http;


import android.content.Intent;
import android.text.TextUtils;

import com.allen.library.base.BaseObserver;
import com.allen.library.utils.ToastUtils;
import com.example.bq.edmp.ProApplication;

import io.reactivex.disposables.Disposable;

/**
 * Created by Allen on 2017/5/3.
 *
 * @author Allen
 * 通用的Observer
 * 用户可以根据自己需求自定义自己的类继承BaseObserver<T>即可
 */

public abstract class NewCommonObserver<T> extends BaseObserver<T> {


    /**
     * 失败回调
     *
     * @param errorMsg
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param t
     */
    protected abstract void onSuccess(T t);


    @Override
    public void doOnSubscribe(Disposable d) {
    }

    @Override
    public void doOnError(String errorMsg) {
        if (!isHideToast() && !TextUtils.isEmpty(errorMsg)) {
            ToastUtils.showToast(errorMsg);
        }
        onError(errorMsg);
        //稍后处理整体异常
       // ProApplication.getinstance().closeAllActiivty();
    }

    @Override
    public void doOnNext(T t) {
        onSuccess(t);
    }

    @Override
    public void doOnCompleted() {
    }

}
