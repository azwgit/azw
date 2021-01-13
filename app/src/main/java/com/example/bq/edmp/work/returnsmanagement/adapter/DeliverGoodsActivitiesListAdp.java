package com.example.bq.edmp.work.returnsmanagement.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.marketingactivities.bean.HistoricalListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.DeliverGoodsListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class DeliverGoodsActivitiesListAdp extends RecyclerView.Adapter<DeliverGoodsActivitiesListAdp.Holder> {

    private List<DeliverGoodsListBean.DataBean.RowsBean> list;

    public DeliverGoodsActivitiesListAdp(List<DeliverGoodsListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<DeliverGoodsListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public DeliverGoodsActivitiesListAdp.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_deliver_goods_activity_list, null));
    }

    @Override
    public void onBindViewHolder(@NonNull DeliverGoodsActivitiesListAdp.Holder holder, final int position) {
        final DeliverGoodsListBean.DataBean.RowsBean rowsBean = list.get(position);
        if (mItemClickListener != null) {
            holder.tv_order_number.setText("订单号 "+rowsBean.getCode());
            holder.tv_company.setText(rowsBean.getCustomerName());
            holder.tv_packing.setText(rowsBean.getVarietyName()+"   *"+MoneyUtils.formatMoney(Double.parseDouble(rowsBean.getQty())) +" 公斤");
            holder.tv_weight.setText(rowsBean.getWarehouseName());
            holder.tv_subsidiary_company.setText(rowsBean.getOrgName());
            holder.tv_time.setText("发货时间 "+rowsBean.getSendOutTimes());
            holder.ly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(position, rowsBean);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView tv_order_number;//订单编号
        private final LinearLayout ly_view;
        private final TextView tv_company;//公司名称
        private final TextView tv_packing;//包装
        private final TextView tv_weight;//重量
        private final TextView tv_subsidiary_company;//分子公司
        private final TextView tv_time;//发货时间

        public Holder(View itemView) {
            super(itemView);
            tv_order_number = itemView.findViewById(R.id.tv_order_number);
            ly_view = itemView.findViewById(R.id.ly_view);
            tv_company = itemView.findViewById(R.id.tv_company);
            tv_packing = itemView.findViewById(R.id.tv_packing);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            tv_subsidiary_company = itemView.findViewById(R.id.tv_subsidiary_company);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

    protected DeliverGoodsActivitiesListAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, DeliverGoodsListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(DeliverGoodsActivitiesListAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
