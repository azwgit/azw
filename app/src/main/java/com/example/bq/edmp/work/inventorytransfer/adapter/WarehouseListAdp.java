package com.example.bq.edmp.work.inventorytransfer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;

import java.util.List;

public class WarehouseListAdp extends BaseQuickAdapter<WareHouseListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public WarehouseListAdp(@Nullable List<WareHouseListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WareHouseListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
