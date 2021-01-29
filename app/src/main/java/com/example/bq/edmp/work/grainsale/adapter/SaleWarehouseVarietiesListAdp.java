package com.example.bq.edmp.work.grainsale.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingDetailBean;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseDetailBean;

import java.util.List;

public class SaleWarehouseVarietiesListAdp extends BaseQuickAdapter<SaleWarehouseDetailBean.DataBean.StockAddItemsBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public SaleWarehouseVarietiesListAdp(@Nullable List<SaleWarehouseDetailBean.DataBean.StockAddItemsBean> data) {
        super(R.layout.item_warehouse_varittiest, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SaleWarehouseDetailBean.DataBean.StockAddItemsBean item) {
        helper.setText(R.id.tv_name, item.getVarietyName());
        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(Double.parseDouble(item.getAddQty()))+" 公斤");
    }
}
