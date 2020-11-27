package com.example.bq.edmp.work.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.work.adapter.DetectionListAdp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewAcquisitionsActivity extends BaseTitleActivity {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    private DetectionListAdp detectionListAdp;

    @Override
    protected void initData() {
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detectionListAdp = new DetectionListAdp(list);
        mRecyclerView.setAdapter(detectionListAdp);
    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(NewAcquisitionsActivity.this);
        txtTabTitle.setText("新增收购");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_acquisitions;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.tv_submit:
                Intent intent=new Intent(getApplicationContext(), NewAcquisitionsSuccessActivity.class);
                startActivity(intent);
                break;
        }
    }
}