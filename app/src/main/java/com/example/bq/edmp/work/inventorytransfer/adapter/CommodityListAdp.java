package com.example.bq.edmp.work.inventorytransfer.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;

import java.util.List;

public class CommodityListAdp extends BaseQuickAdapter<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean, BaseViewHolder> {

    public CommodityListAdp(@Nullable List<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> data) {
        super(R.layout.item_commodity, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean item) {

        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_title, item.getVarietyName());
        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(item.getQty())+" 公斤");
        ImageView mBtnDel = helper.getView(R.id.img_del);
        ImageView mBtnEdit = helper.getView(R.id.img_edit);
        View mView = helper.getView(R.id.view_line);
        if(pos==0){
            mView.setVisibility(View.GONE);
        }else{
            mView.setVisibility(View.VISIBLE);
        }

        mBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemDelListener != null) {
                    onItemDelListener.onItemDelClick(pos, item);
                }
            }
        });

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemEditLisenter != null){
                    onItemEditLisenter.onItemEditClick(pos,item);
                }
            }
        });
    }

    //删除
    public void setOnItemDelListener(OnItemDelListener onItemDelListener) {
        this.onItemDelListener = onItemDelListener;
    }

    private OnItemDelListener onItemDelListener;

    public abstract static class OnItemDelListener {
        public abstract void onItemDelClick(int position, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean);
    }
    //编辑
    public void setOnItemEditLisenter(OnItemEditLisenter onItemEditLisenter){
        this.onItemEditLisenter = onItemEditLisenter;
    }
    private OnItemEditLisenter onItemEditLisenter;
    public abstract static class OnItemEditLisenter{
        public abstract void onItemEditClick(int pos, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean);
    }
}
