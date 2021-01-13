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
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.marketingactivities.bean.ActivityManagementListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnTrackingListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class ReturnsGoodsActivityListAdp extends RecyclerView.Adapter<ReturnsGoodsActivityListAdp.Holder> {

    private List<ReturnTrackingListBean.DataBean.RowsBean> list;

    public ReturnsGoodsActivityListAdp(List<ReturnTrackingListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<ReturnTrackingListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ReturnsGoodsActivityListAdp.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_returns_activity_list, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnsGoodsActivityListAdp.Holder holder, final int position) {
        final ReturnTrackingListBean.DataBean.RowsBean rowsBean = list.get(position);
        if (mItemClickListener != null) {
            holder.tv_no.setText(rowsBean.getCode());
            holder.tv_company.setText(rowsBean.getCustomerName());
            holder.tv_order_number.setText("订单号 " + rowsBean.getOrderCode());
            holder.tv_packing.setText(rowsBean.getVarietyName());
            holder.tv_number.setText("数量 " + rowsBean.getReturnQty() + "公斤");
            if (rowsBean.getTypes() == 1) {
                holder.tv_type.setText("退回仓库");
            } else {
                holder.tv_type.setText("转商销售");
            }
            String status = "";
            switch (rowsBean.getStatus()) {
                case 2:
                    status = "待审批";
                    break;
                case 3:
                    status = "审批拒绝";
                    break;
                case 4:
                    status = "退货中";
                    break;
            }
            holder.tv_status.setText(status);
            holder.tv_money.setText("退货金额 ￥" + MoneyUtils.formatMoney(rowsBean.getAmount()));
            holder.tv_sart_time.setText("发货时间 " + rowsBean.getSendOutTimes());
            holder.tv_end_time.setText("退货时间 " + rowsBean.getAddedTime());
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

        private final TextView tv_no;//退货编号
        private final TextView tv_status;//退货状态
        private final TextView tv_company;//退货公司
        private final LinearLayout ly_view;
        private final TextView tv_order_number;//订单编号
        private final TextView tv_packing;//包装
        private final TextView tv_number;//退货数量
        private final TextView tv_type;//退货类型
        private final TextView tv_money;//退货金额
        private final TextView tv_sart_time;//发货时间
        private final TextView tv_end_time;//退货时间


        public Holder(View itemView) {
            super(itemView);
            tv_no = itemView.findViewById(R.id.tv_no);
            ly_view = itemView.findViewById(R.id.ly_view);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_company = itemView.findViewById(R.id.tv_company);
            tv_order_number = itemView.findViewById(R.id.tv_order_number);
            tv_packing = itemView.findViewById(R.id.tv_packing);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_sart_time = itemView.findViewById(R.id.tv_sart_time);
            tv_end_time = itemView.findViewById(R.id.tv_end_time);
        }
    }

    protected ReturnsGoodsActivityListAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, ReturnTrackingListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(ReturnsGoodsActivityListAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
