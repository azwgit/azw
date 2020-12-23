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
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.work.finished.bean.MachineListBean;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class AccountListAdp extends RecyclerView.Adapter<AccountListAdp.Holder> {

    private List<CustomerAccountListBean.DataBean.RowsBean> list;

    public AccountListAdp(List<CustomerAccountListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<CustomerAccountListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public AccountListAdp.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_account_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountListAdp.Holder holder, final int position) {
        final CustomerAccountListBean.DataBean.RowsBean rowsBean = list.get(position);
        if (mItemClickListener != null) {
            holder.tv_name.setText(rowsBean.getName());
            holder.tv_money.setText("￥" + MoneyUtils.formatMoney(rowsBean.getDeposit()));
            holder.tv_balance.setText("￥" + MoneyUtils.formatMoney(rowsBean.getBalance()));
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

        private final TextView tv_name;//公司名称
        private final TextView tv_info;//款项
        private final TextView tv_balance;//余额
        private final LinearLayout ly_view;
        private final TextView tv_money;//金额

        public Holder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            ly_view = itemView.findViewById(R.id.ly_view);
            tv_info= itemView.findViewById(R.id.tv_info);
            tv_balance= itemView.findViewById(R.id.tv_balance);
            tv_money= itemView.findViewById(R.id.tv_money);
        }
    }

    protected AccountListAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, CustomerAccountListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(AccountListAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
