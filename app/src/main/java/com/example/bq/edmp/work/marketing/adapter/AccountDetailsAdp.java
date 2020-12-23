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
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountDetails;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class AccountDetailsAdp extends RecyclerView.Adapter<AccountDetailsAdp.Holder> {

    private List<CustomerAccountDetails.DataBean.AccountRecordsBean> list;

    public AccountDetailsAdp(List<CustomerAccountDetails.DataBean.AccountRecordsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<CustomerAccountDetails.DataBean.AccountRecordsBean> data) {
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
        final CustomerAccountDetails.DataBean.AccountRecordsBean rowsBean = list.get(position);
        if (mItemClickListener != null) {
            String title = "";
            String money = "";
            switch (rowsBean.getStatus()) {
                case 1:
                    title = "提货";
                    holder.tv_money.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.color33));
                    money = "-" + MoneyUtils.formatMoney(rowsBean.getBalance());
                    break;
                case 2:
                    title = "退货";
                    holder.tv_money.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.colorf9));
                    money = "+" + MoneyUtils.formatMoney(rowsBean.getBalance());
                    break;
                case 3:
                    title = "定转余";
                    holder.tv_money.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.colorf9));
                    money = "+" + MoneyUtils.formatMoney(rowsBean.getBalance());
                    break;
                case 4:
                    title = "打款";
                    holder.tv_money.setTextColor(ProApplication.getmContext().getResources().getColor(R.color.colorf9));
                    money = "+" + MoneyUtils.formatMoney(rowsBean.getBalance());
                    break;
            }
            if (rowsBean.getRemark() != null) {
                title += " (" + rowsBean.getRemark() + ")";
            }
            holder.tv_title.setText(title);
            holder.tv_money.setText(money);
            holder.tv_time.setText(rowsBean.getAddedTime());
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

        private final TextView tv_title;//标题
        private final TextView tv_money;//金额
        private final TextView tv_time;//时间
        private final LinearLayout ly_view;

        public Holder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_time = itemView.findViewById(R.id.tv_time);
            ly_view = itemView.findViewById(R.id.ly_view);
        }
    }

    protected AccountDetailsAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, CustomerAccountDetails.DataBean.AccountRecordsBean rowsBean);
    }

    public void setOnItemClickListener(AccountDetailsAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
