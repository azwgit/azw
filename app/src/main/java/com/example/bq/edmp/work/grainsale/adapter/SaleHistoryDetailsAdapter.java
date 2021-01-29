package com.example.bq.edmp.work.grainsale.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class SaleHistoryDetailsAdapter extends RecyclerView.Adapter<SaleHistoryDetailsAdapter.ViewHolder> {

    private List<SaleHistoryDetailsBean.DataBean.CgOrderItemsBean> list;

    public SaleHistoryDetailsAdapter(List<SaleHistoryDetailsBean.DataBean.CgOrderItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.aslehistorydelails_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SaleHistoryDetailsBean.DataBean.CgOrderItemsBean cgOrderItemBean = list.get(position);

        holder.pz_tv.setText(cgOrderItemBean.getItemName());
        holder.weight_tv.setText("数量 " + cgOrderItemBean.getQty() + " 公斤");
        holder.xiao_price_tv.setText("¥" + cgOrderItemBean.getPrice() + "/公斤");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView pz_tv;
        private final TextView weight_tv;
        private final TextView xiao_price_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            weight_tv = itemView.findViewById(R.id.weight_tv);
            xiao_price_tv = itemView.findViewById(R.id.xiao_price_tv);
        }
    }
}
