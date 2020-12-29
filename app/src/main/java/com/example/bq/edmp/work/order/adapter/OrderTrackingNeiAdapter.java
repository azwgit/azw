package com.example.bq.edmp.work.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.work.order.bean.OrderTJBean;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;

import java.util.List;

public class OrderTrackingNeiAdapter extends RecyclerView.Adapter<OrderTrackingNeiAdapter.ViewHolder> {
    private List<OrderTrackingBean.DataBean.RowsBean.OrderItemsBean> list;

    public OrderTrackingNeiAdapter(List<OrderTrackingBean.DataBean.RowsBean.OrderItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.allocation_nei_adapter, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderTrackingBean.DataBean.RowsBean.OrderItemsBean orderItem = list.get(position);

        holder.pz_tv.setText(orderItem.getPackagingName());
        holder.danwei_tv.setText(FromtUtil.getFromt(orderItem.getQty())+"公斤");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView pz_tv;
        private final TextView danwei_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            danwei_tv = itemView.findViewById(R.id.danwei_tv);

        }
    }
}
