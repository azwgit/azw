package com.example.bq.edmp.work.marketing.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.finished.bean.MachineListBean;
import com.example.bq.edmp.work.shipments.bean.DshipmentsListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class CustomerManagementListAdp extends RecyclerView.Adapter<CustomerManagementListAdp.Holder> {

    private List<DshipmentsListBean.DataBean.RowsBean> list;

    public CustomerManagementListAdp(List<DshipmentsListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<DshipmentsListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public CustomerManagementListAdp.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_customer_management_list, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerManagementListAdp.Holder holder, final int position) {
        final DshipmentsListBean.DataBean.RowsBean rowsBean = list.get(position);
        if (mItemClickListener != null) {
            holder.ly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(position, rowsBean);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView tv_name;
        private final LinearLayout ly_view;

        public Holder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            ly_view = itemView.findViewById(R.id.ly_view);
        }
    }

    protected CustomerManagementListAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, DshipmentsListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(CustomerManagementListAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
