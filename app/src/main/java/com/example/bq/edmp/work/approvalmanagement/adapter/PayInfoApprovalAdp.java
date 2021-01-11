package com.example.bq.edmp.work.approvalmanagement.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;

import java.util.List;

public class PayInfoApprovalAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {
    public PayInfoApprovalAdp(@Nullable List<PayInfoBean> data) {
        super(R.layout.approval_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayInfoBean item) {
        ImageView mImgHead = helper.getView(R.id.img_head);
        helper.setText(R.id.tv_nick,"昵称");
        helper.setText(R.id.tv_time,"2020-10-25 10:12");
        TextView mTvFirst = helper.getView(R.id.tv_first);
        int pos = helper.getLayoutPosition();
        if (pos == 0){
            mTvFirst.setVisibility(View.VISIBLE);
        }else {
            mTvFirst.setText("1111");
        }
        View mLine = helper.getView(R.id.line);
        if (pos == mData.size() - 1){
            mLine.setVisibility(View.GONE);
        }else{
            mLine.setVisibility(View.VISIBLE);
        }
    }
}
