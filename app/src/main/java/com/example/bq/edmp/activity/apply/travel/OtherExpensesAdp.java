package com.example.bq.edmp.activity.apply.travel;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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

public class OtherExpensesAdp extends BaseQuickAdapter<PayInfoBean, BaseViewHolder> {

    public OtherExpensesAdp(@Nullable List<PayInfoBean> data,int icon) {
        super(R.layout.other_expenses_item, data);
        this.showicon= icon;
    }

    private PreviewImageAdapter adapter;
    private int themeId;
    private int showicon;
    public int getShowicon() {
        return showicon;
    }

    public void setShowicon(int showicon) {
        this.showicon = showicon;
    }
    @Override
    protected void convert(BaseViewHolder helper, final PayInfoBean item) {
        final int pos = helper.getLayoutPosition();
        helper.setText(R.id.tv_title, item.getDesc());
        String money = item.getMoney() == null ? "0.00" : MoneyUtils.formatMoney(Double.parseDouble(item.getMoney())) ;
        if(showicon==1){
            helper.setText(R.id.tv_money, "¥" + money + "    >");
        }else{
            helper.setText(R.id.tv_money, "¥" + money);
        }
        int size = item.getImg_list() == null ? 0 : item.getImg_list().size();
        LinearLayout mLyInfo = helper.getView(R.id.ly_info);
        View line = helper.getView(R.id.line);
        if (size == 0){
            mLyInfo.setVisibility(View.GONE);
        }else {
            mLyInfo.setVisibility(View.VISIBLE);
        }
        if (pos == mData.size()-1){
            line.setVisibility(View.GONE);
        }else{
            line.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_list_size, "相关单据（" + size + "）");
        RecyclerView mPicRecycler = helper.getView(R.id.pic_recyclerview);
        LinearLayout mLyLayer = helper.getView(R.id.ly_layer);

        List<LocalMedia> list = new ArrayList<>();
        for (int i = 0; i < item.getImg_list().size(); i++) {
            LocalMedia localnewMedia = new LocalMedia();
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
                if (onPicLisenter != null) {
                    onPicLisenter.onPicClick(themeId, position, item.getPicList());
                }
            }
        });
        mLyLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickLisenter != null) {
                    onClickLisenter.onClick(pos, item);
                }
            }
        });
    }

    //点击添加费用
    public void setOnClickLisenter(OnClickLisenter onClickLisenter) {
        this.onClickLisenter = onClickLisenter;
    }

    private OnClickLisenter onClickLisenter;

    public abstract static class OnClickLisenter {
        public abstract void onClick(int position, PayInfoBean bean);
    }

    //点击查看大图
    public void setOnPicLisenter(PayInfoAdp.OnPicLisenter onPicLisenter) {
        this.onPicLisenter = onPicLisenter;
    }

    private PayInfoAdp.OnPicLisenter onPicLisenter;

    public abstract static class OnPicLisenter {
        public abstract void onPicClick(int themeId, int position, List<LocalMedia> selectList);
    }
}
