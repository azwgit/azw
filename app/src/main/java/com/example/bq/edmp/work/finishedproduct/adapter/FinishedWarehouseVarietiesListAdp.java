package com.example.bq.edmp.work.finishedproduct.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedWareHousingOutDetailBean;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedWarehousingBean;

import java.util.List;

public class FinishedWarehouseVarietiesListAdp extends BaseQuickAdapter<FinishedWarehousingBean.DataBean.StockAddItemsBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public FinishedWarehouseVarietiesListAdp(@Nullable List<FinishedWarehousingBean.DataBean.StockAddItemsBean> data) {
        super(R.layout.item_finished_warehouse_varittiest, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinishedWarehousingBean.DataBean.StockAddItemsBean item) {
        helper.setText(R.id.tv_name, item.getVarietyName());
        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(item.getAddQty())+" 公斤");
    }
}
