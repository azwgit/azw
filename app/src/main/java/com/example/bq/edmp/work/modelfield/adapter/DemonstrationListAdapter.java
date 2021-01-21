package com.example.bq.edmp.work.modelfield.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationListBean;
import com.example.bq.edmp.work.order.bean.CustomerBean;
import com.example.bq.edmp.work.order.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class DemonstrationListAdapter extends RecyclerView.Adapter<DemonstrationListAdapter.ViewHolder> {

    private ArrayList<DemonstrationListBean.DataBean.RowsBean> list;

    public DemonstrationListAdapter(ArrayList<DemonstrationListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<DemonstrationListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.demonstrationlist, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DemonstrationListBean.DataBean.RowsBean rowsBean = list.get(position);

        holder.pz_nd_tv.setText(rowsBean.getVarietyName() + " 品种示范 " + rowsBean.getYears() + "年");
        holder.address_tv.setText(rowsBean.getRegion());
        holder.compay_tv.setText("示范单位: "+rowsBean.getCompanyName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position,rowsBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView pz_nd_tv;
        private final TextView address_tv;
        private final TextView compay_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pz_nd_tv = itemView.findViewById(R.id.pz_nd_tv);
            address_tv = itemView.findViewById(R.id.address_tv);
            compay_tv = itemView.findViewById(R.id.compay_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, DemonstrationListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
