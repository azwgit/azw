package com.example.bq.edmp.word.inventory.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.word.inventory.bean.InventoryBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.Holder> {

    private List<InventoryBean.RowsBean> list;

    public InventoryListAdapter(List<InventoryBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<InventoryBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.inventory_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final InventoryBean.RowsBean rowsBean = list.get(position);
        holder.pz_name_tv.setText(rowsBean.getVarietyName());
        holder.ck_h_tv.setText(rowsBean.getWarehouseName());
        holder.zl_tv.setText(rowsBean.getQty());
        holder.company_name_tv.setText(rowsBean.getOrgName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position,rowsBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView pz_name_tv;
        private final TextView ck_h_tv;
        private final TextView zl_tv;
        private final TextView company_name_tv;

        public Holder(View itemView) {
            super(itemView);
            pz_name_tv = itemView.findViewById(R.id.pz_name_tv);
            ck_h_tv = itemView.findViewById(R.id.ck_h_tv);
            zl_tv = itemView.findViewById(R.id.zl_tv);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, InventoryBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
