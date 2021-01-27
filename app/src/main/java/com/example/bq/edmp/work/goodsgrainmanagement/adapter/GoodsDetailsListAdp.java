package com.example.bq.edmp.work.goodsgrainmanagement.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;

import java.util.List;

public class GoodsDetailsListAdp extends BaseQuickAdapter<EditGoodSalesBean.DataBean.CgOrderItemsBean, BaseViewHolder> {

    public GoodsDetailsListAdp(@Nullable List<EditGoodSalesBean.DataBean.CgOrderItemsBean> data) {
        super(R.layout.item_goods_details_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final EditGoodSalesBean.DataBean.CgOrderItemsBean item) {

        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_info, "销售商品("+helper.getPosition()+")");
        helper.setText(R.id.tv_packing, item.getItemName());
        helper.setText(R.id.tv_return_order_number, "数量 " + MoneyUtils.formatMoney(item.getQty()) + "公斤");
        helper.setText(R.id.tv_return_price, "￥" + MoneyUtils.formatMoney(item.getPrice()) + "/公斤");
        helper.setText(R.id.tv_return_money, "￥" + MoneyUtils.formatMoney(item.getItemAmount()));

        View mView = helper.getView(R.id.view_line);
    }
}
