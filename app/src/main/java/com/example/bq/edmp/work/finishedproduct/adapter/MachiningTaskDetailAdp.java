package com.example.bq.edmp.work.finishedproduct.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainmanagement.bean.StockDetailBean;

public class MachiningTaskDetailAdp extends BaseQuickAdapter<StockDetailBean.DataBean.StockRecordsBean, BaseViewHolder> {

    public MachiningTaskDetailAdp() {
        super(R.layout.machining_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockDetailBean.DataBean.StockRecordsBean item) {
        helper.setText(R.id.tv_time, item.getOperationTime());
        helper.setText(R.id.tv_number, item.getQtys() + "");
        helper.setText(R.id.tv_last_number, item.getResidueQtys() + "");
    }
}
