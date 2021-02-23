package com.example.bq.edmp.work.materialmanagement.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.materialmanagement.bean.ProcurementDetailsBean;

import java.util.List;

public class MaterielDetailsListAdp extends BaseQuickAdapter<ProcurementDetailsBean.DataBean.MaterialPurchaseItemsBean, BaseViewHolder> {

    public MaterielDetailsListAdp(@Nullable List<ProcurementDetailsBean.DataBean.MaterialPurchaseItemsBean> data) {
        super(R.layout.item_materiel_details_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ProcurementDetailsBean.DataBean.MaterialPurchaseItemsBean item) {

        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_name, item.getItemName());
        helper.setText(R.id.tv_number, "数量 " + item.getQty());
        helper.setText(R.id.tv_price, "￥" + MoneyUtils.formatMoney(item.getPrice()));
        View mView = helper.getView(R.id.view_line);
    }
}
