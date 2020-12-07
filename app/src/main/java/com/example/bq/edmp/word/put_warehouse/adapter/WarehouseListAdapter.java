package com.example.bq.edmp.word.put_warehouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.inventory.bean.InventoryBean;
import com.example.bq.edmp.word.put_warehouse.bean.WarehouseListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class WarehouseListAdapter extends RecyclerView.Adapter<WarehouseListAdapter.Holder> {

    private List<WarehouseListBean.RowsBean> list;
    private int WAREHOUSETYPE;

    public WarehouseListAdapter(List<WarehouseListBean.RowsBean> list, int WAREHOUSETYPE) {
        this.list = list;
        this.WAREHOUSETYPE = WAREHOUSETYPE;
    }

    public void addMoreData(List<WarehouseListBean.RowsBean> data) {
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
        final WarehouseListBean.RowsBean rowsBean = list.get(position);

        //1采购入库 2加工入库 3调拨入库
        if (rowsBean.getType2() == 1) {
            if (WAREHOUSETYPE == 1) {
                holder.status_tv.setText("采购入库");
            } else if (WAREHOUSETYPE == 2) {
                holder.status_tv.setText("采购出库");
            }
        } else if (rowsBean.getType2() == 2) {
            if (WAREHOUSETYPE == 1) {
                holder.status_tv.setText("加工入库");
            } else if (WAREHOUSETYPE == 2) {
                holder.status_tv.setText("加工出库");
            }
        } else if (rowsBean.getType2() == 3) {
            if (WAREHOUSETYPE == 1) {
                holder.status_tv.setText("调拨入库");
            } else if (WAREHOUSETYPE == 2) {
                holder.status_tv.setText("调拨出库");
            }
        }
        holder.code_tv.setText(rowsBean.getCode());
        holder.ck_name_tv.setText(rowsBean.getWarehouseName());
        holder.pz_name_tv.setText(rowsBean.getVarietyName());
        holder.weight_tv.setText(rowsBean.getAddQty());
        holder.company_name_tv.setText(rowsBean.getOrgName());
        holder.time_tv.setText(rowsBean.getAddedTime());

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

        private final TextView pz_name_tv;
        private final TextView code_tv;
        private final TextView status_tv;
        private final TextView company_name_tv;
        private final TextView ck_name_tv;
        private final TextView weight_tv;
        private final TextView time_tv;

        public Holder(View itemView) {
            super(itemView);
            pz_name_tv = itemView.findViewById(R.id.pz_name_tv);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            ck_name_tv = itemView.findViewById(R.id.ck_name_tv);
            weight_tv = itemView.findViewById(R.id.weight_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, WarehouseListBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
