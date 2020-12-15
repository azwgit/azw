package com.example.bq.edmp.work.shipments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.shipments.bean.DshipmentsListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class ZiDshipmentsListAdapter extends RecyclerView.Adapter<ZiDshipmentsListAdapter.Holder> {

    private List<DshipmentsListBean.DataBean.RowsBean.OrderItemsBean> list;

    public ZiDshipmentsListAdapter(List<DshipmentsListBean.DataBean.RowsBean.OrderItemsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<DshipmentsListBean.DataBean.RowsBean.OrderItemsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.zi_dshipments_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final DshipmentsListBean.DataBean.RowsBean.OrderItemsBean orderItemsBean = list.get(position);

        holder.pz_tv.setText(orderItemsBean.getVarietyName());
        holder.danwei_tv.setText(orderItemsBean.getPackagingName());
        holder.weight_tv.setText("* "+orderItemsBean.getUnitWeight() + " " + orderItemsBean.getUnits());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView pz_tv;
        private final TextView danwei_tv;
        private final TextView weight_tv;

        public Holder(View itemView) {
            super(itemView);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            danwei_tv = itemView.findViewById(R.id.danwei_tv);
            weight_tv = itemView.findViewById(R.id.weight_tv);
        }
    }
}
