package com.example.bq.edmp.work.materialmanagement.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.materialmanagement.bean.EditMaterialBean;

import java.util.List;

public class GoodsNewListAdp extends BaseQuickAdapter<EditMaterialBean.DataBean.MaterialPurchaseItemsBean, BaseViewHolder> {

    public GoodsNewListAdp(@Nullable List<EditMaterialBean.DataBean.MaterialPurchaseItemsBean> data) {
        super(R.layout.item_material_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final EditMaterialBean.DataBean.MaterialPurchaseItemsBean item) {

        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_name, item.getItemName());
        helper.setText(R.id.tv_title, "采购数量 " + item.getQty());
        helper.setText(R.id.tv_number, "采购单价 ￥" + MoneyUtils.formatMoney(item.getPrice()));
        ImageView mBtnDel = helper.getView(R.id.img_del);
        ImageView mBtnEdit = helper.getView(R.id.img_edit);
        View mView = helper.getView(R.id.view_line);
        if (pos == 0) {
            mView.setVisibility(View.GONE);
        } else {
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
        public abstract void onItemDelClick(int position, EditMaterialBean.DataBean.MaterialPurchaseItemsBean bean);
    }

    //编辑
    public void setOnItemEditLisenter(OnItemEditLisenter onItemEditLisenter) {
        this.onItemEditLisenter = onItemEditLisenter;
    }

    private OnItemEditLisenter onItemEditLisenter;

    public abstract static class OnItemEditLisenter {
        public abstract void onItemEditClick(int pos, EditMaterialBean.DataBean.MaterialPurchaseItemsBean bean);
    }
}
