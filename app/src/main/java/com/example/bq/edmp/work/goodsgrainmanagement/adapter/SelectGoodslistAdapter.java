package com.example.bq.edmp.work.goodsgrainmanagement.adapter;

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
import com.example.bq.edmp.work.goodsgrainmanagement.bean.SelecGoodsListBean;
import com.example.bq.edmp.work.order.bean.GoodsBean;

import java.util.List;

public class SelectGoodslistAdapter extends RecyclerView.Adapter<SelectGoodslistAdapter.ViewHolder> {
    private List<SelecGoodsListBean.DataBean> list;

    public SelectGoodslistAdapter(List<SelecGoodsListBean.DataBean> list) {
        this.list = list;
    }

    public void addMoreData(List<SelecGoodsListBean.DataBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_select_goods, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final SelecGoodsListBean.DataBean dataBean = list.get(position);

        if (dataBean.isSelect()) {
            holder.select.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.icon_select));
        } else {
            holder.select.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.icon_noselect));
        }
//        String customerPrice = String.valueOf(dataBean.getCustomerPrice());
//        if (customerPrice.equals("0.0")) {
//            holder.price_tv.setText("¥"+ FromtUtil.getFromt(dataBean.getPrice()) +"/公斤");
//        }else {
//            holder.price_tv.setText("¥"+FromtUtil.getFromt(dataBean.getCustomerPrice())+"/公斤");
//        }
        holder.pinz_tv.setText(dataBean.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).setSelect(true);
                    } else {
                        list.get(i).setSelect(false);
                    }
                    notifyDataSetChanged();
                }
                mItemClickListener.onItemClick(position, dataBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView select;
        private final TextView pinz_tv;
        private final TextView price_tv;


        public ViewHolder(View itemView) {
            super(itemView);
            select = itemView.findViewById(R.id.select);
            pinz_tv = itemView.findViewById(R.id.pinz_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, SelecGoodsListBean.DataBean dataBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
