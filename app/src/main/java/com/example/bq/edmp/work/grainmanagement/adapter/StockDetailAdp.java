package com.example.bq.edmp.work.grainmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;

public class StockDetailAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder>{

    public StockDetailAdp() {
        super(R.layout.stock_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayInfoBean item) {
        helper.setText(R.id.tv_time,"20201120");
        helper.setText(R.id.tv_type,"调拨出库");
        helper.setText(R.id.tv_number,"-1.132");
        helper.setText(R.id.tv_last_number,"188.848");
    }
}
