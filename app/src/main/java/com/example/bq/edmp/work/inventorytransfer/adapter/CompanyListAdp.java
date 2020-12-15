package com.example.bq.edmp.work.inventorytransfer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;

import java.util.List;

public class CompanyListAdp extends BaseQuickAdapter<SubsidiaryCompanyBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public CompanyListAdp(@Nullable List<SubsidiaryCompanyBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsidiaryCompanyBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
