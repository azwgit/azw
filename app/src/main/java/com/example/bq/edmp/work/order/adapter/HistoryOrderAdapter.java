package com.example.bq.edmp.work.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.order.bean.HistoryBean;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.ViewHolder> {
    private List<HistoryBean.DataBean.RowsBean> list;


    public HistoryOrderAdapter(List<HistoryBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<HistoryBean.DataBean.RowsBean> rowsBeans) {
        if (rowsBeans != null) {
            list.addAll(list.size(), rowsBeans);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.history_order_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final HistoryBean.DataBean.RowsBean rowsBean = list.get(position);


        holder.code_tv.setText(rowsBean.getCode());
        holder.compay_name_tv.setText(rowsBean.getCustomerName());
        holder.price_tv.setText(rowsBean.getAmount());
        holder.tiem_tv.setText(rowsBean.getAllocationTime());
        List<HistoryBean.DataBean.RowsBean.OrderItemsBean> orderItems = rowsBean.getOrderItems();
        if (orderItems != null && orderItems.size() != 0) {
            holder.rv.setVisibility(View.VISIBLE);
            holder.wsj.setVisibility(View.GONE);
            HistoryNeiAdapter historyNeiAdapter = new HistoryNeiAdapter(orderItems);
            holder.rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.rv.setAdapter(historyNeiAdapter);
            historyNeiAdapter.notifyDataSetChanged();
        } else {
            holder.rv.setVisibility(View.GONE);
            holder.wsj.setVisibility(View.VISIBLE);
        }



        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position, rowsBean);
            }
        });
        holder.rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return holder.ll.onTouchEvent(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView code_tv;
        private final TextView compay_name_tv;
        private final TextView price_tv;
        private final TextView tiem_tv;
        private final TextView wsj;
        private final LinearLayout ll;
        private final RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            code_tv = itemView.findViewById(R.id.code_tv);
            compay_name_tv = itemView.findViewById(R.id.compay_name_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            tiem_tv = itemView.findViewById(R.id.tiem_tv);
            wsj = itemView.findViewById(R.id.wsj);
            ll = itemView.findViewById(R.id.ll);
            rv = itemView.findViewById(R.id.rv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, HistoryBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


}
