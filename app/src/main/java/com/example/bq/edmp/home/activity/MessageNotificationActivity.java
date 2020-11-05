package com.example.bq.edmp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.LoginBean;
import com.example.bq.edmp.home.MessageApi;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

public class MessageNotificationActivity extends BaseTitleActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, MessageNotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        Map<String, Object> map = new HashMap<>();
        String password = "1234";
        String username = "12346963651";
        String sign = MD5Util.encode("password=" + password + "&username=" + username);

        map.put("username", username);
        map.put("password", password);
        map.put("sign", sign);
        RxHttpUtils.createApi(MessageApi.class)
                .login(password, username, sign)
                .compose(Transformer.<LoginBean>switchSchedulers())
                .subscribe(new CommonObserver<LoginBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(LoginBean loginBean) {
                        SpUtils.put("UserInfo", loginBean.getData());
                        ToastUtil.setToast("成功");
                    }
                });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        txtTabTitle.setText("消息通知");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_notification;
    }

    @Override
    protected void otherViewClick(View view) {

    }
}