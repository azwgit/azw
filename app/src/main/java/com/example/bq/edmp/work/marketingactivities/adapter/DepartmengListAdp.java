package com.example.bq.edmp.work.marketingactivities.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.marketingactivities.bean.DepartmengListBean;

import java.util.List;

public class DepartmengListAdp extends BaseQuickAdapter<DepartmengListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public DepartmengListAdp(@Nullable List<DepartmengListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepartmengListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
