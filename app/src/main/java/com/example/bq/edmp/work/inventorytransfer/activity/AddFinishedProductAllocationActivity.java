package com.example.bq.edmp.work.inventorytransfer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.activity.WarehousingDetailAct;

import butterknife.BindView;

public class AddFinishedProductAllocationActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type,String id) {
        Intent intent = new Intent(context, AddFinishedProductAllocationActivity.class);
        intent.putExtra(Constant.TYPE, type);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    private ILoadingView loading_dialog;
    private String type="";
    private String id="";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_finished_product_allocation;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("申请成品调拨");
        type=getIntent().getStringExtra(Constant.TYPE);
        if("".equals(type)){
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.tv_submit:
//                Intent intent=new Intent(getApplicationContext(),EditFinishedProductAllocationActivity.class);
//                startActivity(intent);
                break;
        }
    }
}