package com.example.bq.edmp.work.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.order.bean.OrderDetailsBean;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;

import java.util.List;

public class TrackingAdapter extends RecyclerView.Adapter<TrackingAdapter.ViewHolder> {
    private List<OrderDetailsBean.DataBean.OrderItemsBean> list;

    public TrackingAdapter(List<OrderDetailsBean.DataBean.OrderItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.tracking_adapter, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetailsBean.DataBean.OrderItemsBean orderItem = list.get(position);


        holder.baozhuang_tv.setText(orderItem.getPackagingName());
        holder.shuliang_tv.setText(orderItem.getQty()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView baozhuang_tv;
        private final TextView cangku_tv;
        private final TextView zhuangtai_tv;
        private final TextView shuliang_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            baozhuang_tv = itemView.findViewById(R.id.baozhuang_tv);
            cangku_tv = itemView.findViewById(R.id.cangku_tv);
            zhuangtai_tv = itemView.findViewById(R.id.zhuangtai_tv);
            shuliang_tv = itemView.findViewById(R.id.shuliang_tv);

        }
    }
}
