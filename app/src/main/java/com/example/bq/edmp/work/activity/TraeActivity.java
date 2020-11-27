package com.example.bq.edmp.work.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.Dialogger;
import com.example.bq.edmp.utils.ToastUtil;

import butterknife.BindView;

public class TraeActivity extends BaseTitleActivity {
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;//提交按钮
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, TraeActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    private Dialogger dialog = null;
    private String id="";
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        id=getIntent().getStringExtra(Constant.ID);
        ToastUtil.setToast(id);
        txtTabTitle.setText("称重皮重");
        ProApplication.getinstance().addActivity(TraeActivity.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trae;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.tv_submit:
                showUsualDialog();
                break;
        }
    }

    public void showUsualDialog() {
        dialog = Dialogger.Builder(this)
                .setTitle("友情提示")
                .setMessage("确认当前收购品种毛重为1880公斤")
                .setOnConfirmClickListener("确定", new Dialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(TraeActivity.this,TraeSuccessActivity.class);
                        startActivity(intent);
                    }
                })
                .setOnCancelClickListener("取消", new Dialogger.onCancelClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                })
                .build()
                .shown();
    }
}