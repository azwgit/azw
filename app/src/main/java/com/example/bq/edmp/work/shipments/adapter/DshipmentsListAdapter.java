package com.example.bq.edmp.work.shipments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.shipments.bean.DshipmentsListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class DshipmentsListAdapter extends RecyclerView.Adapter<DshipmentsListAdapter.Holder> {

    private List<DshipmentsListBean.DataBean.RowsBean> list;

    public DshipmentsListAdapter(List<DshipmentsListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<DshipmentsListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.dshipments_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final DshipmentsListBean.DataBean.RowsBean rowsBean = list.get(position);

        //状态： 1 待提交 2 待审批 3 待分配 4备货中 5待发货 6在途 7已签收
        String status = rowsBean.getStatus();
        if (status.equals("1")) {
            holder.status_tv.setText("待提交");
        } else if (status.equals("2")) {
            holder.status_tv.setText("待审批");
        } else if (status.equals("3")) {
            holder.status_tv.setText("待分配");
        } else if (status.equals("4")) {
            holder.status_tv.setText("备货中");
        } else if (status.equals("5")) {
            holder.status_tv.setText("待发货");
        } else if (status.equals("6")) {
            holder.status_tv.setText("在途");
        } else if (status.equals("7")) {
            holder.status_tv.setText("已签收");
        }
        holder.ckfl_tv.setText(rowsBean.getWarehouseName());
        holder.code_tv.setText("订单号: " + rowsBean.getCode());
        holder.fen_compay_name_tv.setText(rowsBean.getOrgName());
        if (rowsBean.getSalesman()!=null) {
            holder.user_name_tv.setText("业务员：" + rowsBean.getSalesman());
        }else {
            holder.user_name_tv.setText("业务员：某某");
        }
        holder.xia_code_time_tv.setText("下单时间："+rowsBean.getAddedTime());
        holder.fa_huo_code_time_tv.setText("发货时间："+rowsBean.getSendOutTime());

        List<DshipmentsListBean.DataBean.RowsBean.OrderItemsBean> orderItems = rowsBean.getOrderItems();
        if (orderItems != null && orderItems.size() != 0) {
            holder.zi_rv.setVisibility(ViewGroup.VISIBLE);
            holder.wsj.setVisibility(ViewGroup.GONE);
            ZiDshipmentsListAdapter ziDshipmentsListAdapter = new ZiDshipmentsListAdapter(orderItems);
            holder.zi_rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.zi_rv.setAdapter(ziDshipmentsListAdapter);
        }else {
            holder.zi_rv.setVisibility(ViewGroup.GONE);
            holder.wsj.setVisibility(ViewGroup.VISIBLE);
        }


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
        private final TextView ckfl_tv;
        private final TextView status_tv;
        private final TextView fen_compay_name_tv;
        private final TextView user_name_tv;
        private final TextView xia_code_time_tv;
        private final TextView fa_huo_code_time_tv;
        private final TextView wsj;
        private final RecyclerView zi_rv;

        public Holder(View itemView) {
            super(itemView);
            code_tv = itemView.findViewById(R.id.code_tv);
            ckfl_tv = itemView.findViewById(R.id.ckfl_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            fen_compay_name_tv = itemView.findViewById(R.id.fen_compay_name_tv);
            user_name_tv = itemView.findViewById(R.id.user_name_tv);
            xia_code_time_tv = itemView.findViewById(R.id.xia_code_time_tv);
            fa_huo_code_time_tv = itemView.findViewById(R.id.fa_huo_code_time_tv);
            wsj = itemView.findViewById(R.id.wsj);
            zi_rv = itemView.findViewById(R.id.zi_rv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, DshipmentsListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
