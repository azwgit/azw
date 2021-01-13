package com.example.bq.edmp.work.returnsmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.marketingactivities.bean.DepartmengListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.PackagingListBean;

import java.util.List;

public class PackingListAdp extends BaseQuickAdapter<PackagingListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public PackingListAdp(@Nullable List<PackagingListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PackagingListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
