package com.example.bq.edmp.work.grainmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.work.grainmanagement.bean.AcquisitionBean;

import java.util.List;

public class DetailsDetectionListAdp extends BaseQuickAdapter<AcquisitionBean.DataBean.TestingItemsBean, BaseViewHolder> {
    public DetailsDetectionListAdp(@Nullable List<AcquisitionBean.DataBean.TestingItemsBean> data) {
        super(R.layout.item_details_detection_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AcquisitionBean.DataBean.TestingItemsBean item) {
//        ImageView mImgHead = helper.getView(R.id.img_head);
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_content,item.getValue());
    }
}
