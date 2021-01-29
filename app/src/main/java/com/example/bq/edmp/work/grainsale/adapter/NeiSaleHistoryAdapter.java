package com.example.bq.edmp.work.grainsale.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryBean;
import com.example.bq.edmp.work.library.bean.ChuLibraryBean;

import java.util.List;

public class NeiSaleHistoryAdapter extends RecyclerView.Adapter<NeiSaleHistoryAdapter.ViewHolder> {

    private List<SaleHistoryBean.DataBean.RowsBean.CgOrderItemsBean> list;

    public NeiSaleHistoryAdapter(List<SaleHistoryBean.DataBean.RowsBean.CgOrderItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.base_chengpin_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SaleHistoryBean.DataBean.RowsBean.CgOrderItemsBean stockAddItemsBean = list.get(position);

        holder.pz_tv.setText(stockAddItemsBean.getItemName());
        holder.weight_tv.setText("¥"+stockAddItemsBean.getPrice()+"/公斤 * "+stockAddItemsBean.getQty()+" 公斤");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView pz_tv;
        private final TextView weight_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            weight_tv = itemView.findViewById(R.id.weight_tv);
        }
    }

}
