package com.example.bq.edmp.work.reseller.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.order.bean.OrderTJBean;

import java.util.ArrayList;
import java.util.List;

public class ResellerApplyAdapter extends RecyclerView.Adapter<ResellerApplyAdapter.ViewHolder> {

    private List<BaseAllocationBeam.DataBean.RowsBean> list;

    public ResellerApplyAdapter(ArrayList<BaseAllocationBeam.DataBean.RowsBean> list) {
        this.list = list;
    }


    public void addMoreData(List<BaseAllocationBeam.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.reseller_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final BaseAllocationBeam.DataBean.RowsBean rowsBean = list.get(position);

        //默认全部 10 ，2审批中 3待出库 4待入库 -2 审批拒绝
        String status = rowsBean.getStatus();
        if (status != null) {
            if (status.equals("1")) {
                holder.user_tv.setText(rowsBean.getAddedOperator());
                holder.status_tv.setText("待提交");
            } else if (status.equals("2")) {
                holder.user_tv.setText(rowsBean.getAddedOperator());
                holder.status_tv.setText("审批中");
            } else if (status.equals("3")) {
                holder.img.setVisibility(View.GONE);
                holder.user_tv.setText("审批");
                holder.status_tv.setText("待出库");
            } else if (status.equals("4")) {
                holder.img.setVisibility(View.GONE);
                holder.user_tv.setText("出库");
                holder.status_tv.setText("待入库");
            } else if (status.equals("-2")) {
                holder.img.setVisibility(View.GONE);
                holder.user_tv.setText("审批");
                holder.status_tv.setText("已拒绝");
            } else {
                holder.img.setVisibility(View.GONE);
                holder.user_tv.setText("完成");
                holder.status_tv.setText("已完成");
            }
        }


        String types = rowsBean.getTypes();
        if (types != null) {
            if (types.equals("3")) {
                holder.allocation_fen_tv.setText("转商调拨");
            }
        } else {
            holder.allocation_fen_tv.setText("暂无");
        }
        holder.danhao_tv.setText(rowsBean.getCode());
        holder.chu_gongsi_tv.setText(rowsBean.getOutOrgWarehouseName());
        holder.ru_gongsi_tv.setText(rowsBean.getInOrgWarehouseName());
        holder.tiem_tv.setText(rowsBean.getAddedTime());

        List<BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean> stockAllotItems = rowsBean.getStockAllotItems();
        if (stockAllotItems != null && stockAllotItems.size() != 0) {
            holder.wsj.setVisibility(View.GONE);
            holder.rv.setVisibility(View.VISIBLE);

            NeiResellerApplyAdapter neiResellerApplyAdapter = new NeiResellerApplyAdapter(stockAllotItems);
            holder.rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.rv.setAdapter(neiResellerApplyAdapter);
            neiResellerApplyAdapter.notifyDataSetChanged();
        } else {
            holder.wsj.setVisibility(View.VISIBLE);
            holder.rv.setVisibility(View.GONE);
        }

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemLeftClckListener.onItemLeftClck(rowsBean, position);
            }
        });
        holder.rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return holder.ll.onTouchEvent(event);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView danhao_tv;
        private final TextView status_tv;
        private final TextView user_tv;
        private final TextView tiem_tv;
        private final TextView chu_gongsi_tv;
        private final TextView ru_gongsi_tv;
        private final TextView allocation_fen_tv;
        private final TextView wsj;
        private final ImageView img;
        private final RecyclerView rv;
        private final LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            chu_gongsi_tv = itemView.findViewById(R.id.chu_gongsi_tv);
            ru_gongsi_tv = itemView.findViewById(R.id.ru_gongsi_tv);
            danhao_tv = itemView.findViewById(R.id.danhao_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            user_tv = itemView.findViewById(R.id.user_tv);
            tiem_tv = itemView.findViewById(R.id.tiem_tv);
            allocation_fen_tv = itemView.findViewById(R.id.allocation_fen_tv);
            wsj = itemView.findViewById(R.id.wsj);
            img = itemView.findViewById(R.id.img);
            rv = itemView.findViewById(R.id.rv);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    //适配器接口回调
    private OnItemLeftClckListener onItemLeftClckListener;

    public interface OnItemLeftClckListener {
        void onItemLeftClck(BaseAllocationBeam.DataBean.RowsBean rowsBean, int mPosition);

    }

    public void setOnItemLeftClckListener(OnItemLeftClckListener onItemLeftClckListener) {
        this.onItemLeftClckListener = onItemLeftClckListener;
    }

}
