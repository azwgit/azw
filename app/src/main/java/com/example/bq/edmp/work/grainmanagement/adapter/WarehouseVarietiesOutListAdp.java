package com.example.bq.edmp.work.grainmanagement.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingDetailBean;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingOutDetailBean;

import java.util.List;

public class WarehouseVarietiesOutListAdp extends BaseQuickAdapter<WarehouseingOutDetailBean.DataBean.StockSubItemsBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public WarehouseVarietiesOutListAdp(@Nullable List<WarehouseingOutDetailBean.DataBean.StockSubItemsBean> data) {
        super(R.layout.item_warehouse_varittiest_out, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarehouseingOutDetailBean.DataBean.StockSubItemsBean item) {
        helper.setText(R.id.tv_name, item.getVarietyName());
        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(item.getSubQty())+" 公斤");
    }
}
