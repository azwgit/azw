package com.example.bq.edmp.work.grainsale.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryBean;
import com.example.bq.edmp.work.library.adapter.ChuNeiListAdapter;
import com.example.bq.edmp.work.library.bean.ChuLibraryBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class SaleHistoryAdapter extends RecyclerView.Adapter<SaleHistoryAdapter.Holder> {

    private List<SaleHistoryBean.DataBean.RowsBean> list;


    public SaleHistoryAdapter(List<SaleHistoryBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<SaleHistoryBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.salehistory_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final SaleHistoryBean.DataBean.RowsBean rowsBean = list.get(position);

        holder.code_tv.setText("订单号:" + rowsBean.getCode());
        holder.company_name_tv.setText(rowsBean.getOrgName());
        holder.time_tv.setText("完成 " + rowsBean.getAddedTime());
        holder.he_price_tv.setText("¥" + rowsBean.getAmount());
        holder.zi_company_name_tv.setText(rowsBean.getOrgName());
        holder.cangku_tv.setText(rowsBean.getWarehouseName());
        holder.user_name_tv.setText(rowsBean.getAddedOperator());


        List<SaleHistoryBean.DataBean.RowsBean.CgOrderItemsBean> stockSubItems = rowsBean.getCgOrderItems();
        if (stockSubItems != null && stockSubItems.size() != 0) {
            holder.zi_rv.setVisibility(ViewGroup.VISIBLE);
            holder.wsj.setVisibility(ViewGroup.GONE);
            NeiSaleHistoryAdapter neiSaleHistoryAdapter = new NeiSaleHistoryAdapter(stockSubItems);
            holder.zi_rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.zi_rv.setAdapter(neiSaleHistoryAdapter);
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
        private final TextView cangku_tv;
        private final TextView zi_company_name_tv;
        private final TextView he_price_tv;
        private final TextView user_name_tv;
        private final TextView time_tv;
        private final RecyclerView zi_rv;
        private final TextView wsj;


        public Holder(View itemView) {
            super(itemView);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);
            cangku_tv = itemView.findViewById(R.id.cangku_tv);
            zi_company_name_tv = itemView.findViewById(R.id.zi_company_name_tv);
            he_price_tv = itemView.findViewById(R.id.he_price_tv);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            user_name_tv = itemView.findViewById(R.id.user_name_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            zi_rv = itemView.findViewById(R.id.zi_rv);
            wsj = itemView.findViewById(R.id.wsj);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, SaleHistoryBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
