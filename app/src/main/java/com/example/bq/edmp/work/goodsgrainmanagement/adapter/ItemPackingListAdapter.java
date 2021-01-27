package com.example.bq.edmp.work.goodsgrainmanagement.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.order.bean.OrderTJBean;

import java.util.List;

public class ItemPackingListAdapter extends RecyclerView.Adapter<ItemPackingListAdapter.ViewHolder> {
    private List<GoodsSalesManagementListBean.DataBean.RowsBean.CgOrderItemsBean> list;

    public ItemPackingListAdapter(List<GoodsSalesManagementListBean.DataBean.RowsBean.CgOrderItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_packing_list_adapter, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsSalesManagementListBean.DataBean.RowsBean.CgOrderItemsBean orderItem = list.get(position);

        holder.pz_tv.setText(orderItem.getItemName());
//        holder.danwei_tv.setText(orderItem.getQty()+"");
        holder.danwei_tv.setText("￥" + MoneyUtils.formatMoney(orderItem.getPrice())+ "/公斤 * " + MoneyUtils.formatMoney(orderItem.getQty()) + "公斤");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView pz_tv;
        private final TextView danwei_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            danwei_tv = itemView.findViewById(R.id.danwei_tv);

        }
    }
}
