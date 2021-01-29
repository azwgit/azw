package com.example.bq.edmp.work.grainsale.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseDetailBean;

import java.util.List;

public class SaleWarehouseVarietiesDCPZListAdp extends BaseQuickAdapter<SaleWarehouseDetailBean.DataBean.StockAllotsBean.StockAllotItemsBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public SaleWarehouseVarietiesDCPZListAdp(@Nullable List<SaleWarehouseDetailBean.DataBean.StockAllotsBean.StockAllotItemsBean> data) {
        super(R.layout.sale_item_warehouse_varittiest, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SaleWarehouseDetailBean.DataBean.StockAllotsBean.StockAllotItemsBean item) {
        helper.setText(R.id.tv_transfer_pz, item.getOutItemName());
    }
}
