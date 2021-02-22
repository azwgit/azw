package com.example.bq.edmp.work.materialmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.materialmanagement.bean.QueryAllitemListBean;

import java.util.List;

public class AllPurchaseGoodsAdp extends BaseQuickAdapter<QueryAllitemListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public AllPurchaseGoodsAdp(@Nullable List<QueryAllitemListBean.DataBean> data) {
        super(R.layout.item_detection_select_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryAllitemListBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
    }

}
