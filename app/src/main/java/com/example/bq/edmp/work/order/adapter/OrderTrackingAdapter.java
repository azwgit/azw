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
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.detection.bean.DetectionRecorListBean;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;

import java.util.ArrayList;
import java.util.List;

public class OrderTrackingAdapter extends RecyclerView.Adapter<OrderTrackingAdapter.ViewHolder> {
    private List<OrderTrackingBean.DataBean.RowsBean> list;


    public OrderTrackingAdapter(List<OrderTrackingBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<OrderTrackingBean.DataBean.RowsBean> rowsBeans) {
        if (rowsBeans != null) {
            list.addAll(list.size(), rowsBeans);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.order_tracking_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final OrderTrackingBean.DataBean.RowsBean rowsBean = list.get(position);

        //0 全部   3待分配 4待回款 5备货中 7部份发货 8在途
        String status = rowsBean.getStatus();
        if (status != null) {
            if (status.equals("3")) {
                holder.status_tv.setText("待分配");
            } else if (status.equals("4")) {
                holder.status_tv.setText("待回款");
            } else if (status.equals("5")) {
                holder.status_tv.setText("备货中");
            } else if (status.equals("7")) {
                holder.status_tv.setText("部份发货");
            } else if (status.equals("8")) {
                holder.status_tv.setText("在途");
            }else {
                holder.status_tv.setText("暂无状态");
            }
        } else {
            holder.status_tv.setText("暂无状态");
        }

        holder.code_tv.setText("订单号"+rowsBean.getCode());
        holder.compay_name_tv.setText(rowsBean.getCustomerName());
        holder.price_tv.setText("¥"+ FromtUtil.getFromt(rowsBean.getAmount()));
        holder.tiem_tv.setText(rowsBean.getSubmitTime());
        List<OrderTrackingBean.DataBean.RowsBean.OrderItemsBean> orderItems = rowsBean.getOrderItems();
        if (orderItems != null && orderItems.size() != 0) {
            holder.rv.setVisibility(View.VISIBLE);
            holder.wsj.setVisibility(View.GONE);
            OrderTrackingNeiAdapter orderTrackingNeiAdapter = new OrderTrackingNeiAdapter(orderItems);
            holder.rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.rv.setAdapter(orderTrackingNeiAdapter);
            orderTrackingNeiAdapter.notifyDataSetChanged();
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
        private final TextView tiem_tv;
        private final TextView wsj;
        private final LinearLayout ll;
        private final RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
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
        void onItemClick(int pos, OrderTrackingBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
