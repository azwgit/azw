package com.example.bq.edmp.word.put_warehouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.put_warehouse.bean.ChuKuWarehouseListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class ChuListAdapter extends RecyclerView.Adapter<ChuListAdapter.Holder> {

    private List<ChuKuWarehouseListBean.DataBean.RowsBean.StockSubItemsBean> list;

    public ChuListAdapter(List<ChuKuWarehouseListBean.DataBean.RowsBean.StockSubItemsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<ChuKuWarehouseListBean.DataBean.RowsBean.StockSubItemsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.base_yuanliang_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final ChuKuWarehouseListBean.DataBean.RowsBean.StockSubItemsBean orderItemsBean = list.get(position);

        holder.pz_tv.setText(orderItemsBean.getVarietyName());
        holder.weight_tv.setText(orderItemsBean.getSubQty());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView pz_tv;
        private final TextView weight_tv;

        public Holder(View itemView) {
            super(itemView);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            weight_tv = itemView.findViewById(R.id.weight_tv);
        }
    }
}
