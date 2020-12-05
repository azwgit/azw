package com.example.bq.edmp.work.finishedproduct.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainmanagement.bean.TestingBeanList;

import java.util.List;

public class DeliverGoodsDetailsVarietiesListAdp extends BaseQuickAdapter<TestingBeanList.DataBean.TestPlanItemsBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public DeliverGoodsDetailsVarietiesListAdp(@Nullable List<TestingBeanList.DataBean.TestPlanItemsBean> data) {
        super(R.layout.item_deliver_goods_detailslist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestingBeanList.DataBean.TestPlanItemsBean item) {
        EditText etResult = helper.getView(R.id.et_result);
//        helper.setText(R.id.tv_name, item.getName());

    }
}
