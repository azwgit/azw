package com.example.bq.edmp.work.inventorytransfer.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.MoneyUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class CommodityListAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {

    public CommodityListAdp(@Nullable List<PayInfoBean> data) {
        super(R.layout.item_commodity, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final PayInfoBean item) {

        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_title, item.getDesc());
        helper.setText(R.id.tv_number, item.getDesc());
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
        public abstract void onItemDelClick(int position, PayInfoBean bean);
    }
    //编辑
    public void setOnItemEditLisenter(OnItemEditLisenter onItemEditLisenter){
        this.onItemEditLisenter = onItemEditLisenter;
    }
    private OnItemEditLisenter onItemEditLisenter;
    public abstract static class OnItemEditLisenter{
        public abstract void onItemEditClick(int pos,PayInfoBean bean);
    }
}
