package com.example.bq.edmp.work.finishedproduct.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.finishedproduct.bean.WarehouseListBean;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;

import java.util.List;

public class WarehouseListAdp extends BaseQuickAdapter<WarehouseListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public WarehouseListAdp(@Nullable List<WarehouseListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarehouseListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
