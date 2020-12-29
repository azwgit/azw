package com.example.bq.edmp.work.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.work.order.bean.OrderDetailsBean;

import java.util.ArrayList;

/*
 * 修改订单商品
 * */
public class GoodsGrayAdapter extends RecyclerView.Adapter<GoodsGrayAdapter.ViewHolder> {

    private ArrayList<OrderDetailsBean.DataBean.OrderItemsBean> list;

    public GoodsGrayAdapter(ArrayList<OrderDetailsBean.DataBean.OrderItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.goods_gray_list_padater, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final OrderDetailsBean.DataBean.OrderItemsBean orderItem = list.get(position);

        holder.pinzhong_tv.setText(orderItem.getPackagingName());
        holder.xiaoshoulaing_tv.setText("¥" + FromtUtil.getFromt(orderItem.getPrice()) + "/公斤 * " + FromtUtil.getFromt(orderItem.getQty())+"公斤");
        holder.price_tv.setText("¥" + FromtUtil.getFromt(orderItem.getSettlement()));

        //删除  物理
        holder.delet_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(0, position, orderItem);
            }
        });

        //修改
        holder.edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(1, position, orderItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView delet_img;
        private final ImageView edit_img;
        private final TextView pinzhong_tv;
        private final TextView xiaoshoulaing_tv;
        private final TextView price_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            delet_img = itemView.findViewById(R.id.delet_img);
            edit_img = itemView.findViewById(R.id.edit_img);
            pinzhong_tv = itemView.findViewById(R.id.pinzhong_tv);
            xiaoshoulaing_tv = itemView.findViewById(R.id.xiaoshoulaing_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
        }
    }


    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int type, int pos, OrderDetailsBean.DataBean.OrderItemsBean orderItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
