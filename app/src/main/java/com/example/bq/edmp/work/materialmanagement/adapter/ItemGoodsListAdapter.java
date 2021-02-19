package com.example.bq.edmp.work.materialmanagement.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.materialmanagement.bean.MaterialListBean;

import java.util.List;

public class ItemGoodsListAdapter extends RecyclerView.Adapter<ItemGoodsListAdapter.ViewHolder> {
    private List<MaterialListBean.DataBean.RowsBean.MaterialPurchaseItemsBean> list;

    public ItemGoodsListAdapter(List<MaterialListBean.DataBean.RowsBean.MaterialPurchaseItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_goods_list_adapter, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaterialListBean.DataBean.RowsBean.MaterialPurchaseItemsBean orderItem = list.get(position);

        holder.pz_tv.setText(orderItem.getItemName());
//        holder.danwei_tv.setText(orderItem.getQty()+"");
        holder.danwei_tv.setText("数量" + orderItem.getQty() + "   *   " + MoneyUtils.formatMoney(orderItem.getPrice()) + "公斤");

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
