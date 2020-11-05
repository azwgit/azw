package com.example.bq.edmp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.activity.login.LoginApi;
import com.example.bq.edmp.home.adapter.MessageListAdapter;
import com.example.bq.edmp.home.bean.MessageBean;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.LoginBean;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MessageNotificationListActivity extends BaseTitleActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    public static void start(Context context) {
        Intent intent = new Intent(context, MessageNotificationListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        List list=new ArrayList();

        for(int i=0;i<5;i++){
            MessageBean bean=new MessageBean();
            bean.setTitle("嘻嘻哈哈"+i);
            bean.setContent("乐乐呵呵"+i);
            list.add(bean);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MessageListAdapter messageListAdapter = new MessageListAdapter(list);
        recyclerview.setAdapter(messageListAdapter);

        String password = "1234";
        String username = "12346963651";
        String sign = MD5Util.encode("password=" + password + "&username=" + username);
        RxHttpUtils.createApi(LoginApi.class)
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

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }
}