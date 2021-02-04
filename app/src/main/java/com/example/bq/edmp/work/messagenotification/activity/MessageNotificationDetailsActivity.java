package com.example.bq.edmp.work.messagenotification.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.activity.GoodsSalesTrackingDetailsActivity;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.messagenotification.api.MessageNotificationApi;
import com.example.bq.edmp.work.messagenotification.bean.MessageDetailsBean;

import butterknife.BindView;

public class MessageNotificationDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, MessageNotificationDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.tv_time)
    TextView mTvTime;//通知时间
    @BindView(R.id.tv_title)
    TextView mTvTitle;//标题
    @BindView(R.id.tv_content)
    TextView mTvContent;//内容
    private String id = "";
    private LoadingDialog loading_dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_notification_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("消息详情");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            finish();
            return;
        }
        loading_dialog = new LoadingDialog(this);
        ProApplication.getinstance().addActivity(this);
        getMessageDetails();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

    //获取商品粮销售详情
    private void getMessageDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(MessageNotificationApi.class)
                .getMessageDetails(id, sign)
                .compose(Transformer.<MessageDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MessageDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MessageDetailsBean bean) {
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    //详情赋值
    private void setData(MessageDetailsBean.DataBean bean) {
        mTvTime.setText(bean.getAddedTime());
        mTvTitle.setText(bean.getTitle());
        mTvContent.setText(bean.getContent());
    }
}