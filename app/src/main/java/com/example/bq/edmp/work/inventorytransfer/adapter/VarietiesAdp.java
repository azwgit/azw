package com.example.bq.edmp.work.inventorytransfer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.VarittiesListBean;

import java.util.List;

public class VarietiesAdp extends BaseQuickAdapter<VarittiesListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public VarietiesAdp(@Nullable List<VarittiesListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VarittiesListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
