package com.example.bq.edmp.work.inventorytransfer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;

import java.util.List;

public class VarietiesNameListAdp extends BaseQuickAdapter<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean, BaseViewHolder> {
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
    public VarietiesNameListAdp(@Nullable List<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> data) {
        super(R.layout.item_varieties_name_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean item) {
        helper.setText(R.id.tv_title,item.getVarietyName()+"  "+ MoneyUtils.formatMoney(item.getQty())+"公斤");
    }

}
