package com.example.bq.edmp.work.allocation.adapter;

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

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class AllocationApplyListAdapter extends RecyclerView.Adapter<AllocationApplyListAdapter.Holder> {

    private List<BaseAllocationBeam.DataBean.RowsBean> list;


    public AllocationApplyListAdapter(List<BaseAllocationBeam.DataBean.RowsBean> list) {
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
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.allocation_apply_padater, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        final BaseAllocationBeam.DataBean.RowsBean rowsBean = list.get(position);

        holder.code_tv.setText(rowsBean.getCode());
        holder.up_name_tv.setText(rowsBean.getOutOrgWarehouseName());
        holder.go_name_tv.setText(rowsBean.getInOrgWarehouseName());
        holder.user_tv.setText(rowsBean.getAddedOperator());
        holder.tiem_tv.setText(rowsBean.getAddedTime());

        //状态：1待提交 2待审批 3审批通过 4出库中 5在途 6已完成 -1取消 -2审批拒绝
        String status = rowsBean.getStatus();
        if (status != null && !status.equals("")) {
            if (status.equals("1")) {
                holder.status_tv.setText("待提交");
            }else if (status.equals("2")){
                holder.status_tv.setText("待审批");
            }else if (status.equals("3")){
                holder.status_tv.setText("审批通过");
            } else if (status.equals("4")) {
                holder.status_tv.setText("出库中");
            } else if (status.equals("5")) {
                holder.status_tv.setText("在途");
            } else if (status.equals("-1")) {
                holder.status_tv.setText("取消");
            } else if (status.equals("-2")) {
                holder.status_tv.setText("审批拒绝");
            }else {
                holder.status_tv.setText("暂无");
            }
        } else {
            holder.status_tv.setText("暂无状态");
        }

        //调拨类型：1原粮调拨 2成品调拨 3转商调拨
        String types = rowsBean.getTypes();
        if (types != null) {
            if (types.equals("1")) {
                holder.allocation_fen_tv.setText("原粮调拨");
            } else if (types.equals("2")) {
                holder.allocation_fen_tv.setText("成品调拨");
            } else if (types.equals("3")) {
                holder.allocation_fen_tv.setText("转商调拨");
            } else {
                holder.allocation_fen_tv.setText("暂无");
            }
        } else {
            holder.allocation_fen_tv.setText("暂无");
        }

        List<BaseAllocationBeam.DataBean.RowsBean.StockAllotItemsBean> stockAllotItems = rowsBean.getStockAllotItems();
        if (stockAllotItems != null && stockAllotItems.size() != 0) {
            holder.wsj.setVisibility(ViewGroup.GONE);
            holder.rv.setVisibility(ViewGroup.VISIBLE);

            BaseAllocationNeiAdapter baseAllocationNeiAdapter = new BaseAllocationNeiAdapter(stockAllotItems);
            holder.rv.setLayoutManager(new LinearLayoutManager(ProApplication.getmContext()));
            holder.rv.setAdapter(baseAllocationNeiAdapter);

        } else {
            holder.wsj.setVisibility(ViewGroup.VISIBLE);
            holder.rv.setVisibility(ViewGroup.GONE);
        }


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position, rowsBean);
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

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView code_tv;
        private final TextView status_tv;
        private final TextView up_name_tv;
        private final TextView go_name_tv;
        private final TextView wsj;
        private final TextView allocation_fen_tv;
        private final TextView tiem_tv;
        private final TextView user_tv;
        private final RecyclerView rv;
        private final ImageView img;
        private final LinearLayout ll;


        public Holder(View itemView) {
            super(itemView);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            up_name_tv = itemView.findViewById(R.id.up_name_tv);
            go_name_tv = itemView.findViewById(R.id.go_name_tv);
            user_tv = itemView.findViewById(R.id.user_tv);
            tiem_tv = itemView.findViewById(R.id.tiem_tv);
            wsj = itemView.findViewById(R.id.wsj);
            allocation_fen_tv = itemView.findViewById(R.id.allocation_fen_tv);
            rv = itemView.findViewById(R.id.rv);
            img = itemView.findViewById(R.id.img);
            ll = itemView.findViewById(R.id.ll);

        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, BaseAllocationBeam.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
