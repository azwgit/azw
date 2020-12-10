package com.example.bq.edmp.work.finishedproduct.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedStockDetailBean;
import com.example.bq.edmp.work.grainmanagement.bean.StockDetailBean;

public class FinishedStockDetailAdp extends BaseQuickAdapter<FinishedStockDetailBean.DataBean.StockRecordsBean, BaseViewHolder> {

    public FinishedStockDetailAdp() {
        super(R.layout.finished_stock_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinishedStockDetailBean.DataBean.StockRecordsBean item) {
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
        helper.setText(R.id.tv_number, item.getQtys() + "");
        helper.setText(R.id.tv_last_number, item.getResidueQtys() + "");
    }
}
