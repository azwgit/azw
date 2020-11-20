package com.example.bq.edmp.activity.apply.travel;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoAdp;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.MoneyUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class TravelDayInfoAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {

    public TravelDayInfoAdp( @Nullable List<PayInfoBean> data) {
        super(R.layout.travel_day_info_item, data);
    }
    private PreviewImageAdapter adapter;
    private int themeId;
    @Override
    protected void convert(BaseViewHolder helper, final PayInfoBean item) {
        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_id, "项目（" + pos + "）");
        helper.setText(R.id.tv_start_date,item.getSetOutTime());
        helper.setText(R.id.tv_start_address,item.getSetOutRegion());
        helper.setText(R.id.tv_end_date,item.getArriveTime());
        helper.setText(R.id.tv_end_address,item.getArriveRegion());
        helper.setText(R.id.tv_car,item.getTransport());
        helper.setText(R.id.tv_car_money,MoneyUtils.formatMoney(Double.parseDouble(item.getTransportFee())));
        helper.setText(R.id.tv_subsidy,item.getDays()+"天 * ¥"+ MoneyUtils.formatMoney(Double.parseDouble(item.getSubsidy())));
        double money=Double.parseDouble(item.getDays())*Double.parseDouble(item.getSubsidy());
        helper.setText(R.id.tv_subsidy_money,MoneyUtils.formatMoney(money));
        helper.setText(R.id.tv_pic_count,"相关单据（"+item.getImg_list().size()+"）");
        ImageView mBtnDel = helper.getView(R.id.img_del);
        ImageView mBtnEdit = helper.getView(R.id.img_edit);
        View mView= helper.getView(R.id.view_line);
        if(pos==0){
            mView.setVisibility(View.GONE);
        }else{
            mView.setVisibility(View.VISIBLE);
        }
        RecyclerView mPicRecycler = helper.getView(R.id.pic_recyclerview);
        List<LocalMedia> list = new ArrayList<>();
        for(int i=0;i<item.getImg_list().size();i++){
            LocalMedia localnewMedia=new LocalMedia();
            localnewMedia.setChecked(item.getImg_list().get(i).isChecked());
            localnewMedia.setCompressed(item.getImg_list().get(i).isCompressed());
            localnewMedia.setCompressPath(item.getImg_list().get(i).getCompressPath());
            localnewMedia.setCut(item.getImg_list().get(i).isCut());
            localnewMedia.setCutPath(item.getImg_list().get(i).getCutPath());
            localnewMedia.setDuration(item.getImg_list().get(i).getDuration());
            localnewMedia.setHeight(item.getImg_list().get(i).getHeight());
            localnewMedia.setMimeType(item.getImg_list().get(i).getMimeType());
            localnewMedia.setNum(item.getImg_list().get(i).getNum());
            localnewMedia.setPath(item.getImg_list().get(i).getPath());
            localnewMedia.setPictureType(item.getImg_list().get(i).getPictureType());
            localnewMedia.setPosition(item.getImg_list().get(i).getPosition());
            localnewMedia.setWidth(item.getImg_list().get(i).getWidth());
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

    //点击查看大图
    public void setOnPicLisenter(OnPicLisenter onPicLisenter){
        this.onPicLisenter = onPicLisenter;
    }
    private OnPicLisenter onPicLisenter;
    public abstract static class OnPicLisenter{
        public abstract void onPicClick(int themeId,int position,List<LocalMedia> selectList);
    }

}
