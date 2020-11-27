package com.example.bq.edmp.work.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;

import java.util.List;

public class DetectionListAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {
    public DetectionListAdp(@Nullable List<PayInfoBean> data) {
        super(R.layout.item_detection_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayInfoBean item) {
//        ImageView mImgHead = helper.getView(R.id.img_head);
//        helper.setText(R.id.tv_nick,"昵称");
//        helper.setText(R.id.tv_time,"2020-10-25 10:12");
    }
}
