package com.example.bq.edmp.work.allocation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;

import java.text.DecimalFormat;
import java.util.List;

public class BaseAllocationNeiAdapter extends RecyclerView.Adapter<BaseAllocationNeiAdapter.ViewHolder> {
    private List<BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean> list;

    public BaseAllocationNeiAdapter(List<BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.allocation_nei_adapter,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean stockAllotItemsBean = list.get(position);

        holder.pz_tv.setText(stockAllotItemsBean.getVarietyName());
        holder.danwei_tv.setText(new DecimalFormat("#0.00").format(stockAllotItemsBean.getQty())+"公斤");

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
