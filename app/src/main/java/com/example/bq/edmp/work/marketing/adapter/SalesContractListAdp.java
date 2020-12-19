package com.example.bq.edmp.work.marketing.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.finished.bean.MachineListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class SalesContractListAdp extends RecyclerView.Adapter<SalesContractListAdp.Holder> {

    private List<MachineListBean.DataBean.RowsBean> list;

    public SalesContractListAdp(List<MachineListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<MachineListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public SalesContractListAdp.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_sales_contract, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SalesContractListAdp.Holder holder, final int position) {
        final MachineListBean.DataBean.RowsBean rowsBean = list.get(position);
        if (mItemClickListener != null) {
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

        private final TextView tv_number;//合同编号
        private final TextView tv_name;//品种 重量
        private final TextView tv_position;//职位 姓名
        private final TextView tv_company;//公司名称
        private final TextView tv_time;//公司名称
        private final LinearLayout ly_view;


        public Holder(View itemView) {
            super(itemView);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_position = itemView.findViewById(R.id.tv_position);
            tv_company = itemView.findViewById(R.id.tv_company);
            tv_time = itemView.findViewById(R.id.tv_time);
            ly_view = itemView.findViewById(R.id.ly_view);
        }
    }

    protected SalesContractListAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, MachineListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(SalesContractListAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
