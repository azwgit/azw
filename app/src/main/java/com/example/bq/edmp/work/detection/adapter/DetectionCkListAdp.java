package com.example.bq.edmp.work.detection.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.detection.bean.DetectionCkBean;

import java.util.List;

public class DetectionCkListAdp extends BaseQuickAdapter<DetectionCkBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public DetectionCkListAdp(@Nullable List<DetectionCkBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetectionCkBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
