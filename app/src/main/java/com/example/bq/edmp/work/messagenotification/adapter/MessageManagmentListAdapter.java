package com.example.bq.edmp.work.messagenotification.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.ItemPackingListAdapter;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.messagenotification.bean.MessageManagementListBean;

import java.util.ArrayList;
import java.util.List;

public class MessageManagmentListAdapter extends RecyclerView.Adapter<MessageManagmentListAdapter.ViewHolder> {
    private List<MessageManagementListBean.DataBean.RowsBean> list;

    public MessageManagmentListAdapter(ArrayList<MessageManagementListBean.DataBean.RowsBean> list) {
        this.list = list;
    }


    public void addMoreData(List<MessageManagementListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_message_layout, null));
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MessageManagementListBean.DataBean.RowsBean rowsBean = list.get(position);
        if (position == 0) {
            holder.ly_one.setVisibility(View.GONE);
        } else {
            holder.ly_one.setVisibility(View.VISIBLE);
        }
        holder.tv_title.setText(rowsBean.getTitle());
        holder.tv_time.setText(rowsBean.getAddedTime());
        holder.tv_content.setText(rowsBean.getRelationInfo());
        switch (rowsBean.getTypes()) {
            case 1:
                holder.img_icon.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.icon_message));
                break;
            case 2:
                holder.img_icon.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.icon_approval));
                break;
            default:
                holder.img_icon.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.icon_message));
                break;
        }
        if (rowsBean.getStatus() == 1) {
            holder.tv_number.setVisibility(View.VISIBLE);
        } else {
            holder.tv_number.setVisibility(View.GONE);
        }
        holder.ly_view.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img_icon;//推送图标
        private final TextView tv_title;//标题
        private final TextView tv_time;//日期
        private final TextView tv_content;//消息内容
        private final TextView tv_number;//已读未读
        private final RelativeLayout ly_view;//分子公司名称
        private final LinearLayout ly_one;

        public ViewHolder(View itemView) {
            super(itemView);
            img_icon = itemView.findViewById(R.id.img_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_number = itemView.findViewById(R.id.tv_number);
            ly_view = itemView.findViewById(R.id.ly_view);
            ly_one = itemView.findViewById(R.id.ly_one);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, MessageManagementListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
