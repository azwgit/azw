package com.example.bq.edmp.work.returnsmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.marketingactivities.bean.DepartmengListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.CustomerListBean;

import java.util.List;

public class CustomerListAdp extends BaseQuickAdapter<CustomerListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public CustomerListAdp(@Nullable List<CustomerListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
