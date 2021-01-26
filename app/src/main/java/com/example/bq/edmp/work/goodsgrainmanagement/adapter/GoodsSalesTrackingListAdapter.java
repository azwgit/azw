package com.example.bq.edmp.work.goodsgrainmanagement.adapter;

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
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.work.order.bean.OrderTJBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsSalesTrackingListAdapter extends RecyclerView.Adapter<GoodsSalesTrackingListAdapter.ViewHolder> {
    private List<OrderTJBean.DataBean.RowsBean> list;

    public GoodsSalesTrackingListAdapter(ArrayList<OrderTJBean.DataBean.RowsBean> list) {
        this.list = list;
    }


    public void addMoreData(List<OrderTJBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_goods_sales_managment_list_padater, null));
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final OrderTJBean.DataBean.RowsBean rowsBean = list.get(position);

        String status = rowsBean.getStatus();
        if (status != null) {
            if (status.equals("1")) {
                holder.status_tv.setText("待提交");
            } else {
                holder.status_tv.setText("暂无");
            }
        } else {
            holder.status_tv.setText("暂无");
        }

        holder.code_tv.setText("订单号: " + rowsBean.getCode());
        holder.compay_name_tv.setText(rowsBean.getCustomerName());
        holder.price_tv.setText("¥" + FromtUtil.getFromt(rowsBean.getAmount()));
        holder.tiem_tv.setText(rowsBean.getAddedTime());


        List<OrderTJBean.DataBean.RowsBean.OrderItemsBean> orderItem = rowsBean.getOrderItems();
        if (orderItem != null && orderItem.size() != 0) {
            holder.rv.setVisibility(View.VISIBLE);
            holder.wsj.setVisibility(View.GONE);
//            ItemPackingListAdapter orderNeiAdapter = new ItemPackingListAdapter(orderItem);
//            holder.rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
//            holder.rv.setAdapter(orderNeiAdapter);
//            orderNeiAdapter.notifyDataSetChanged();
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
        private final TextView status_tv;
        private final TextView compay_name_tv;
        private final TextView price_tv;
        private final TextView wsj;
        private final TextView tiem_tv;
        private final RecyclerView rv;
        private final LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            compay_name_tv = itemView.findViewById(R.id.compay_name_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            wsj = itemView.findViewById(R.id.wsj);
            tiem_tv = itemView.findViewById(R.id.tiem_tv);
            rv = itemView.findViewById(R.id.rv);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, OrderTJBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
