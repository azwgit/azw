package com.example.bq.edmp.base;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.LogUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;


/**
 * Created by GaoSheng on 2016/9/13.
 */

public abstract class BaseTitleActivity extends AutoLayoutActivity implements View.OnClickListener {
    protected View view;


    protected Bundle mBundle;

    protected ProApplication application;
    public TextView txtTabTitle;
    public ImageView ivOperate;
    public ImageView ivBack;
    //查看 一下
    public TextView tvNotice;
    public RelativeLayout rlTitle;

    //测8试
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.CustomTitle);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(getView());
        setCustomTitle();
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

    private void test() {

    }

    //代 码
    private void bq() {
        String str = "1454544加东西了";
    }

    /**
     * 标题
     */
    public void setCustomTitle() {
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
        startView();
    }

    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }

    private void startView() {
        rlTitle = findViewById(R.id.rl_title);
        ivBack = findViewById(R.id.txt_back);
        tvNotice = findViewById(R.id.tv_notice);
        txtTabTitle = findViewById(R.id.txt_tab_title);
        ivOperate = findViewById(R.id.img_operate);
        ivBack.setOnClickListener(listener);
    }

    //全局回退鍵
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt_back:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

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