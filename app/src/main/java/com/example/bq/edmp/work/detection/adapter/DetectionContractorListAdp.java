package com.example.bq.edmp.work.detection.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.detection.bean.DetectonLxBean;

import java.util.List;

public class DetectionContractorListAdp extends BaseQuickAdapter<DetectonLxBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public DetectionContractorListAdp(@Nullable List<DetectonLxBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetectonLxBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getTestName());
    }

}
