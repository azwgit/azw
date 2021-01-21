package com.example.bq.edmp.work.reseller.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class NeiResellerApplyAdapter extends RecyclerView.Adapter<NeiResellerApplyAdapter.ViewHolder> {

    private List<BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean> list;

    public NeiResellerApplyAdapter(List<BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean> list) {
        this.list = list;
    }


    public void addMoreData(List<BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.reseller_adapter_item_nei, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean stockAllotItemsBean = list.get(position);

        holder.zhuanchu_tv.setText(stockAllotItemsBean.getOutItemName());
        holder.zhuanru_tv.setText(stockAllotItemsBean.getInItemName());
        holder.umber_tv.setText("数量  " + new DecimalFormat("#0.00").format(stockAllotItemsBean.getQty()) + " 公斤");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView zhuanchu_tv;
        private final TextView zhuanru_tv;
        private final TextView umber_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            zhuanchu_tv = itemView.findViewById(R.id.zhuanchu_tv);
            zhuanru_tv = itemView.findViewById(R.id.zhuanru_tv);
            umber_tv = itemView.findViewById(R.id.umber_tv);
        }
    }
}
