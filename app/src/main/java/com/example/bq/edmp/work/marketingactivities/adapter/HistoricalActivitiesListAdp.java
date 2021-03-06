package com.example.bq.edmp.work.marketingactivities.adapter;

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
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;
import com.example.bq.edmp.work.marketingactivities.bean.HistoricalListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */

public class HistoricalActivitiesListAdp extends RecyclerView.Adapter<HistoricalActivitiesListAdp.Holder> {

    private List<HistoricalListBean.DataBean.RowsBean> list;

    public HistoricalActivitiesListAdp(List<HistoricalListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<HistoricalListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public HistoricalActivitiesListAdp.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.item_historical_activity_list, null));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricalActivitiesListAdp.Holder holder, final int position) {
        final HistoricalListBean.DataBean.RowsBean rowsBean = list.get(position);
        if (mItemClickListener != null) {
            holder.tv_name.setText(rowsBean.getDeptName()+"-"+rowsBean.getResponsiblePeople());
            holder.tv_title.setText(rowsBean.getName());
            holder.tv_money.setText("￥" + MoneyUtils.formatMoney(rowsBean.getAdvanceLoan()));
            holder.tv_time.setText("活动时间 "+rowsBean.getStartTime()+" 至 "+rowsBean.getEndTime());
            holder.tv_end__time.setText("完成时间 "+rowsBean.getFinishedTime());
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

        private final TextView tv_end__time;//完成时间
        private final LinearLayout ly_view;
        private final TextView tv_name;//负责人
        private final TextView tv_status;//活动状态
        private final TextView tv_title;//活动标题
        private final TextView tv_money;//金额
        private final TextView tv_time;//活动时间

        public Holder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            ly_view = itemView.findViewById(R.id.ly_view);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_end__time = itemView.findViewById(R.id.tv_end__time);

        }
    }

    protected HistoricalActivitiesListAdp.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, HistoricalListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(HistoricalActivitiesListAdp.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
