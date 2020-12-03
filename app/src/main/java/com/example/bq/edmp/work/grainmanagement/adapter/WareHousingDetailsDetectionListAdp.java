package com.example.bq.edmp.work.grainmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingDetailBean;

import java.util.List;

public class WareHousingDetailsDetectionListAdp extends BaseQuickAdapter<WarehouseingDetailBean.DataBean.TestingItemsBean, BaseViewHolder> {
    public WareHousingDetailsDetectionListAdp(@Nullable List<WarehouseingDetailBean.DataBean.TestingItemsBean> data) {
        super(R.layout.item_details_detection_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarehouseingDetailBean.DataBean.TestingItemsBean item) {
//        ImageView mImgHead = helper.getView(R.id.img_head);
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_content,item.getValue());
    }
}
