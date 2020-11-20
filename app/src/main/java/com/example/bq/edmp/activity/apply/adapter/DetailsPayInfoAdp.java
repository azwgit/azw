package com.example.bq.edmp.activity.apply.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.activity.apply.bean.PayReimbursementDetailsInfo;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.MoneyUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class DetailsPayInfoAdp extends BaseQuickAdapter<PayReimbursementDetailsInfo.DataBean.ReimburserItemsBean, BaseViewHolder> {

    public DetailsPayInfoAdp(@Nullable List<PayReimbursementDetailsInfo.DataBean.ReimburserItemsBean> data,int icon) {
        super(R.layout.pay_info_item, data);
        this.showicon=icon;
    }
    private PreviewImageAdapter adapter;
    private int showicon;
    public int getShowicon() {
        return showicon;
    }

    public void setShowicon(int showicon) {
        this.showicon = showicon;
    }

    private int themeId;
    @Override
    protected void convert(BaseViewHolder helper, final PayReimbursementDetailsInfo.DataBean.ReimburserItemsBean item) {
        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_id, "项目（" + pos + "）");
        helper.setText(R.id.tv_desc, item.getName());
        helper.setText(R.id.tv_pro_money, MoneyUtils.formatMoney(item.getAmount()));
        helper.setText(R.id.tv_pic_count, "相关单据（"+item.getReimburserItemBills().size()+"）");
        ImageView mBtnDel = helper.getView(R.id.img_del);
        ImageView mBtnEdit = helper.getView(R.id.img_edit);
        View mView= helper.getView(R.id.view_line);
        if(pos==0){
            mView.setVisibility(View.GONE);
        }else{
            mView.setVisibility(View.VISIBLE);
        }
        if(showicon==1){
            mBtnDel.setVisibility(View.VISIBLE);
            mBtnEdit.setVisibility(View.VISIBLE);
        }else{
            mBtnDel.setVisibility(View.GONE);
            mBtnEdit.setVisibility(View.GONE);
        }
        RecyclerView mPicRecycler = helper.getView(R.id.pic_recyclerview);
        List<LocalMedia> list = new ArrayList<>();
        for(int i=0;i<item.getReimburserItemBills().size();i++){
            LocalMedia localnewMedia=new LocalMedia();
            localnewMedia.setPath(BaseApi.base_img_url +item.getReimburserItemBills().get(i).getUri());
            list.add(localnewMedia);
            item.setPicList(list);
        }
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);
        mPicRecycler.setLayoutManager(manager);
        adapter = new PreviewImageAdapter(mContext, null);
        adapter.setList(item.getPicList());
        mPicRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new PreviewImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                themeId = R.style.picture_QQ_style;
                if(onPicLisenter != null){
                    onPicLisenter.onPicClick(themeId,position,item.getPicList());
                }
            }
        });

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
        public abstract void onItemDelClick(int position, PayReimbursementDetailsInfo.DataBean.ReimburserItemsBean bean);
    }
    //编辑
    public void setOnItemEditLisenter(OnItemEditLisenter onItemEditLisenter){
        this.onItemEditLisenter = onItemEditLisenter;
    }
    private OnItemEditLisenter onItemEditLisenter;
    public abstract static class OnItemEditLisenter{
        public abstract void onItemEditClick(int pos, PayReimbursementDetailsInfo.DataBean.ReimburserItemsBean bean);
    }

    //点击查看大图
    public void setOnPicLisenter(OnPicLisenter onPicLisenter){
        this.onPicLisenter = onPicLisenter;
    }
    private OnPicLisenter onPicLisenter;
    public abstract static class OnPicLisenter{
        public abstract void onPicClick(int themeId,int position,List<LocalMedia> selectList);
    }
}
