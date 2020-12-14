package com.example.bq.edmp.work.inventorytransfer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoAdp;
import com.example.bq.edmp.activity.apply.activity.UpdatePayInfoAct;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.adapter.CommodityListAdp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditFinishedProductAllocationActivity extends BaseTitleActivity {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_add_info)
    LinearLayout mBtnAddInfo;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;


    private CommodityListAdp mAdapter;
    private List<PayInfoBean> payInfoBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_finished_product_allocation;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("申请成品调拨");
        payInfoBeanList = new ArrayList<PayInfoBean>();
        for (int i = 0; i < 5; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setDesc("嘻嘻哈哈" + i);
            payInfoBeanList.add(bean);
        }
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new CommodityListAdp(payInfoBeanList);
        mRecyclerView.setAdapter(mAdapter);
        //删除
        mAdapter.setOnItemDelListener(new CommodityListAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, PayInfoBean bean) {
                ToastUtil.setToast("点击了删除");
                mAdapter.remove(position);
                mAdapter.notifyDataSetChanged();
//                if (payInfoBeanList.size() > 0) {
//                    payInfoBeanList.remove(position);
//                }
//                if (payInfoBeanList.size() <= 0) {
//                    mRecyclerView.setVisibility(View.GONE);
//                }
            }
        });

        //编辑
        mAdapter.setOnItemEditLisenter(new CommodityListAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, PayInfoBean bean) {
                ToastUtil.setToast("点击了编辑");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnAddInfo.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case  R.id.btn_add_info:
                PayInfoBean payInfoBean=new PayInfoBean();
                payInfoBean.setDesc("添加商品");
                payInfoBeanList.add(payInfoBean);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_submit:
                ToastUtil.setToast("调拨成功");
                Intent intent=new Intent(getApplicationContext(),AddTransferGoodsActivity.class);
                startActivity(intent);
                break;
        }
    }
}