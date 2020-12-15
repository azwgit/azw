package com.example.bq.edmp.work.library.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.library.bean.ChuLibraryBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class ChuLibraryListAdapter extends RecyclerView.Adapter<ChuLibraryListAdapter.Holder> {

    private List<ChuLibraryBean.DataBean.RowsBean> list;
    private String TYPE;


    public ChuLibraryListAdapter(List<ChuLibraryBean.DataBean.RowsBean> list, String TYPE) {
        this.list = list;
        this.TYPE = TYPE;
    }

    public void addMoreData(List<ChuLibraryBean.DataBean.RowsBean> data) {
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
        final ChuLibraryBean.DataBean.RowsBean rowsBean = list.get(position);

        //1采购入库 2加工入库 3调拨入库
        if (rowsBean.getType2().equals("1")) {
            if (TYPE.equals("1")) {
                holder.status_tv.setText("采购入库");
            } else {
                holder.status_tv.setText("采购出库");
            }
        } else if (rowsBean.getType2().equals("2")) {
            if (TYPE.equals("1")) {
                holder.status_tv.setText("加工入库");
            } else {
                holder.status_tv.setText("加工出库");
            }
        } else if (rowsBean.getType2().equals("3")) {
            if (TYPE.equals("1")) {
                holder.status_tv.setText("调拨入库");
            } else {
                holder.status_tv.setText("调拨出库");
            }
        }


        holder.code_tv.setText(rowsBean.getCode());
        holder.ck_name_tv.setText(rowsBean.getWarehouseName());
        holder.company_name_tv.setText(rowsBean.getOrgName());
        holder.time_tv.setText(rowsBean.getAddedTime());

        List<ChuLibraryBean.DataBean.RowsBean.StockSubItemsBean> stockSubItems = rowsBean.getStockSubItems();
        if (stockSubItems != null && stockSubItems.size() != 0) {
            holder.zi_rv.setVisibility(ViewGroup.VISIBLE);
            holder.wsj.setVisibility(ViewGroup.GONE);
            ChuNeiListAdapter chuNeiListAdapter = new ChuNeiListAdapter(stockSubItems);
            holder.zi_rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.zi_rv.setAdapter(chuNeiListAdapter);
        } else {
            holder.wsj.setVisibility(ViewGroup.VISIBLE);
            holder.zi_rv.setVisibility(ViewGroup.GONE);
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
        private final RecyclerView zi_rv;
        private final TextView wsj;

        public Holder(View itemView) {
            super(itemView);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            ck_name_tv = itemView.findViewById(R.id.ck_name_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            zi_rv = itemView.findViewById(R.id.zi_rv);
            wsj = itemView.findViewById(R.id.wsj);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, ChuLibraryBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
