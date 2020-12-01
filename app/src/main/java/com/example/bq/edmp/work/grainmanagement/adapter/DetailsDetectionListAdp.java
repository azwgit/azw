package com.example.bq.edmp.work.grainmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;

import java.util.List;

public class DetailsDetectionListAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {
    public DetailsDetectionListAdp(@Nullable List<PayInfoBean> data) {
        super(R.layout.item_details_detection_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayInfoBean item) {
//        ImageView mImgHead = helper.getView(R.id.img_head);
        helper.setText(R.id.tv_name,"水份");
        helper.setText(R.id.tv_content,"<15");
    }
}
