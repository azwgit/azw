package com.example.bq.edmp.work.modelfield.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.detection.bean.DetectionPzBean;
import com.example.bq.edmp.work.order.bean.OrderZuoWuBean;

import java.util.List;

public class DemonstrationZWAdp extends BaseQuickAdapter<OrderZuoWuBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public DemonstrationZWAdp(@Nullable List<OrderZuoWuBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderZuoWuBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
