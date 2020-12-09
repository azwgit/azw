package com.example.bq.edmp.work.finishedproduct.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.finishedproduct.bean.SendGoodsDetailsBean;
import com.example.bq.edmp.work.grainmanagement.bean.TestingBeanList;

import java.util.List;

public class DeliverGoodsDetailsVarietiesListAdp extends BaseQuickAdapter<SendGoodsDetailsBean.DataBean.OrderItemsBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public DeliverGoodsDetailsVarietiesListAdp(@Nullable List<SendGoodsDetailsBean.DataBean.OrderItemsBean> data) {
        super(R.layout.item_deliver_goods_detailslist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SendGoodsDetailsBean.DataBean.OrderItemsBean item) {
        helper.setText(R.id.tv_title, "销售品种"+(helper.getPosition()+1));
        helper.setText(R.id.tv_name, item.getVarietyName()+"  "+item.getPackagingName());
        helper.setText(R.id.tv_number, MoneyUtils.formatMoney(item.getQty())+" 公斤");
    }
}
