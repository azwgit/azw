package com.example.bq.edmp.word.put_warehouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
public class ChuKuListAdapter extends RecyclerView.Adapter<ChuKuListAdapter.Holder> {

    private List<ChuKuWarehouseListBean.DataBean.RowsBean> list;
    private int WAREHOUSETYPE;

    public ChuKuListAdapter(List<ChuKuWarehouseListBean.DataBean.RowsBean> list, int WAREHOUSETYPE) {
        this.list = list;
        this.WAREHOUSETYPE = WAREHOUSETYPE;
    }

    public void addMoreData(List<ChuKuWarehouseListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.warehouse_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final ChuKuWarehouseListBean.DataBean.RowsBean rowsBean = list.get(position);

        //1采购入库 2加工入库 3调拨入库
        if (rowsBean.getType2().equals("1")) {
            holder.status_tv.setText("采购出库");
        } else if (rowsBean.getType2().equals("2")) {
            holder.status_tv.setText("加工出库");
        } else if (rowsBean.getType2().equals("3")) {
            holder.status_tv.setText("调拨出库");
        }
        holder.code_tv.setText(rowsBean.getCode());
        holder.ck_name_tv.setText(rowsBean.getWarehouseName());
        holder.company_name_tv.setText(rowsBean.getOrgName());
        holder.time_tv.setText(rowsBean.getAddedTime());

        List<ChuKuWarehouseListBean.DataBean.RowsBean.StockSubItemsBean> stockAddItems = rowsBean.getStockSubItems();
        if (stockAddItems != null && stockAddItems.size() != 0) {
            holder.zi_rv.setVisibility(ViewGroup.VISIBLE);
            holder.wsj.setVisibility(ViewGroup.GONE);
            ChuListAdapter chuListAdapter = new ChuListAdapter(stockAddItems);
            holder.zi_rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.zi_rv.setAdapter(chuListAdapter);

        } else {
            holder.zi_rv.setVisibility(ViewGroup.GONE);
            holder.wsj.setVisibility(ViewGroup.VISIBLE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position, rowsBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView code_tv;
        private final TextView status_tv;
        private final TextView company_name_tv;
        private final TextView ck_name_tv;
        private final TextView time_tv;
        private final TextView wsj;
        private final RecyclerView zi_rv;

        public Holder(View itemView) {
            super(itemView);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            ck_name_tv = itemView.findViewById(R.id.ck_name_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            wsj = itemView.findViewById(R.id.wsj);
            zi_rv = itemView.findViewById(R.id.zi_rv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, ChuKuWarehouseListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
