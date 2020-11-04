package com.example.bq.edmp.activity.mine_activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;

import butterknife.BindView;

/*
* 个人信息
* */
public class Message_Activity extends BaseActivity {

    private static final String TAG = "Message_Activity==";
    @BindView(R.id.head_rl)
    RelativeLayout head_rl;
    @BindView(R.id.head_img)
    ImageView head_img;
    @BindView(R.id.signature_img)
    ImageView signature_img;
    @BindView(R.id.phone_rl)
    RelativeLayout phone_rl;
    @BindView(R.id.special_rl)
    RelativeLayout special_rl;
    @BindView(R.id.mail_rl)
    RelativeLayout mail_rl;

    public static void start(Context context) {
        Intent intent = new Intent(context, Message_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        head_rl.setOnClickListener(this);
        phone_rl.setOnClickListener(this);
        special_rl.setOnClickListener(this);
        signature_img.setOnClickListener(this);
        mail_rl.setOnClickListener(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.head_rl://头像
                break;
            case R.id.phone_rl://手机号
                break;
            case R.id.special_rl://座机
                break;
            case R.id.mail_rl://邮箱
                break;
            case R.id.signature_img://签名
                break;
        }
    }

}
