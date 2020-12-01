package com.example.bq.edmp.work.grainmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;

import java.util.List;

public class DetectionSelectListAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public DetectionSelectListAdp(@Nullable List<PayInfoBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayInfoBean item) {
    }

}
