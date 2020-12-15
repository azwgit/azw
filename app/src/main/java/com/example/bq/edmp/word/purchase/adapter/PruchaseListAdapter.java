package com.example.bq.edmp.word.purchase.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.purchase.bean.PurchaseListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class PruchaseListAdapter extends RecyclerView.Adapter<PruchaseListAdapter.Holder> {

    private List<PurchaseListBean.DataBean.RowsBean> list;

    public PruchaseListAdapter(List<PurchaseListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<PurchaseListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.purchase_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final PurchaseListBean.DataBean.RowsBean rowsBean = list.get(position);

        String status = rowsBean.getStatus();
        if (status != null) {
            if (status.equals("1")) {
                holder.status_tv.setText("收购中");
//            holder.status_tv.setTextColor(R.color.colorf9);
                holder.status_tv.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.purchase_red_shape));
            } else if (status.equals("2")) {
                holder.status_tv.setText("已完成");
//            holder.status_tv.setTextColor(R.color.color_d0d0d0);//colorf9
                holder.status_tv.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.purchase_back_shape));
            }
        } else {
            holder.status_tv.setText("无状态");
        }

        if (rowsBean.getGrossWeight() == null || rowsBean.getGrossWeight().equals("")) {
            holder.weight_m_tv.setText("毛重: ----");
        } else {
            holder.weight_m_tv.setText("毛重: " + rowsBean.getGrossWeight());
        }
        if (rowsBean.getTareWeight() == null || rowsBean.getTareWeight().equals("")) {
            holder.weight_p_tv.setText("皮重: ----");
        } else {
            holder.weight_p_tv.setText("皮重: " + rowsBean.getTareWeight());
        }
        if (rowsBean.getNetWeight() == null || rowsBean.getNetWeight().equals("")) {
            holder.weight_j_tv.setText("净重: ----");
        } else {
            holder.weight_j_tv.setText("净重: " + rowsBean.getNetWeight());
        }
        holder.code_tv.setText(rowsBean.getCode());
        holder.variety_name_tv.setText(rowsBean.getVarietyName());
        holder.farm_tv.setText(rowsBean.getFarmName());
        holder.user_name_tv.setText(rowsBean.getFarmerName());
        holder.company_name_tv.setText(rowsBean.getOrgName());
        holder.time_tv.setText(rowsBean.getAddedTime());
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
        private final TextView variety_name_tv;
        private final TextView weight_m_tv;
        private final TextView weight_p_tv;
        private final TextView weight_j_tv;
        private final TextView farm_tv;
        private final TextView user_name_tv;
        private final TextView company_name_tv;
        private final TextView time_tv;

        public Holder(View itemView) {
            super(itemView);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            variety_name_tv = itemView.findViewById(R.id.variety_name_tv);
            weight_m_tv = itemView.findViewById(R.id.weight_m_tv);
            weight_p_tv = itemView.findViewById(R.id.weight_p_tv);
            weight_j_tv = itemView.findViewById(R.id.weight_j_tv);
            farm_tv = itemView.findViewById(R.id.farm_tv);
            user_name_tv = itemView.findViewById(R.id.user_name_tv);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, PurchaseListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
