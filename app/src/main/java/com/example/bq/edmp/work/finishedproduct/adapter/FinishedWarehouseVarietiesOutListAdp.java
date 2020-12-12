package com.example.bq.edmp.work.finishedproduct.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedWareHousingOutDetailBean;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingOutDetailBean;

import java.util.List;

public class FinishedWarehouseVarietiesOutListAdp extends BaseQuickAdapter<FinishedWareHousingOutDetailBean.DataBean.StockSubItemsBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public FinishedWarehouseVarietiesOutListAdp(@Nullable List<FinishedWareHousingOutDetailBean.DataBean.StockSubItemsBean> data) {
        super(R.layout.item_finished_warehouse_varittiest_out, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinishedWareHousingOutDetailBean.DataBean.StockSubItemsBean item) {
        helper.setText(R.id.tv_name, item.getVarietyName());
        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(item.getSubQty())+" 公斤");
    }
}
