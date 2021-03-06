package com.example.bq.edmp.work.grainmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.grainmanagement.bean.StockDetailBean;

public class StockDetailAdp extends BaseQuickAdapter<StockDetailBean.DataBean.StockRecordsBean, BaseViewHolder> {

    public StockDetailAdp() {
        super(R.layout.stock_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockDetailBean.DataBean.StockRecordsBean item) {
        helper.setText(R.id.tv_time, item.getOperationTime());
        String type = "";
        switch (item.getOperationType()) {
            case 1:
                type = "采购入库";
                break;
            case 2:
                type = "加工入库";
                break;
            case 3:
                type = "调拨入库";
                break;
        }
        helper.setText(R.id.tv_type, type);
        helper.setText(R.id.tv_number, MoneyUtils.formatWeight(item.getQtys()));
        helper.setText(R.id.tv_last_number, MoneyUtils.formatWeight(item.getResidueQtys()));
    }
}
