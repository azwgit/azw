package com.example.bq.edmp.work.tracking.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.tracking.bean.TrackingListBean;

import java.util.ArrayList;
import java.util.List;

public class TrackingListAdapter extends RecyclerView.Adapter<TrackingListAdapter.ViewHolder> {
    private List<TrackingListBean.DataBean.RowsBean> list;

    public TrackingListAdapter(ArrayList<TrackingListBean.DataBean.RowsBean> list) {
        this.list = list;
    }


    public void addMoreData(List<TrackingListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.tracking_list_padater, null));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TrackingListBean.DataBean.RowsBean rowsBean = list.get(position);

        //1待提交 2审批中 3审批通过 4审批拒绝 5已完成 -1已删除
        final String status = rowsBean.getStatus();
        if (status != null) {
            if (status.equals("1")) {
                holder.status_tv.setText("待提交");
            } else if (status.equals("2")) {
                holder.status_tv.setText("审批中");
            } else if (status.equals("3")) {
                holder.status_tv.setText("审批通过");
            } else if (status.equals("4")) {
                holder.status_tv.setText("审批拒绝");
            } else if (status.equals("5")) {
                holder.status_tv.setText("已完成");
            } else if (status.equals("-1")) {
                holder.status_tv.setText("已删除");
            } else {
                holder.status_tv.setText("暂无");
            }
        } else {
            holder.status_tv.setText("暂无");
        }
        holder.username_tv.setText(rowsBean.getDeptName() + "-" + rowsBean.getResponsiblePeople());
        holder.tracking_name_tv.setText(rowsBean.getName());
        holder.price_tv.setText("¥" + FromtUtil.getFromt(rowsBean.getAdvanceLoan()));
        holder.tiem_tv.setText("活动时间 " + rowsBean.getStartTime() + " 至 " + rowsBean.getEndTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status != null) {
                    if (!status.equals("1") && !status.equals("5") && !status.equals("-1")) {
                        mItemClickListener.onItemClick(position, rowsBean);
                    } else {
                        ToastUtil.setToast("暂时无详情");
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView username_tv;
        private final TextView status_tv;
        private final TextView tracking_name_tv;
        private final TextView price_tv;
        private final TextView tiem_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            username_tv = itemView.findViewById(R.id.username_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            tracking_name_tv = itemView.findViewById(R.id.tracking_name_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            tiem_tv = itemView.findViewById(R.id.tiem_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, TrackingListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
