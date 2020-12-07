package com.example.bq.edmp.work.finishedproduct.adapter;

import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;

import java.util.List;

public class CardTypeAdp extends BaseQuickAdapter<ContractorListBean.DataBean, BaseViewHolder> {
    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    public int ischeck = -1;



    public CardTypeAdp(@Nullable List<ContractorListBean.DataBean> data) {
        super(R.layout.item_card_type, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ContractorListBean.DataBean item) {
        int pos = helper.getPosition();
        TextView mTvcard = helper.getView(R.id.tv_card);
        if (ischeck == pos) {
            mTvcard.setBackground(mContext.getResources().getDrawable(R.drawable.tv_text_red_shape));
            mTvcard.setTextColor(mContext.getResources().getColor(R.color.colorf9));
        } else {
            mTvcard.setBackground(mContext.getResources().getDrawable(R.drawable.tv_text_grey_shape));
            mTvcard.setTextColor(mContext.getResources().getColor(R.color.color_85));
        }
    }

}
