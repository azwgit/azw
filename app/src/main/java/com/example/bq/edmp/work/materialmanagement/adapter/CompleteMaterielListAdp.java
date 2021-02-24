package com.example.bq.edmp.work.materialmanagement.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.materialmanagement.bean.ProcurementDetailsBean;

import java.util.List;

public class CompleteMaterielListAdp extends BaseQuickAdapter<ProcurementDetailsBean.DataBean.MaterialPurchaseItemsBean, BaseViewHolder> {

    public CompleteMaterielListAdp(@Nullable List<ProcurementDetailsBean.DataBean.MaterialPurchaseItemsBean> data) {
        super(R.layout.item_complete_material, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ProcurementDetailsBean.DataBean.MaterialPurchaseItemsBean item) {

        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_name, item.getItemName());
        helper.setText(R.id.tv_number, "数量 " + item.getQty());
        View mView = helper.getView(R.id.view_line);
    }
}
