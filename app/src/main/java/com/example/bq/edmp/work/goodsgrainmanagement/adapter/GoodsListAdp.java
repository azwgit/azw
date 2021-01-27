package com.example.bq.edmp.work.goodsgrainmanagement.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;

import java.util.List;

public class GoodsListAdp extends BaseQuickAdapter<EditGoodSalesBean.DataBean.CgOrderItemsBean, BaseViewHolder> {

    public GoodsListAdp(@Nullable List<EditGoodSalesBean.DataBean.CgOrderItemsBean> data) {
        super(R.layout.item_goods_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final EditGoodSalesBean.DataBean.CgOrderItemsBean item) {

        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.pinzhong_tv, item.getItemName());
        helper.setText(R.id.price_tv, "￥" + MoneyUtils.formatMoney(item.getQty() * item.getPrice()));
        helper.setText(R.id.xiaoshoulaing_tv, "¥" +MoneyUtils.formatMoney( item.getPrice()) + "/公斤 * " + MoneyUtils.formatMoney(item.getQty()) + "公斤");
        ImageView mBtnDel = helper.getView(R.id.img_del);
        ImageView mBtnEdit = helper.getView(R.id.img_edit);
//        View mView = helper.getView(R.id.view_line);
//        if (pos == 0) {
//            mView.setVisibility(View.GONE);
//        } else {
//            mView.setVisibility(View.VISIBLE);
//        }

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
                if (onItemEditLisenter != null) {
                    onItemEditLisenter.onItemEditClick(pos, item);
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
        public abstract void onItemDelClick(int position, EditGoodSalesBean.DataBean.CgOrderItemsBean bean);
    }

    //编辑
    public void setOnItemEditLisenter(OnItemEditLisenter onItemEditLisenter) {
        this.onItemEditLisenter = onItemEditLisenter;
    }

    private OnItemEditLisenter onItemEditLisenter;

    public abstract static class OnItemEditLisenter {
        public abstract void onItemEditClick(int pos, EditGoodSalesBean.DataBean.CgOrderItemsBean bean);
    }
}
