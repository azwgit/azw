package com.example.bq.edmp.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.utils.LogUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;


/**
 * Created by GaoSheng on 2016/9/13.
 */

public abstract class BaseActivity extends AutoLayoutActivity implements  View.OnClickListener {
    protected View view;


    protected Bundle mBundle;

    protected ProApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());

        ButterKnife.bind(this);
        mBundle = savedInstanceState;
        initView();
        initListener();
        initData();

        if (application == null) {
            application = (ProApplication) getApplicationContext();
        }
        application.addActivity(this);
    }


    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected abstract void otherViewClick(View view);

    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }

    /**
     * 点击的事件的统一的处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                otherViewClick(view);
                break;
        }
    }

    /**
     * @param str 显示一个内容为str的toast
     */
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param contentId 显示一个内容为contentId指定的toast
     */
    public void toast(int contentId) {
        Toast.makeText(this, contentId, Toast.LENGTH_SHORT).show();
    }


    /**
     * @param str 日志的处理
     */
    public void LogE(String str) {
        LogUtils.e(getClass(), str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void fund() {
        finish();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

}