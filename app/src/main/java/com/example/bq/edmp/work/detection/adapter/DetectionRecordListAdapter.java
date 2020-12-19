package com.example.bq.edmp.work.detection.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.work.detection.bean.DetectionRecorListBean;

import java.util.List;

/**
 * Created by bq on 2020/11/6.
 */
public class DetectionRecordListAdapter extends RecyclerView.Adapter<DetectionRecordListAdapter.Holder> {

    private List<DetectionRecorListBean.DataBean.RowsBean> list;


    public DetectionRecordListAdapter(List<DetectionRecorListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public void addMoreData(List<DetectionRecorListBean.DataBean.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(ProApplication.getmContext()).inflate(R.layout.detection_list_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final DetectionRecorListBean.DataBean.RowsBean rowsBean = list.get(position);

        //1合格 -1不合格 0—
        String results = rowsBean.getResults();
        if (results!=null) {
            if (results.equals("1")) {
                holder.hg_tv.setText("合格");
                holder.hg_tv.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.detection_yes_shape));
            } else if (results.equals("-1")) {
                holder.hg_tv.setText("不合格");
                holder.hg_tv.setBackground(ProApplication.getmContext().getResources().getDrawable(R.drawable.detection_no_shape));
            } else {
                holder.hg_tv.setText("暂无");
            }
        }else {
            holder.hg_tv.setText("暂无");
        }

        holder.status_tv.setText(rowsBean.getTestPlanName());
        holder.code_tv.setText(rowsBean.getCode());
        holder.company_name_tv.setText(rowsBean.getOrgName());
        holder.time_tv.setText(rowsBean.getAddedTime());
        holder.pz_tv.setText(rowsBean.getFarmLandCode()+rowsBean.getWareshouseName()+" - "+rowsBean.getVarietyName());

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
        private final TextView status_tv;
        private final TextView company_name_tv;
        private final TextView pz_tv;
        private final TextView hg_tv;
        private final TextView time_tv;

        public Holder(View itemView) {
            super(itemView);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);
            code_tv = itemView.findViewById(R.id.code_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            pz_tv = itemView.findViewById(R.id.pz_tv);
            hg_tv = itemView.findViewById(R.id.hg_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos, DetectionRecorListBean.DataBean.RowsBean rowsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
