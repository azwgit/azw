package com.example.bq.edmp.work.shipments.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.shipments.bean.UserNameListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class UserNameListAdapter extends RecyclerView.Adapter<UserNameListAdapter.Holder> {

    private List<UserNameListBean.DataBean> list;

    public UserNameListAdapter(List<UserNameListBean.DataBean> list) {
        this.list = list;
    }

    public void addMoreData(List<UserNameListBean.DataBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.username_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        final UserNameListBean.DataBean dataBean = list.get(position);
        holder.username_tv.setText(dataBean.getName());

        //判断点击与不点击的背景
        if (list.get(position).isSelected()) {
            holder.username_tv.setBackgroundColor(Color.parseColor("#FFF9F8F8"));
//            holder.tv.setBackgroundResource(R.drawable.audit_ch_shape_yes);
            holder.username_tv.setTextColor(Color.parseColor("#B317171A"));//FFFEE8E8    FFF2F5F6
        } else {
            holder.username_tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            holder.tv.setBackgroundResource(R.drawable.audit_ch_shape_no);
            holder.username_tv.setTextColor(Color.parseColor("#000000"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position, dataBean, holder.itemView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView username_tv;

        public Holder(View itemView) {
            super(itemView);
            username_tv = itemView.findViewById(R.id.username_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, UserNameListBean.DataBean dataBean, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
