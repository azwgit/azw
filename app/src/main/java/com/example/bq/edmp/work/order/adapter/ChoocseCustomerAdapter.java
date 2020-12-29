package com.example.bq.edmp.work.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.library.bean.ChuLibraryBean;
import com.example.bq.edmp.work.order.bean.CustomerBean;

import java.util.ArrayList;
import java.util.List;

public class ChoocseCustomerAdapter extends RecyclerView.Adapter<ChoocseCustomerAdapter.ViewHolder> {

    private ArrayList<CustomerBean.DataBean.RowsBean> list;

    public ChoocseCustomerAdapter(ArrayList<CustomerBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<CustomerBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.customer_list_padater, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CustomerBean.DataBean.RowsBean rowsBean = list.get(position);

        holder.select.setChecked(list.get(position).isSelect());
        holder.comay_tv.setText(rowsBean.getName());
        holder.user_tv.setText(rowsBean.getContacts());
        holder.phone_tv.setText(rowsBean.getMobTel());
        holder.address_tv.setText(rowsBean.getRegion());
        holder.userzhiwei_tv.setText(rowsBean.getSaleName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).setSelect(true);
                    } else {
                        list.get(i).setSelect(false);
                    }
                    notifyDataSetChanged();
                }
                mItemClickListener.onItemClick(position,rowsBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox select;
        private final TextView comay_tv;
        private final TextView user_tv;
        private final TextView phone_tv;
        private final TextView address_tv;
        private final TextView userzhiwei_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            select = itemView.findViewById(R.id.select);
            comay_tv = itemView.findViewById(R.id.comay_tv);
            user_tv = itemView.findViewById(R.id.user_tv);
            phone_tv = itemView.findViewById(R.id.phone_tv);
            address_tv = itemView.findViewById(R.id.address_tv);
            userzhiwei_tv = itemView.findViewById(R.id.userzhiwei_tv);

        }
    }


    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, CustomerBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
