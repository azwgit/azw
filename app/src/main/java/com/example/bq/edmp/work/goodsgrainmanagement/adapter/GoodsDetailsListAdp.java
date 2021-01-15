package com.example.bq.edmp.work.goodsgrainmanagement.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;

import java.util.List;

public class GoodsDetailsListAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {

    public GoodsDetailsListAdp(@Nullable List<PayInfoBean> data) {
        super(R.layout.item_goods_details_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PayInfoBean item) {

        final int pos = helper.getLayoutPosition();
//        helper.setText(R.id.tv_title, item.getVarietyName());
//        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(item.getQty()) + " 公斤");
//        View mView = helper.getView(R.id.view_line);
//        if (pos == 0) {
//            mView.setVisibility(View.GONE);
//        } else {
//            mView.setVisibility(View.VISIBLE);
//        }

    }
}
