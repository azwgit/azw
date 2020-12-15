package com.example.bq.edmp.work.finished.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.finished.bean.MachineListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class DmachineListAdapter extends RecyclerView.Adapter<DmachineListAdapter.Holder> {

    private List<MachineListBean.DataBean.RowsBean> list;

    public DmachineListAdapter(List<MachineListBean.DataBean.RowsBean> list) {
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
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.base_machine_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final MachineListBean.DataBean.RowsBean rowsBean = list.get(position);

        holder.jiagongliang_tv.setVisibility(ViewGroup.GONE);
        holder.shijitime_tv.setVisibility(ViewGroup.GONE);

        //1 备货加工 2按单加工
        String types = rowsBean.getTypes();
        if (types.equals("1")) {
            holder.jg_tv.setText("备货加工");
        } else if (types.equals("2")) {
            holder.jg_tv.setText("按单加工");
        }
        holder.status_tv.setText("待确认");
        holder.code_tv.setText(rowsBean.getCode());
        holder.pz_tv.setText(rowsBean.getVarietyName());
        holder.danwei_tv.setText(rowsBean.getVarietyPackagingName());
        holder.jihua_tv.setText("计划量:   " + rowsBean.getPlanQty() + " 公斤");
        holder.jihuatime_tv.setText("计划完成时间" + rowsBean.getPlanFinishTime());
        holder.fen_company_tv.setText(rowsBean.getOrgName());


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
        private final TextView jg_tv;
        private final TextView status_tv;
        private final TextView pz_tv;
        private final TextView danwei_tv;
        private final TextView jihua_tv;
        private final TextView jihuatime_tv;
        private final TextView fen_company_tv;
        private final TextView jiagongliang_tv;
        private final TextView shijitime_tv;

        public Holder(View itemView) {
            super(itemView);
            code_tv = itemView.findViewById(R.id.code_tv);
            jg_tv = itemView.findViewById(R.id.jg_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            danwei_tv = itemView.findViewById(R.id.danwei_tv);
            jihua_tv = itemView.findViewById(R.id.jihua_tv);
            jihuatime_tv = itemView.findViewById(R.id.jihuatime_tv);
            fen_company_tv = itemView.findViewById(R.id.fen_company_tv);
            jiagongliang_tv = itemView.findViewById(R.id.jiagongliang_tv);
            shijitime_tv = itemView.findViewById(R.id.shijitime_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, MachineListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
