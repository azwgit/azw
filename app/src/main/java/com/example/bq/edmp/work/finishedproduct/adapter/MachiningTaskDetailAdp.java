package com.example.bq.edmp.work.finishedproduct.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.finishedproduct.bean.MachiningTaskDetailsBean;
import com.example.bq.edmp.work.grainmanagement.bean.StockDetailBean;

public class MachiningTaskDetailAdp extends BaseQuickAdapter<MachiningTaskDetailsBean.DataBean.StockAddsBean, BaseViewHolder> {

    public MachiningTaskDetailAdp() {
        super(R.layout.machining_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MachiningTaskDetailsBean.DataBean.StockAddsBean item) {
        helper.setText(R.id.tv_time, item.getAddedTime());
        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(item.getAddQty())+" 公斤");
        helper.setText(R.id.tv_last_number, item.getCode() + "");
    }
}
