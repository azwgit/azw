package com.example.bq.edmp.work.reseller.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;

import java.util.List;

public class AllListPackageAdp2 extends BaseQuickAdapter<AllpackageListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public AllListPackageAdp2(@Nullable List<AllpackageListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllpackageListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
