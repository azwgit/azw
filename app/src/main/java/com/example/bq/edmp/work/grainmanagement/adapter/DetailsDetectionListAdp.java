package com.example.bq.edmp.work.grainmanagement.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bq.edmp.R;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.work.grainmanagement.bean.AcquisitionBean;

import java.util.List;

public class DetailsDetectionListAdp extends BaseQuickAdapter<AcquisitionBean.DataBean.TestingItemsBean, BaseViewHolder> {
    public DetailsDetectionListAdp(@Nullable List<AcquisitionBean.DataBean.TestingItemsBean> data) {
        super(R.layout.item_details_detection_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AcquisitionBean.DataBean.TestingItemsBean item) {
        helper.setText(R.id.tv_name, item.getName() + " (" + item.getUnit() + ")");
        TextView mTvContent = helper.getView(R.id.tv_content);
        TextView mTvInfo = helper.getView(R.id.tv_info);
        //1为合格
        if (item.getResults() == 1) {
            mTvContent.setTextColor(mContext.getResources().getColor(R.color.color_e6));
        } else {
            mTvContent.setTextColor(mContext.getResources().getColor(R.color.colorf9));
        }
        mTvContent.setText(item.getValue());
        if(item.getUpperLimit()!=null&&item.getLowerLimit()!=null){
            mTvInfo.setText(item.getLowerLimit()+"-"+item.getUpperLimit());
        }else{
            if(item.getLowerLimit()!=null){
                mTvInfo.setText("≥"+item.getLowerLimit());
            }else{
                mTvInfo.setText("≤"+item.getUpperLimit());
            }
        }

    }
}
