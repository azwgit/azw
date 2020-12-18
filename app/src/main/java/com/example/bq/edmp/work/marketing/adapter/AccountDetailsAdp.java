package com.example.bq.edmp.work.marketing.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.word.bean.SubmitListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class AccountDetailsAdp extends RecyclerView.Adapter<AccountDetailsAdp.Holder> {

    private List<SubmitListBean.RowsBean> list;

    public AccountDetailsAdp(List<SubmitListBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<SubmitListBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public AccountDetailsAdp.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_money_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountDetailsAdp.Holder holder, final int position) {
        final SubmitListBean.RowsBean rowsBean = list.get(position);
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

        private final TextView tv_name;
        private final LinearLayout ly_view;

        public Holder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            ly_view = itemView.findViewById(R.id.ly_view);
        }
    }

    protected AccountDetailsAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, SubmitListBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(AccountDetailsAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
