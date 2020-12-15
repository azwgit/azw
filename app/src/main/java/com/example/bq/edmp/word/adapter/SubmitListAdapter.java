package com.example.bq.edmp.word.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.bean.SubmitListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class SubmitListAdapter extends RecyclerView.Adapter<SubmitListAdapter.Holder> {

    private List<SubmitListBean.RowsBean> list;
    private int newPoistion=0;
    public SubmitListAdapter(List<SubmitListBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<SubmitListBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public SubmitListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.submit_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SubmitListAdapter.Holder holder, final int position) {
        final  SubmitListBean.RowsBean rowsBean = list.get(position);
        newPoistion=position;
        if (rowsBean.getStatus() == 1) {//待提交
            Glide.with(ProApplication.getmContext()).load(R.drawable.property_1daitijiao).into(holder.state_img);
        } else if (rowsBean.getStatus() == 2) {//待审批
            Glide.with(ProApplication.getmContext()).load(R.drawable.property_1daishenpi).into(holder.state_img);
        } else if (rowsBean.getStatus() == 3) {//已审批
            Glide.with(ProApplication.getmContext()).load(R.drawable.property_1yishenpi).into(holder.state_img);
        } else if (rowsBean.getStatus() == 4) {//已完成
            Glide.with(ProApplication.getmContext()).load(R.drawable.property_1yiwancheng).into(holder.state_img);
        } else if (rowsBean.getStatus() == -2) {//已拒绝
            Glide.with(ProApplication.getmContext()).load(R.drawable.property_1yijujue).into(holder.state_img);
        } else if (rowsBean.getStatus() == -3) {//已撤回
            Glide.with(ProApplication.getmContext()).load(R.drawable.property_1yichexiao).into(holder.state_img);
        }


        if (rowsBean.getTypes() == 1) {
            holder.bz_distinction_tv.setText("支出报账");
        } else if (rowsBean.getTypes() == 2) {
            holder.bz_distinction_tv.setText("差旅报账");
        }

        if (rowsBean.getId()==0){
            holder.spdh_tv.setText("审批单号：000000000" );
        }else {
            holder.spdh_tv.setText("审批单号：" + rowsBean.getId());
        }
        if (rowsBean.getDeptName()==null){
            holder.bm_tv.setText("某部门");
        }else {
            holder.bm_tv.setText(rowsBean.getDeptName());
        }
        if (rowsBean.getEmpName()==null){
            holder.name_tv.setText("某某");
        }else {
            holder.name_tv.setText(rowsBean.getEmpName());
        }

        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(newPoistion,rowsBean );
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final ImageView state_img;
        private final TextView spdh_tv;
        private final TextView price_tv;
        private final TextView bm_tv;
        private final TextView name_tv;
        private final TextView bz_distinction_tv;
        private final TextView time_tv;

        public Holder(View itemView) {
            super(itemView);
            state_img = itemView.findViewById(R.id.state_img);
            spdh_tv = itemView.findViewById(R.id.spdh_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            bm_tv = itemView.findViewById(R.id.bm_tv);
            name_tv = itemView.findViewById(R.id.name_tv);
            bz_distinction_tv = itemView.findViewById(R.id.bz_distinction_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }
    }

    protected SubmitListAdapter.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, SubmitListBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(SubmitListAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
