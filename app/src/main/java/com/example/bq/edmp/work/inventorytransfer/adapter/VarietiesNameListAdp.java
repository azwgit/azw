package com.example.bq.edmp.work.inventorytransfer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;

import java.util.List;

public class VarietiesNameListAdp extends BaseQuickAdapter<ContractorListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public VarietiesNameListAdp(@Nullable List<ContractorListBean.DataBean> data) {
        super(R.layout.item_varieties_name_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContractorListBean.DataBean item) {
//        helper.setText(R.id.tv_title,item.getName());
    }

}
