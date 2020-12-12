package com.example.bq.edmp.work.finishedproduct.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.finishedproduct.bean.LogisticsListBean;
import com.example.bq.edmp.work.finishedproduct.bean.WarehouseListBean;

import java.util.List;

public class LogisticsListAdp extends BaseQuickAdapter<LogisticsListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public LogisticsListAdp(@Nullable List<LogisticsListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
